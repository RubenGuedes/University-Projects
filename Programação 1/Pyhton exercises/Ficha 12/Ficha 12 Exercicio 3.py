def meio_segmento(p1,p2):
    x_med = (p1[0] + p2[0])/2
    y_med = (p1[1] + p2[1])/2
    return (x_med,y_med)

print(meio_segmento((1,9),(2,6)))