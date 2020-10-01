prod_price = [['Feijão',2],['Arroz',1.7],['Massa',.4],['Pudim',5.7]]
copy_prodPri = []
for nome in prod_price:
    copy_prodPri.append(nome[0])
lista = []
copy_lista = []

def add_prod(nome_P,quant):
    # Atualização dos produtos que estão no carrinho
    for prod in lista:
        copy_lista.append(prod[0])

    if copy_lista.count(nome_P) != 0:
        adição = quant + lista[copy_lista.index(nome_P)][1]
        lista[copy_lista.index(nome_P)].pop(1)
        lista[copy_lista.index(nome_P)].append(adição)
    else:
        lista.append([nome_P,quant])

    return lista

def remove(nome_P):
    i = 0
    while nome_P != lista[i][0]:
        i += 1
    return lista.pop(i)

def pagamento():
    valor = 0
    i = 0
    for obj in lista:
        copy_lista.append(obj[0])
    for y in lista:
        valor = valor + (lista[i][1]*prod_price[copy_prodPri.index(y[0])][1])
        i += 1
    return round(valor,2)

while True:
    print('Produtos e seus valores: ',prod_price,'\nIndique a sua opção:\nAdicionar produto(1), Remover todas as unidades com o nome indicado(2), Valor Total(3), Sair(0)')
    opcao = int(input('=> '))
    if opcao == 0:
        print('Fim da Execução!')
        break
    elif opcao == 1:
        prod = str(input('Qual é o produto a adicionar no carrinho? '))
        quant = int(input('Qual a quantidade que vai colocar no carrinho? '))
        add_prod(prod,quant)
        print(lista)
    elif opcao == 2:
        prod = str(input('Qual é o produto a remover do carrinho? '))
        remove(prod)
        print(lista)
    elif opcao == 3:
        print('Total= ',pagamento(),'€')
