#include <stdbool.h>

struct list;

// devolve a posição de uma ocorrência do valor na lista (correspondendo ao primeiro elemento a posição 0), ou -1 se não o encontrar
int list_find( struct list* list, int value); 
// indica se a lista está vazia;
bool list_empty(struct list* list);
// – cria e devolve uma nova lista (vazia);
struct list *list_new(); 
//– insere o novo elemento no início da lista, e devolve true se o elemento foi inserido e false caso contrário;
bool list_insert(struct list* list,int value); 
// – mostra a lista na consola na forma [v0 v1 ...] (sem acrescentar '\n');
void list_print(struct list* list); 
//  – liberta toda a memória ocupada pela lista.
void list_destroy(struct list* list);
// – remove da lista uma ocorrência do valor dado, e devolve true se removeu algum elemento e false caso contrário; quando é removido um elemento, a memória ocupada pelo nó em que se encontrava deve ser libertada;
bool list_remove(struct list* list, int value); 
// – devolve o comprimento da lista;
int list_length(struct list* list);
// – devolve o elemento na n-ésima posição da lista (correspondendo a 0-ésima posição ao primeiro elemento da lista).
int list_nth(struct list* list, int n); 