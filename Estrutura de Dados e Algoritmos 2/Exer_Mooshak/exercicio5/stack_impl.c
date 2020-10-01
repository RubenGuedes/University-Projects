#include <stdio.h>
#include <stdlib.h>

#include "stack.h"

typedef struct stack
{
    unsigned int size,
                 space_used;
    int *values;
} Stack;

Stack *new_stack(int size)
{
    Stack *stack = malloc(sizeof(Stack));
    stack->size = size;
    stack->space_used = 0;

    stack->values = malloc(sizeof(int) * size);

    return stack;
}
void push(Stack *stack, int value)
{
    if ( stack->size - 1 == stack->space_used  )
    {
        int *new_array = stack->values; 
        stack->values = realloc(new_array, sizeof(int) * stack->size * 2);
        stack->size *= 2;
    }

    stack->values[stack->space_used++] = value;
}
int pop( Stack *stack )
{
    return stack->values[--stack->space_used];
}
int top( Stack *stack )
{
    return stack->values[stack->space_used - 1];
}
bool empty_stack( Stack *stack )
{
    return stack->space_used == 0;
}
void destroy_stack( Stack *stack )
{
    free(stack->values);
    free(stack);
}