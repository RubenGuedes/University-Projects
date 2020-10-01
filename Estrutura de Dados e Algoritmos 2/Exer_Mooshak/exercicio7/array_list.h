#include <stdlib.h>
#include "linklist.h"

#define ARRAY_SIZE 1000000

void set_array_null(LinkedList *arrayl[]);
void free_array_list(LinkedList *arrayl[]);

void add_array(LinkedList *[], unsigned int, unsigned int);
void remove_array(LinkedList *[], unsigned int, unsigned int);
LinkedList *get_i_list(LinkedList *[], unsigned int);