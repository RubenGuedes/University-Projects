def mdc(num_1, num_2):
    reserva = None

    if num_1 % num_2 == 0:
        print(num_2)
        return num_2

    elif num_2 % num_1 == 0:
        print(num_1)
        return num_1


    elif num_1 % num_2 != 0 and num_1 > num_2:
        if num_1 // num_2 == 1:
            print(num_2)
            return num_2

        else:
            reserva = num_1 // num_2
            reserva = (num_2 * reserva)/2
            print(reserva)
            return reserva


    elif num_2 % num_1 != 0 and num_2 > num_1:
        if num_2 // num_1 == 1:
            print(num_1)
            return num_1

        else:
            reserva = num_2 // num_1
            reserva = (num_1 * reserva)/2
            print(reserva)
            return reserva

mdc(28,8)