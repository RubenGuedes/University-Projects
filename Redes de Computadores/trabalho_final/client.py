import socket
import sys

PORT        = 5000
RECV_BUFFER = 4096

def main():
     inp  = input(str("Seja bem-vindo:\n")) 

     # socket
     s = socket.socket( socket.AF_INET, socket.SOCK_STREAM )
     s.setsockopt(      socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

     # connect
     s.connect( ('192.168.1.65', PORT) )

     while inp is not "":

          print(intermediario(s, inp)) # funçao escolhida pelo inp do cliente
          inp = input("")

     s.close()
###############################################################
def arranjarInput( input_string ):

     sinal = 0
     str_palavra = ""
     lista_palavras = []
     
     for index in range(len(input_string)):
          if input_string[index] != " ":
               str_palavra += input_string[index]
               sinal = 0
          
          else:
               if input_string[index] == " ":
                    sinal = sinal + 1 
               if sinal == 1:
                    lista_palavras.append(str_palavra)
                    str_palavra = ""
                    
     if str_palavra != " ":
          lista_palavras.append(str_palavra)

     return lista_palavras
###############################################################
def getNumber(socket, name, info):     
     string_Envia = info + " " + name

     encrypt_st = encripta(string_Envia, 3)
     socket.send( str.encode( encrypt_st ) ) 
     final = desencripta(socket.recv(RECV_BUFFER).decode(), 3)
     return final

def setNumber(socket, name, number, posicao):
     string_Envia = posicao + " " + name + " " + number

     encrypt_st = encripta(string_Envia, 3)
     socket.send( str.encode( encrypt_st ) ) 
     final = desencripta(socket.recv(RECV_BUFFER).decode(), 3)
     return final

def deleteNumber(socket, name, number, info):
     string_Envia = info + " " + name+ " " + number

     encrypt_st = encripta(string_Envia, 3)
     socket.send( str.encode( encrypt_st ) ) 
     final = desencripta(socket.recv(RECV_BUFFER).decode(), 3)

     return final
     
def deleteClient(socket, name, info):
     string_Envia = info + " " + name

     encrypt_st = encripta(string_Envia, 3)
     socket.send( str.encode( encrypt_st ) ) 
     final = desencripta(socket.recv(RECV_BUFFER).decode(), 3)

     return final     

def reverse(socket, number, info):
     string_Envia = info + " " + number

     encrypt_st = encripta(string_Envia, 3)
     socket.send( str.encode( encrypt_st ) ) 
     final = desencripta(socket.recv(RECV_BUFFER).decode(), 3)

     return final
###############################################################
def intermediario( socket, info):
     lista = arranjarInput(info)
    
     if lista[0] == "getphone" and (ord(lista[1][0]) < 48 or ord(lista[1][0]) > 57) and len(lista) == 2:
          name      = lista[1]
          pos_lista = str(take_action(lista))
          return getNumber(socket, name, pos_lista)
     
     elif (lista[0] + lista[1]) == "getphone-set" :
          name      = lista[2]
          number    = lista[3]
          pos_lista = str(take_action(lista))
          return setNumber(socket, name, number, pos_lista)

     elif (lista[0] + lista[1]) == "getphone-del" and len(lista) == 4 :
          name      = lista[2]
          number    = lista[3]
          pos_lista = str(take_action(lista))
          return deleteNumber(socket, name, number, pos_lista)
     
     elif (lista[0] + lista[1]) == "getphone-del" and len(lista) == 3:
          name      = lista[2]
          pos_lista = str(take_action(lista))
          return deleteClient(socket, name, pos_lista)
     
     elif lista[0] == "getphone" and (ord(lista[1][0]) >= 48 or ord(lista[1][0]) >= 57) :
          number    = lista[1]
          pos_lista = str(take_action(lista))
          return reverse(socket, number, pos_lista)
###############################################################
def take_action( list_commands ):
     if len(list_commands) == 2:
          if list_commands[1].isdigit():
               return 1 # O conteudo é um numero
          else:
               return 0 # O conteudo deve ser um nome
     elif len(list_commands) == 3:
          if "-del" in list_commands:
               return 3 
          else:
               return None
     elif len(list_commands) == 4:
          if "-set" in list_commands:
               return 2
          elif "-del" in list_commands:
               return 4
          else:
               return None
################################################################
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
################################################################
if __name__ == "__main__":
     main()