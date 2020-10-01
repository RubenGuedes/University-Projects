import math

num_dec = float(input('Insira um numero: '))

#dec = round(num_dec, 3) OU  dec = ((num_dec*1000) // 1)/1000
dec = ((num_dec*1000) // 1)/1000
int_part = int(num_dec)
dec_part = round((num_dec - int_part),9)

print('Numero com 3 casas decimais: ', dec, '\nParte Inteira: ', int_part,'\nParte Decimal: ', dec_part)
