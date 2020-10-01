def conta_elementos(lista1,lista2):
    cont = 0

    for i in range(len(lista1)):
        if lista2.count(lista1[i]) != 0:
            cont += 1
        else:
            cont += 0

    return cont

print(conta_elementos([1,2,4,5],[4,4]))