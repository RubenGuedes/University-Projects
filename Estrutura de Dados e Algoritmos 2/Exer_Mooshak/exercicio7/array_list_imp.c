#include "array_list.h"

void set_array_null(LinkedList *arrayl[])
{
    for (int i = 0; i < ARRAY_SIZE; i++)
        arrayl[i] = NULL;
}

void free_array_list(LinkedList *arrayl[])
{
    for (int i = 0; i < ARRAY_SIZE; i++)
    {
        LinkedList *list = get_i_list(arrayl, i);
        if (list != NULL)
            free_linked_list(list);
    }
}

void add_array(LinkedList *arrayl[], unsigned int i, unsigned int j)
{
    LinkedList *list = get_i_list(arrayl, i);

    if (list == NULL)
    {
        arrayl[i] = new_linked_list();
        list = get_i_list(arrayl, i);
    }
    
    add_linked(list, j);
}
void remove_array(LinkedList *arrayl[], unsigned int i, unsigned int j)
{
    LinkedList *list = arrayl[i];

    if (list != NULL)
    {
        remove_linked(list, j);

        if (empty_linked(list))
        {
            free_linked_list(list);
            arrayl[i] = NULL;
        }
    }   
}

LinkedList *get_i_list(LinkedList *arrayl[], unsigned int i)
{
    return arrayl[i];
}