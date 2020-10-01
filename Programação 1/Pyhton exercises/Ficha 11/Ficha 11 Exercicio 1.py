def count_method(lista,elemento):
    contador = 0
    for i in range(len(lista)):
        if lista[i] == elemento:
            contador += 1
        else:
            contador += 0

    return contador

def in_method(lista,elemento):
    for i in range(len(lista)):
        if lista[i] == elemento:
            return True
            break
        else:
            continue
    return False

def reverse_method(lista):
    lista_rev = []
    for i in range(1,len(lista)+1):
        lista_rev.append(lista[-i])
    return lista_rev

def index_method(lista,elemento):
    index = 0
    i = 0
    while lista[i] != elemento:
        i += 1
        index += 1
    return index


def insert_method(lista,elemento):
    return lista + [elemento]
