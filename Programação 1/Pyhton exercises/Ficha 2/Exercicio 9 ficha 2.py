num = int(input("Insira um numero com 3 digitos: "))
     
alg1 = int(num/100)
alg3 = num - (int(num/10)*10)

if alg1 == alg3:
     print("O numero", num, "é uma capicua")
else:
     print("O numero", num, "não é uma capicua")
          
