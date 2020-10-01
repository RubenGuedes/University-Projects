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

void my_strcpy(char dest[], char src[])
{
    int index = 0;

    while (src[index] != '\0')
    {
        dest[index] = src[index];
        index++;
    }
}

void my_strcat(char dest[], char src[])
{
    int last_index = my_strlen(dest),
        len_src = my_strlen(src);

    for (int i = 0; i < len_src; i++)
        dest[last_index++] = src[i];

    dest[last_index] = '\0';
}

int main(void)
{
    char str1[6] = "Hello",
         str1_a[6],
         str2[6] = "World",
         str2_a[20] = "Hello ";

    my_strcpy(str1_a, str1);
    my_strcat(str2_a, str2);

    printf("Original = '%s' || Copy = '%s'\n", str1, str1_a);
    printf("Cat = '%s'\n", str2_a);

    return 0;
}