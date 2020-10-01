#include <stdio.h>

int char_index(int size, char vetor[size], char c)
{
    for (int i = 0; i < size; i++)
    {
        if (vetor[i] == c)
            return i;
    }
    return -1;
}

int main(void)
{
    char vetor[5] = {'a', 'b', '\0', '0', 'd'};

    printf("Index of '\\0' is %d\n",  char_index(5, vetor, '\0'));
}