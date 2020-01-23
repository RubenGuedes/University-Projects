from sys import stdin, stdout
from binary_class import *
from dictionary_class import *


def list_diff_words(user_inp):
    list_diff = []
    concat_input = []

    for line in user_inp:
        for charact in line:
            if charact not in list_diff:
                list_diff.append(charact)

    return list_diff

def initialize_dictionary(list_diff):
    output = []
    dictionary = Dictionary()

    for ch in list_diff:
        dictionary.add_item(ch)
        output.append(ch)

    output.append(None)
    return output, dictionary

def compress(info):
    b_converter = Binary()
    user_inp = b_converter.bin_to_string(info, 7)

    # Aply LZW algorithm
    list_diff = list_diff_words(user_inp)

    output, dictionary = initialize_dictionary(list_diff)
    aux_expr = ""

    count = 1
    for input_user in user_inp:

        index_input = 0
        while index_input != len(input_user):
            actual_item = input_user[index_input]

            aux_expr += actual_item

            dictionary_ind = dictionary.item_index(aux_expr) 
            if dictionary_ind == -1:
                dictionary.add_item(aux_expr)

                index_prev_item = dictionary.item_index(aux_expr[:-1])
                output.append(index_prev_item)

                index_input -= 1
                aux_expr = ""

            elif dictionary_ind != -1 and len(user_inp) == count and index_input == len(input_user) - 1:
                dictionary.add_item(aux_expr)

                index_last_item = dictionary.item_index(aux_expr)
                output.append(index_last_item)

            index_input += 1
        count += 1

    final_result = ""
    for item in output:

        if not item:
            final_result += '\0'

        elif type(item) == str:
            final_result += item

        elif type(item) == int:
            aux_value = chr(item)
            final_result += aux_value
    
    return final_result

def add_zeros(expr, expected_size):
    
    while len(expr) < expected_size:
        expr = '0' + expr

    return expr