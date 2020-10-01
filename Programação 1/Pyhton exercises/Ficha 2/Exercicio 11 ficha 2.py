import math

# Ponto 1
x1 = float(input("Insira a abcissa do ponto 1: "))
y1 = float(input("Insira a ordenada do ponto 1: "))

# Ponto 2
x2 = float(input("Insira a abcissa do ponto 2: "))
y2 = float(input("Insira a ordenada do ponto 2: "))

# Operação para calcular a distância
distancia = math.sqrt((x2-x1)**2 +(y2-y1)**2)

print(" A distancia entre os dois pontos é de", round(distancia,4))

