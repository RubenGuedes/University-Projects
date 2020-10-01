def create_matrizXY(x,y):
    matriz = []
    for i in range(y):
        matriz.append(colunas(x))
    return matriz

def colunas(num):
    matriz_vazia = []
    for i in range(num):
        matriz_vazia.append([])

    return matriz_vazia

print(create_matrizXY(2,5))