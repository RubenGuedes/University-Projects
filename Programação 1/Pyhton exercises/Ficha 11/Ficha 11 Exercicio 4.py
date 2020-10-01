def str_list(string):
    lista = []
    i = 0
    while i != len(string):
        if string[i] != ',':
            lista.append(string[i])
            i += 1
        else:
            i += 1
    return lista


def ordenar(lista):
    lista_copy = lista[:]
    for i in range(1,len(lista_copy)):
        if str(lista_copy[i-1]) < str(lista_copy[i]):
            None
        else:
            copi_valor = lista_copy[i-1]
            lista_copy[i-1] = lista_copy[i]
            lista_copy[i] = copi_valor
    lista = lista_copy
    return lista

print(ordenar(['z', 'n', 't', 'h', 'b', 'a']))
