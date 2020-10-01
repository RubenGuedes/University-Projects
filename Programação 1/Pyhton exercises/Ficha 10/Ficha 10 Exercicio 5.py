def soma_colunas(lista_1,lista_2,lista_3):
    new_lista = []

    # Ver qual é que tem maior numero de valores
    if len(lista_1)>= len(lista_2) and len(lista_1) >= len(lista_3):
        for i in range(len(lista_1)):
            a = int(lista_1[i])+ int(lista_2[i])+ int(lista_3[i])
            new_lista.append(a)

        return new_lista

    elif len(lista_2) >= len(lista_1) and len(lista_2) >= lista_3:
        for i in range(len(lista_2)):
            a = lista_1[i] + lista_2[i] + lista_3[i]
            new_lista.append(a)
        return new_lista

    elif len(lista_3) >= len(lista_1) and len(lista_3) >= lista_2:
        for i in range(len(lista_3)):
            a = lista_1[i] + lista_2[i] + lista_3[i]
            new_lista.append(a)
        return new_lista
print(soma_colunas([1,2,3],[3,4,5],[6,7,8]))

def soma_col(lista):
    new_lista = []
    for i in range(len(lista)):
        reserva = 0
        for a in range(len(lista)):
            a = lista[a][i]
            reserva = reserva+ a
        new_lista.append(reserva)

    return new_lista
print(soma_col([[1,2,3],[3,4,5],[6,7,8]]))
