# Converte segundos em dias

seg = int(input('Insira a quantidade de segundos: '))


dias1    =   seg / 86400 #obtenho a parte inteira
dias      =  dias1 - int(dias1) # obtenho só a parte decimal

horas1  =  dias * 24
horas    =  horas1 - int(horas1) 

minu1    =  horas * 60
minu     = minu1 - int(minu1)

segund =  minu *60

print( int(dias1),' dia ,', int(horas1),' horas ,' , int(minu1),' minutos ,', int(segund),' segundos')
