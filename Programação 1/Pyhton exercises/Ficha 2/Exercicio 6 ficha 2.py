#Exercicio 6, ficha 2

import math

num = float(input("Insira um numero: "))
x = int(num)
     
if x > 0 or x == 0:
     quad = math.sqrt(num)        
     print('A raiz quadrada de ',num,' é ', quad,'.')

else:
     print('O numero inserido é negativo')
     
