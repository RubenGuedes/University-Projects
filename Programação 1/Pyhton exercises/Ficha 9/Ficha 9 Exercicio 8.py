def vogal(string):
    contador = 0

    for i in range(0,len(string)+1):
        if string[i:i+1] == "a" or string[i:i+1] == "e" or string[i:i+1] == "i" or string[i:i+1] == "o" or string[i:i+1] == "u":
            contador += 1
        else:
            contador += 0

    if contador != 0:
        return True
    else:
        return False

def conta_vogais(string):
    contador = 0

    for i in range(0,len(string)+1):
        if string[i:i+1] == "a"or string[i:i+1] == "e" or string[i:i+1] == "i" or string[i:i+1] == "o" or string[i:i+1] == "u":
            contador += 1
        else:
            contador += 0

    if contador != 0:
        return contador
    else:
        return "Não existem vogais"

x = "casa"
print(vogal(x))
print(conta_vogais(x))