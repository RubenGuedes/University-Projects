dicEnPt= {'white':'branco',
          'the':'o',
          'cat':'gato',
          'mouse':'rato',
          'chases':'persegue',
          'black':'preto'
          }

# Exercicio 6
def traduz(pal,dic):
    return dic.get(pal,pal)

# Exercicio 7
def palavra_para_portugues(pal):
    return traduz(pal,dicEnPt)

#Exercicio 8
def lista_para_portugues(lista_pals):
    lista = []
    for i in range(len(lista_pals)):
        lista.append(dicEnPt.get(lista_pals[i],'EMPTY'))
    return lista

# Exercicio 9
def frase_para_portugues(frase):
    if frase[-1]!= ' ':
        frase = frase + ' '

    antigo = 0
    frasE = ''
    lista = []

    for i in range(len(frase)):
        if frase[i].isspace():
            lista.append(frase[antigo:i])
            antigo = i+1
    new_list = lista_para_portugues(lista)

    for i in range(len(new_list)):
        frasE = frasE + new_list[i] + ' '

    return frasE

print(frase_para_portugues('the white black mouse'))