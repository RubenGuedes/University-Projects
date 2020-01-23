class Abstract:
    
    def __init__(self, head = [],  body = None, leftSymbol = '', rightSymbol = ''):

        self.body  = body
        self.head  = head
        self.leftSymbol = leftSymbol
        self.rightSymbol = rightSymbol

    def print_node(self):

        head = ''.join(self.head)
        
        body = self.body.print_node()

        return self.leftSymbol + '\\' + head + '.' + body + self.rightSymbol