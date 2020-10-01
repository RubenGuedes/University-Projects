#include "afd.h"

AFD *new_afd(unsigned short qi)
{
    AFD *afd = malloc(sizeof(AFD));
    afd->qinicial = qi;
        
    return afd;
}

unsigned short transitar_estado(AFD *afd, unsigned short est_atual, unsigned short simb)
{
    return afd->transicoes[est_atual][simb];
}

void free_afd(AFD *afd)
{
    free(afd);
}

bool acepted_state(AFD *afd, unsigned short state)
{ 
    return afd->aceites[state];
}

unsigned short get_qinit(AFD *afd)
{
    return afd->qinicial;
}