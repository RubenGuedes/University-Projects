def see_diff_char(string):
     array = []
     
     for ch in string:
          if ch not in array:
               array.append(ch)
     
     return array

def initialize_dictio(array_diff):
     dictio = {}

     for index in range(len(array_diff)):
          dictio[index+1] = array_diff[index]

     return dictio

def ch_in_dictionary(dictio, ch):
     index = 1
     find = False

     for value in dictio.values():
          if value == ch:
               find = True
               break
          index += 1

     if find:
          return index
     else:
          return -1

def lzw(string):
     len_string = len(string)
     array_diff = see_diff_char(string)
     dictio = initialize_dictio(array_diff)
     output = []

     index = 0
     stored_state = ''
     count_dictio = len(dictio) + 1
     while index != len_string:
          ch = string[index]
          stored_state += ch

          ind_ch = ch_in_dictionary(dictio, stored_state)
          index += 1

          # If not in dictionary
          if ind_ch == -1 :
               len_stored = len(stored_state)
               sub_st = stored_state[:len_stored-1]

               # See index of sub_string(stored_state)
               ind_sub = ch_in_dictionary(dictio, sub_st)

               # Store in output and dicitonary
               dictio[count_dictio] = stored_state
               output.append(ind_sub)
               
               # Go with index 1 step back
               index -= 1
               count_dictio += 1
               stored_state = ''
          
          elif ind_ch != -1 and  index == len_string:
               ind = ch_in_dictionary(dictio, stored_state)
               output.append(ind)
               break

     return dictio, output

def main():
     user_input = "AABABAABABAB"

     dictio, out = lzw(user_input)

     print(dictio)
     print(out)

if __name__ == "__main__":
    main()
