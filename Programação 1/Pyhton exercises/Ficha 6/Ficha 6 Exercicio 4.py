def multiplo(m,i):
    if i == 1:
        return m
    elif i > 1:
        return m + multiplo(m, i - 1)

print(multiplo(3,3))
