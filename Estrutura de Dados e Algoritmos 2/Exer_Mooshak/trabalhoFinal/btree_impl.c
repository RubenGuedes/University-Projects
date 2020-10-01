#include "btree.h"

BTreeConf *new_btree_conf(FILE *fd_conf)
{
  BTreeConf *bconf = calloc(2, BTREECONF_SIZE);

  if (fseek(fd_conf, 0, SEEK_SET) == 0)
    fread(bconf, BTREECONF_SIZE, 1, fd_conf);

  // Se não está no ficheiro -> Guarda a nova informação
  if (bconf->root_index == 0)
  {
    bconf->last_index = 0;
    fwrite(bconf, BTREECONF_SIZE, 1, fd_conf);
  }

  return bconf;
}

void destroy_btree_conf(BTreeConf *tree_conf)
{
  free(tree_conf);
}

/**
 * Node Index
 */
Node_Index *new_node_index(BNode *no, int i)
{
  Node_Index *n_ind = malloc(NODE_INDEX_SIZE);

  n_ind->no = no;
  n_ind->indice = i;

  return n_ind;
}

void destroy_node_index(Node_Index *st)
{
  free(st);
}

/**
 * BNode
 */
BNode *new_bnode()
{
  BNode *node = malloc(BNODE_SIZE);

  node->folha = false;
  node->ocupacao = 0;
  node->indice_ficheiro = 0;

  return node;
}

void destroy_bnode(BNode *node, FILE *fd_nodes)
{
  node->ocupacao = 0;

  disk_write_cur_node(fd_nodes, node);
  free(node);
}

/**
 * BTree
 */
BTree *new_btree(FILE *fd_nodes, BTreeConf *b_conf)
{
  BTree *tree = malloc(BTREE_SIZE);
  tree->raiz = new_bnode();

  // Se não tem nada nós no ficheiro -> criar o nó e guardá-lo
  if (b_conf->last_index == 0)
  {
    tree->raiz->folha = true;

    // Atualizar configurações
    b_conf->root_index = 0;
    b_conf->last_index += 1;

    disk_write_cur_node(fd_nodes, tree->raiz);
  }
  // Se tiver -> buscar o conteúdo
  else
  {
    disk_read_cur_node(fd_nodes, b_conf->root_index, tree->raiz);
  }

  return tree;
}

void destroy_btree(BTree *btree)
{
  free(btree->raiz);
  free(btree);
}

/**
 * Funções para pesquisar na BTree
 */
Node_Index *btree_search(BNode *root, char id[GLOBAL_ID], FILE *fd_nodes)
{
  int i = 0;

  int cmp = compare(id, get_id(&root->elementos[i]));

  while (i < root->ocupacao && cmp == -1)
    {
      i++;
      cmp = compare(id, get_id(&root->elementos[i]));
    }

  if (i < root->ocupacao && cmp == 0)
    return new_node_index(root, i);

  else if (root->folha)
    return NULL;

  else
  {
    int filho = root->filhos[i];
    disk_read_cur_node(fd_nodes, filho, root);
    return btree_search(root, id, fd_nodes);
  }
}

void btree_insert(BTree *tree, Student *st, FILE *fd_nodes, BTreeConf *configs, HashCountry *hc)
{
  BNode *node = tree->raiz;

  if (node->ocupacao == 2 * T - 1)
  {
    BNode *s = new_bnode();
    tree->raiz = s;
    s->folha = false;
    s->filhos[0] = node->indice_ficheiro;
    s->indice_ficheiro = configs->last_index;

    // Atualizar configurações
    configs->root_index = s->indice_ficheiro; // Indice nova root
    configs->last_index++;                    // Ultimo indice atribuido

    btree_slipt_child(s, node, 0, fd_nodes, configs);
    btree_insert_nonfull(s, st, fd_nodes, configs, hc);
    free(node);
  }
  else
    btree_insert_nonfull(node, st, fd_nodes, configs, hc);
}

