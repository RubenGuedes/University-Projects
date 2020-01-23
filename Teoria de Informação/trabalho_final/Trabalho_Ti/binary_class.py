class Binary():
    def bin_to_char(self, expr):
        return chr(int('0b' + expr, 2))

    def bin_to_string(self, expr, size):
        result = ""

        for index in range(0, len(expr), size):
            ch_bin = expr[index : index + size]
            ch = self.bin_to_char(ch_bin)
            result += str(ch)
            
        return result

    def bin_to_list(self, bin_input):
        index = 0
        list_input = []
        
        content = bin_input[0]
        while content != '\0':
            list_input.append(content)

            index += 1
            content = bin_input[index]

        list_input.append(None)
        index += 1

        for ind in range(index, len(bin_input)):
            content_char = bin_input[ind]
            original = ord(content_char)
            list_input.append(original)
        
        return list_input
