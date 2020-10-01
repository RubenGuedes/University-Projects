def mmc(val_1,val_2):

    if val_2 % val_1 == 0 or val_1 % val_2 == 0:
        if val_1 < val_2:

            resto = val_2 // val_1
            min_mult = val_1 * resto
            print(min_mult)
            return min_mult

        elif val_1 > val_2:

            resto = val_1 // val_2
            min_mult = val_2 * resto
            print(min_mult)
            return min_mult

        elif val_2 == val_1:
            print(val_1)
            return val_1

    else:
        min_mult = val_1 * val_2
        print(min_mult)
        return min_mult


mmc(14,21)