#include <stdio.h>

#define TRUE 1
#define FALSE 0
#define ELE_SEQUENCE 15000
#define COLUMN_SIZE 3

void console_log(unsigned short ind, unsigned short end, unsigned short steps, int buffer[ELE_SEQUENCE])
{
    int start = ind + 1,
        end_1 = end + 1;

    if (steps == 1)
        printf("s[%d] = %d\n", start, buffer[ind]);
    
    else if (steps == 2)
        printf("s[%d] + s[%hu] = %d\n", start, end_1, buffer[ind]);

    else
        printf("s[%d] + ... + s[%hu] = %d\n", start, end_1, buffer[ind]);   
}

char calc_subsequence(int vetor[ELE_SEQUENCE], unsigned short start, unsigned short end, int value, int buffer[ELE_SEQUENCE])
{
    buffer[start] += vetor[end];

    if ( buffer[start] == value )
    {
        unsigned short steps = end - start + 1;

        // Print result
        console_log(start, end, steps, buffer);
        return TRUE;
    }
    return FALSE;
}

void subsequences(unsigned short size, int vetor[size], int value_goal)
{
    char found_sub_seq = FALSE;
    static int buffer[ELE_SEQUENCE];

    // Length of steps
    for (unsigned short steps = 0; steps < size; steps++)
    {
        for (unsigned short ind = 0; ind + steps < size; ind++)
        {
            unsigned short start = ind,
                           end   = ind + steps;

            found_sub_seq = calc_subsequence(vetor, start, end, value_goal, buffer);

            if (found_sub_seq)
                break;
        }
        if (found_sub_seq)
                break;
    }

    // Não encontrou uma unica subsequência
    if (!found_sub_seq)
        printf("nenhuma subsequencia soma %d\n", value_goal);
}

int main(void)
{
    unsigned short n_elements,
                   ind_elemts = 0;
    int value_goal,
        element,
        elements_seq[ELE_SEQUENCE];

    // Number of elements
    scanf("%hu", &n_elements);

    // Elements to fill array
    for (; ind_elemts < n_elements; ind_elemts++)
    {
        scanf("%d", &element);
        elements_seq[ind_elemts] = element;
    }
    
    // Result value
    scanf("%d", &value_goal);

    // Verifica a menor subsequência possível
    subsequences(n_elements, elements_seq, value_goal);
}