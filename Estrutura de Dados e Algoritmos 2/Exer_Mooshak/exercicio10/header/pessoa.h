#include <stdio.h>
#include <stdlib.h>

#define QUANT_TEL 2
#define POL 55 + 1
#define HAB 55 + 1

typedef struct pessoa Pessoa;

Pessoa *new_pessoa();
Pessoa *new_pessoa_args(int, int, int, int, char[POL], char[HAB]);
void destroy_pessoa(Pessoa *);

void set_id(Pessoa *, int);
int get_id(Pessoa *);

void set_contactos(Pessoa *, int, int);
int get_contacto_1(Pessoa *);
int get_contacto_2(Pessoa *);

void set_rendimentos(Pessoa *, int);
int get_rendimentos(Pessoa *);

void set_politica(Pessoa *, char[POL]);
char *get_politica(Pessoa *);

void set_consumo(Pessoa *, char[HAB]);
char *get_consumo(Pessoa *);

void print_pessoa(Pessoa *);
