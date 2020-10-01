#include <stdio.h>

#include "list.h"

#define VALORES 100000

int main(void)
{
    struct list* linked = list_new();

    for (int i = 2; i <= VALORES; i+=2)
        list_insert(linked, i);
    

    list_destroy(linked);
}