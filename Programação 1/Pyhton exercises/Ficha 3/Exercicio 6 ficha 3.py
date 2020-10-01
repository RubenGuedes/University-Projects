import math


while True:

    epsilon = 0.0001
    x = int(input("Qual o valor de n? "))
    estimativa = int(input("Qual o valor da estimativa inicial? "))


    while True:
        raiz_Newton = (estimativa + x/estimativa)/2

        if abs(raiz_Newton - estimativa) < epsilon:
            break
        estimativa = raiz_Newton

    math_Python = math.sqrt(x)
    diferença = estimativa - math_Python

    print("Raiz_Newton =", raiz_Newton,"\nMath.sqrt =", math_Python, "\nDiferença =",diferença )