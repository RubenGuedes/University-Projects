#include "header/pessoa.h"

struct pessoa
{
  int id,
      contactos[QUANT_TEL],
      rendimentos;
  char politica[POL],
       consumo[HAB];
};

void mystrcopy(char *src, char *dest)
{
  while (*src != '\0')
    {
      *dest = *src;

      dest++;
      src++;
    }

  *dest = '\0';
}

Pessoa *new_pessoa()
{
  Pessoa *p = malloc(sizeof(Pessoa));
  return p;
}

Pessoa *new_pessoa_args(int id, int tel1, int tel2, int rend, char poli[POL], char cons[HAB])
{
  Pessoa *p = malloc(sizeof(Pessoa));

  set_id(p, id);
  set_contactos(p, tel1, tel2);
  set_rendimentos(p, rend);
  set_politica(p, poli);
  set_consumo(p, cons);

  return p;
}

void destroy_pessoa(Pessoa *p)
{
  free(p);
}

void set_id(Pessoa * p, int id)
{
  p->id = id;
}

int get_id(Pessoa *p)
{
  return p->id;
}

void set_contactos(Pessoa *p, int tel1, int tel2)
{
  p->contactos[0] = tel1;
  p->contactos[1] = tel2;
}

int get_contacto_1(Pessoa * p)
{
  return p->contactos[0];
}

int get_contacto_2(Pessoa * p)
{
  return p->contactos[2];
}

void set_rendimentos(Pessoa *p, int rend)
{
  p->rendimentos = rend;
}

int get_rendimentos(Pessoa *p)
{
  return p->rendimentos;
}

void set_politica(Pessoa *p, char polit[POL])
{
  mystrcopy(polit, p->politica);
}

char *get_politica(Pessoa *p)
{
  return p->politica;
}

void set_consumo(Pessoa *p, char cons[HAB])
{
  mystrcopy(cons, p->consumo);
}

char *get_consumo(Pessoa *p)
{
  return p->consumo;
}

void print_pessoa(Pessoa *p)
{
  printf(
	 "%d %d\n%d\n%s\n%s\n",
	 get_contacto_1(p),
	 get_contacto_2(p),
	 get_rendimentos(p),
	 get_politica(p),
	 get_consumo(p)
	 );
}
