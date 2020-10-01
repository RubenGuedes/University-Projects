import math

def distancia(x1, y1, x2, y2):
     dist = math.sqrt(((x2-x1)**2)+((y2-y1)**2))
     
     print(round(dist,2))
     
     return round(dist,2)

