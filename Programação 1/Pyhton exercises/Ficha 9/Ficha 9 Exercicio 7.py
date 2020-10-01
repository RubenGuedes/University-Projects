def palindromo(string):
    if string == string[::-1]:
        return True
    else:
        return False

print(palindromo("revr"))