#!/usr/bin/env python3

from sys import stdout, stdin
from hamming_class import *

# Hamming(11, 7)
REDUNDANCY_SIZE = parity(NUMBER_BITS)
TOTAL_DATA_SIZE = NUMBER_BITS + REDUNDANCY_SIZE

def hamming_add_redundancy():
    # List with the position of the parity bits
    positions = parity_bit_position(REDUNDANCY_SIZE)

    result_str = ""
    for word in stdin:

        for ch in word:
            bin_char = bin(ord(ch))[2:]

            if bin_char == '\n':
                break

            # Add redundancy to binary set
            data = data_before_redudancy(positions, bin_char, TOTAL_DATA_SIZE, NUMBER_BITS)
            data_str = add_redundancy(data, positions, TOTAL_DATA_SIZE, REDUNDANCY_SIZE).replace("'", "")
            
            first_half = chr(int(data_str[0:5], 2))
            second_half = chr(int(data_str[5:len(data_str)], 2))

            data_str_to_char = first_half + second_half
            
            # Concat redundancy with result
            result_str += data_str_to_char

    # Put content in the output
    stdout.write(result_str)

hamming_add_redundancy()