void btree_slipt_child(BNode *root, BNode *y, int index, FILE *fd_nodes, BTreeConf *configs)
{
  BNode *z = new_bnode();

  z->folha = y->folha;
  z->ocupacao = T - 1;
  z->indice_ficheiro = configs->last_index;
  configs->last_index++;

  for (int j = 0; j < T - 1; j++)
  {
    z->elementos[j] = y->elementos[j + T];
    memset(&y->elementos[j + T], 0, STUDENT_SIZE);
  }

  if (!y->folha)
    for (int j = 0; j < T; j++)
    {
      z->filhos[j] = y->filhos[j + T];
      y->filhos[j + T] = 0;
    }

  y->ocupacao = T - 1;

  for (int j = root->ocupacao; j >= index + 1; j--)
    root->filhos[j + 1] = root->filhos[j];
  root->filhos[index + 1] = z->indice_ficheiro;

  for (int j = root->ocupacao - 1; j >= index; j--)
    root->elementos[j + 1] = root->elementos[j];
  root->elementos[index] = y->elementos[T - 1];

  memset(&y->elementos[T - 1], 0, STUDENT_SIZE);

  root->ocupacao++;

  disk_write_cur_node(fd_nodes, y);
  disk_write_cur_node(fd_nodes, z);
  disk_write_cur_node(fd_nodes, root);
  free(z);
}

void btree_insert_nonfull(BNode *node, Student *st, FILE *fd_nodes, BTreeConf *configs, HashCountry *hc)
{
  int i = node->ocupacao - 1;

  if (node->folha)
  {
    // Verificar se existe um id igual
    int j = 0;
    for (; j < node->ocupacao; j++)
      if (compare(get_id(st), get_id(&node->elementos[j])) != -1)
        break;

    int cmp = compare(get_id(st), get_id(&node->elementos[j]));

    if (cmp == 0)
      printf("+ estudante %s existe\n", get_id(st));

    else
    {
      while (i >= j)
      {
        node->elementos[i + 1] = node->elementos[i];
        i--;
      }

      node->elementos[i + 1] = *st;
      node->ocupacao++;

      disk_write_cur_node(fd_nodes, node);

      // Verificar se a info deste país está na HashTable
      HashNode *hn = hash_search(hc, get_country(st));

      if ( hn  != NULL )
        hnode_increase_active(hn);
      else
      {
        HashNode *hn = new_hash_node(get_country(st), 1, 0, 0);
        hash_insert(hc, hn);
      }
    }
  }
  else
  {
    int cmp = compare(get_id(st), get_id(&node->elementos[i]));
    while (i >= 0 && cmp >= 0)
      {
        i--;
        cmp = compare(get_id(st), get_id(&node->elementos[i]));
      }

    i++;
    cmp = compare(get_id(st), get_id(&node->elementos[i]));
    
    if (cmp == 0)
      printf("+ estudante %s existe\n", get_id(st));

    else
    {
      bool stop = false;

      BNode *child_node = new_bnode();
      disk_read_cur_node(fd_nodes, node->filhos[i], child_node);

      if (child_node->ocupacao == 2 * T - 1)
      {
        btree_slipt_child(node, child_node, i, fd_nodes, configs);
        cmp = compare(get_id(st), get_id(&node->elementos[i]));

        if (cmp == 0)
        {
          stop = true;
          printf("+ estudante %s existe\n", get_id(st));
          free(child_node);
        }

        else if (cmp == -1)
        {
          i++;
          disk_read_cur_node(fd_nodes, node->filhos[i], child_node);
        }
      }

      if (!stop)
        btree_insert_nonfull(child_node, st, fd_nodes, configs, hc);
      
      free(child_node);
    }
  }
}

/**
 * DELETE
 */
void btree_delete(BTree *btree, char id[GLOBAL_ID], FILE *fd_nodes, BTreeConf *config, HashCountry *hc)
{
  BNode *node = btree->raiz;

  btree_delete_safe(node, id, fd_nodes, hc);

  if (node->ocupacao == 0 && !node->folha)
  {
    BNode *aux = new_bnode();
    *aux = *node;

    disk_read_cur_node(fd_nodes, node->filhos[0], node);

    destroy_bnode(aux, fd_nodes);

    // Atualizar o indice da raiz (Uma vez que foi alterado)
    config->root_index = node->indice_ficheiro;
  }
}

