#!/usr/bin/env python3

from sys import stdin, argv
from math import log2
from matplotlib import pyplot as plt

FILE_PATH = "fonte-values"

def count_words():
    """ Dictionary to store different words
        -> key: word
        -> value: many times that the key appears
    """
    dictio = {}
    # Read file content
    for line in stdin:

        # If end of file -> stop
        if not line:
            break
        
        word = line[:-1].replace("-", "")
        if word not in dictio:
            dictio[word] = 1
        else:
            dictio[word] += 1

    stdin.close()

    # Store in a file
    file = open( FILE_PATH, "a")
    file.write(str(dictio) + "\n")
    file.close()

def fonte():
    list_dictio = []
    dictio_calc = {}
    count = 0
    # Read content from file
    file = open( FILE_PATH, "r")
    for line in file:
    
        # If end of file -> stop
        if not line:
            break

        # Add line/dictionary to list_dictio
        dictio_calc = eval(line[:-1])
        list_dictio.append( dictio_calc )
    
    # Close file
    file.close()

    # Iterate list_dictio
    for dictio in list_dictio:

        count = 0
        list_x_words = []
        list_y_value = []
        for key in dictio.keys():
            list_x_words.append(key)

        list_x_words = sorted(list_x_words)
        
        for key in list_x_words:
            list_y_value.append(dictio[key])
            count += dictio[key]

        label_name = str(count) + ' linhas'
        plt.plot(list_x_words, list_y_value, label=label_name)
    
    # Create a diagram
    plt.title("Diagrama com contagem das palavras ao escrever-se N linhas")
    plt.xlabel("Palavras")
    plt.ylabel('Quantidade de linhas')
    plt.legend()
    plt.show()

    return dictio_calc, count

def calc_surprise():

    dictio_calc, count = fonte()

    surp_on = surprise(dictio_calc, "on", count, 3)
    surp_off = surprise(dictio_calc, "off", count, 3)
    
    print("Surpresa on = " + str(surp_on))
    print("Surpresa off = " + str(surp_off))

def surprise(dictio_calc, word, count, round_dec):
    prob_word = dictio_calc[word]/count

    return round(-log2( prob_word ), round_dec)

def program():
    has_input = int(argv[1])
    
    if has_input == 0:
        count_words()
    else:
        calc_surprise()

# Execute function
program()