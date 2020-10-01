def rot13(str):
    fin_string = ''

    for i in range(0,len(str)):
        n_letra = ord(str[i:i+1])

        if n_letra + 13 > 122:

            numero = 96 + ((n_letra+13) - 122)
            n_letra = chr(numero)
            fin_string = fin_string + n_letra

        else:
            n_letra = chr(n_letra +13)
            fin_string = fin_string + n_letra

    return fin_string

# Comp 65 <-> 122
print(ord("a"))
print(rot13("hello"))
print(rot13("uryyb"))