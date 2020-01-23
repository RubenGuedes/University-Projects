#include "queue.h"

#define LIST_FREE_SIZE 30

struct node
{
    struct node *left, 
                *right;
    short posicoes[2],
          order;
};

typedef struct linkedlist
{
    struct node *head,
                *tail;
    short size, 
          last_ocupied;
} LinkedList;


struct node *new_Node();
LinkedList  *new_LinkedList();

void join_fragments(LinkedList *list);
void add_list(LinkedList *list, short *pos);
void remove_item(LinkedList *list, short index);
void update_linkedlist(LinkedList *list, int *arr );
void remove_interval_linked_list(LinkedList *free_mem, int *interval);

void print_forward(LinkedList *list);
void print_backward(LinkedList *list);

int *can_insert_process(LinkedList *list, short necessary_len);

short fragment_size(int *arr_pos);
short *pos_mem_linked_list(LinkedList *list, short index);