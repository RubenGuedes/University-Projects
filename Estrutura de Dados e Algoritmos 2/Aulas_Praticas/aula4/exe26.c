#include <stdio.h>

#define QUANT 10
#define SHORT_SIZE sizeof(short)

FILE *open_file(char *file)
{
    FILE *fd = fopen(file, "w+");

    // Se o ficheiro está vazio
    if ( fseek(fd, SHORT_SIZE, SEEK_SET) == 0 )
    {
        short zero =  0;
        for (short i = 0; i < QUANT; i++)
            fwrite( &zero, SHORT_SIZE, 1, fd );
    }

    return fd;
}

// Só no fim é que se fecha o ficheiro
void close_file(FILE *fd)
{
    fclose(fd);
}

// funcao que le o n-essimo inteiro
short get_value(FILE *fd, short pos)
{
    short result;

    fseek(fd, SHORT_SIZE * (pos-1), SEEK_SET);
    fread(&result, SHORT_SIZE, 1, fd);

    return result;
}

// funcao que regista o novo valor
void store_value(FILE *fd, short value, short pos)
{
    fseek(fd, SHORT_SIZE * (pos-1), SEEK_SET);
    fwrite( &value, SHORT_SIZE, 1, fd );
}

// Print do conteúdo do ficheiro
void print_content(FILE *fd)
{
    short value;
    fseek(fd, 0, SEEK_SET);

    for (short i = 0; i < QUANT; i++)
    {
        fread(&value, SHORT_SIZE, 1, fd);
        printf("%d ", value);
    }
    printf("\n");
}

int main(void)
{
    FILE *fd = open_file("zeros");

    char option;

    do
    {
        short value, position;
    
        scanf("%c", &option);

        switch (option)
        {
            case '?':
                scanf("%hd", &position);
                printf("%d\n", get_value(fd, position));
                break;
        
            case '+':
                scanf("%hd", &position);
                scanf("%hd", &value);
                store_value(fd, value, position);
                break;
        
            case 'r':
                scanf("%hd", &position);
                store_value(fd, 0, position);
                break;

            case 'p':
                print_content(fd);
                break;
        }
    } while (option != 'q');
    

    close_file(fd);
    return 0;
}