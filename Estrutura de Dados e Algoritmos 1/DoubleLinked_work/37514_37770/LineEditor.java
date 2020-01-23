import java.util.Scanner;
import java.util.ListIterator;
/**
 * Main
 */
class Main 
{
    /**
     * Variables
     */
    // indica a posicao do editor
    private                 int     posicao;
    // editor Double Linked List
    private DoubleLinkedList<String>   editor;
    /**
     * Constructor
     */
    public Main()
    {
        editor  = new DoubleLinkedList<>();
        posicao = 0;
    }
    /**
     * Methods
     */
    /**
     * insertEnd(String text)
     * @param text
     */
    public void insertEnd(String text)
    {
        editor.add(text);
    }
    /**
     * insert(String text, int index) Method
     * @param text
     * @param index
     */
    public void insert(String text, int index)
    {
        if(index >= 0 && index <= editor.size())
            editor.add(index, text);
        else
            System.out.println("\nPosicao fora dos limites.\n!TEXTO NAO ADICIONADO!\n");
    }
    /**
     * delete(int index) Method
     * @param index
     */
    public void delete(int index)
    {
        if(index >= 0 && index < editor.size())
        {
            editor.remove(index);

            if (posicao >= editor.size())
                posicao--;
        }
        else
            System.out.println("\nPosicao fora dos limites.\n!REMOCAO NAO EFECTUADA!\n");
    }
    /**
     * print() Method
     */
    public String print()
    {
        int pos = 0;
        String document = "";
        System.out.println("\nCursor: " + posicao + "\n");
        System.out.println("Print:");
        for (String var : editor) 
        {
            document += "Linha: " + pos + " || Texto: " + var + "\n";   
            pos++;
        }
        return document;
    }
    public String printBack()
    {
        int pos = editor.size() - 1;
        String document = "";
        System.out.println("\nCursor: " + posicao + "\n");
        System.out.println("Print:");

        ListIterator<String> ite = editor.backIterator();
        while (ite.hasPrevious()) 
        {
            document += "Linha: " + pos + " || Texto: " + ite.previous() + "\n";   
            pos--;
        }

        return document;
    }
    /**
     * edit(String text, int index) Method
     * @param text
     * @param index
     */
    public void edit(String text, int index)
    {
        if(index >= 0 && index < editor.size())
            editor.set(index, text);
        else
            System.out.println("\nPosicao fora dos limites.\n!ALTERACAO NAO EFECTUADA!\n");
    }
    /**
     * search(String exp) Method
     * @param exp
     * @return
     */
    public String search(String exp)
    {
        // String to save the contents
        String expr_searched = "";
        // Array duplo com o numero da linha e o texto pertencente
        String[][] out = new String[editor.size()][2];
        // posição do array duplo "out"
        int pos_out = 0;
        // posicao no editor
        int line_editor = 0;
        // percorrer as linhas do editor
        for (String var : editor)
        {
            // Copy var contents
            String var_aux = var;

            // remove '?', ',', ';', '!', '.', '(', ')', '"' symbols
            if (hasSymbol(var, '?') ) 
                var = removeSymbols(var, '?');
            if (hasSymbol(var, '!') ) 
                var = removeSymbols(var, '!');
            if (hasSymbol(var, ',') ) 
                var = removeSymbols(var, ',');
            if (hasSymbol(var, ';') ) 
                var = removeSymbols(var, ';');
            if (hasSymbol(var, '.') ) 
                var = removeSymbols(var, '.');
            if (hasSymbol(var, '(') ) 
                var = removeSymbols(var, '(');
            if (hasSymbol(var, ')') ) 
                var = removeSymbols(var, ')');
            if (hasSymbol(var, '"') ) 
                var = removeSymbols(var, '"');
            
            // Array com a frase
            String[] line = var.split(" ");
            // percorrer a frase
            for (int i = 0; i < line.length; i++) 
            {
                CharSequence string_b = exp;
                if (line[i].contains(string_b) ) 
                {
                    out[pos_out][0] = String.valueOf(line_editor);
                    out[pos_out][1] = String.valueOf(var_aux); 
                    pos_out++;
                    break;
                }    
            }
            line_editor++;
        }
        for (int i = 0; i < out.length; i++) 
        {
            // If array doesn't have more content; break the cycle
            if (out[i][0] == null)
                break;
            expr_searched += "Linha: " + out[i][0] + " || Texto: " + out[i][1] + "\n";
        }
        return expr_searched;
    }
    /**
     * hasSymbol(String phrase, char symbol) Method
     * @param symbol
     * @return true : false
     */
    public boolean hasSymbol(String phrase, char symbol)
    {
        short flag = 0;
        for (int i = 0; i < phrase.length(); i++) 
        {
            if (phrase.charAt(i) == symbol) 
            {
                flag++;
                break;
            }
        }
        return (flag > 0) ? true : false;
    }
    /**
     * removeSymbols(String phrase, char symbol) Method
     * @param phrase
     * @param symbol
     * @return new_phrase
     */
    public String removeSymbols(String phrase, char symbol)
    {
        String new_phrase = "";
        int phrase_len = phrase.length();
        for (int i = 0; i < phrase_len; i++) 
        {
            char value = phrase.charAt(i); 
            if (value != symbol) 
            {
                new_phrase += String.valueOf(value);
            }
        }
        return new_phrase;
    }
    /**
     * lineUp() Method
     */
    public void lineUp()
    {
        if(posicao > 0)
        {
            posicao--;
            System.out.println("Cursor: " + posicao);
        }
        else
            System.out.println("CURSOR NAO MOVIDO!");
    }
    /**
     * lineDown() Method
     */
    public void lineDown()
    {
        if (posicao < (editor.size()-1) )
        {
            posicao++;
            System.out.println("Cursor: " + posicao);
        }
            else    
            System.out.println("CURSOR NAO MOVIDO!");
    }
    /**
     * getPosition() Method
     * @return posicao
     */
    public int getPosition()
    {
        return posicao;
    }
}
/**
 * //////////////////////////////////////////////////////////////////////////////////////////////////
 *                                       LINE EDITOR
 * //////////////////////////////////////////////////////////////////////////////////////////////////
 */
