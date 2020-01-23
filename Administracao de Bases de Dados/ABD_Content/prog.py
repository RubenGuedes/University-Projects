def main():
     files = open("content.txt", "r")
     buffer = []

     for coord in files:
          buffer.append(coord)

     files.close()

     file_name = input("Insira o nome do novo ficheiro:")
     file_name = file_name + ".txt"
     
     # ADD TO A NEW FILE
     new_file = open(file_name, "a")

     for i in range(30000):
          for x in range(100):
               new_file.write(buffer[x])
     
     new_file.close()

main()