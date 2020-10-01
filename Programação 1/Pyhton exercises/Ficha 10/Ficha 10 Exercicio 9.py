x = int(input('Quantos tempos pretende inserir? '))
i = 1
listaA = []
while i != x+1:
    temp = float(input("Tempo do atleta "+str(i)+": "))
    listaA.append(temp)
    i += 1

def top3(lista):
    listaA.sort()
    lista.reverse()
    lista.pop(0)
    lista.sort()
    return lista

print(top3(listaA))