def main():
     f = open("longitude.txt","a")

     for i in range(1000000):
          f.write( str(i) + "\n")
               
     f.close()

main()