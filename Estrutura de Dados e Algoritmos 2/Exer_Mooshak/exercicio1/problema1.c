#include <stdio.h>

unsigned char is_prime_number(unsigned int value)
{
    // Flag start with value 'False'
    unsigned char flag = 0;

    if (value == 0 || value == 1)
        return flag;

    for (unsigned int number = 2; number <= value; number++)

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

unsigned char number_of_prime_factors(unsigned int val)
{
    unsigned int aux_value;
    unsigned char counter = 0;

    while ( val != 1 )
    {
        // Encontra o menor factor primo de um certo valor
        aux_value = smallest_divisible_prime_number(val);

        // Conta o número de fatores primos
        if (aux_value != 0)
        {
            counter++;
            val /= aux_value;
        }
        else
            break;
    }

    return counter;
}

int main(void)
{
    unsigned int input_number;
    unsigned short n_elements;
    unsigned char prime_factors;

    // Número de elementos da sequência
    scanf("%hu", &n_elements);

    while (n_elements != 0)
    {
        // Elemento da sequência
        scanf("%u", &input_number);

        // Calcula o número de fatores primos e imprime na consola
        prime_factors = number_of_prime_factors(input_number);
        printf("%u: %d\n", input_number, prime_factors);
 
        n_elements--;
    }
}