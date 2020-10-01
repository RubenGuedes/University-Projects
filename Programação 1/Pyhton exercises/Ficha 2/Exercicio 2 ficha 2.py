import math

raio = float(input('Insira o raio: '))

perímetro = 2*math.pi*raio
area_circulo = 2*math.pi*(raio**2)
vol_esfera = (4/3)*math.pi*(raio**3)

print(round(perímetro,3) ,',', round(area_circulo,3), ',',round(vol_esfera,3))
