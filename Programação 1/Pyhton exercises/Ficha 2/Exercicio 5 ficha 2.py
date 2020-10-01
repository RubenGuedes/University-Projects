#Exercicio 5 ficha 2
"""
numero =  int(input("Insira um numero: ")) 
if numero < -10 and 5 <=  numero < 45 and  120 < numero <= 245:
     print("O numero está no intervalo")
else:
     print("O numero não está no intervalo")
"""

def num(numero):
     if numero < -10 and 5 <=  numero < 45 and  120 < numero <= 245:
          print("O numero está no intervalo")
          return True
     else:
          print("O numero não está no intervalo")
          return False
num(2)
