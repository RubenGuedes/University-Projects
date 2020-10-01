def conj_de_letras(str):
    lista = []
    for i in range(len(str)):
        if str[i] not in lista:
            lista.append(str[i])
    return lista

print(conj_de_letras('banana'))