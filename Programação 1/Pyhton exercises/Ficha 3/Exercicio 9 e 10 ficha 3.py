while True:

    # Constantes
    contador = 0

    # Input
    a_value = int(input("Valor de a: "))
    b_value = int(input("Valor de b: "))

    while contador <=50:
        contador += 1
        if contador % a_value == 0 and contador % b_value != 0:
            print(contador, ",multiplo de", a_value)

        elif contador % a_value != 0 and contador %b_value ==0 :
            print(contador, ",multiplo de", b_value)

        elif contador % a_value == 0 and contador %b_value ==0:
            print(contador,",multiplo de", a_value,", multiplo de",b_value)
