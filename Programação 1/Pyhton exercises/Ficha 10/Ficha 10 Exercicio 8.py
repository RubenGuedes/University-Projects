def descodifica(pals,ordem):

    if len(pals) != len(ordem):
        return 'Error'
    else:
        Frase = ''
        for i in range(len(pals)):
            # Posição dos numeros
            Frase = Frase + pals[ordem.index(i)] +' '

        return Frase

print(descodifica(['exemplo','isto um','pode','ser'],[4,0,3,1,2]))