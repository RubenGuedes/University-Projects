def e_primo(num):
    contador = 0
    if num <= 1:
        print('Não é número primo')
        return False

    else:
        for i in range(2,num):
            if num % i == 0:
                contador += 1
            else:
                contador = contador

        if contador==0:
            print('É número primo')
            return True
        else:
            print('Não é número primo')
            return False
e_primo(4)