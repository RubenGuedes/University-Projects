#include <stdio.h>

#define SIZE 100

char in_storage(int size, int storage[size], int index_st)
{
    return 0;
}

// Recursivo
int fibonacci_recursivo(int val)
{
    static int storage[SIZE],
               index_storage;
    
    if (val == 0)
        return 0;

    else if (val == 1)
        return 1;
    
    else if ( storage[index_storage] != 0 && index_storage > 0)
        return storage[index_storage];
    
    else
        storage[index_storage++] = fibonacci_recursivo(val - 1) + fibonacci_recursivo(val - 2);

    return storage[--index_storage];
}

int main(void)
{
    int storage[SIZE];

    printf("na posicao %d o valor é: %d\n", 10, storage[10]);
    return 0;
}
