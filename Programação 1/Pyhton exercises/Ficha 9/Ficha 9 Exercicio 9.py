def ocorrencias(letra,string):
    contador = 0

    for i in range(0, len(string) + 1):
        if string[i:i + 1] == letra:
            contador += 1
        else:
            contador += 0

    if contador != 0:
        return contador
    else:
        return "Não existem vogais"

print(ocorrencias("a","casa"))