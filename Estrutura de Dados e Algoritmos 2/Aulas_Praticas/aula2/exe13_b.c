#include <stdio.h>

#define VETOR_SIZE 50000
#define MAX_ELEMENT (VETOR_SIZE * 2 + 2) 

void preencher_vetor(int *vetor, int size)
{
    for (int i = 0, val = 2; i < size; i++, val += 2)
        vetor[i] = val;
}

int pesquisa_dictomica(int vetor[], int element, int start, int end)
{
    if (start > end)
        return -1;

    int mid = (start + end) / 2;

    if (vetor[mid] == element)
        return mid;
    
    else if ( vetor[mid] > element )
        return pesquisa_dictomica(vetor, element, start, mid - 1);
    
    else
        return pesquisa_dictomica(vetor, element, mid + 1, end);
}

void encontrou_valores(int *vetor, int size)
{
    int vetor_valores[3] = {0, 11, MAX_ELEMENT};

    for (int i = 0; i < 3; i++)
    {
        int var_aux = vetor_valores[i],
            index_val = pesquisa_dictomica(vetor ,var_aux, 0, VETOR_SIZE);

        if (index_val != -1)
            printf("Encontrou %d na posição: %d\n", var_aux, index_val);
        else
            printf("Não encontrou %d\n", var_aux);
    }
}

void procura_todos_elementos(int *vetor, int size)
{
    for (int i = 1; i <= size; ++i)
    {
      int p = pesquisa_dictomica(vetor, 2 * i, 0, VETOR_SIZE);

      if (p == -1)
        printf("Não encontrou %d\n", 2 * i);
        
      else if(vetor[p] != 2 * i)
        printf("Encontrou %d na posição errada: %d\n", 2 * i, p);
    }
}

int main(void)
{
    int vetor[VETOR_SIZE];
    preencher_vetor(vetor, VETOR_SIZE);

    // Pesquisas
    encontrou_valores(vetor, VETOR_SIZE);
    procura_todos_elementos(vetor, VETOR_SIZE);
}