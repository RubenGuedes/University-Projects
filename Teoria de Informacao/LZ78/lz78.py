def lz78(user_inp):
     len_input = len(user_inp)
     dictionary = {0: ""}
     output = []

     count_dict = 1
     store_state = ''
     for index in range(len_input):

          ch = user_inp[index]
          store_state += ch
          
          if not char_in_dict(dictionary, store_state):
               # See substring of storestate
               sub_st = store_state[:len(store_state)-1]
               index_sub_st = find_st(dictionary, sub_st)

               dictionary[count_dict] = store_state
               output.append((index_sub_st, ch))

               count_dict += 1
               store_state = ''

     return dictionary, output

def char_in_dict(dictio, ch):

     for value in dictio.values():
          if ch == value:
               return True
     
     return False

def find_st(dict, sub_st):
     index = 0

     for value in dict.values():
          if value == sub_st:
               break

          index += 1
     
     return index

def main():
     user_input = "ABBCBCABA"
     
     dictio, result = lz78(user_input)
     
     print(dictio)
     print(result)

if __name__ == "__main__":
    main()