class LineEditor
{
    // Editor 
    private static Main  editor = new Main();
    /**
     * Main
     * @param args
     */
    public static void main(String[] args) 
    {
        inter();
    }
    /**
     * Interface
     */
    public static void inter()
    {
        boolean exit = false;
        print_acao("------------------------\n       Aplicacao\n------------------------");

        while (!exit) 
        {
            String  text = "";
            int     line;
            print_comandos();

            switch( scanner_text("Insira um comando") )
            {
                // Move Next
                case "MN":  editor.lineDown();
                            break;
                // Move Previous
                case "MP":  editor.lineUp();
                            break;
                // Print
                case "PR":  print_acao( editor.print() );
                            break;
                // Print back
                case "PRB": print_acao( editor.printBack() );
                            break;
                // Exit
                case "E":   exit = true; 
                            print_acao("\nTHE END!\n");
                            break;
                // Insert End
                case "IE":  text = scanner_text("Escreva o texto que pretende inserir:");
                            editor.insertEnd(text);
                            break;
                // Insert
                case "I":   text = scanner_text("Escreva o texto que pretende inserir.");
                            line = scanner_line("Qual e a linha que quer inserir?");
                            editor.insert(text, line);
                            break;
                // Delete
                case "D":   line = scanner_line("Qual e a linha que quer apagar?");
                            editor.delete(line);
                            break;
                // Edit
                case "ED":  text = scanner_text("Escreva o texto que pretende inserir:");
                            line = scanner_line("Qual a a linha que quer inserir?");
                            editor.edit(text, line);
                            break;
                // Search Expression
                case "SR":  text = scanner_text("Qual e o conteudo que quer procurar?");
                            print_acao( editor.search(text) );
                            break;
                // Not a command
                default:    print_acao("Operacao Errada!!\nTente usar um comando dentro da lista de comandos que e fornecida!");
                            break;
            }   
        }
    }
    /**
     * scanner_text(String phrase) Method
     * @param phrase
     * @return text
     */
    public static String scanner_text(String phrase)
    {
        Scanner scanner = new Scanner(System.in);
        print_acao(phrase);
        String text = scanner.nextLine();

        return text;
    }
    /**
     * scanner_line(String phrase) Method
     * @param phrase
     * @return int
     */
    public static int scanner_line(String phrase)
    {
        Scanner scanner = new Scanner(System.in);
        print_acao(phrase);
        String text = scanner.nextLine();

        return (text.equals("")) ? editor.getPosition() : Integer.valueOf(text);
    }
    /**
     * print_acao(String text) Method
     * @param text
     */
    public static void print_acao(String text)
    {
        System.out.println(text);
    }
    /**
     * print_comandos() Method
     */
    public static void print_comandos()
    {
        System.out.println("Comandos:\n-> MN (Move Next)\n-> MP (Move Previous)\n-> PR (Print)\n-> PRB (Print Back)\n-> SR (Search)\n-> E  (Exit)\n-> IE (Insert End)\n-> I  (Insert)\n-> D  (Delete)\n-> ED (Edit)");
    }
}