
def estrela_arvore(altura):
    #VARIAVEIS
    contador = 0
    stars = "*"
    copy_altura = altura - 1

    while altura != 0:
        val = (altura-1) * ' ' + (contador + 1) * stars
        altura -= 1
        contador += 2
        #print(contador)
        print(val)

    # Desenhar tronco
    print(copy_altura * ' '+ '*\n'+ copy_altura * ' ' +'*')

estrela_arvore(3)
