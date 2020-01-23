import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Corretor
 */
public class Corretor 
{
    public static void main(String[] args) 
    {
        InnerCorretor corretor = new InnerCorretor();
        corretor.readPhrase();
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * InnerCorretor
 */
class InnerCorretor 
{
    /////////////////////////////////////////

    //              Variables

    /////////////////////////////////////////
    private final String[] LETRAS = new String[]
    {   
        "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
        "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
        "á","ã","â","à","é","ẽ","ê","í","ó","õ","ô","ú","ũ","û",
        "Ã","Â","À","É","Ẽ","Ê","Ò","Õ","Ô","Ú","Ũ","Û"
    };
    private LinearHashTable<String> linear_hash;
    /////////////////////////////////////////

    //              Constructor

    /////////////////////////////////////////
    public InnerCorretor()
    {
        this(691171, "wordlist.txt");
    }
    public InnerCorretor( int size, String file_name )
    {
        linear_hash = new LinearHashTable<String>(size);
        adicionar_dicionario( file_name );
    }
    /////////////////////////////////////////

    //              Methods

    /////////////////////////////////////////
    public void readPhrase()
    {
        LinkedList<String> editor  = new LinkedList<>();
        Scanner            scanner = new Scanner(System.in);
        String content = "";
        
        print("Insira o seu texto:");
        try (
            FileReader     fr       = new FileReader("texto.txt");
            BufferedReader reader   = new BufferedReader( fr )
            ) 
        {
            while ((content = reader.readLine()) != null) 
            {
                content = removerSinaisPontuacao(content, '.');
                content = removerSinaisPontuacao(content, '!');
                content = removerSinaisPontuacao(content, '?');
                content = removerSinaisPontuacao(content, ';');
                content = removerSinaisPontuacao(content, ',');

                editor.add(content);
            }
        } 
        catch (IOException x) 
        {
            System.out.println( "Something wrong: " + x );
        }
        texto( editor );
    }

    private void texto(LinkedList<String> lines)
    {
        int i = 0;

        while ( i < lines.size() ) 
        {
            print(  ler_frase( lines.get(i) , i)  );
            i++;
        }
    }

    private String ler_frase(String lines, int linha)
    {
        String correcao = "";
        String word     = "";

        if ( lines.contains(" ") ) 
        {    
            String[] line_words = lines.split(" ");
            for (int i = 0; i < line_words.length; i++) 
            {
                word = line_words[i];

                if( linear_hash.procurar(word) == null )
                {
                    String sug = sugestao(word);
                    if (!sug.equals("[]")) 
                    {
                        correcao += "Palavra errada: " + word + "; Linha " + linha + "; Sugestoes: " + sug + "\n"; 
                    }
                }
            }
        }
        else
        {
            if( linear_hash.procurar(lines) == null )
            {
                String sug = sugestao(lines);
                if (!sug.equals("[]")) 
                {
                    correcao += "Palavra errada: " + lines + "; Linha " + linha + "; Sugestoes: " + sug + "\n"; 
                }
            }
        }
        return correcao;
    }

    public String removerSinaisPontuacao(String text, char simb)
    {
        StringBuffer string = new StringBuffer(text);

        for (int i = 0; i < string.length(); i++) 
        {
            if (string.charAt(i) == simb) 
            {
                string.deleteCharAt(i);
            }
        }
        return string.toString();
    }

    public  void adicionar_dicionario(String file)
    {
        try (
            FileReader     fr       = new FileReader(file);
            BufferedReader reader   = new BufferedReader( fr )
            ) 
        {
            String line = null;
            while ((line = reader.readLine()) != null) 
            {
                linear_hash.inserir( line );
            }
        } 
        catch (IOException x) 
        {
            System.out.println( "Something wrong: " + x );
        }
    }

    public  String adicionar_letra(String word)
    {
        int                 string_len  = word.length();
        LinkedList<String>  list        = stringLinkedList(word);

        String output       = "";
        String string_aux   = "";
        String comp_String  = null;

        for (int i = 0; i <= string_len; i++) 
        {
            for (int a = 0; a < LETRAS.length; a++) 
            {
                list.add(i, LETRAS[a]); 
                string_aux  = linkedListString(list);
                comp_String = linear_hash.procurar(string_aux);
                
                if (comp_String != null) 
                {
                    output += string_aux+";";
                }
                list = stringLinkedList(word);
            }
        }
        return (output == "") ? null : output;
    }

    public  String remover_letra(String word)
    {
        String output       = "";
        String string_aux   = "";
        LinkedList<String> list = stringLinkedList(word);
        for (int i = 0; i < list.size(); i++) 
        {
            list.remove(i);
            string_aux          = linkedListString(list);
            String comp_String  = linear_hash.procurar(string_aux);

            if (comp_String == null) 
            {
            }
            else if ( comp_String.equals(string_aux) ) 
            {
                output += string_aux+";";
            }
            list = stringLinkedList(word);
        }
        return (output == "") ? null : output;
    }

    public  String trocar_2letras(String word)
    {
        String output           = "";
        String string_aux       = "";
        LinkedList<String> list = stringLinkedList(word);
        
        for (int i = 0; i < list.size()-1; i++) 
        {
            // trocar letras
            String val1 = list.get(i);
            String val2 = list.get(i+1);
            list.set(i, val2);
            list.set(i+1, val1);

            string_aux          = linkedListString(list);
            String comp_String  = linear_hash.procurar(string_aux);

            if (comp_String == null) 
            {
            }
            else if ( comp_String.equals(string_aux) ) 
            {
                output += string_aux+";"; 
            }
            list = stringLinkedList(word);
        }
        return (output == "") ? null : output;
    }

    public  String sugestao(String word)
    {
        String word_exist = linear_hash.procurar(word);
        String store_var  = "";

        if (  word_exist == null ) 
        {
            word_exist = adicionar_letra(word);
            if (word_exist != null)
            {
                store_var += word_exist;
            }

            word_exist = remover_letra(word);
            if (word_exist != null)
            {
                store_var += word_exist;
            }

            word_exist = trocar_2letras(word);
            if ( word_exist != null )
            {
                store_var += word_exist;
            }

            return reajustString(store_var);
        }
        return null;
    }

    private String reajustString(String new_string)
    {
        String string = "[" + new_string;

        // Pequenos retoques à String
        if (string.charAt( string.length() -1) == ';') 
        {
            string = string.substring(0, (string.length()-1) )  + "]";    
        }
        else
        {
            string += "]";
        }
        return string;
    }

    private String linkedListString(LinkedList<String> list)
    {
        String word = "";
        for (int i = 0; i < list.size(); i++) 
        {
            word += list.get(i);    
        }
        return word;
    }

    private LinkedList<String> stringLinkedList(String word)
    {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < word.length(); i++) 
        {
            list.add( word.substring(i, i+1)  );    
        }
        return list;
    }

    private void print(String text)
    {
        System.out.println(text);
    }
}