void btree_delete_safe(BNode *bnode, char id[GLOBAL_ID], FILE *fd_nodes, HashCountry *hc)
{
  int i = 0,
      cmp = compare(id, get_id(&bnode->elementos[i]));
  
  while (i < bnode->ocupacao && cmp == -1)
    {
      i++;
      cmp = compare(id, get_id(&bnode->elementos[i]));
    }

  if (i < bnode->ocupacao && cmp == 0)
  {
    if (bnode->folha)
      btree_delete_from_leaf(bnode, i, fd_nodes, hc);

    else if (get_dropped_out(&bnode->elementos[i]))
      printf("+ estudante %s abandonou\n", id);

    else if (get_completed(&bnode->elementos[i]))
      printf("+ estudante %s terminou\n", id);

    else
      btree_delete_from_internal_node(bnode, i, id, fd_nodes, hc);
  }

  else if (bnode->folha && cmp != 0)
    printf("+ estudante %s inexistente\n", id);

  else if (!bnode->folha)
    btree_delete_from_subtree(bnode, i, id, fd_nodes, hc);
}

void btree_delete_from_leaf(BNode *node, int index, FILE *fd_nodes, HashCountry *hc)
{
  int j = index;
  Student *st = &node->elementos[j];

  if (get_active_student(st))
  {
    // Ver se existe info sobre o país na HashTable
    HashNode *hn = hash_search(hc, get_country(st));
    if (hn != NULL)
      hnode_student_remove(hn);
    
    for (; j < node->ocupacao; j++)
      node->elementos[j] = node->elementos[j + 1];

    node->ocupacao--;
    disk_write_cur_node(fd_nodes, node);
  }
  else if (get_completed(st))
    printf("+ estudante %s terminou\n", get_id(st));

  else if (get_dropped_out(st))
    printf("+ estudante %s abandonou\n", get_id(st));
}

void btree_delete_from_internal_node(BNode *node, int index, char id[GLOBAL_ID], FILE *fd_nodes, HashCountry *hc)
{
  BNode *y = new_bnode();
  disk_read_cur_node(fd_nodes, node->filhos[index], y);

  if (y->ocupacao > T - 1)
  {
    // Verificar se a info deste país está na HashTable
    Student *st = &node->elementos[index];
    HashNode *hn = hash_search(hc, get_country(st));
    if (hn != NULL)
    {
      if (get_active_student(st))
        hnode_student_remove(hn);
      else if (get_completed(st))
        hn->diplo--;
      else if (get_dropped_out(st))
        hn->aband--;
    }

    node->elementos[index] = btree_delete_max(y, fd_nodes);
    disk_write_cur_node(fd_nodes, node);
  }
  else
  {
    BNode *z = new_bnode();
    disk_read_cur_node(fd_nodes, node->filhos[index + 1], z);

    if (z->ocupacao > T - 1)
    {
      // Verificar se a info deste país está na HashTable
      Student *st = &node->elementos[index];
      HashNode *hn = hash_search(hc, get_country(st));
      if (hn != NULL)
      {
        if (get_active_student(st))
          hnode_student_remove(hn);
        else if (get_completed(st))
          hn->diplo--;
        else if (get_dropped_out(st))
          hn->aband--;
      }

      node->elementos[index] = btree_delete_min(z, fd_nodes);
      disk_write_cur_node(fd_nodes, node);

      free(z);
    }
    else
    {
      btree_merge_child(node, y, z, index, fd_nodes);
      btree_delete_safe(y, id, fd_nodes, hc);
    }
  }
  free(y);
}

