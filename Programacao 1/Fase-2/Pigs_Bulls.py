import random,datetime
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
    a = []
    codigo = ''
    # Guarda na lista
    while len(a) != 4:
        i = str(random.randint(0, 9))
        while i not in a:
            a.append(i)
    # Passa o que está na lista para string
    for b in range(len(a)):
        codigo = codigo + a[b]
    return codigo

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

#################################################################
# allTypesCod - Função que cria todos os códigos possíveis
# Argumentos:
#   None
# Valor de Retorno:
#   lista - lista de em que os seus elementos são listas em que
#           cada elemento dessa lista é um caracter do código
#################################################################
def allTypesCod():
    lista = []
    listamain = []

    while len(lista) != (10*9*8*7):
        while len(listamain) != 4:
            i = str(random.randint(0, 9))
            while i not in listamain:
                listamain.append(i)
        if listamain not in lista:
            lista.append(listamain[:])
        listamain = []
    return lista

#################################################################
# listTOstr - Converte uma lista para string
# Argumentos:
#   lista - list, input dado pelo computador
# Valor de Retorno:
#   cod - str, conversão de lista para string
#################################################################
def strTOlist(lista):
    string = ''
    for i in range(len(lista)):
        string = string + lista[i]
    return string

#################################################################
# combinações - função que servirá para reduzir o tamanho da lista
#               original
# Argumentos:
#   somaTP - int, soma dos porcos com os touros
#   CodComp - list, codigo do computador que está na lista com todos os códigos
#   listALL - list, lista com todos os códigos possiveis
# Valor de Retorno:
#   lista_cod - list, lista com todos os códigos depois da avaliação do jogador
#################################################################
def combinações(touro, porco , CodComp,listALL):
    somaTP = touro + porco
    lista_cod = []
    if somaTP == 0:
        for Ind_listAll in range(len(listALL)):
            contador = 0
            for i in range(len(CodComp)):
                if CodComp[i] not in listALL[Ind_listAll]:
                    contador += 1
            if contador == 4:
                lista_cod.append(listALL[Ind_listAll])

    elif somatp != 0:
        if touro == 0:
            for Ind_listAll in range(len(listALL)):
                contador = 0
                for i in range(len(CodComp)):
                    if CodComp[i] not in listALL[Ind_listAll][i]:
                        contador += 1
                if contador == 4 :
                    lista_cod.append(listALL[Ind_listAll])
            if CodComp in lista_cod:
                lista_cod.remove(CodComp)

        elif touro != 0:
            for Ind_listAll in range(len(listALL)):
                contador = 0
                for i in range(len(CodComp)):
                    if CodComp[i] in listALL[Ind_listAll][i]:
                        contador += 1
                if contador == touro :
                    lista_cod.append(listALL[Ind_listAll])

            if CodComp in lista_cod:
                lista_cod.remove(CodComp)

    return lista_cod

###############################################################
#------------------------------ FIM DAS FUNÇÔES ------------------------------
##############################################################################
################################## PROGRAMA ##################################
##############################################################################
while True:
    dicionario = {}
    compCod = []
    lista_fail = []
    lista_posAlg = []
    lista_AllCod = []

    ############_MENU_############
    print('\nSEJA BEM-VINDO\n\n'
          'Selecione uma das opções:\n'
          'A -> Jogador VS Computador\n'
          'B -> Computador VS Jogador\n')
    opcao = str(input('Opção-> '))

    ############################################################################################
    ################################## JOGADOR VS COMPUTADOR ###################################
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
    ################################### COMPUTADOR VS JOGADOR ##################################
    ############################################################################################
    elif opcao.lower() == 'b':
        lista_AllCod = allTypesCod()
        touros = 0
        porcos = 0
        tentativas = 0

        # O jogador insere o código
        jogCod = str(input('\nJogador insere um número com 4 algarismos para ser adivinhado pelo computador: '))
        while len(jogCod) != 4 or alg_diferente(jogCod) == False or conf_numero(jogCod) == False :
            if len(jogCod) != 4 or alg_diferente(jogCod) == False or conf_numero(jogCod) == False:
                jogCod = str(input('\nERRO -> O número tem que ter 4 algarismos e estes não se podem repetir\n\n'
                                             'Jogador volta a inserir um número com 4 algarismo: '))
        # O computador introduz o código
        CodComp = lista_AllCod[0]
        print('\nInput do Jogador:   ',jogCod,
              '\nInput do Computador:',strTOlist(CodComp),'\n')

        # Avaliação do jogador
        touros = int(input('Avalia a quantidade de touros: '))
        porcos = 0
        if touros != 4:
            porcos = int(input('Avalia a quantidade de porcos: '))
        somatp = porcos + touros
        while somatp > 4 or somatp < 0 or touros <0 or touros > 4 or porcos <0 or porcos > 4 :
            print('\nA quantidade de touros e/ou de porcos não está correta.'
                  '\nInsira novamente:\n')
            touros = int(input('Avalia a quantidade de touros: '))
            if touros != 4:
                porcos = int(input('Avalia a quantidade de porcos: '))
            somatp = porcos + touros


        if touros == 4:
            print('------------------------------------------------------\n'
                  'O COMPUTADOR ACERTOU LOGO À PRIMEIRA COM O NÚMERO %s\n'
                  '------------------------------------------------------' %(CodComp))
        else:
            while touros != 4:

                lista_fail = armazena_fail( strTOlist(CodComp), lista_fail )
                lista_posAlg.append( [str(touros) + ' Touro(s)', str(porcos) + ' Porco(s)'] )
                add_AllDic( tentativas, lista_fail[tentativas], lista_posAlg[tentativas], dicionario )
                tentativas = tentativas + 1

                # Apagar elementos da lista com todos os códigos
                lista_AllCod = combinações(touros,porcos,CodComp,lista_AllCod)

                CodComp = lista_AllCod[0]
                print('\nInput do Jogador:   ', jogCod,
                      '\nInput do Computador:', strTOlist(CodComp), '\n')

                # Avaliação do jogador
                touros = int(input('Avalia a quantidade de touros: '))
                porcos = 0
                if touros != 4:
                    porcos = int(input('Avalia a quantidade de porcos: '))

                somatp = touros + porcos
                while somatp > 4 or somatp < 0 or touros < 0 or touros > 4 or porcos <0 or porcos  > 4:
                    print('\nA quantidade de touros e/ou de porcos não está correta.'
                          '\nInsira novamente:\n')
                    touros = int(input('Avalia a quantidade de touros: '))
                    porcos = int(input('Avalia a quantidade de porcos: '))
                    somatp = touros + porcos

            # RESULTADO
            print('--------------------------------------------------------\n'
                  '------------------ TERMINOU O JOGO ---------------------\n'
                  'As tentativas foram:\n\n'
                  ' Tentativa  | Código |      Dicas dadas\n'
                  '---------------------------------------------------')
            for a in range(tentativas):
                print('Tentativa_%s:' % (str(a)), dicionario['T' + str(a)])
            print('-----------------------------------------------------')

    ############################################################################################
    ####################################### FALHA NO INPUT #####################################
    ############################################################################################
    else:
        print('\nFim do Programa!\n')
        break