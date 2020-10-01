def serie(valor, termos):
    i = 0
    reserva = 0
    termo = 0
    sinal = 1

    while i != termos * 2:
        termo = (valor **(i+1) / (i+1)) * sinal
        reserva = reserva + termo
        sinal = -sinal
        i = i + 2

    return reserva
serie(1,100)