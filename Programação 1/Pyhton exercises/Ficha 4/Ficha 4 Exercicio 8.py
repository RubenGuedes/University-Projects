def somacubos(inteiro):

    cent = int(inteiro/100)
    dec = int((inteiro - (cent*100)) /10)
    uni = inteiro - (cent*100) - (dec *10)

    soma_cubos = cent**3 + dec**3 + uni**3

    if inteiro == soma_cubos:
        print("A soma do cubo dos algarismo de", inteiro, "é igual a", inteiro)
        return True
    print("A soma do cubo dos algarismo de", inteiro, "não é igual a", inteiro)
    return False
somacubos(157)