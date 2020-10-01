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
    if n==0:
        return "1"
    else:
        return trianguloPascal_recursivo(n-1) +'\n'+ linha_triangulo(n,n)


def linha_triangulo(n_linha,n_coluna):
    if  n_coluna == 0:
        return '1' #str(pascal(n_linha, n_coluna))

    else:
        return linha_triangulo(n_linha,n_coluna-1) + ' '+ str(pascal ( n_linha,  n_coluna))


#print(trianguloPascal_recursivo(3))
print(linha_triangulo(5,5))

















    
