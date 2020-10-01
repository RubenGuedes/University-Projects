lista = []
while True:

    print('Indique a sua opção:\nAdicionar nome(1), Consultar lista(2), Retirar os primeiros n(3), Sair(0)')
    opcao = int(input('=> '))

    if opcao == 0:
        print('Fim da Execução!')
        break
    elif opcao == 1:
        nome = str(input('Insira o nome: '))
        lista.append(nome)
    elif opcao == 2:
        print(lista)
    elif opcao == 3:
        i = 0
        lugares = int(input('Insira o numero de lugares a eliminar: '))

        while i != lugares:
            lista.pop(0)
            i += 1

        print('Lista:', lista)



