lista_tel= {'joao':961111, 'ana':932222, 'carla':913333, 'manuel':964444}

#Exercicio 1
def encontra_telefone(nome,l_tel):
    return l_tel.get(nome,0)

#Exercicio 2
def novo_tel(nome,tel,dic):
    dic[nome] = tel

#Exercicio 3
def cliente(tel,ltel):
    dic = {}
    lista1 = []
    lista2 = []

    for a in ltel.keys():
        lista1.append(a)
    for b in ltel.values():
        lista2.append(b)

    for i in range(len(lista2)):
        dic[lista2[i]] = lista1[i]
    return dic.get(tel,'Desconhecido')

#Exercicio 4
def mostra_lista(ltel):
    lista = []
    for i in ltel.items():
        lista.append(i)
    lista.sort()
    return lista

