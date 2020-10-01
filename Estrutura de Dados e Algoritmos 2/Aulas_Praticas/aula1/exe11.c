#include <stdio.h>

#define ARRAY_SIZE 40

char int_to_symbol(int val)
{
    char result = '\0';

    switch (val)
    {
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
            result = (char) (48 + val);
            break;
        case 10:
            result = 'A';
            break;
        case 11:
            result = 'B';
            break;
        case 12:
            result = 'C';
            break;
        case 13:
            result = 'D';
            break;
        case 14:
            result = 'E';
            break;
        case 15:
            result = 'F';
            break;
    }

    return result;
}

void to_hexa(int val)
{
    int resto,
        array[ARRAY_SIZE],
        index_array = -1;

    while (val > 0)
    {
        resto = val % 16;
        val /= 16;

        array[++index_array] = resto;
    }

    for (int i = index_array; i >= 0; i--)
        printf( "%c", int_to_symbol(array[i]) );
    printf("\n");
}

int main(void)
{
    to_hexa(1128);
    to_hexa(703198);
    to_hexa(14605018);
}