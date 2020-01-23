from table_elias import *

def main():
     table = Table()
     
     table.set_prob('A', 0.15)
     table.set_prob('B', 0.1)
     table.set_prob('C', 0.7)
     table.set_prob('D', 0.05)
     
     table.solve_table()

     print(table)

if __name__ == "__main__":
    main()