#include <stdio.h>

// Recursivo
int fibonacci_recursivo(int val)
{
    if (val == 0)
        return 0;

    else if (val == 1)
        return 1;
    
    else
        return fibonacci_recursivo(val - 1) + fibonacci_recursivo(val - 2);
}

void fibonacci_recursivo_list(int val)
{
    for (int i = 0; i < val; i++)
        printf("%d ", fibonacci_recursivo(i));
    printf("\n"); 
}

// Iterativo
int fibonacci_iterativo(int val)
{
    int aux = 0,
        fib_1 = 0,
        fib_2 = 1;

    for (int i = 0; i < val; i++)
    {
        aux = fib_2;

        fib_2 = fib_1 + fib_2;
        fib_1 = aux;
    }
    return fib_1;
}

void fibonacci_iterativo_list(int val)
{
    for (int i = 0; i < val; i++)
        printf("%d ", fibonacci_iterativo(i));
    printf("\n"); 
}

int main(void)
{
    fibonacci_iterativo_list(20);
    fibonacci_recursivo_list(20);
}