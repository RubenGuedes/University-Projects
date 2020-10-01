#include <stdio.h>

int minimum(int val1, int val2)
{
    if ( val1 <= val2 )
        return val1;
    
    return val2;   
}

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

int my_strcmp(char str1[], char str2[])
{
    int len_str1 = my_strlen(str1),
        len_str2 = my_strlen(str2),
        min = minimum(len_str1, len_str2);

    for (int i = 0; i < min; i++)
    {
        if (str1[i] > str2[i])
            return 1;
        else if (str1[i] < str2[i])
            return -1;
    }

    if (len_str1 == len_str2)
        return 0;
    else if (len_str1 < len_str2)
        return -1;
    else
        return 1;
}

int main(void)
{
    char str1[] = "Hello",
         str2[] = "Hello";

    printf("Comparação entre '%s' e '%s' é %d\n", str1, str2, my_strcmp(str1, str2));

    return 0;
}
