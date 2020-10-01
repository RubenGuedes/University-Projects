#include <stdio.h>

#define ARRAY_SIZE 50000
#define MAX_ELEMENT (ARRAY_SIZE * 2 + 2) 

void preencher_array(int *array, int size)
{
    for (int i = 0, val = 2; i < size; i++, val += 2)
        array[i] = val;
}

int procura(int n, int s, int v[s])
{
    int index = 0;

    while (n < v[index] && index < s)
        index++;
    
    if (n == v[index])
        return index;
    
    return -1;
}

void encontrou_valores(int *array, int size)
{
    int //auxiliar,
        procura_val,
        array_valores[3] = {0, 11, MAX_ELEMENT};

    for (int i = 0; i < 3; i++)
    {
        int auxiliar = array_valores[i];
        procura_val = procura(auxiliar, ARRAY_SIZE, array);

        if (procura_val != -1)
            printf("Encontrou %d na posição: %d\n", auxiliar, procura_val);
        else
            printf("Não encontrou %d\n", auxiliar);
    }
}

void procura_todos_elementos(int *array, int size)
{
    for (int i = 1; i <= size; ++i)
    {
      int p = procura(2 * i, size, array);

      if (p == -1)
        printf("Não encontrou %d\n", 2 * i);
        
      else if(array[p] != 2 * i)
        printf("Encontrou %d na posição errada: %d\n", 2 * i, p);
    }
}

int main(void)
{
    int array[ARRAY_SIZE];
    preencher_array(array, ARRAY_SIZE);

    // Pesquisas
    encontrou_valores(array, ARRAY_SIZE);
    procura_todos_elementos(array, ARRAY_SIZE);
}