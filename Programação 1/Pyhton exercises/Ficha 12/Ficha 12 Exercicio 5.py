posição = [('João',300,20),('Ana',80,15),('Patrícia',17,90)]
def acima(pos,y):
    emp_tuplo = ()

    for i in range(len(pos)):
        if pos[i][2] > y:
            val = (pos[i][0],)
            emp_tuplo  =  emp_tuplo + val
    return emp_tuplo

print(acima(posição,18))