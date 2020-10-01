#include "btree.h"

/**
 * Função que lê do standard input o id e o pais de um estudante e 
 * guarda na BTree e no ficheiro.
 */
void insert_student(BTree *btree, FILE *fd_nodes, BTreeConf *config, HashCountry *hc)
{
  char id[GLOBAL_ID],
      country[COUNTRY];
  scanf("%s %s", id, country);

  // Criar um novo estudante
  Student *n_st = new_student_args(false, false, country, id);

  // Inserir estudante
  btree_insert(btree, n_st, fd_nodes, config, hc);
  destroy_student(n_st);
}

/**
 * Função que dado um identificador, vai remover o respetivo estudante
 * da BTree
 */
void remove_student(BTree *btree, FILE *fd_nodes, BTreeConf *config, HashCountry *hc)
{
  // Input id
  char id[GLOBAL_ID];
  scanf("%s", id);

  // Remover estudante
  btree_delete(btree, id, fd_nodes, config, hc);
}

/**
 * Funcão que vai assinalar se um estudante terminou o curso
 */
void student_finish(BTree *btree, FILE *fd_nodes, BTreeConf *config, HashCountry *hc)
{
  // Input
  char id[GLOBAL_ID];
  scanf("%s", id);

  // Usar um nó auxiliar para fazer a pesquisa
  BNode *aux = new_bnode();
  *aux = *btree->raiz;

  // Pesquisar pelo elemento
  Node_Index *no_in = btree_search(aux, id, fd_nodes);

  // Se encontrou o elemento
  if (no_in != NULL)
  {
    Student *st = &no_in->no->elementos[no_in->indice];

    if (get_active_student(st))
    {
      set_completed(st, true);

      // Reescrever o nó
      disk_write_cur_node(fd_nodes, no_in->no);

      // Recuperar a raiz
      if (config->root_index == no_in->no->indice_ficheiro)
        *btree->raiz = *no_in->no;

      // Buscar info do pais na HashTable para atualizar. (Se existir)
      HashNode *hn = hash_search(hc, get_country(st));

      if (hn != NULL)
        hnode_student_finish(hn);
    }
    else if (get_completed(st))
      printf("+ estudante %s terminou\n", id);

    else if (get_dropped_out(st))
      printf("+ estudante %s abandonou\n", id);

    free(aux);
    destroy_node_index(no_in);
  }
  else
  {
    printf("+ estudante %s inexistente\n", id);
    free(aux);
  }
}

/**
 * Função que vai assinalar se um estudante desistiu do curso
 */
void student_left(BTree *btree, FILE *fd_nodes, BTreeConf *config, HashCountry *hc)
{
  // Input
  char id[GLOBAL_ID];
  scanf("%s", id);

  // Usar um nó auxiliar para fazer a pesquisa
  BNode *aux = new_bnode();
  *aux = *btree->raiz;

  // Pesquisar pelo elemento através do hash code
  Node_Index *no_in = btree_search(aux, id, fd_nodes);

  // Se encontrou o elemento
  if (no_in != NULL)
  {
    Student *st = &no_in->no->elementos[no_in->indice];

    if (get_active_student(st))
    {
      set_dropped_out(st, true);

      // Reescrever o nó
      disk_write_cur_node(fd_nodes, no_in->no);

      // Recuperar a raiz
      if (config->root_index == no_in->no->indice_ficheiro)
        *btree->raiz = *no_in->no;

      // Buscar info do pais na HashTable para atualizar. (Se existir)
      HashNode *hn = hash_search(hc, get_country(st));

      if (hn != NULL)
        hnode_student_left(hn);
    }
    else if (get_completed(st))
      printf("+ estudante %s terminou\n", id);

    else if (get_dropped_out(st))
      printf("+ estudante %s abandonou\n", id);

    free(aux);
    destroy_node_index(no_in);
  }
  else
  {
    free(aux);
    printf("+ estudante %s inexistente\n", id);
  }
}

/**
 * Função que vai fazer um estudo da situação escolar num deter-
 * minado país.
 */
void country_data(BTree *btree, FILE *fd_nodes, HashCountry *hc)
{
  // Input
  char country[COUNTRY];
  scanf("%s", country);

  // Pesquisar pela informação na HashTable
  HashNode *hn = hash_search(hc, country);

  // Se não está na hashtable, pesquisar na BTree
  if (hn == NULL)
    printf("+ sem dados sobre %s\n", country);
  else
  {
    int t = hn->corr + hn->aband + hn->diplo;
    if (t == 0)
      printf("+ sem dados sobre %s\n", country);

    else
      printf("+ %s - correntes: %d, diplomados: %d, abandonaram: %d, total: %d\n",
             country, hn->corr, hn->diplo, hn->aband, t);
  }
}

int main(void)
{
  ///////////////////////////////////////////////
  //  Iniciar BTree, configurações e ficheiros //
  ///////////////////////////////////////////////
  // Open files
  FILE *fd_index = open_file(INDEX_FILE);
  FILE *fd_nodes = open_file(BNODES_FILE);

  // Buscar/Criar as configurações iniciais da BTree
  BTreeConf *config = disk_read_conf(fd_index);
  HashCountry *hash_country = disk_read_hash(fd_index);
  BTree *btree = new_btree(fd_nodes, config);
  //////////////////////////////////////////////////////

  // Interface
  bool end_prog = false;
  while (!end_prog)
  {
    char option;
    scanf(" %c", &option);

    switch (option)
    {
    case 'I':
      insert_student(btree, fd_nodes, config, hash_country);
      break;

    case 'R':
      remove_student(btree, fd_nodes, config, hash_country);
      break;

    case 'T':
      student_finish(btree, fd_nodes, config, hash_country);
      break;

    case 'A':
      student_left(btree, fd_nodes, config, hash_country);
      break;

    case 'P':
      country_data(btree, fd_nodes, hash_country);
      break;

    case 'X':
      end_prog = true;
      break;
    }
  }

  /////////////////////////////////////////////////////////////
  // Actualizar ficheiro, libertar memória e fechar ficheiros /
  /////////////////////////////////////////////////////////////
  // Atualizar as configurações nos ficheiros
  disk_write_conf(fd_index, config);
  disk_write_hash(fd_index, hash_country);

  // Libertar memória
  destroy_btree(btree);
  destroy_btree_conf(config);
  destroy_hash_country(hash_country);

  // Close files
  close_file(fd_index);
  close_file(fd_nodes);

  return 0;
}
