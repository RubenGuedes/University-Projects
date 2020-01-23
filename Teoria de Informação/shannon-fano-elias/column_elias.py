class Column():

     def __init__( self, 
                   x, 
                   p_x = None, 
                   l_x = None,
                   f_x = None, 
                   f_x_bin = None, 
                   c_x = None
               ):
          self.x = x
          self.p_x = p_x
          self.l_x = l_x
          self.f_x = f_x
          self.f_x_bin = f_x_bin
          self.c_x = c_x

     def set_x(self, x):
          self.x = x

     def set_p_x(self, p_x):
          self.p_x = p_x
     
     def set_l_x(self, l_x):
          self.l_x = l_x

     def set_f_x(self, f_x):
          self.f_x = f_x
     
     def set_f_x_bin(self, f_x_bin):
          self.f_x_bin = f_x_bin
     
     def set_c_x(self, c_x):
          self.c_x = c_x

     def get_x(self):
          return self.x

     def get_p_x(self):
          return self.p_x
     
     def get_l_x(self):
          return self.l_x 
     
     def get_f_x(self):
          return self.f_x
     
     def get_f_x_bin(self):
          return self.f_x_bin
     
     def get_c_x(self):
          return self.c_x 

     def __str__(self):
          return  str(self.x) + " " + str(self.p_x) + " " + str(self.l_x) + " " + str(self.f_x) + " " + str(self.f_x_bin) + " " + str(self.c_x)
