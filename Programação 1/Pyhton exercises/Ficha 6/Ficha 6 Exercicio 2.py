def fibonacci(n):
    if n == 0 or n == 1:
        return 1
    elif n>1:
        return fibonacci(n-1) + fibonacci(n-2)

print(fibonacci(7))