def minimo(num1, num2, num3):
    if num1 < num2 and num1 < num3:
        print(num1, "é o menor numero.")
        return num1

    elif num2 < num1 and num2 < num3:
        print(num2, "é o menor numero.")
        return num2

    else:
        print(num3, "é o menor numero.")

minimo(4,6,3)