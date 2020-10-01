def fatorial(num):
    res = 1
    while num > 0:
        res = res * num
        num = num -1
    return res



def rec_fatorial(num):
    if num == 1:
        return 1
    else:
        return num * rec_fatorial(num-1)

print(fatorial(rec_fatorial(3)))
print(rec_fatorial(3))