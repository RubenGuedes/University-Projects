#include <stdio.h>

void troca(int *val1, int *val2)
{
    int aux = *val1;
    *val1 = *val2;
    *val2 = aux;
}

int main(void)
{
  int a, b;

  // lê dois valores inteiros
  printf("insira dois inteiros: "); 
  scanf("%d %d", &a, &b);

  // mostra-os
  printf("antes da troca: a = %d, b = %d\n", a, b);

  // troca os valores das variáveis
  troca(&a, &b);	

  // mostra os valores depois da troca
  printf("depois da troca: a = %d, b = %d\n", a, b);

  return 0;
}
