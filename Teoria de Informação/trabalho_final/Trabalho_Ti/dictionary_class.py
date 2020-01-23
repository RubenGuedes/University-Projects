class Dictionary():

    def __init__(self):
        self.dictionary = {}
        self.index = 0
        self.add_item("")

    def add_item(self, item):
        self.dictionary[self.index] = item
        self.index += 1

    def find_item(self, index):
        try:
            return self.dictionary[index]
        except:
            return -1
    
    def item_index(self, item):
        
        for (key, value) in self.dictionary.items():
            if value == item:
                return key

        return -1

    def initialize_dictionary(self, list_items):
        index = 0
        for item in list_items:
            index += 1
            
            if item == None:
                break

            self.add_item(item)
            
        return index

    def __str__(self):
        output = ""
        
        for value in self.dictionary.values():
            output += value + "\n"

        return output