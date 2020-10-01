def substitui(s,old,new):
    new_string = ''
    # Input  = //s = Ananas // old = A  // new = g
    # Output = Gngngs
    for i in range(len(s)):
        if s[i] == str(old):
            new_string = new_string + new
        else:
            new_string = new_string + s[i]

    return new_string

print(substitui('ola','a','g'))