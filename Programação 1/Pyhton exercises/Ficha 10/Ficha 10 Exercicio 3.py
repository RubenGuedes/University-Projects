# a)

def corta(lista):
    lista.pop(0)
    lista.pop(len(lista)-1)

l = [1,2,3,4]
corta(l)
print(l)

# b)
def meio(lista):
    cop_lista = lista[:]
    return cop_lista

#print(meio([1,2,3]))

