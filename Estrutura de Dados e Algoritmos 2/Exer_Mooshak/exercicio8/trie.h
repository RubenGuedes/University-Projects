/* trie interface 

    Código dos ficheiros "trie" fornecido pelo professor.
    Nesses ficheiros, as funções implementadas por mim, 
    tendo como base o pseudo-código do professor, foram:
        trie_count,
        trie_delete,
        trie_find,
        trie_count_aux,
        trie_print_completions,
        trie_print_completions_aux.
    
    Na função 'trie_insert' acrescentei uma linha para guardar o nó pai.
    A struct "node" também foi alterada para guardar um parametro que 
    receberá um "node" pai.
*/
#include <stdbool.h>

#define WORD_SIZE 65

struct trie;

struct trie *trie_new(void);
void trie_destroy(struct trie *);

bool trie_empty(struct trie *);

bool trie_find(struct trie *, char []);
bool trie_insert(struct trie *, char []);
void trie_delete(struct trie *, char []);

int trie_count(struct trie *);

void trie_print_completions(struct trie *, char []);
