letra_1 = input(str('Insira uma letra maiúscula: '))

new_letra = ord(letra_1)
new_letra = new_letra + (ord('a') - ord('A'))
new_letra = chr(new_letra)

print(new_letra)

