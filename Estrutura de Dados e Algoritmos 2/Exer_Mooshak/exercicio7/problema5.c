#include <stdio.h>

#include "array_list.h"

int main(void)
{
    LinkedList *arrayl[ARRAY_SIZE];
    set_array_null(arrayl);
    char operation;

    while (scanf(" %c", &operation) != EOF)
    {
        unsigned int i;
        scanf("%u", &i);

        if (operation == 'p')
        {
            unsigned int j;
            scanf("%u", &j);

            add_array(arrayl, i, j);
        }
        
        else if (operation == 'x')
        {
            unsigned int j;
            scanf("%u", &j);

            remove_array(arrayl, i, j);
        }
        
        else if (operation == 'q')
        {
            LinkedList *list = get_i_list(arrayl, i);
            unsigned int lsize = get_size(list);

            // Print "i n j1 j2 ... jn"        
            printf("%u %d", i, lsize);

            if (lsize > 0)
                print_linked_list(list);
            else
                printf("\n");
        }
    }
    free_array_list(arrayl);
    return 0;
}