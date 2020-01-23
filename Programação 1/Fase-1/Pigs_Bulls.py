import random
#------------------------------  FUNÇÕES  ------------------------------
#################################################################
# create_cod - Esta função cria um numero com 4 algarismo em que
#              todos os algarismos são diferentes entre si
# Argumentos:
#   None
# Valor de retorno:
#   Número aleatório com 4 algarismos em que todos os algarismos
#   são diferentes entre si
#################################################################
def create_cod():
    a = str(random.randint(0,9))
    b = str(random.randint(0,9))
    while a == b:
        b = str(random.randint(0,9))
    c = str(random.randint(0, 9))
    while a == c or b == c:
        c = str(random.randint(0,9))
    d = str(random.randint(0,9))
    while a == d or b == d or c == d:
        d = str(random.randint(0,9))
    return a+b+c+d
#################################################################
# armazena_fail - Função que adiciona as tentativas do jogador
#                 numa lista
# Argumentos:
#   inpuT - número inserido pelo jogador
#   listA - lista
# Valor de Retorno:
#   listA - lista com os valores inseridos pelo jogador guardados
#################################################################
def armazena_fail(inpuT,listA):
    listA = listA + [inpuT]
    return listA
#################################################################
# ver_posAlgarismo - Função que retorna uma dica sobre a posição
#                    dos algarismo dados pelo jogador fase ao nú-
#                    mero criado
# Argumentos:
#   cod_comp  - Código criado pelo computador ou pelo jogador.
#   cod_jogad - Código inserido pelo jogador para tentar adi-
#               vinhar a resposta.
#   lista     - Lista, inicialmente vazia, para armazenar as
#               dicas.
# Valor de Retorno:
#   lista     -  Lista que mostra quantos Touros acertou e/ou
#                quantos Pigs acertou
#################################################################
def ver_posAlgarismo(cod_comp,cod_jogad, lista):
    contadorT = 0
    contadorP = 0
    if cod_comp is cod_jogad:
        return ['4 Touro(s)', '0 Porco(s)']
    else:
        for i in range(len(str(cod_comp))):
            if cod_comp[i] == cod_jogad[i]:
                contadorT = contadorT + 1
            elif (cod_comp[i] != cod_comp[i]) or (cod_jogad[i] in cod_comp):
                contadorP = contadorP + 1
        lista.append( [str(contadorT)+' Touro',str(contadorP)+' Porcos'] )
        return lista
#################################################################
# add_AllDic - Função que adiciona as tentativas do jogador e as
#              dicas dadas pelo jogo num dicionário
# Argumentos:
#   numero - numero da tentativa, inteiro
#   fail   - lista com as tentativas do jogador
#   posAlg - lista com as dicas do jogo
#   dicio  - dicionário
# Valor de Retorno:
#   dicio  - dicionário com as tentivas do jogador e com as dicas
#            dadas pelo jogo
#################################################################
def add_AllDic(numero,fail,posAlg,dicio):
    dicio['T'+str(numero)] = (fail,posAlg)
    return dicio
#################################################################
# alg_diferente - verifica se os algarimos são diferentes
#
# Argumentos:
#   cod   - string, codigo inserido pelo jogador que vai adivinhar ou que vai criar um código
# Valor de Retorno:
#   True  - booleano, True se os algarismo forem diferentes
#   False - booleano, False se algum algarismo forem iguais
#################################################################
def alg_diferente(cod):
    if cod[0] == cod[1] or cod[0] == cod[2] or cod[0] == cod[3] or cod[1] == cod[2] or cod[1] == cod[3] or cod[2] == cod[3]:
        return False
    else:
        return True
#################################################################
# conf_numero - Funcão que confirma se todos os caracteres do
#               são números
# Argumentos
#   cod - string, código inserido pelo jogador
# Valor de Retorno
#   True - booleano, se todos os caracteres forem inteiros
#   False - booleano, se algum caracter não for número inteiro
#################################################################
def conf_numero(cod):
    contador = 0
    numeros = ('0','1','2','3','4','5','6','7','8','9')
    for i in range(len(cod)):
        if cod[i] in numeros:
            contador = contador + 1
    if contador == 4:
        return True
    else:
        return False
