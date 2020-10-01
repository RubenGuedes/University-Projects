#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#include "stack.h"

#define MAX_SYMB 1000
#define STACK_SIZE 30
#define ZERO_CHAR 48

// Convert char to int
int char_to_int( char val )
{
    return val - ZERO_CHAR;
}

bool opertation(Stack *stack, char *elem)
{
    bool div_zero = false;
    int val2 = pop(stack),
        val1 = pop(stack),
        result;

    switch (*elem)
    {
        case '+':
            result = val1 + val2;
            break;
        case '-':
            result = val1 - val2;
            break;
        case '*':
            result = val1 * val2;
            break;
        case '/':
            if (val2 == 0)
            {
                div_zero = true;
                printf("divisao por 0\n");
            }
            else
                result = val1 / val2;
            break;
    }
    push(stack, result);
    return div_zero;
}

void calc(Stack *stack, char *input)
{
    char *elem = input;
    bool div_zero = false;

    while (*elem != '\0')
    {
        int value0;

        switch (*elem)
        {
            case '+':
            case '-':
            case '*':
            case '/':
                div_zero = opertation(stack, elem);
                break;
            case '~':
                value0 = pop(stack);
                value0 *= -1;
                push(stack, value0);
                break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                value0 = char_to_int(*elem);
                push(stack, value0);
                break;
        }
        if (div_zero)
            break;
        
        elem++;
    }
    if (!div_zero)
        printf("%d\n", top(stack));
}

int main(void)
{
    Stack *s;
    char *inp = malloc( sizeof(char) * MAX_SYMB );

    while (scanf("%s", inp) != EOF)
    {
        s = new_stack(STACK_SIZE);
        
        calc(s, inp);
        
        destroy_stack(s);
    }
    free(inp);
    return 0;
}
