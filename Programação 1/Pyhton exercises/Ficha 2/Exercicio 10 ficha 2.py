# Exercicio 10 Ficha 2

while True == True:
     x = float(input("Insira a coordenada x: "))
     y = float(input("Insira a coordenada y: "))
     
     if x > 0 and y > 0:
          print(" O ponto está no primeiro quadrante")
          
     elif x > 0 and y < 0:
          print(" O ponto está no quarto quadrante")

     elif x < 0 and y > 0:
          print(" O ponto está no segundo quadrante")

     elif x < 0 and y < 0:
          print(" O ponto está no terceiro quadrante")
          
     else:
          print(" O ponto está na origem")
