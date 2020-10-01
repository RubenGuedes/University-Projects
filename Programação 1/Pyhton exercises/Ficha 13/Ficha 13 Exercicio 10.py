adeptos={'benfica':['joao','ana','carla'],
         'sporting':['hugo','patricia'],
         'porto':['jose']}

def consulta_clube(nome,d):
    lista1 = []
    lista2 = []

    for a in d.keys():
        lista1.append(a)
    for b in d.values():
        lista2.append(b)
    print(lista1,'\n',lista2)
    for i in range(len(lista1)):
        if nome in lista2[i]:
            return lista1[i]


def mais_adeptos(d):
    lista = []
    for i in adeptos.values():
        lista.append(len(i))
    a = i
    lista.sort()
    lista.reverse()
    return lista[0]