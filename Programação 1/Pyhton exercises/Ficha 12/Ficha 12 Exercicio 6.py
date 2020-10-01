posicao=[('joao',300,20),('ana',80,15),('patricia',17,90)]
def direita(pos,x):
    lista = []
    for i in range(len(pos)):
        if pos[i][1] > 100:
            lista.append(pos[i][0])
    return lista
print(direita(posicao,100))