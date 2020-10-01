def pascal(linha , coluna):
    if coluna == 0 or coluna == linha:
        return 1
    else:
        return pascal(linha-1, coluna-1) + pascal(linha-1, coluna)

def triangulo(linha):
    colunas = linha + 1
    if linha == 0:
        return str(pascal(linha-1, colunas-1)) + str(pascal(linha-1,colunas))
    else:
        return triangulo(linha-1)

print(triangulo(3))

"""
def triangulo_it(linhasN):
    colunas = 0
    linhas = 0
    while linhas != linhasN:
        while colunas != linhasN:
            print (str(pascal(linhas, colunas)) + " ")
            colunas += 1
        linhas += 1
        return pascal(linhas, colunas)
"""