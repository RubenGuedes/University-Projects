#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define SIZE_MARCA 21
#define SIZE_MODELO 21
#define T 39
#define CHILDS (2 * T)
#define ELEMENTS (2 * T - 1)

// 48 bytes
typedef struct automovel 
{
    char marca[SIZE_MARCA],
         modelo[SIZE_MODELO];
    unsigned short cilindrada,
                   ano_intro,
                   ano_retirada;
} Automovel;

// Tem que ser aproximadamente 4096 bytes
typedef struct bnode
{
    bool folha;
    unsigned short n_elements;
    // 2t
    unsigned int childs[CHILDS]; 
    // 2t - 1
    Automovel automoveis[ELEMENTS]; 
} BNode;

typedef struct btree 
{
    BNode *raiz;
} BTree;

int main(void)
{
    printf("Hello");
}