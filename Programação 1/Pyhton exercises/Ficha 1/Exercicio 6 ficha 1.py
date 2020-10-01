ins_letra = str(input('Insira uma letra: '))
conv = ord('a') - ord('A')
new_letra = chr(ord(ins_letra) + conv)

print(new_letra)
