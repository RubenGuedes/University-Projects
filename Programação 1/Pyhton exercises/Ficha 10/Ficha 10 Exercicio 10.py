def acumulado(lista):
    reserva = 0
    for i in range(len(lista)):
        reserva = reserva + lista[i]
        lista[i] = reserva

    return lista
print(acumulado([1,5,3]))