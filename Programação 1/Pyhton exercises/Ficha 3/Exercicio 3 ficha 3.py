while True:

    num = int(input("Insira um número: "))
    i = 1

    while i != num + 1:
        print(i)
        i += 1

    num -= 1
    while num != -1:
        print(num)
        num -= 1
