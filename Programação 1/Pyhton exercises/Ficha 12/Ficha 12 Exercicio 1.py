tuplo = (('Feijão',2),('Carne',4),('Pudim',3))

def consulta_preco(artigo,tabela):
    for i in range(len(tabela)):
        if artigo == tabela[i][0]:
            return tabela[i][1]

print(consulta_preco('Pudim',tuplo))

def artigo_mais_caro(tabela):

    max_val = 0
    nome_max = ''
    for i in range(len(tabela)):
        nome, preco = tabela[i]
        if preco > max_val:
            max_val = preco
            nome_max = nome
    print("Preço do artigo mais caro é %.2f:" %(max_val))
    return nome_max

print(artigo_mais_caro((('Jarro',10.6),('Almofada',18),('Candeeiro',45))))