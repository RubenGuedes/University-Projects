#include <stdio.h>
#include <stdlib.h>

#include "list.h"

struct node
{
    int value;
    struct node *next;
};

struct list 
{
    int size;
    struct node *head;
};

// Cria e devolve uma nó
struct node *new_node(int value)
{
    struct node* node = malloc( sizeof(struct node) );
    
    node->value = value;
    node->next = NULL;

    return node;
}

// devolve a posição de uma ocorrência do valor na lista (correspondendo ao primeiro elemento a posição 0), ou -1 se não o encontrar
int list_find(struct list* list, int value)
{
    int pos = 0;
    struct node* head = list->head;

    while (head != NULL)
    {
        if (head->value == value)
            return pos;

        head = head->next;
        pos++;
    }
    return -1;
}

// indica se a lista está vazia;
bool list_empty(struct list* list)
{
    return list->size == 0;
}

// Cria e devolve uma nova lista (vazia);
struct list *list_new()
{
    struct list* linked_list = malloc( sizeof(struct list) );

    linked_list->size = 0;
    linked_list->head = NULL;

    return linked_list;
}

// Insere o novo elemento no início da lista, e devolve true se o elemento foi inserido e false caso contrário;
bool list_insert(struct list* linked, int value)
{
    struct node *body = linked->head;
    struct node *newnode = new_node(value);

    if (linked->head != NULL)
        newnode->next = body;
    
    // Inserir à cabeça
    linked->size += 1;
    linked->head = newnode;

    if ( linked->head == newnode )
        return true;
    
    return false;
} 
// Mostra a lista na consola na forma [v0 v1 ...] (sem acrescentar '\n');
void list_print(struct list* list)
{
    struct node* body = list->head;

    if (body == NULL)
        printf("[]");
    
    else
    {
        printf("[");
        while (body->next != NULL)
        {
            printf("%d ", body->value);
            body = body->next;
        }
        printf("%d]", body->value);
    }
}
// Liberta toda a memória ocupada pela lista.
void list_destroy(struct list* list)
{
    struct node* tmp;
    
    while (list->head != NULL)
    {
        tmp = list->head;
        list->head = list->head->next;
        free(tmp);   
    }
    free(list);
}

// – remove da lista uma ocorrência do valor dado, e devolve true se removeu algum elemento e false caso contrário; quando é removido um elemento, a memória ocupada pelo nó em que se encontrava deve ser libertada;
bool list_remove(struct list* list, int value)
{
    struct node *tmp;

    if (list->head == NULL)
        return false;
    
    else if (list->head->value == value)
    {
        tmp = list->head;
        list->head = list->head->next;
        list->size--;
        free(tmp);

        return true;
    }
    else
    {
        struct node *prev_node = list->head;

        while (prev_node->next->value != value && prev_node->next->next != NULL)
            prev_node = prev_node->next;
        
        if ( prev_node->next->value == value )
        {
            tmp = prev_node->next;
            prev_node->next = prev_node->next->next;
            list->size--;
            free(tmp);

            return true;
        }
        return false;
    }
    return false;
}

// – devolve o comprimento da lista;
int list_length(struct list* list)
{
    return list->size;
}

// – devolve o elemento na n-ésima posição da lista (correspondendo a 0-ésima posição ao primeiro elemento da lista).
int list_nth(struct list* list, int n)
{
    if (n > list->size)
        return -1;
    
    struct node *head = list->head;

    for (int i = 0; i < n; i++)
        head = head->next;

    return head->value;
}