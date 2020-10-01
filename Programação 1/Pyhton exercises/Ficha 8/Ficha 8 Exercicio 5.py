x = int(input("Insira um valor para calcular o fibonacci: "))
def fibonacci(valor):
    reserva = 2
    anterior = 1
    string = ''

    for h in range(0,valor+1):
        if h == 0 or h == 1:
            string = "1" + ' ' + string

        else:
            string = string + str(reserva) +' '

            copy_reserva = reserva

            reserva = reserva + anterior
            anterior = copy_reserva
    return string

print(fibonacci(x))