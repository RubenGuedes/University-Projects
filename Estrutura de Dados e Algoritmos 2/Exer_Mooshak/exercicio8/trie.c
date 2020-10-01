/* trie implementation, with arrays */
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#include "trie.h"

#define ALPHABET_MIN 'a'
#define ALPHABET_MAX 'z'
#define ALPHABET_SIZE (ALPHABET_MAX - ALPHABET_MIN + 1)

#define POS(c)  ((c) - ALPHABET_MIN)	// character position in alphabet
#define CHAR(n) ((n) + ALPHABET_MIN)	// n-th alphabet character

/* trie node */
struct node {
  struct node *child[ALPHABET_SIZE],	// children
              *parent;
  bool        word;			// end-of-word mark
};

/* trie */
struct trie {
  struct node *root;
};


/*
  Allocates and returns a new trie node.
*/
static struct node *node_new(void)
{
  struct node *node = malloc(sizeof(*node));

  if (node != NULL)
  {
    node->word = false;
  
    for (int i = 0; i < ALPHABET_SIZE; ++i)
	    node->child[i] = NULL;
  }
  node->parent = NULL;

  return node;
}

/* Frees a trie NODE. */
static void node_free(struct node *node)
{
  free(node);
}


/* Destroys the sub-trie with root NODE. */
static void node_destroy(struct node *node)
{
  if (node == NULL)
    return;

  for (int i = 0; i < ALPHABET_SIZE; ++i)
    node_destroy(node->child[i]);

  node_free(node);
}


/*
  Creates and returns a new, empty trie.
*/
struct trie *trie_new(void)
{
  struct trie *trie = malloc(sizeof(struct trie));

  if (trie != NULL)
    trie->root = NULL;

  return trie;
}

/* Destroys trie T, freeing the memory it occupies. */
void trie_destroy(struct trie *t)
{
  node_destroy(t->root);

  free(t);
}

/* Checks whether trie T is empty. */
bool trie_empty(struct trie *t)
{
  return t->root == NULL;
}

/*
  Inserts word P into trie T.

  Returns true if the word was successfully inserted (or was already
  in the trie), false otherwise.
*/
bool trie_insert(struct trie *t, char p[])
{
  struct node *n;
  int i = 0;

  if (t->root == NULL)
    t->root = node_new();	// new, empty root node

  if (t->root == NULL)
    return false;

  n = t->root;

  // follow the word down the trie as long as possible,
  // taking care not to go to a nonexisting node
  while (p[i] != '\0' && n->child[POS(p[i])] != NULL)
  {
    n = n->child[POS(p[i])];
    i++;
  }

  // insert the new suffix into the trie
  while (p[i] != '\0')
  {
    n->child[POS(p[i])] = node_new();
    n->child[POS(p[i])]->parent = n; // Store parent

    if (n->child[POS(p[i])] == NULL)
	    return false;

    n = n->child[POS(p[i])];
    i++;
  }

  n->word = true;

  return true;
}

/*
   Searches for word P in trie T.
   Returns true if it finds it, false otherwise.
*/
bool trie_find(struct trie *t, char p[])
{
  struct node *root = t->root;
  char *p_word = p;

  while ( root != NULL && *p_word != '\0' )
  {
    root = root->child[  POS(*p_word )  ];
    p_word++;
  }
  
  return root != NULL && root->word;
}
//////////////////////////////////////////////////
/* Counts and returns the number of words in trie T. */
void trie_count_aux(struct node* no, int *counter)
{
  for (unsigned char i = 0; i < ALPHABET_SIZE; i++)
  {
    struct node* child = no->child[i];

    if (child != NULL)
    {
      if (child->word)
        (*counter)++;

      trie_count_aux(child, counter);
    }
  }
}

/* Counts and returns the number of words in trie T. */
int trie_count(struct trie *t)
{
  if (trie_empty(t))
    return 0;
  
  struct node *root = t->root;
  int count = 0;

  trie_count_aux(root, &count);
  return count;
}
//////////////////////////////////////////////////
int count_parents(struct node *no)
{
  int count = 0;
  
  while (no->parent != NULL)
    count++;

  return count;
}
//////////////////////////////////////////////////
void trie_print_completions_aux(struct node* no, unsigned char ind, char word_print[WORD_SIZE])
{
  if ( no != NULL )
  {
    if (no->word)
    {
      word_print[ind] = '\0';
      printf("%s\n", word_print);
    }
    
    // See the child
    for (unsigned char i = 0; i < ALPHABET_SIZE; i++)
    {
      struct node *aux = no->child[i];
      if (aux != NULL)
      {
        word_print[ind] = CHAR(i);
        trie_print_completions_aux(aux, ind + 1, word_print);
      }
    }
  }
}

/* Prints all words in trie T with prefix P. */
void trie_print_completions(struct trie *t, char p[])
{
  char word_to_print[WORD_SIZE];
  unsigned char index_word = 0;
  struct node* root = t->root;

  // Ir até ao fim do prefixo
  while (p[index_word] != '\0' && root != NULL)
  {
    root = root->child[ POS(p[index_word]) ];  
    word_to_print[index_word] = p[index_word];
    index_word++;
  }
  if (root != NULL)
    trie_print_completions_aux(root, index_word, word_to_print);
}
//////////////////////////////////////////////////

/* Removes word P from trie T. */
void trie_delete(struct trie *t, char p[])
{
  struct node *no = t->root;
  char *p_palavra = p;

  while (no != NULL && *p_palavra != '\0')
  {
    no = no->child[ POS(*p_palavra) ];
    p_palavra++;
  }

  if (no != NULL && no->word)
    no->word = false;
  // Se não é word
  else
    return;
  
  bool childess = false;
  while (no != NULL && (!childess || no->word))
  {
    p_palavra--;
    childess = true;

    int j = 0;
    while (j < ALPHABET_SIZE && childess)
    {
      if (no->child[j] != NULL)
        childess = false;

      j++;
    }

    struct node* parent_node;
    if (childess)
    {
      parent_node = no->parent;

      if (parent_node == NULL)
        t->root = NULL;
      else
        parent_node->child[ POS(*p_palavra) ] = NULL;
      
      node_destroy(no);
    }
    else
      break;
      
    no = parent_node;
  }
}
