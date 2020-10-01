while True:

    # CONSTANTES, antes do dia 15
    gasolina95 = 1.364
    gasoleo = 1.149
    gasolina98 = 1.414 # Mantem o valor após o dia 15

    # Input
    Litros = int(input("Quantos litros: "))
    Combustivel = str(input("Escreva o combustivel (gasolina95, gasoleo, gasolina98): "))
    Dia = int(input("Indique o dia de abastecimento: "))

    # Modificação
    if Combustivel == "gasoleo":
        if Dia < 15:
            valor = round(gasoleo * Litros,2)
        else:
            valor = round((gasoleo - 0.023) * Litros,2)
    elif Combustivel == "gasolina95":
        if Dia < 15:
            valor = round(gasolina95 * Litros,2)
        else:
            valor =  round((gasolina95 - 0.021) * Litros,2)
    elif Combustivel == "gasolina98":
        valor = round(gasolina98 * Litros, 2)

    # Print
    print("O custo de", Litros,"litros de", Combustivel,"no dia", Dia,"é de", valor,"€.")
