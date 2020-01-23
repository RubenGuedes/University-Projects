import json
import traceback
import socket, select

PORT            = 5000 # Porta indicada no Enunciado 
SOCKET_LIST     = []
NUMBER_OF_USERS = 5
RECV_BUFFER     = 4096
##############################################################
########################## MAIN ##############################
##############################################################
def main():

     # dicionario para guardar conteúdo
     dicio = readFile_Dict("names")
     

     # socket
     server_socket = socket.socket( socket.AF_INET   , socket.SOCK_STREAM    )
     server_socket.setsockopt(      socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

     # bind
     server_socket.bind( ('0.0.0.0', PORT) )
     
     # listen
     server_socket.listen(3)
     server_socket.setblocking(False)

     # adicionar o socket do servidor à lista de sockets
     SOCKET_LIST.append(server_socket)

     #receive
     timeout = 4 # para usar na função "select" linha 43
     while True:

          # apagamos os sockets que já não são utilizáveis
          for sock in SOCKET_LIST:
               if sock.fileno() < 0:
                    SOCKET_LIST.remove(sock)

          # Espera até receber dados nalgum dos sockets que temos
          clients_socket,_,_ = select.select(SOCKET_LIST, [], [], timeout)

          for sock in clients_socket:  # percorrer os sockets com nova informação
            # há uma nova ligação
            if sock == server_socket:
                newsock, addr = server_socket.accept()
                newsock.setblocking(0)
                SOCKET_LIST.append(newsock)
                
                print("Novo cliente com o ip -> %s" % (addr,))
                
            else: # há dados num socket ligado a um cliente
                try:
                    info = sock.recv(RECV_BUFFER).decode()
                    data = desencripta( info, 3 )
                    if data:
                         # funcao auxiliar que retorna uma string
                         content = encripta(funcao_auxiliar(data, dicio), 3) # input do cliente
                         sock.send( str.encode(content) ) # Enviar o output ao cliente
                        
                    else: # não há dados, o cliente fechou o socket
                        print("Client disconnected")
                        sock.close()
                        SOCKET_LIST.remove(sock)
                        
                except Exception as e: # excepção ao ler o socket, o cliente fechou ou morreu
                    print("Client disconnected")
                    print("Exception -> %s" % (e,))
                    print(traceback.format_exc())
                    
                    sock.close()
                    SOCKET_LIST.remove(sock)
##############################################################
####################  MANIPULAR O FICHEIRO  ##################
##############################################################
'''
Lê o conteúdo do ficheiro de texto e coloca-o 
num dicionário, retornando-o.
'''
def readFile_Dict(namefile):
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
'''
Coloca o conteúdo do dicionário no ficheiro de
texto names.txt
'''
def writeDict_File(dictio, name_file):
     f = open(name_file + ".txt", "w")
     dictio_string = json.dumps(dictio)
     f.writelines(dictio_string)
     f.close()
##############################################################
################### Funções do Enunciado #####################
##############################################################
'''
Se o nome estiver no dicionário, retorna todos os número
Contrário retorna None
'''
def clientHasNumber(dictio, name):
     if name in dictio:
          return "O " + name + " tem os seguintes contactos " + str(dictio[name])
     else:
          return notFound(name)
'''
Se o nome exitir adicionar um numero
'''
def numberSet(dictio, name, number):
     if name in dictio:
          dictio[name].append(number)
          writeDict_File(dictio, "names") # atualiza a base de dados
          return "Numero " + number + " adicionado com sucesso"
     else:
          dictio[name] = [number]
          writeDict_File(dictio, "names") # atualiza a base de dados
          return "Numero " + number + " e o nome " + name + " adicionados com sucesso"
'''
Apagar o nome
'''
def deleted2(dictio, name):
     if name in dictio:
          del dictio[name]
          writeDict_File(dictio, "names") # atualiza a base de dados
          return name + " retirado da base de dados."
     else:
          return notFound(name)
'''
Função que apaga apenas o numero.
Mas se um cliente não tiver contactos associados pode-se apagar
o cliente do dicionario
'''
def deleted3(dict, name, number):
     if name in dict:
          if number in dict[name]:
               dict[name].remove(number)
               # Se o cliente nao tiver contactos, apagar nome
               if dict[name] == []:
                    deleted2(dict,name)

               writeDict_File(dict, "names") # atualiza a base de dados
               return "O numero " + number + " pertencente a " + name + " foi removido com sucesso"
          else:
               notFound(number)
     else:
          notFound(name)
'''
Procura quantos nomes estão associados a um numerosocket.send( str.encode(string_Envia) ) #  bytearray(lista_Envia)
     final = socket.recv(RECV_BUFFER).decode()
'''
def clientHasNames(dictio, number):
     guardar_nomes = []
     for chave, valor in dictio.items():
          if number in valor:
               guardar_nomes.append(chave)

     if guardar_nomes == []:
          return notFound(number)
     else:
          return str(guardar_nomes)
'''
Funcao que retorna uma string a dizer que não descobriu a expressao
'''
def notFound(expr):
     return "A expressão '" + expr + "' não encontrada!"
##############################################################
##### Funções para tratar do conteúdo que vem do usuário #####
##############################################################
'''
Esta funcao tem como objetivo, ao receber o input do client
associar a uma função (Funções do Enunciado) para depois devolver
o output.
Ou seja é uma função intermediária
'''
def funcao_auxiliar( data, dictio ):
     listContent =  data.split(" ")
     action      = listContent[0]

     if action == "0":
          return clientHasNumber( dictio, listContent[1] )
     elif action == "1":
          return clientHasNames( dictio, listContent[1])
     elif action == "2":
          return numberSet(dictio, listContent[1], listContent[2])
     elif action == "3":
          return deleted2( dictio, listContent[1])
     elif action == "4":
          return deleted3(dictio, listContent[1], listContent[2])
     else:
          return "Comando não encontrado"

##############################################################
def encripta(content, key):
     res = ""
     for ind in range(len(content)):
          if content[ind] != " ":
               value = ord( content[ind] ) + key
               if value > 122:
                    value = (value % 122) + 39
               res += chr( value )
          else:
               res += " "
     return res

def desencripta(content, key):
     res = ""
     for ind in range(len(content)):
          if content[ind] != " ": 
               value = ord( content[ind] ) - key
               if value < 39:
                    value = 122 - 39 + key
               res += chr(value)
          else:
               res += " "
     return res
##############################################################
###################################
if __name__ == "__main__":
     main()
###################################