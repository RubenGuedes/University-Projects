num = int(input('Insere um numero inteiro: ')) #14

num1 = num /10 #1.4
prim_dig = num1 - (num1 - int(num1)) # 1.0
seg_dig = num - (prim_dig*10) # 4.0

print('primeiro digito:', int(prim_dig),',  segundo digito:', int(seg_dig))
