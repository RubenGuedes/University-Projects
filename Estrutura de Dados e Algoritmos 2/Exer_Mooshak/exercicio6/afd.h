#include <stdlib.h>
#include <stdbool.h>

#define Q 100
#define F Q
#define S (26 + 1)

typedef struct afd 
{
    unsigned short qinicial,
                   transicoes[Q][S];
    bool aceites[F];
} AFD;


AFD *new_afd(unsigned short qi);
void free_afd(AFD *afd);

unsigned short transitar_estado(AFD *afd, unsigned short est_atual, unsigned short simb);
unsigned short get_qinit(AFD *afd);
bool acepted_state(AFD *afd, unsigned short state);