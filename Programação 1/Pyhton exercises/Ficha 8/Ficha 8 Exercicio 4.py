while True:

    valor = int(input("Insira um valor: "))
    reserva = 1

    for valores in range(1,valor+1):
        reserva = reserva * valores

    print(reserva)