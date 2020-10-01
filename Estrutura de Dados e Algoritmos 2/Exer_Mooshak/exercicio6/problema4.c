#include <stdio.h>
#include <stdlib.h>

#include "afd.h"

#define N 21 // Quantidade de estados máximo de um AFD
#define K (128 + 1) // Número de simbolos de uma palavra
#define ASCII_LOWER_A 97 // ASCII do "a"

unsigned short uchar_to_value(unsigned short c)
{
    return c - ASCII_LOWER_A;
}

/**
 * Registar as transições de um estado
 */
void guardar_estados(unsigned short max, unsigned short size, unsigned short estados[size])
{
    for (unsigned short i = 0; i < max; i++)
    {
        unsigned short est;
        scanf("%hu", &est);
        estados[i] = est;
    }
}

int main(void)
{   
    AFD *automatos[N];
    unsigned short size_autos;
 
    /**
     * Este ciclo while vai definir o problema
     */
    // Número de AFDs
    unsigned short n;
    scanf("%hu", &n);
    
    for (size_autos = 0; size_autos < n; size_autos++)
    {
        unsigned short q, // Número de estados de um AFD
                       f, // Número de estados de aceitação
                       s, // Número de simbolos do alfabeto
                       qi; // Estado inicial

        // Descrição de um AFD
        scanf("%hu %hu %hu", &q, &s, &qi);

        // Quantidade de estados de aceitação
        scanf("%hu", &f);

        // Guardar no array de automatos
        automatos[size_autos] = new_afd(qi);
        AFD *afd = automatos[size_autos];

        // Guardar estados aceites
        for (unsigned short i = 0; i < f; i++)
        {
            unsigned short est;
            scanf("%hu", &est);
            afd->aceites[est] = true;
        }

        // Transições [0 - Q-ésima] estados
        for (unsigned short i = 0; i < q; i++)
            // Guarda todos as transições de um estado
            guardar_estados(s, (s+1), afd->transicoes[i]);
    }

    /**
     * Este ciclo vai verificar se os automatos são aceites
     */
    unsigned short index;
    unsigned char palavra[K];

    while (scanf("%hu %s", &index, palavra) != EOF)
    {
        AFD *aut = automatos[index];
        unsigned short estado_act = get_qinit(aut);

        // Aplicar o algoritmo para ver se é aceite
        unsigned char *p_palavra = palavra;
        while (*p_palavra != '$')
        {
            unsigned short value = uchar_to_value(*p_palavra);
            estado_act = transitar_estado(aut, estado_act, value);

            p_palavra++;
        }
        
        // Se a palavra é aceite
        if (*p_palavra == '$' && acepted_state(aut, estado_act))
        {
            *p_palavra = '\0';
            printf("\"%s\" aceite\n", palavra);
        }
        else
        {
            while (*p_palavra != '$')
                p_palavra++;

            *p_palavra = '\0';
            printf("\"%s\" rejeitada\n", palavra);
        }
    }

    // Libertar o espaço em memória
    for (unsigned short i = 0; i < size_autos; i++)
        free_afd(automatos[i]);
    
    return 0;
}