class Aplication:

    def __init__(self, leftNode = None, rightNode = None, leftSymbol = '', rightSymbol = ''):

        self.leftNode = leftNode
        self.rightNode = rightNode
        self.leftSymbol = leftSymbol
        self.rightSymbol = rightSymbol

    def print_node(self):

        left = self.leftNode.print_node()
        right = self.rightNode.print_node()

        return self.leftSymbol + left + ' ' + right + self.rightSymbol