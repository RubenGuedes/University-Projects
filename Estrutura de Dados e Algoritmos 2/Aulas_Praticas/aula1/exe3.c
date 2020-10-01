#include <stdio.h>

// Recursivo
long recursive_fact(int number)
{
    if (number == 0)
        return 1;
    else
        return number * recursive_fact(number - 1);
}

// Iterativo
long iterative_fact(int number)
{
    long result = 1;

    for (int val = 1; val <= number; val++)
        result *= val;

    return result;
}

int main(void)
{
    long iter_10  = iterative_fact(10),
         iter_20  = iterative_fact(20),
         iter_21  = iterative_fact(21),
         recur_10 = recursive_fact(10),
         recur_20 = recursive_fact(20),
         recur_21 = recursive_fact(21);

    /* 
        O factorial de 21 vai dar error porque
        o número de bytes que vai ocupar é su-
        perior ao número de bytes que o "long"
        consegue armazenar que é 8 bytes.
     */
    printf("Recursive:\n10 = %ld\n20 = %ld\n21 = %ld\n", recur_10, recur_20, recur_21);
    
    printf("\nIterative:\n10 = %ld\n20 = %ld\n21 = %ld\n", iter_10, iter_20, iter_21);
    
    return 0;
}
