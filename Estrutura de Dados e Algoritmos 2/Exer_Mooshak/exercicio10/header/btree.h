#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "pessoa.h"

#define T 15
#define BEGIN_FILE_INDEX 0

typedef struct bnode BNode;
typedef struct btree BTree;

/*
 * Esta estrutura vai ser usada no btree_search().
 * Não tem a haver com a implementação da Btree
 */
typedef struct node_index Node_Index;

BTree *new_btree();
BNode *new_bnode();

/**
 * int vai ser o id da pessoa
 */
Node_Index *btree_search(BNode *, int);

// Insert
void btree_insert(BTree *, Pessoa *);
void btree_slipt_child(BNode *, int);
void btree_insert_nonfull(BNode *, Pessoa *);

// Delete
void btree_delete(BTree *, int);
void btree_delete_safe(BNode *, int);
void btree_delete_fromleaf(BNode *, int);
void btree_delete_from_subtree(BNode *, int);
void btree_merge_child(BNode *, int);
void btree_borrow_from_left_sibling(BNode *, int);
void btree_borrow_from_right_sibling(BNode *, int);
