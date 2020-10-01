def consulta_preco(artigo, tabela):
    i = 0
    while artigo[0] != tabela[i][0]:
        i += 1
    return tabela[i][1]

def artigo_mais_caro(tabela):
    new_list = []

    for i in range(len(tabela)):
        new_list.append(tabela[i][1])
    new_list.sort()
    new_list.reverse()

    I = 0
    while tabela[I][1] != new_list[0]:
        I += 1
    print("Preço do artigo mais caro é",tabela[I][1])
    return tabela[I][0]



#print(consulta_preco(['jarro',20.6],[['jarro',20.6],['almofada',18],['candeeiro',45]]))
print(artigo_mais_caro([['jarro',20.6],['almofada',18],['candeeiro',45]]))
