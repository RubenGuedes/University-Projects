#include <stdio.h>


int mult_10(int n)
{
    int result = 1;

    for (int i = 0; i < n; i++)
    {
        result *= 10;
    }
    
    return result;
}

int bin_representation(int val)
{
    char flag = 0;
    int resto,
        n_ciclos = 0,
        resultado = 0;


    if (val < 0) 
    {
        flag = 1;
        val *= -1;
    }

    while ( val > 1 )
    {
        resto = val % 2;
        resultado += (resto * mult_10(n_ciclos)); 

        n_ciclos++;
        val /= 2;
    }
    
    resto = val % 2;
    resultado += (resto * mult_10(n_ciclos)); 

    return (!flag) ? resultado : -resultado;
}

int main(void)
{
    int val1 = 170,
        val2 = -val1;
    
    printf( "%9d\n", bin_representation(val1));
    printf( "%9d\n", bin_representation(val2));
}