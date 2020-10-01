def triangulo(opt, adj, hip):
    # Condições de existencia
    if hip >= adj+opt or adj >= hip + opt or opt >= hip + adj:
        print("Não existe triângulo")
        return None


    else:
        if hip**2 == opt**2 + adj**2 or opt**2 == hip**2 + adj**2 or adj**2 == hip**2 + opt**2:
            print("Triângulo Rectângulo")
            return "Triângulo Rectângulo"


        elif  hip**2 > opt**2 + adj**2 or opt**2 > hip**2 + adj**2 or adj**2 > hip**2 + opt**2:
            print("Triângulo Obtuso")
            return "Triângulo Obtuso"


        elif hip**2 < opt**2 + adj**2 or opt**2 < hip**2 + adj**2 or adj**2 < hip**2 + opt**2:
            print("Triângulo Agudo")
            return "Triângulo Agudo"

triangulo(3,3,5)