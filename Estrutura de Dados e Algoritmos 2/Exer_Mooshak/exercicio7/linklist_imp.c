#include "linklist.h"

struct node 
{
    unsigned int j;
    Node *next;
};

struct linkedlist
{
    unsigned int size;
    Node *head;
};

/**
 * Funções para o node da linked list
 */
Node *new_node(unsigned int value)
{
    Node *node = malloc(sizeof(Node));
    node->j = value;
    node->next = NULL;
    return node;
}
void free_node(Node *node)
{
    free(node);
}
void set_next_node(Node *act_node, Node *next_node)
{
    act_node->next = next_node;
}

/**
 * Funções para a linked list
 */
LinkedList *new_linked_list()
{
    LinkedList *list = malloc(sizeof(LinkedList));
    list->size = 0;
    list->head = NULL;

    return list;
}

void free_linked_list(LinkedList *list)
{
    Node *tmp;

    while (list->head != NULL)
    {
        tmp = list->head;
        list->head = list->head->next;
        free_node(tmp);
    }
    free(list);
}

// Inserir no fim da lista
void add_linked(LinkedList *list, unsigned int value)
{
    Node *body = list->head;

    if (body == NULL)
    {
        list->head = new_node(value);
        
        // Incrementar o tamanho da linked list
        list->size++;
    }
    else
    {
        while (body->j != value && body->next != NULL)
            body = body->next;

        if (body->j != value)
        {
            body->next = new_node(value);
            
            // Incrementar o tamanho da linked list
            list->size++;
        }
    }
}

void remove_linked(LinkedList *list, unsigned int value)
{
    Node *node_to_del = list->head;

    // Se nó não existe
    if (node_to_del == NULL)
        return;
    
    // Se o elemento está na primeira posição da lista
    if (node_to_del->j == value)
    {
        Node *node = list->head;
        list->head = list->head->next;
        free_node(node);

        list->size--;
    }
    else
    {
        // Se o elemento está nas restantes posições da lista
        while (node_to_del->next != NULL && node_to_del->next->j != value)
            node_to_del = node_to_del->next;        

        if (node_to_del->next != NULL && node_to_del->next->j == value)
        {
            // Buscar o nó depois deste
            Node *next = node_to_del->next->next;

            // Libertar o node
            free_node(node_to_del->next);

            // Refazer as ligações
            set_next_node(node_to_del, next);

            list->size--;
        }
    }
}

int value_position(LinkedList *list, unsigned int value)
{
    int counter = 0;
    Node *body = list->head;
    while (body != NULL)
    {
        if(body->j == value)
            return counter;

        body = body->next;
        counter++;
    }
    return -1;
}

unsigned int get_size(LinkedList *list)
{
    if (list == NULL)
        return 0;
    
    return list->size;
}

bool value_exists(LinkedList *list, unsigned int value)
{
    Node *body = list->head;
    while (body != NULL)
    {
        if(body->j == value)
            return true;

        body = body->next;
    }
    return false;
}

bool empty_linked(LinkedList *list)
{
    return list->size == 0;
}

void print_linked_list(LinkedList *list)
{
    Node *body = list->head;

    while (body != NULL)
    {
        printf(" %d", body->j);
        body = body->next;
    }
    printf("\n");
}