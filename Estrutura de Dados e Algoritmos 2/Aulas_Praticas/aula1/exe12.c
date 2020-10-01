#include <stdio.h>

#define BITS_12 12
#define BITS_64 64
#define BITS_65 65

void binary_representation(int val, int bits_size)
{
    char negative = 0;
    int resto,
        base = 2,
        array[bits_size],
        index_array = -1;

    // See if val is negative
    if (val < 0)
    {
        negative = 1;
        val *= -1;
    }
    
    // Convert to binary representation
    while (val > 0)
    {
        resto = val % base;
        val /= base;

        array[++index_array] = resto;
    }
    
    // Fill empty spaces
    while (index_array < bits_size)
        array[++index_array] = 0;
    

    // Complemento
    if (negative)
        for (int i = 0; i < bits_size; i++)
            array[i] = (array[i] + 1) % 2;
    
    // print
    for (int i = (index_array - 1); i >= 0; i--)
        printf("%d", array[i]);
    printf("\n");
}

int main(void)
{
    binary_representation(  170, BITS_12);
    binary_representation(  170, BITS_64);
    binary_representation(  170, BITS_65);
    
    binary_representation( -170, BITS_12);
    binary_representation( -170, BITS_64);
    binary_representation( -170, BITS_65);
}