void btree_delete_from_subtree(BNode *node, int index, char id[GLOBAL_ID], FILE *fd_nodes, HashCountry *hc)
{
  int m = index;
  
  BNode *y = new_bnode();
  disk_read_cur_node(fd_nodes, node->filhos[index], y);

  if (y->ocupacao == T - 1)
  {
    BNode *z = new_bnode();
    bool borrowed = false;

    if (index > 0)
    {
      disk_read_cur_node(fd_nodes, node->filhos[index - 1], z);

      if (z->ocupacao > T - 1)
      {
        btree_borrow_from_left_sibling(node, y, z, index, fd_nodes);
        borrowed = true;
      }
      else
        m = index - 1;
    }

    if (!borrowed && index < node->ocupacao)
    {
      disk_read_cur_node(fd_nodes, node->filhos[index + 1], z);
      
      if (z->ocupacao > T - 1)
      {
        btree_borrow_from_right_sibling(node, y, z, index, fd_nodes);
        borrowed = true;
      }
      else
        m = index;
    }

    if (!borrowed)
    {
      if (m == index - 1)
      {
        BNode *aux_pt = z;
        z = y;
        y = aux_pt;
      }
      btree_merge_child(node, y, z, m, fd_nodes);
    }
    else
      free(z);
  }

  btree_delete_safe(y, id, fd_nodes, hc);
  free(y);
}

void btree_merge_child(BNode *node, BNode *y, BNode *z, int index, FILE *fd_nodes)
{
  y->elementos[T - 1] = node->elementos[index];
  memset(&node->elementos[index], 0, STUDENT_SIZE);

  for (int j = 0; j < T - 1; j++)
  {
    y->elementos[T + j] = z->elementos[j];
    memset(&z->elementos[j], 0, STUDENT_SIZE);
  }

  if (!y->folha)
    for (int j = 0; j < T; j++)
      y->filhos[T + j] = z->filhos[j];

  y->ocupacao = 2 * T - 1;

  int j;
  for (j = index + 1; j < node->ocupacao; j++)
    node->elementos[j - 1] = node->elementos[j];

  for (j = index + 2; j < node->ocupacao + 1; j++)
    node->filhos[j - 1] = node->filhos[j];

  node->ocupacao--;

  destroy_bnode(z, fd_nodes);
  disk_write_cur_node(fd_nodes, y);
  disk_write_cur_node(fd_nodes, node);
}

void btree_borrow_from_left_sibling(BNode *node, BNode *y, BNode *z, int index, FILE *fd_nodes)
{
  for (int j = T - 1; j >= 0; j--)
    y->elementos[j + 1] = y->elementos[j];

  y->elementos[0] = node->elementos[index - 1];
  node->elementos[index - 1] = z->elementos[z->ocupacao - 1];
  memset(&z->elementos[z->ocupacao - 1], 0, STUDENT_SIZE);

  if (!y->folha)
  {
    for (int j = T; j >= 0; j--)
      y->filhos[j + 1] = y->filhos[j];

    y->filhos[0] = z->filhos[z->ocupacao];
    z->filhos[z->ocupacao] = 0;
  }

  y->ocupacao = T;
  z->ocupacao--;

  disk_write_cur_node(fd_nodes, z);
  disk_write_cur_node(fd_nodes, y);
  disk_write_cur_node(fd_nodes, node);
}

void btree_borrow_from_right_sibling(BNode *node, BNode *y, BNode *z, int index, FILE *fd_nodes)
{
  y->elementos[y->ocupacao] = node->elementos[index];
  node->elementos[index] = z->elementos[0];

  for (int j = 0; j < z->ocupacao; j++)
    z->elementos[j] = z->elementos[j + 1];

  if (!z->folha)
  {
    y->filhos[y->ocupacao + 1] = z->filhos[0];
    for (int j = 0; j <= z->ocupacao; j++)
      z->filhos[j] = z->filhos[j + 1];
    z->filhos[z->ocupacao] = 0;
  }

  y->ocupacao = T;
  z->ocupacao--;

  disk_write_cur_node(fd_nodes, z);
  disk_write_cur_node(fd_nodes, y);
  disk_write_cur_node(fd_nodes, node);
}