#------------------------------ FIM DAS FUNÇÔES ------------------------------
##############################################################################
################################## PROGRAMA ##################################
##############################################################################
while True:
    dicionario = {}
    lista_fail = []
    lista_posAlg = []

    print('\nSEJA BEM-VINDO\n\n'
          'Selecione uma das opções:\n'
          'A -> Jogar com o código gerado pelo computador\n'
          'B -> Jogar com o código gerado pelo jogador\n')
    opcao = str(input('Opção-> '))
    ############################################################################################
    ################################## COMPUTADOR VS JOGADOR ###################################
    ############################################################################################

    if opcao.lower() == 'a':
        codComputador = create_cod()
        print(codComputador)

        input_jogador = str(input('\nJogador insere um número com 4 algarismos para ver se acertas: '))
        while len(input_jogador) != 4  or alg_diferente(input_jogador) == False or conf_numero(input_jogador) == False :
            if len(input_jogador) != 4  or alg_diferente(input_jogador) == False or conf_numero(input_jogador) == False:
                input_jogador = str(input('\nERRO -> O número tem que ter 4 algarismos e estes não se podem repetir\n\n'
                                          'Jogador 2 volta a inserir um número com 4 algarismo: '))
        if codComputador == input_jogador:
            print('------------------------------------------------------\n'
                  'PARABÉNS ACERTASTE LOGO À PRIMEIRA COM O NUMERO %s\n'
                  '------------------------------------------------------' %(input_jogador))
        else:
            i = 0
            while codComputador != input_jogador:
                lista_fail = armazena_fail(input_jogador,lista_fail)
                ver_posAlgarismo(codComputador,input_jogador, lista_posAlg)
                add_AllDic(i, lista_fail[i], lista_posAlg[i],dicionario)

                print('lista de falhas => ',lista_fail,'\nNão acertou no número e tem',lista_posAlg[i])

                input_jogador = str(input('Insere outro numero com 4 algarismos: '))
                while len(input_jogador) != 4 or alg_diferente(input_jogador) == False or conf_numero(input_jogador) == False:
                    if len(input_jogador) != 4 or alg_diferente(input_jogador) == False or conf_numero(input_jogador) == False:
                        input_jogador = str(input('\nERRO -> O número tem que ter 4 algarismos e estes não se podem repetir\n\n'
                                                  'Jogador 2 volta a inserir um número com 4 algarismo: '))
                i += 1


            # Resultado
            print('------------------------------------------------------\n'
                  '------------------ TERMINOU O JOGO -------------------\n'
                  'As suas tentativas foram:\n\n'
                  ' Tentativa  | Código |      Dicas dadas\n'
                  '---------------------------------------------')
            for a in range(i):
                print('Tentativa_%s:' % (str(a)), dicionario['T' + str(a)])
            print('---------------------------------------------')

    ############################################################################################
    ################################### JOGADOR 1 VS JOGADOR 2 #################################
    ############################################################################################

    elif opcao.lower() == 'b':

        # Quem cria o código
        input_Criador = str(input('\nJogador 1 insere um número com 4 algarismos para ser adivinhado: '))
        while len(input_Criador) != 4 or alg_diferente(input_Criador) == False or conf_numero(input_Criador) == False :
            if len(input_Criador) != 4 or alg_diferente(input_Criador) == False or conf_numero(input_Criador) == False:
                input_Criador = str(input('\nERRO -> O número tem que ter 4 algarismos e estes não se podem repetir\n\n'
                                             'Jogador 1 volta a inserir um número com 4 algarismo: '))
        print(50*'\n'+"------------------------------------------------------------------")

        # Quem tenta adivinhar
        input_jogador = str(input('\nJogador insere um número com 4 algarismos para ver se acertas: '))
        while len(str(input_jogador)) != 4 or alg_diferente(input_jogador) == False or conf_numero(input_jogador) == False:
            if len(input_jogador) != 4 or alg_diferente(input_jogador) == False or conf_numero(input_jogador) == False:
                input_jogador = str(input('\nERRO -> O número tem que ter 4 algarismos e estes não se podem repetir \n\n'
                                          'Jogador 2 volta a inserir um número com 4 algarismo: '))

        if input_Criador == input_jogador:
            print('------------------------------------------------------\n'
                  'PARABÉNS ACERTASTE LOGO À PRIMEIRA COM O NUMERO %s\n'
                  '------------------------------------------------------' % (input_jogador))
        else:
            i = 0
            while input_Criador != input_jogador:
                lista_fail = armazena_fail(input_jogador, lista_fail)
                ver_posAlgarismo(input_Criador, input_jogador, lista_posAlg)
                add_AllDic(i, lista_fail[i], lista_posAlg[i], dicionario)

                print('\nlista de falhas => ', lista_fail, '\nNão acertou no número e tem', lista_posAlg[i],'\n')

                input_jogador = str(input('Insere outro numero com 4 algarismos: '))
                while len(input_jogador) != 4 or alg_diferente(input_jogador) == False or conf_numero(input_jogador) == False:
                    if len(input_jogador) != 4 or alg_diferente(input_jogador) == False or conf_numero(input_jogador) == False:
                        input_jogador = str(input('\nERRO -> O número tem que ter 4 algarismos e estes não se podem repetir\n\n'
                                                  'Jogador 2 volta a inserir um número com 4 algarismo: '))
                i += 1

            #Resultado
            print('------------------------------------------------------\n'
                  '------------------ TERMINOU O JOGO -------------------\n'
                  'As suas tentativas foram:\n\n'
                  ' Tentativa  | Código |      Dicas dadas\n'
                  '---------------------------------------------')
            for a in range(i):
                print('Tentativa_%s:'%(str(a)),dicionario['T'+str(a)])
            print('---------------------------------------------')

    ############################################################################################
    ####################################### FALHA NO INPUT #####################################
    ############################################################################################
    else:
        print('\nFim do Programa!\n')
        break