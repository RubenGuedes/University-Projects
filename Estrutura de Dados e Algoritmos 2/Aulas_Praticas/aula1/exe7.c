#include <stdio.h>

int main(void)
{
    int val0, 
        val1;
    float real,
          coord0,
          coord1;

    // i)
    scanf("%d + %d =", &val0, &val1);

    // ii)
    printf("soma = %d\n", val0 + val1);

    // iii)
    scanf("%f", &real);

    // iv)
    printf("%f\n", real);

    // v)
    scanf(" (%f, %f)", &coord0, &coord1);

    // vi)
    printf("(%.3f, %.3f)\n", coord0, coord1);
}