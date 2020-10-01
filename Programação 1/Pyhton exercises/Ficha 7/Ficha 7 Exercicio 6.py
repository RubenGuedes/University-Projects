def som_linhas(num_int):
    valor_linha = 1
    lk = ''


    while valor_linha != num_int + 1:
        # Construção linha
        arvore = lk + str(valor_linha)
        lk = arvore + ''

        # Avançar com o contador
        valor_linha += 1
        print(arvore)

som_linhas(25)