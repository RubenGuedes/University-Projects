#include <stdbool.h>

typedef struct stack Stack;

Stack *new_stack(int size);
void push( Stack *stack, int value );
int pop( Stack *stack );
int top(Stack *stack);
bool empty_stack( Stack *stack );
void destroy_stack(Stack *stack);