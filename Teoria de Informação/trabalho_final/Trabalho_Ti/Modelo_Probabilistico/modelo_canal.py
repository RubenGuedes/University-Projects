#!/usr/bin/env python3

from sys import stdin, argv
from matplotlib import pyplot as plt

FILE_PATH = "canal-errors"

def count_error_position():
    argu = str(argv[2])
    line = stdin.readline()

    dictio = { "0": 0,"1": 0,"2": 0,"3": 0,"4": 0,"5": 0, "6": 0, "7": 0}
    for index in range(len(argu)):
        ch_argu = bin(ord(argu[index]))
        ch_line = bin(ord(line[index]))
        
        calc_diff = bin(int(ch_argu, 2) ^ int(ch_line, 2))[2:]

        while len(calc_diff) != len(argu):
            calc_diff = '0' + calc_diff

        for ind in range(len(calc_diff)):
            bit = calc_diff[ind]

            if bit != "0":
                dictio[str(ind)] += 1

    # Store in a file
    file = open( FILE_PATH, "a")
    file.write(str(dictio) + "\n")
    file.close()

def show_diagram():
    """
    dictio_wrong
        key -> (int) = position error
        value -> (int) = many times that the error occur in key position
    """
    dictio_wrong = {}
    list_x_pos = [0, 1, 2, 3, 4, 5, 6, 7]
    list_y_qua = [0, 0, 0, 0, 0, 0, 0, 0]

    file = open( FILE_PATH, "r")
    for line in file:

        if not line:
            break
        
        line_dict = eval(line)

        # See and count wrong positions
        for item, value in line_dict.items():
            if value != 0:
                ind = int(item)
                list_y_qua[ind] += 1

    # Display in a diagram
    plt.plot(list_x_pos, list_y_qua)
    plt.title("Quantidade de erros por posição")
    plt.xlabel("Posições com erro")
    plt.ylabel("Quantidade de erros")
    plt.show()

def program():
    has_input = int(argv[1])
    
    if has_input == 0:
        count_error_position()
    else:
        show_diagram()

# Execute program
program()