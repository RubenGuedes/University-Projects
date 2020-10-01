def fibonacci_ate(num):
    reserva = 2
    anterior = 1
    string = ''

    for h in range(0, num + 1):
        if h == 0 or h == 1:
            string = "1" + ' ' + string

        else:
            if reserva > num:
                break
            else:
                string = string + str(reserva) + ' '
                copy_reserva = reserva

                reserva = reserva + anterior
                anterior = copy_reserva
    return string

print(fibonacci_ate(100))