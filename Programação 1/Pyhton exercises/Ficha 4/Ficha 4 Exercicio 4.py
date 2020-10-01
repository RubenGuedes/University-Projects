import math

def raiz1(a, b,c):
    "com valor positivo"
    resultado = ((-b)+math.sqrt(b**2 -4 *a*c))/(2*a)
    print(resultado)
    return resultado

def raiz2(a,b,c):
    "com valor negativo"
    resultado = ((-b)-math.sqrt(b**2 -4*a*c) ) / (2*a)
    print(resultado)
    return resultado

def main(a,b,c):
    raiz1(a,b,c)
    raiz2(a,b,c)

main(1,-2,1)