def ocorrencias(letra,string,indice):
    contador = 0

    for i in range(int(indice), len(string) + 1):
        if string[i:i + 1] == letra:
            contador += 1
        else:
            contador += 0

    if contador != 0:
        return contador
    else:
        return "Não existem vogais"

ocorrencias("a","casasa",2)