Student get_min_st(BNode *node, FILE *fd_nodes)
{
  if (node->folha)
  {
    Student st = node->elementos[0];

    for (int j = 0; j < node->ocupacao; j++)
      node->elementos[j] = node->elementos[j + 1];

    node->ocupacao--;
    disk_write_cur_node(fd_nodes, node);

    return st;
  }

  else
  {
    // Left node
    BNode *y = new_bnode();
    disk_read_cur_node(fd_nodes, node->filhos[0], y);

    if (y->ocupacao == T - 1)
    {
      bool borrowed = false;

      // Left node
      BNode *z = new_bnode();
      disk_read_cur_node(fd_nodes, node->filhos[1], z);

      if (z->ocupacao > T - 1)
      {
        btree_borrow_from_right_sibling(node, y, z, 0, fd_nodes);
        borrowed = true;
      }

      if (!borrowed)
        btree_merge_child(node, y, z, 0, fd_nodes);
      else
        free(z);
    }

    *node = *y;
    free(y);
    return get_min_st(node, fd_nodes);
  }
}
Student btree_delete_min(BNode *node, FILE *fd_nodes)
{
  BNode *aux = new_bnode();
  *aux = *node;

  // Encontrar o menor id da subárvore
  Student st = get_min_st(aux, fd_nodes);

  free(aux);
  return st;
}

Student get_max_st(BNode *node, FILE *fd_nodes)
{
  if (node->folha)
  {
    Student st = node->elementos[node->ocupacao - 1];

    memset(&node->elementos[node->ocupacao - 1], 0, STUDENT_SIZE);
    node->ocupacao--;

    disk_write_cur_node(fd_nodes, node);

    return st;
  }
  else
  {
    int index = node->ocupacao - 1;

    // Right node
    BNode *y = new_bnode();
    disk_read_cur_node(fd_nodes, node->filhos[index + 1], y);

    if (y->ocupacao == T - 1)
    {
      bool borrowed = false;

      // Left node
      BNode *z = new_bnode();
      disk_read_cur_node(fd_nodes, node->filhos[index], z);

      if (z->ocupacao > T - 1)
      {
        btree_borrow_from_left_sibling(node, y, z, index, fd_nodes);
        borrowed = true;
      }

      if (!borrowed)
      {
        BNode *aux_pt = z;
        z = y;
        y = aux_pt;
        btree_merge_child(node, y, z, index, fd_nodes);
      }
      else
        free(z);
    }

    *node = *y;
    free(y);
    return get_max_st(node, fd_nodes);
  }
}
Student btree_delete_max(BNode *node, FILE *fd_nodes)
{
  BNode *aux = new_bnode();
  *aux = *node;

  Student st = get_max_st(aux, fd_nodes);

  free(aux);
  return st;
}

/**
 * Funções para manipular ficheiros
 */
FILE *open_file(char *name)
{
  FILE *fd = fopen(name, "r+");

  if (fd == NULL)
    fd = fopen(name, "w+");

  return fd;
}

void disk_read_cur_node(FILE *fd, int ind_next, BNode *res)
{
  long fp = ftell(fd);
  int jump = ind_next - (fp / BNODE_SIZE);

  if (fseek(fd, (jump * BNODE_SIZE), SEEK_CUR) == 0)
    fread(res, BNODE_SIZE, 1, fd);
}

void disk_write_cur_node(FILE *fd, BNode *node)
{
  long fp = ftell(fd);
  int jump = node->indice_ficheiro - (fp / BNODE_SIZE);

  if (fseek(fd, (jump * BNODE_SIZE), SEEK_CUR) == 0)
    fwrite(node, BNODE_SIZE, 1, fd);
}

void disk_write_conf(FILE *fd_conf, BTreeConf *btree_conf)
{
  if (fseek(fd_conf, 0, SEEK_SET) == 0)
    fwrite(btree_conf, BTREECONF_SIZE, 1, fd_conf);
}

BTreeConf *disk_read_conf(FILE *fd_conf)
{
  BTreeConf *conf = new_btree_conf(fd_conf);

  if (fseek(fd_conf, 0, SEEK_SET) == 0)
    fread(conf, BTREECONF_SIZE, 1, fd_conf);

  return conf;
}

void close_file(FILE *fd)
{
  fclose(fd);
}
