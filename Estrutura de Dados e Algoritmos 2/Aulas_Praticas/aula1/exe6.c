#include <stdio.h>

int main(void)
{
    int val;

    /**
     * Aqui o '\n' vai fazer com que se leia todos os caracteres brancos.
     * Por isso este scanner só vai parar quando se inserir um valor.
     */
    scanf("%d\n", &val);
    
    printf("%d\n", val);
}