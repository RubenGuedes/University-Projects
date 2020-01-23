import json

logged = False

def readFile_Dict(namefile = "names1"):
     fil = open(namefile + ".txt", "r+")
     
     if fil.read() == "":
          fil.write("{}")
     fil.close()

     file_object = json.loads(open( namefile + ".txt").read())

     # Se o ficheiro estiver vazio cria um dicionario vazio
     if file_object is "":
        file_object = {}
     # Caso contrário vai buscar o conteudo ao ficheiro
     
     return file_object

def writeDict_File(dictio, name_file = "names1"):
     f = open(name_file + ".txt", "w")
     dictio_string = json.dumps(dictio)
     f.writelines(dictio_string)
     f.close()

def logIn(dicionario,user):
    global logged

    if user in dicionario:
        while not logged:
            senha = input("digite a sua senha -> ")

            if senha in dicionario[user]:
                logged = True
                print("está logado")
        

    elif user not in dicionario:
        senha = input("Coloque uma nova senha -> ")
        dicionario[user] = [senha,{}]
        logged = True
        writeDict_File(dicionario)
        print("Conta Criada com sucesso!!!\nEstás logado")
    return dicionario

def comandos():
    global logged
    file = readFile_Dict()
    user = input(str("Digite o seu nome de Utilizador -> "))
    dicionario = logIn(file, user)
    
    while logged:
        comando = input(str("Digite o que quer saber -> "))
        comando = comando.split(" ")
        
        if len(comando) > 1:
            if (comando[0]+comando[1]) == "getphone-set":
                if comando[2] in dicionario[user][1]:
                    dicionario1 = dicionario[user][1]
                  
                    dicionario1[comando[2]].append(comando[3])
                    dicionario[user][1]= dicionario1
                    writeDict_File(dicionario)
                    print("adicionado com sucesso")
                else:
                    dicionario1 = dicionario[user]
                    dicionario1[1][comando[2]] = [comando[3]]
                    writeDict_File(dicionario)
                    print("adicionado com sucesso")
            
            elif comando[0] == "getphone" and (ord(comando[1][0]) < 48 or ord(comando[1][0]) > 57) and comando[1][0] != "-":
                
                dicionario1 = dicionario[user][1]

                if comando[1] not in dicionario1:
                    print("Not Found!!")
                else:
                    dicionario1 = dicionario1[comando[1]]
                    print(dicionario1)

            elif comando[0] == "getphone" and (ord(comando[1][0]) >= 48 and ord(comando[1][0]) <= 57) :
                dicionario1 = dicionario[user][1]
                guardar_nomes = []

                for chave, valor in dicionario1.items():
                    if comando[1] in valor:
                        guardar_nomes.append(chave)

                if guardar_nomes == []:
                    print("Not Found!!")
                else:
                    print(guardar_nomes)
            
            elif comando[0] + comando[1] == "getphone-del" and len(comando) == 4 :
                dicionario1 = dicionario[user][1]
                if comando[2] in dicionario1:
                
                    if comando[3] in dicionario1[comando[2]]:
                        dicionario1[comando[2]].remove(comando[3])
                        print(dicionario[comando[2]])
                        

                        if dicionario1[comando[2]] == []:
                            if comando[2] in dicionario1:
                                del dicionario1[comando[2]]
                                writeDict_File(dicionario1, "names1")
                                print(comando[2] + " retirado da base de dados")
                            else:
                                print(comando[2] + " Não consta na base de dados")
                        writeDict_File(dicionario1, "names1")
                    else:
                        print(comando[2] + " Não consta na base de dados")
                else:
                    print(comando[2] + " Não consta na base de dados")

        if comando[0] == "sair":
            logged = False
            
        
    print("terminou a sessão")

comandos()

