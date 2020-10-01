def verifica_ordem(lista):

    lista2 = lista[:]
    lista2.sort()
    """
    Atenção:
        -> lista2 = lista (Copia a posição de memoria de uma lista
                            para a outra lista, ou seja, modificando
                            uma lista também estamos a modificar a outra
                            automaticamente)
                            
        -> lista2 = lista[:] (Copia os conteudos da lista para a
                              lista2 mas não copia a posição de memoria
    """


    print('',lista,'\n',lista2)

    if lista != lista2:
        return False
    else:
        return True

print(verifica_ordem([1,3,2]))