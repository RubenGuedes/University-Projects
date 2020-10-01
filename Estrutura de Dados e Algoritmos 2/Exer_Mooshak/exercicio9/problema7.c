#include <stdio.h>
#include <stdlib.h>

#define TEMAS 1000
#define TUTORES 1000 + 1

int min(int val1, int val2)
{
  if (val1 <= val2)
    return val1;

  return val2;
}

int menor_numero_desistentes(int m, int n, int tabela_input[m][n])
{
  static int t[TEMAS][TUTORES];

  // temas
  for (int i = m - 1; i >= 0; i--)
 
    // tutores
    for (int k = 0; k <= n; k++)
      t[i][k] = tabela_input[i][k] + t[i][n - k];
  
  int min = t[0][n];
  return min;
}

int main(void)
{
  int temas, tutores;
  scanf("%d %d", &temas, &tutores);

  int tabela[temas][tutores];

  // Preencher a tabela
  for (int linha = 0; linha < temas; linha++)

    for (int col = 0; col <= tutores; col++)
    {
      int desistentes;
      scanf("%d", &desistentes);
      tabela[linha][col] = desistentes;
    }

  // Encontrar o menor número de desistentes
  printf("%d\n", menor_numero_desistentes(temas, tutores, tabela));
}
