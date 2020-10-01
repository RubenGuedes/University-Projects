def matriz_identidade(lista_id):

    cont = 0
    for i in range(len(lista_id)):
        if lista_id[i][i] == 1:
            cont += 1
        else:
            cont += 0

        for a in range(len(lista_id)):
            if a != i and lista_id[i][a] == 0:
                cont += 1
            else:
                cont += 0

    if cont == len(lista_id)**2:
        return True
    else:
        return False

print(matriz_identidade([[1, 0, 0], [2, 1, 0], [0, 0, 1]]))
