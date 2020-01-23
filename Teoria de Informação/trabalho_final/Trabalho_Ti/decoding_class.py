#!/usr/bin/env python3

from sys import stdin, stdout

from hamming_class import *
from compress_class import *
from decompress_class import *

DIRECTORY = "Output_Files/"

# Hamming(11, 7)
REDUNDANCY_SIZE = parity(NUMBER_BITS)
TOTAL_DATA_SIZE = NUMBER_BITS + REDUNDANCY_SIZE

def store_result(filename, mode, str_result):
    file = open(DIRECTORY + filename, mode)
    file.write(str_result)
    file.close()

def decoding():
    
    line = stdin.readline()
    len_line = len(line)

    # Group input in blocks of size "TOTAL_DATA_SIZE" 
    list_words = []

    for index in range(0, len_line, 2):
        var_aux1 = line[index]
        var_aux2 = line[index + 1]

        word_redundant1 = bin(ord(var_aux1))[2:]
        word_redundant2 = bin(ord(var_aux2))[2:]

        word_redundant1 = add_zeros(word_redundant1, 5)
        word_redundant2 = add_zeros(word_redundant2, 6)

        word_redundant = word_redundant1 + word_redundant2
        
        list_words.append(word_redundant)

    # See erros and correct them if it can
    dictio_errors = detect_errors(list_words, TOTAL_DATA_SIZE, REDUNDANCY_SIZE)
    if dictio_errors != {}:
        list_words = correct_errors(list_words, dictio_errors, REDUNDANCY_SIZE)

    # Function to remove redundancy
    str_without_redu = remove_redundancy_list(list_words, REDUNDANCY_SIZE)
    store_result("File_Without_Redundancy", "w" , str_without_redu)

    # Apply Compression algorithm
    compress_input = compress(str_without_redu)
    store_result("Compressed_File", "w", compress_input)

    # Apply Decompression algorithm
    decomp_result = decompress(compress_input)
    store_result("Decompressed_File", "w", decomp_result)

decoding()