while True:

    # Constantes
    num = int(input("Insira um numero inteiro: "))
    copy_num = num
    fatorial = 1

    # Calculo Fatorial
    if num >= 0:
        if num == 0 or num == 1:
            fatorial = 0

        else:
            while num != 0:
                fatorial = fatorial * num
                num -= 1

        print("Fatorial de",copy_num,"é",fatorial)

    else:
        print("Não há fatorial")