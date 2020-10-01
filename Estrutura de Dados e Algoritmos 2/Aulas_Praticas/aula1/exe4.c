#include <stdio.h>

int biggest_value(int val0, int val1)
{
    if (val0 >= val1)
        return val0;
    else
        return val1;
}

int main(void)
{
    int val0, val1;

    scanf("%d %d", &val0, &val1);
    
    printf("%d\n", biggest_value(val0, val1));
}