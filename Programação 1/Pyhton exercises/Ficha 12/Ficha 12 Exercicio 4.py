posição = [('João',300,20),('Ana',80,15),('Patrícia',17,90)]
def coordenadas(pos,nome):
    for i in range(len(posição)):
        if nome == pos[i][0]:
            break
    return (pos[i][1],pos[i][2])

print(coordenadas(posição,'Ana'))