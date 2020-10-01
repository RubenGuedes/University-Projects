# Ver se "a" é multiplo de "b"
print('
a = int(input('Valor de a: '))
b = int(input('Valor de b: '))

intervalo = int(input('Define o maior número para verificar se é dívisivel: '))
intervalo = intervalo +1

while intervalo != 1:
     
     if a%(intervalo-1) == 0 and b%(intervalo-1) == 0:
          intervalo = intervalo -1
          print(intervalo)
     else:
          intervalo = intervalo -1
          
