while True:

    armazena_valor = 0
    quant_valores = 0 # Exercicio 5 da ficha 3

    num = int(input("Qual o valor? "))

    while num != 0:
        armazena_valor += num
        quant_valores += 1 # Exercicio 5 da ficha 3
        num = int(input("Qual o valor? "))


    # Exercicio 4 da ficha 3
    print("A soma é",armazena_valor)


    # Exercicio 5 da ficha 3
    media = armazena_valor / quant_valores
    print("Foram introduzidos",quant_valores,"valores e a média é",media)