#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct node Node;
typedef struct linkedlist LinkedList;

LinkedList *new_linked_list();
void free_linked_list(LinkedList *);

void add_linked(LinkedList *, unsigned int);
void remove_linked(LinkedList *, unsigned int);

int value_position(LinkedList *, unsigned int);
unsigned int get_size(LinkedList *);

bool value_exists(LinkedList *, unsigned int);
bool empty_linked(LinkedList *);

void print_linked_list(LinkedList *);