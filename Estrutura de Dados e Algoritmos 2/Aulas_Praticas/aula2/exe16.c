#include <stdio.h>

int my_strlen(char vetor[])
{
    int index = 0,
        counter = 0;

    while ( vetor[index] != '\0' )
    {
        counter++;
        index++;
    }
    
    return counter;
}

int main(void)
{
    char str1[] = "123",
         str2[] = "uma maiorzita",
         str3[] = "uma string com \"outra string\" la\' dentro",
         str4[] = "\n",
         str5[] = "\\n",
         str6[] = "";

    printf("String '%s' tem comprimento = %d\n", str1, my_strlen(str1));
    printf("String '%s' tem comprimento = %d\n", str2, my_strlen(str2));
    printf("String '%s' tem comprimento = %d\n", str3, my_strlen(str3));
    printf("String '\\n' tem comprimento = %d\n",   my_strlen(str4));
    printf("String '\\\\n' tem comprimento = %d\n", my_strlen(str5));
    printf("String '%s' tem comprimento = %d\n", str6, my_strlen(str6));

    return 0;
}