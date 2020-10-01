#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>

#include "student.h"

#define LETRAS_ALFABETO 26
#define SIZEOF_HASH sizeof(HashCountry)
#define SIZEOF_HASHNODE sizeof(HashNode)
#define TAMANHO_HASH ((LETRAS_ALFABETO * LETRAS_ALFABETO) + 75)

/**
 * Implementação baseada no livro:
 * -> Introduction to Algorithms, Third Edition
 * 
 * Esta hashtable vai conter as informações de um país
 */
typedef struct hash_node
{
    bool occupied;
    char country[COUNTRY];
    int corr,
        diplo,
        aband;
} HashNode;

typedef struct hash_country
{
    int ocupacao;
    HashNode h[TAMANHO_HASH];
} HashCountry;

// ----------------------------
// HASHNODE
/**
 * Cria um nó para a HashTable
 * Argumentos:
 *  -> Alunos Correntes
 *  -> Alunos que Abandonara,
 *  -> Alunos que Terminaram
 * Return:
 *  -> Nó com as configurações dadas pelos argumentos
 */
HashNode *new_hash_node(char [COUNTRY], int, int, int);
/**
 * Destroi um nó da HashTable
 */
void destroy_hash_node(HashNode *);
/**
 * Indica se o nó está preenchido ou não
 */
bool is_empty(HashNode *);

/**
 * Acede ao nó e aumenta em 1 o número de alunos correntes
 */
void hnode_increase_active(HashNode *);
/**
 * Acede ao nó e aumenta em 1 o número de alunos que
 * terminaram e retira 1 ao número de alunos ativos
 */
void hnode_student_finish(HashNode *);
/**
 * Acede ao nó e aumenta em 1 o número de alunos que
 * abandonaram e retira 1 ao número de alunos ativos
 */
void hnode_student_left(HashNode *);
/**
 * Retira 1 ao número de alunos ativos
 */
void hnode_student_remove(HashNode *);

// ----------------------------
// HASHCOUNTRY
/**
 * Cria uma nova HashTable, em que 
 * cada elemento desta é um HashNode
 */
HashCountry *new_hash_country();
/**
 * Lê a HashTable do ficheiro
 */
HashCountry *disk_read_hash(FILE *);
/**
 * Escreve a HashTable no ficheiro
 */
void disk_write_hash(FILE *, HashCountry *);
/**
 * Destroi o espaço dedicado à HashTable
 * que foi usado na memória principal
 */
void destroy_hash_country(HashCountry *);

// Verifica se a HashTable está vazia
bool empty_hash(HashCountry *);
// Verifica se a HashTable está cheia
bool full_hash(HashCountry *);

/**
 * Insere um HashNode no ficheiro
 * Argumentos:
 *  -> HashTable onde se quer inserir o conteúdo (HashCountry)
 *  -> HashNode, conteúdo a ser inserido.
 * Return:
 *  -> Posição onde foi inserido o nó
 */
int hash_insert(HashCountry *, HashNode *);
/**
 * Procura por um elemento com um determinado país
 * Argumentos:
 *  -> HashTable
 *  -> Nome do país
 * Return:
 *  -> Nó que contém a informação necessária
 */
HashNode *hash_search(HashCountry *, char [COUNTRY] );

/**
 * Double Hashing foi baseado no livro:
 *  Introduction to Algorithms, Third Edition
 */
int double_hashing(long key, int index);
int hash1(long key);
int hash2(long key);
