from math import ceil, log

BASE_LOG = 2

def count(sorted_input):
     table_count = {}

     for cont in range(len(sorted_input)):
          str_part = sorted_input[cont]

          if str_part not in table_count:
               table_count[str_part] = 1
          else:
               table_count[str_part] += 1

     return table_count

def l_x(sorted_input, table_count):
     input_size = len(sorted_input)
     
     prob = 1.0

     for char_int in range(input_size):
          char_str = sorted_input[char_int]

          prob_char = float(table_count[char_str] / input_size)

          prob *= prob_char

     calc_l_x = ceil(-log(prob, BASE_LOG)) + 1
     
     return calc_l_x

def build_tree(user_input, table_count):

     essencial_char = []
     for key in table_count.keys():
          essencial_char.append(key)

     final_tree = []
     for ind_input in user_input:
          new_array = []
          
          for ch in essencial_char:
               if ch < ind_input:
                    new_array.append(ch)

               elif ch == ind_input:
                    new_array.append(ch)
                    break

          final_tree.append(new_array)
     
     return final_tree

def create_strings(tree):
     tree_size = len(tree)
     create_strings_arr = []

     store_state = ''
     for index in range(tree_size):

          word = store_state
          sub_tree_size = len(tree[index])

          for sub_index in range(sub_tree_size):

               ch = tree[index][sub_index]

               if sub_index == sub_tree_size - 1:
                    store_state += ch

               else:
                    word += ch
                    create_strings_arr.append(word)
                    word = store_state

     create_strings_arr.append(store_state)

     return create_strings_arr

# E.g string = ANANAN
def prob(table_count, input_size, string):
     prob = 1.0

     for ch in string:
          prob *= table_count[ch] / input_size

     return prob

def f_x_acum(array_words, table_count, input_size):
     val = 1
     result = 0.0
     len_array = len(array_words)

     for index in range(len_array):
          word = array_words[index]

          if index == len_array - 1: 
               val = 0.5
          
          result += val * prob(table_count, input_size, word)
     
     return result
     
def f_x_bin(f_x, lx):
     result = "0."

     for _ in range(lx):
          f_x *= 2
          result += str(int(f_x))
          f_x %= 1

     return result

def c_x(f_x_binary):
     return f_x_binary[2:]

def main():
     input_user = "SQSY"
     len_input = len(input_user)
     sorted_input = sorted(input_user)

     table_count = count(sorted_input)
     result_l_x = l_x(sorted_input, table_count)
     tree = build_tree(input_user, table_count)
     array_words = create_strings(tree)
     f_x = f_x_acum(array_words, table_count, len_input)
     f_bin = f_x_bin(f_x, result_l_x)
     cx = c_x(f_bin)

     print(cx)

if __name__ == "__main__":
    main()
