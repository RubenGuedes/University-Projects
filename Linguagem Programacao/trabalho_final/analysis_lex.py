from ply import lex

tokens = (
    'DOT'       ,
    'LAMBDA'    ,
    'LPAREN'    ,
    'RPAREN'    ,
    'CHARACTERS',
    'SPACE'
)
#############################################################
# Regular expression rules for simple tokens
t_LAMBDA  = r'\\'
t_LPAREN  = r'\('
t_RPAREN  = r'\)'
t_DOT     = r'\.'

def t_SPACE(t):

    r'\ '
    return t

#############################################################
# Define a character
def t_CHARACTERS(t):
    r'[a-z]'  
    return t
#############################################################
# Define a rule so we can track line numbers
def t_newline(t):
    r'\n+'
    t.lexer.lineno += len(t.value)
#############################################################
# Error handling rule
def t_error(t):

    print("Illegal character '%s'" % t.value[0])
    t.lexer.skip(1)
#############################################################
# Build the lexer
lexer = lex.lex()
#############################################################
