from math import ceil, log
from typing import Final

from column_elias import *

class Table():
     LOG_BASE = 2
     INIT_CHARACT: Final = 'A'

     def __init__(self, size = 4):
          self.table = self.init_table(size)

     def init_table(self, size):
          table = {}
          actual_char = self.INIT_CHARACT

          for index in range(size):
               table[actual_char] = Column(actual_char)
               actual_char = chr(ord(actual_char) + 1)
          
          return table

     def set_prob(self, charact, prob):
          str_char = str(charact)
          self.table[str_char].set_p_x(prob)

     def calculate_l_x(self):
          for value in self.table.values():
               prob = value.get_p_x()
               calc_l_x = ceil(-log(prob, self.LOG_BASE)) + 1

               value.set_l_x(calc_l_x)

     def calculate_f_x(self):
          for key1, value in self.table.items():
               item = 0.0

               for key2, value in self.table.items():
                    if key1 != key2:
                         item += self.table[key2].get_p_x()
                    else:
                         break
               
               item += float(self.table[key1].get_p_x()) / 2
               
               self.table[key1].set_f_x(item)

     def calculate_f_x_bin(self):
          for value in self.table.values():
               out = '0.'
               l_x = int(value.get_l_x())
               f_x = float(value.get_f_x())
               
               for _ in range(l_x):
                    f_x *= 2
                    out += str(int(f_x))
                    f_x %= 1

               value.set_f_x_bin(out)

     def calculate_c_x(self):
          for value in self.table.values():
               f_x_bin = value.get_f_x_bin()
               c_x = f_x_bin[2:]
               
               value.set_c_x(c_x)

     def solve_table(self):
          self.calculate_l_x()
          self.calculate_f_x()
          self.calculate_f_x_bin()
          self.calculate_c_x()

     def __str__(self):
          output = ""

          for _, value in self.table.items():
               output += str(value) + "\n"
          
          return output