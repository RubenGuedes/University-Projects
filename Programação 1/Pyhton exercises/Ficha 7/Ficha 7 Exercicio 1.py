def mdc(valor_1,valor_2):
    if valor_1 == valor_2:
        return valor_2
    elif valor_2 > valor_1:
        return mdc(valor_2 - valor_1,valor_1)
    elif valor_2 < valor_1:
        return mdc(valor_2, valor_1 - valor_2)

def simplifica(numerador,denominador):
    if numerador/ mdc(numerador,denominador) == int(numerador/ mdc(numerador,denominador)) and (denominador/ mdc(numerador,denominador) == int(denominador/ mdc(numerador,denominador))):
        n_numerador = numerador/mdc(numerador,denominador)
        n_denominador = denominador/mdc(numerador,denominador)
        return str(n_numerador) + "/" + str(n_denominador)

    else:
        return str(numerador) +"/"+str(denominador)
    
def som_frac(num_1,den_1, num_2,den_2):
    if den_1 == den_2:
        soma_num = num_1 + num_2

        return str(soma_num)+'/'+str(den_2)
    else:
        new_num1 = num_1 * den_2
        new_den1 = den_1 * den_2
        new_num2 = num_2 * den_1
        som_newNum = new_num1 + new_num2

        return str(som_newNum) +'/' + str(new_den1)

print(som_frac(2,5,1,3))