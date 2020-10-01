#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#include "hash_country.h"

/**
 * Implementação baseada do pseudo codigo do professor Vasco Pedro
 */ 
// Grau minimo de ramificaçao
#define T 128

// Tamanho das struct
#define BNODE_SIZE sizeof(BNode)
#define BTREE_SIZE sizeof(BTree)
#define BTREECONF_SIZE sizeof(BTreeConf)
#define NODE_INDEX_SIZE sizeof(Node_Index)

// Directorias
#define INDEX_FILE "index_file"
#define BNODES_FILE "bnodes_file"

// Nó da BTree
typedef struct bnode BNode;
// BTree
typedef struct btree BTree;
// Estrutura com as configurações da BTree, que está no ficheiro "index_file"
typedef struct btree_conf BTreeConf;
// Esta estrutura vai ser usada no btree_search().
typedef struct node_index Node_Index;

struct bnode
{
  bool folha;
  int ocupacao,
      indice_ficheiro,
      filhos[2*T];
  Student elementos[2*T-1];
};

struct btree
{
  BNode *raiz;
};

struct btree_conf
{
  int root_index,
      last_index;
};

struct node_index
{
  BNode *no;
  int indice;
};

BTreeConf *new_btree_conf(FILE *);
void destroy_btree_conf(BTreeConf *);

Node_Index *new_node_index(BNode *, int);
void destroy_node_index(Node_Index *);
  
BTree *new_btree(FILE *, BTreeConf *);
void destroy_btree(BTree *btree);
  
BNode *new_bnode();
void destroy_bnode(BNode *, FILE *);

// Search
Node_Index *btree_search(BNode *, char [GLOBAL_ID], FILE *);

// Insert
void btree_insert(BTree *, Student *, FILE *, BTreeConf *, HashCountry *);
void btree_slipt_child(BNode *, BNode *, int, FILE *, BTreeConf *);
void btree_insert_nonfull(BNode *, Student *,  FILE *, BTreeConf *, HashCountry *);

// Delete
void btree_delete(BTree *, char [GLOBAL_ID], FILE *, BTreeConf *, HashCountry *);
void btree_delete_safe(BNode *, char [GLOBAL_ID], FILE *, HashCountry *);
void btree_delete_from_leaf(BNode *, int, FILE *, HashCountry *);
void btree_delete_from_subtree(BNode *, int, char [GLOBAL_ID],  FILE *, HashCountry *);
void btree_delete_from_internal_node(BNode *, int, char [GLOBAL_ID], FILE *, HashCountry *);
void btree_merge_child(BNode *, BNode *, BNode *, int, FILE *);
void btree_borrow_from_left_sibling(BNode *, BNode *, BNode *, int, FILE *);
void btree_borrow_from_right_sibling(BNode *, BNode *, BNode *, int, FILE *);
Student btree_delete_min(BNode *, FILE *);
Student btree_delete_max(BNode *, FILE *);

/**
 * Funções para manipulação de ficheiros
 */
/**
 * Argumentos:
 *  -> Nome do ficheiro (char[])
 * Retorna:
 *  -> File descriptor
 */
FILE *open_file(char *);

/**
 * Argumentos:
 *  -> File descriptor (FILE)
 *  -> indice do nó no ficheiro (int)
 *  -> Nó onde se quer colocar o conteúdo encontrado no ficheiro
 * Return:
 *  -> void
 */
void disk_read_cur_node(FILE *, int, BNode *);
/**
 * Argumentos:
 *  -> File descriptor (FILE)
 *  -> Nó que se quer escrever no ficheiro
 * Return:
 *  -> void
 */
void disk_write_cur_node(FILE *, BNode *);

/**
 * Escreve as configurações da BTree no ficheiro
 * Argumentos:
 *  -> File descriptor (FILE)
 *  -> Estrutura que contem os conteudos a escrever no ficheiro (BTreeConf)
 * Return:
 *  -> void
 */
void disk_write_conf(FILE *, BTreeConf *);

/**
 * Lê as configurações iniciais da BTree
 * Argumentos:
 *  -> File descriptor (FILE)
 * Return:
 *  -> void
 */
BTreeConf *disk_read_conf(FILE *);

/**
 * Função que fecha o ficheiro
 * Argumentos:
 *  -> File descriptor (FILE) 
 * Return:
 *  -> void
 */
void close_file(FILE *);
