def pascal(linha , coluna):
    if coluna == 0 or coluna == linha:
        return 1
    else:
        return pascal(linha-1, coluna-1) + pascal(linha-1, coluna)

print(pascal(4,2))