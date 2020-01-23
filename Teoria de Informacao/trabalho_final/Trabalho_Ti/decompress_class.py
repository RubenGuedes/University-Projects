from sys import stdin, stdout
from binary_class import *
from dictionary_class import *

def decompress(bin_input):
    b_converter = Binary()
    compress_output = b_converter.bin_to_list(bin_input)

    # Initialize the dictionary
    dictionary = Dictionary()

    # Insert the different words in to the dictionary
    index_output = dictionary.initialize_dictionary(compress_output)

    # Variable with the final output
    result = ""

    # Algorithm's first step
    actual_item = dictionary.find_item(compress_output[index_output])
    result += str(actual_item)

    # Iterate compress_output
    while index_output < len(compress_output) - 1:
        
        # Index 
        index_actual = compress_output[index_output]
        index_next = compress_output[index_output + 1]

        # Get actual item and next item
        actual_item = str(dictionary.find_item( index_actual ))
        next_item = str(dictionary.find_item( index_next ))

        # Add new word in to dictionary
        if next_item == -1:
            next_item = actual_item
        
        to_dictionary = actual_item + next_item[:1]
        dictionary.add_item(to_dictionary)

        # Add next item to output
        result += next_item

        # Increment the index of compress_output
        index_output += 1
    
    return result
