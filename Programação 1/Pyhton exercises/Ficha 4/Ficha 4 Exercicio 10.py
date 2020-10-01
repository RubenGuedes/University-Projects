import math

def fatorial(numero):
    reserva = 1

    while numero != 0:
        reserva = reserva * numero
        numero -= 1

    return reserva

fatorial(4)
