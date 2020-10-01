jogos=[("ana","rui"),("manuel","maria"),("rita","joel")]
resultados=[(2,0),(1,3),(1,1)]

def nome_vencedores(jog, res):
    lista = []
    for i in range(len(jog)):
        if res[i][0] > res[i][1]:
            lista.append(jog[i][0])
        elif res[i][0] < res[i][1]:
            lista.append(jog[i][1])
        elif res[i][0] == res[i][1]:
            lista.append('EMPATE')
    return lista

listaV = nome_vencedores(jogos,resultados)
print(listaV)