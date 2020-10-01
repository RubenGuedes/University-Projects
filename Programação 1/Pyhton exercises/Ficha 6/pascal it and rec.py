def pascal(n,k):
    if n<k:
        print('erro')
    if k==0 or k==n:
        return 1
    else:
        return pascal(n-1,k-1)+pascal(n-1,k)

def trianguloPascal_iterativo(n):
        linha=0
        while linha<n:
            l=''
            coluna=0
            while linha>=coluna:
                l+=str(pascal(linha,coluna))+' '
                coluna+=1
            linha+=1
            print(l)
            

def trianguloPascal_recursivo(n):
    colunas = n
    if n!=0:
        while colunas != 0:
             colunas -= 1
             return str(pascal(n,colunas)) + " "
    else:
        trianguloPascal_recursivo(t-1)
        
print(trianguloPascal_recursivo(3))
