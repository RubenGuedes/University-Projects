#include <stdio.h>
#include "header/pessoa.h"

int main(void)
{
  Pessoa *p = new_pessoa_args(1, 9000, 9001, 1000000, "esquerda", "maça");
  print_pessoa(p);
  destroy_pessoa(p);
}
