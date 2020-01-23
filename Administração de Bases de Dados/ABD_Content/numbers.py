def main():
     tamanho = 3000000
     
     fil = open("numeros.txt", "a")
     
     for i in range(tamanho):
          fil.write(str(i) + "\n" )

     fil.close()

main()