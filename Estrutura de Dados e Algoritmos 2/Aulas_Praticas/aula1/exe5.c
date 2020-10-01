#include <stdio.h>

int main(void)
{
    int n, val;

    scanf("%d", &n);

    for (val = 1 ; val < n; val++)
        printf("%d ", val);
    printf("%d\n", val);
}