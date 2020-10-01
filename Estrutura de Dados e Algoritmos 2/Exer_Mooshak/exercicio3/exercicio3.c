#include <stdio.h>

#define TRUE 1
#define FALSE 0
#define ELE_SEQUENCE 1000

void console_log(unsigned short ind, int vetor[], int goal)
{
    int index = ind - 1,
        start = vetor[index] + 1;

    if (ind == 1)
        printf("s[%d] = %d\n", start, goal);
    
    else if (ind == 2)
    {
        start = vetor[0] + 1;
        int end = vetor[index] + 1;

        printf("s[%d] + s[%d] = %d\n", start, end, goal);
    }

    else
    {
        start = vetor[0] + 1;
        int end = vetor[index] + 1;

        printf("s[%d] + ... + s[%d] = %d\n", start, end, goal);   
    }
}

char calc_subsequence(int vetor[], unsigned short start, unsigned short end, int value)
{
    int soma = 0;
    static int array_aux[ELE_SEQUENCE];
    unsigned short index_array = 0;

    for (unsigned short i = start; i <= end; i++)
    {
        // Guarda a soma
        soma += vetor[i];
        array_aux[index_array++] = i;
        
        // Print da sequência pretendida!
        if (soma == value)
        {         
            console_log(index_array, array_aux, value);
            break; 
        }
    }
    if (soma == value)
        return TRUE;
    
    // Se não encontramos o valor pretendido
    return FALSE;
}

void subsequences(unsigned short size, int vetor[size], int value_goal)
{
    char found_sub_seq = FALSE;

    for (unsigned short steps = 0; steps < size; steps++)
    {
        for (unsigned short ind = 0; ind + steps < size; ind++)
        {
            unsigned short start = ind,
                           end   = ind + steps;

            found_sub_seq = calc_subsequence(vetor, start, end, value_goal);

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