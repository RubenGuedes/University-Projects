def mdc(m,n):
    if n == m:
        return m
    elif m>n :
        return mdc(m-n, n)
    elif m<n:
        return mdc(m, n-m)

print(mdc(4,8))