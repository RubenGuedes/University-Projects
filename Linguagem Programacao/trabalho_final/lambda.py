import ply.yacc as yacc
import sys
import copy

from aplication import Aplication
from abstract import Abstract
from leaf import Leaf
from analysis_lex import tokens 

precedence = (('left','SPACE'),)

def p_termo_0(p):
    'start : termo'

    p[0] = p[1]

def p_termo_1(p):
    'termo :  variavel'

    p[0] = Leaf(p[1])

def p_termo_2(p):
    'termo : termo SPACE termo'
   
    p[0] =  Aplication(p[1], p[3])

def p_termo_3(p):
    'termo : LPAREN termo SPACE termo RPAREN'
   
    p[0] =  Aplication(p[2], p[4], p[1], p[5])

def p_termo_4(p):
    'termo : LAMBDA listval DOT termo'
    
    l = str_list(p[2])
    p[0] = Abstract(l, p[4])

def p_termo_5(p):
    'termo : LPAREN LAMBDA listval DOT termo RPAREN'
    
    l = str_list(p[3])
    p[0] = Abstract(l, p[5], p[1], p[6])

def p_listval_1(p):
    'listval : variavel'

    p[0] = p[1]

def p_listval_2(p):
    'listval :  variavel listval'

    p[0] = p[1] + p[2]
#############################################################
# Variavel
def p_variable(p):
    'variavel : CHARACTERS' 

    p[0] = p[1]
#############################################################
# Error rule for syntax errors
def p_error(p):

    print("Syntax error in input!")
#############################################################
# Build the parser
parser = yacc.yacc()
#############################################################
def str_list(exp):

    l = [i for i in exp]

    return l
    
def get_free(root):
    
    frees =  get_free_vars(root, [])

    return frees

def get_free_vars(root, seen):
    # Abstraction
    if type(root) == Abstract:

        head = root.head
        body = root.body

        new_seen = seen.copy()

        new_seen += head

        frees = get_free_vars(body, new_seen)

        return frees

    # Aplication
    elif type(root) == Aplication:

        left  = root.leftNode
        right = root.rightNode

        free_left  = get_free_vars(left , seen)
        free_right = get_free_vars(right, seen)

        return free_left + free_right

    # free
    else:

        if root.value not in seen:
            
            return [root.value]
    
        return []

def alpha(root, free_vars):

    new_root = alpha_eq(root, [], [{}], free_vars)

    return new_root

def alpha_eq(root, seen, sub, free_vars):

    if type(root) == Abstract:
        
        head = root.head
        body = root.body

        new_sub = sub.copy()
        
        new_sub.append({})

        new_head = []

        for i in head:

            if (i in seen) or (i in free_vars):

                j = choose_symbol(seen + free_vars, i)

                new_sub[-1][i] = j
                new_head.append(j)
                seen.append(j)

            else:

                new_sub[-1][i] = i
                new_head.append(i)
                seen.append(i)

        root.head = new_head
        root.body = alpha_eq(body, seen, new_sub, free_vars)

        return root

    elif type(root) == Aplication:

        left  = root.leftNode
        right = root.rightNode

        new_left  = alpha_eq(left, seen, sub, free_vars)
        new_right = alpha_eq(right, seen, sub, free_vars)

        root.leftNode  = new_left
        root.rightNode = new_right

        return root
    
    # livre ou lig?? 
    else: 

        for j in reversed(sub):

           if root.value in j.keys():

               return Leaf(j[root.value])

        return root
        
def choose_symbol(seen, old_symbol):

    code = ord(old_symbol)

    shift = 0

    while chr(shift + 97) in seen:

        shift = (shift + 1) % 36

    return chr(shift + 97)

def redex(node, sub):

    if type(node) == Abstract:

        node.body = redex(node.body, sub)

        return node

    elif type(node) == Aplication:

        left_node = node.leftNode
        right_node = node.rightNode

        node.leftNode = redex(left_node, sub)
        node.rightNode = redex(right_node, sub)

        return node

    elif type(node) == Leaf:

        if node.value in sub.keys():

            node = copy.deepcopy(sub[node.value])

        return node

def beta(node):

    if type(node) == Aplication:

        left_node = node.leftNode
        right_node = node.rightNode

        if type(left_node) == Abstract:

            sub = {left_node.head.pop(0): right_node}

            new_node = redex(left_node, sub)

            if len(left_node.head) == 0:

                node = new_node.body

            else:

                node = new_node

            node.leftSymbol = ""
            node.rightSymbol = ""
            
        else:

            node.leftNode = beta(left_node)
            node.rightNode = beta(right_node)

        return node

    elif type(node) == Abstract:

        node.body = beta(node.body)

        return node

    elif type(node) == Leaf:

        return node

def is_beta(node):

    if type(node) == Aplication:

        left_node = node.leftNode
        right_node = node.rightNode

        if type(left_node) == Abstract:

            return True
        else:

            beta_left = is_beta(left_node)
            beta_right = is_beta(right_node)

        return (beta_left or beta_right)

    elif type(node) == Abstract:

        beta_body = is_beta(node.body)

        return beta_body

    return False

def run_file(file_name):

    f = open(file_name, 'r')
    expressions =  f.readlines()

    for s in expressions:

        print('termo: ' + s.strip())

        root = parser.parse(s)

        print('<< ' + root.print_node())

        free_vars = get_free(root)
        new_root  = alpha(root, free_vars)

        print('>> ' + new_root.print_node())

    f.close()

def run_file_beta(file_name):

    f = open(file_name, 'r')
    expressions =  f.readlines()

    for s in expressions:

        print('termo: ' + s.strip())

        root = parser.parse(s)

        print('<- ' + root.print_node())

        free_vars = get_free(root)
        new_root  = alpha(root, free_vars)

        while is_beta(new_root):
            
            beta_expr = beta(new_root)

            beta_expr = alpha(beta_expr, free_vars)

            new_root = beta_expr

        print('-> ' + new_root.print_node())

    f.close()

while True:

    try:
        s = input('termo: ')
        
    except EOFError:
        break

    if not s: 
        continue

    if s == 'run':

        run_file('exemplos.txt')
        continue

    if s == 'runB':
        
        run_file_beta('exemplos.txt')
        continue

    if s == 'quit':
        break

    try:
        
        root = parser.parse(s)

        print('<- ' + root.print_node())

        free_vars = get_free(root)
        new_root  = alpha(root, free_vars)

        while is_beta(new_root):
            
            beta_expr = beta(new_root)

            free_vars = get_free(root)

            beta_expr = alpha(beta_expr, free_vars)

            new_root = beta_expr

        print('-> ' + new_root.print_node())

    except:
        pass
