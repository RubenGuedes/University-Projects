#include "header/btree.h"
#include "header/pessoa.h"

struct bnode
{
  bool folha;
  int indice_ficheiro,
      ocupacao,
      filhos[2*T];
  Pessoa elementos[2*T-1];
};

struct btree
{
  BNode *raiz;
};

struct node_index
{
  BNode *no;
  int indice;
};

Node_Index *new_node_index(BNode *no, int i)
{
  Node_Index *n_ind = malloc(sizeof(Node_Index));
  n_ind->no = no;
  n_ind->indice = i;

  return n_ind;
}

void destroy_node_index(Node_Index *st)
{
  free(st->no);
  free(st);
}

// New BNode
BNode *new_bnode()
{
  BNode *node = malloc(sizeof(BNode));

  node->folha = false;
  node->ocupacao = 0;

  return node;
}
/*
BTree *new_btree()
{
  BTree *tree = malloc(sizeof(BTree));
  tree->raiz = new_bnode();
  tree->raiz->folha = true;

  disk_write(tree->raiz, BEGIN_FILE_INDEX);
  return tree;
}

// int vai ser o id da pessoa
Node_Index *btree_search(BNode *no, int index)
{
  int i = 0;
  
  while( i <= no->ocupacao && index > get_id(no->elementos[i]) )
    i++;

  if ( i <= no->ocupacao && index == get_id(no->elementos[i]))
    return new_node_index(no, i);

  else if (no->folha)
    return NULL;

  else
    {
      no = disk_read(no->filhos[i]);
      return btree_search(no, index);
    }
  
  return NULL;
}

// Insert
void btree_insert(BTree *tree, Pessoa *p)
{
  BNode *r = tree->raiz;
  
  if ( r->ocupacao == (2*T-1) )
    {
      BNode *aux_no = new_bnode();
      tree->raiz = aux_no;
      aux_no->folha = false;

      // Get_file_last_index
      int last_index = btree_max(),
	  new_last_index = last_index + 1;
      aux_no->filhos[0] = new_last_index;

      btree_slipt_child(aux_no, 1);
      btree_insert_nonfull(aux_no, p);
    }
  else
    btree_insert_nonfull(r, p);
}

void btree_slipt_child(BNode *node, int val)
{
  BNode z = *new_bnode();

  // Get node on disk
  BNode y = disk_read(node->filhos[val]);

  z.folha = y.folha;
  z.ocupacao = T - 1;

  for (int j = 0; j < T-1; j++)
    z.elementos[j] = y.elementos[j+T];

  if (!y.folha)
    for (int j = 0; j < T; j++)
      z.filhos[j] = y.filhos[j+T];

  y.ocupacao = T - 1;
  for (int j = node->ocupacao+1; j > val+1; j--)
    continue;
}

void btree_insert_nonfull(BNode *node, Pessoa *p);

// Delete
void btree_delete(BTree *, int);
void btree_delete_safe(BNode *, int);
void btree_delete_fromleaf(BNode *, int);
void btree_delete_from_subtree(BNode *, int);
void btree_merge_child(BNode *, int);
void btree_borrow_from_left_sibling(BNode *, int);
void btree_borrow_from_right_sibling(BNode *, int);
*/