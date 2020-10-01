import math

while True:

    Cat_oposto = float(input("Qual o comprimento do cateto 1? "))
    Cat_adjacente = float(input("Qual o comprimento do cateto 2? "))

    if Cat_oposto < 0 or Cat_adjacente < 0:
        print("Erro, a distância tem que ter valores positivos")

    else:
        hipotenusa = math.sqrt(Cat_adjacente**2 + Cat_oposto**2)
        print("hipotenusa =", hipotenusa)
