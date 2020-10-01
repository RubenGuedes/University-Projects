#include <math.h>
#include <stdio.h>

unsigned char is_prime_number(unsigned int value)
{
    // Sugestão do professor.
    unsigned int sqrt_value = (unsigned int) sqrt(value);

    // Flag start with value 'False'
    unsigned char flag = 0;

    if (value == 0 || value == 1)
        return flag;

    for (unsigned int number = 2; number <= sqrt_value; number++)

        if ( number != value && value % number == 0 )
            return flag;
    
    flag = 1;
    return flag;
}

unsigned int smallest_divisible_prime_number(unsigned int value)
{
    // Retorna value caso este seja primo
    if ( is_prime_number(value) )
        return value;

    // Caso contrário vai encontrar o menor fator primo
    for (unsigned int number = 1; number <= value; number++)

        if ( is_prime_number(number) && value % number == 0 )
            return number;
    
    // Se não encontrou retorna 0
    return 0;
}

void number_of_prime_factors(unsigned int val)
{
    unsigned int aux_value;

    while (val != 1)
    {
        // Encontra o menor factor primo de um certo valor
        aux_value = smallest_divisible_prime_number(val);

        // Imprime na consola o menor fator primo
        if (aux_value != 0)
        {
            printf(" %u", aux_value);
            val /= aux_value;
        }
        else
            break;
    }
    printf("\n");
}

int main(void)
{
    unsigned short n_elements;
    unsigned int input_number;

    // Número de elementos da sequência
    scanf("%hu", &n_elements);

    while (n_elements != 0)
    {
        // Elemento da sequência
        scanf("%u", &input_number);

        // Imprime na consola
        printf("%u:", input_number);
        number_of_prime_factors(input_number);

        n_elements--;
    }
}