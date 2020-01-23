from math import pow

NUMBER_BITS = 7

def parity(size_line):
    m = 0

    while (pow(2, m) -1 - m) < size_line:
        m += 1
    
    return m

def find_m(data):
    m = 0
    len_data = len(data)

    while pow(2, m) - 1 < len_data:
        m += 1

    return m

def get_item_word(word, pos_code, m):

    if len(word) == m:
        return word[pos_code]
    else:
        number_of_zeros = m - len(word)
        if pos_code < number_of_zeros:
            return 0
        else:
            new_pos = pos_code - number_of_zeros
            return word[new_pos]

def parity_bit_position(m):
    list_pos = []

    for ind in range(m):
        list_pos.append( int(pow(2, ind)) - 1)

    return list_pos

def create_matrix(size_data, begin):
    matrix = []

    for i in range(begin, size_data):
        matrix.append( bin(i) )

    return matrix

def add_zeros(expr, expected_size):
    
    while len(expr) < expected_size:
        expr = '0' + expr

    return expr
"""
    ERRORS (DETECTION AND CORRECTION)
"""
def detect_errors(list_words, total_data, redundancy_size):
    # key = index_word
    # values = list with the positions of all errors
    dictio_errors = {}

    matrix = create_matrix(total_data + 1, 1)

    for index in range(len(list_words)):
        word = list_words[index]
        errors_word, flag_error = detect_error_character(word, matrix, total_data, redundancy_size)
        
        if flag_error:
            if index not in dictio_errors:
                dictio_errors[index] = []

            dictio_errors[index].append(index)
    
    return dictio_errors

def detect_error_character(word, matrix, total_data,redundancy_size):
    flag_error = False
    resultant_matrix = []

    for index_redund in range(redundancy_size):

        stream_matrix = ""
        for index_matrix in range(len(matrix)):

            word_matrix = matrix[index_matrix][2:]
            word_matrix = add_zeros(word_matrix, redundancy_size)

            stream_matrix += word_matrix[index_redund]

        # Multiply matrix
        val = 0
        for index in range(len(word)):
            v0 = int(word[index])
            v1 = int(stream_matrix[index])

            val = (val + ((v0 + v1) % 2) % 2)

        if val == 1:
            flag_error = True

        resultant_matrix.append(val)

    return resultant_matrix, flag_error

def correct_errors(list_words, dictio_errors, redundancy):
    redu = redundancy - 1

    for item, values in dictio_errors.items():
        position_to_correct = 0
        for index in range(len(values), -1):
            pos_bit = values[index]

            if pos_bit == 1:
                position_to_correct += int(pow(2, redu - index))

        list_words[position_to_correct] = str((int(list_words[position_to_correct]) + 1) % 2)

    return list_words
"""
    ADD/REMOVE REDUNDANCY
"""
def remove_redundancy_list(list_words, redundancy_size):
    new_list = ""

    for word in list_words:
        non_redundat_word = str(remove_redundancy_word(word, redundancy_size))
        non_redundat_word = add_zeros(non_redundat_word, len(word)-redundancy_size)
        
        new_list += non_redundat_word

    return new_list

def remove_redundancy_word(word, redundancy_size):
    result_str = ""
    parity_position = parity_bit_position(redundancy_size)

    for index in range(len(word)):
        
        if index not in parity_position:
            ch = word[index]
            result_str += ch

    result_str = add_zeros(result_str, 7)
    return result_str

def data_before_redudancy(list_parity_position, message, size_data, number_bits):
    data = []
    index_message = 0
    
    message = add_zeros(message, number_bits)

    for index in range(size_data):
        if index in list_parity_position:
            data.append(0)
        else:
            item = message[index_message]
            data.append(item)
            index_message += 1

    return data

def add_redundancy(data, list_parity_position, size_data, m):

    dictionary = {}
    index_parity = 0
    binary_matrix = create_matrix(size_data, 1)

    for parity_n in range((m - 1), -1, -1):

        for pos_binary in range(len(binary_matrix)):

            val_matrix = binary_matrix[pos_binary][2:]
            val_matrix = add_zeros(val_matrix, m)

            bit_matrix = val_matrix[parity_n]

            if bit_matrix == '1':
                pos_parity = list_parity_position[index_parity]

                if pos_parity not in dictionary:
                    dictionary[pos_parity] = []
                dictionary[pos_parity].append(pos_binary)

        index_parity += 1

    for item, values in dictionary.items():
        val = 0

        for value in values:
            bit = int(data[value])
            val = (val + bit) % 2

        data[item] = str(val)

    data_str = str(data).replace('[', "").replace(']', "").replace(",", "").replace(" ", "")
    return data_str

"""
    CONVERT BINARY IN TO STRING
"""
def binary_list_to_string(list_words):
    result = ""

    for word in list_words:
        new_ch = binary_word_to_string(word)
        result += new_ch

    return result

def binary_word_to_string(word):
    result_str = ""

    bin_val = '0b0'
    for index in range(len(word)):
        ch = word[index]
        ch_int = int(ch)

        bin_val = bin( int(bin_val, 2) << 1)
        bin_val = bin( int(bin_val, 2) ^ ch_int)

    result_str += chr(int(bin_val, 2))     
    return result_str 
