#include <stdio.h>
#include "trie.h"

#define ANSWER_SIZE 13

void mystrcpy(char sourc[], char dest[])
{
    char *p_sourc = sourc;
    short ind = 0;

    while ( *p_sourc != '\0' )
    {
        dest[ind++] = *p_sourc;
        p_sourc++;
    }
}

int main(int argc, char const *argv[])
{
    struct trie *t = trie_new();
    
    char operation,
         word[WORD_SIZE];
    while (scanf("%c", &operation) != EOF)
    {
        switch (operation)
        {
            case 'q':
                printf("palavras conhecidas: %d\n", trie_count(t));
                break;

            case 'i':
                scanf("%s", word);
                trie_insert(t, word);
                break;

            case 'r':
                scanf("%s", word);
                trie_delete(t, word);
                break;

            case 'e':
                scanf("%s", word);

                char answer[ANSWER_SIZE];
                if (trie_find(t, word))
                {
                    mystrcpy("conhecida", answer);
                    answer[9] = '\0';
                }
                else
                {
                    mystrcpy("desconhecida", answer);
                    answer[12] = '\0';
                }

                printf("\"%s\" %s\n", word, answer);
                break;

            case 'c':
                scanf("%s", word);

                printf("palavras com prefixo \"%s\":\n", word);
                trie_print_completions(t, word);
                break;
        }   
    }
    trie_destroy(t);
    return 0;
}