#include <stdio.h>

int strwords(char str[])
{
    int index = 0,
        counter = 0;

    while (str[index] != '\0')
    {
        if ( str[index] != ' ' )
        {
            while ( str[index] != '\0' && str[index] != ' ' )
                index++;
            counter++;
        }
        else
            index++;   
    }
    return counter;
}

int main(void)
{
    char str[] = "  uma duas   quatro ";

    printf("A string '%s' tem %d palavras\n", str, strwords(str) );

    return 0;
}