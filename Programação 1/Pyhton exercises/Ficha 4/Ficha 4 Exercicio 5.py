def calcula(reais1, reais2 ,string):

    if string == "soma":
        operação = reais1 + reais2

    elif string == "subtracao":
        operação = reais1 - reais2

    elif string == "divisão":
        operação = reais1 / reais2

    elif string == "multiplicacao":
        operação = reais1 * reais2

    print("Operação:", operação)
    return operação

calcula(10.5 , 2.7, "multiplicacao")