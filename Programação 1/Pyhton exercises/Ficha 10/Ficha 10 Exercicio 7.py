def combina(lista1,lista2):
    ba = ''
    for a in range(len(lista1)):
        for b in range(len(lista1)):
            text = lista1[a]+' '+lista2[b]+'\n'
            ba = ba + text

    return ba

print(combina(['um','pequeno'],['teste','exemplo']))