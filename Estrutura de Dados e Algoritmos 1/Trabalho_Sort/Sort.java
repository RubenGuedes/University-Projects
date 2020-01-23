import java.util.Scanner;
/**
 * Algoritmos de ordenação:
 *   -> Shellsort
 *   -> Bubblesort
 * 
 * Feito por:
 *   -> Pedro Mendes:  37770
 *   -> Rúben Peixoto: 37514
 */
public class Sort 
{
     public static void main(String[] args) 
     {
          Comparable[] array_le = le_array(); 
          Integer[] arr         = new Integer[]{10,2,100,20,50,5};
          String[]  arr_string  = new String[]{"Ana", "Ruben", "Pedro", "Amanda"};

          // Testar a ordenação quando executado o metodo 'le_array()'
          shellsort(array_le);
          printArray(array_le);
          
          // Strings
          shellsort( arr_string );
          printArray( arr_string );

          // Inteiros
          bubblesort( arr );
          printArray( arr );
     }
     /* 
          Métodos do enunciado
      */
     public static void shellsort(Comparable[] array)
     {
          int pos;
          int len   = array.length;
          int chave = len;

          while( chave > 0 )
          {
               for (int ind = chave; ind < len; ind++) 
               {
                    // Guarda o elemento na posição 'ind' para não só comparar, como também para trocar com outro elemento
                    Comparable temp = array[ ind ];
                    // Vai comparar o elemento que esta a 'chave' de distancia
                    for ( pos = ind;     pos >= chave && (temp.compareTo( array[ pos - chave ] ) < 0) ;     pos -= chave ) 
                    {
                         array[ pos ] = array[pos - chave];
                    }
                    array[ pos ] = temp;
               }
               chave /= 2;
          }
     }
     public static void bubblesort(Comparable[] array)
     {
          int len = array.length;

          // Numero de vezes que vai executar a ordenação até termos a certeza que fica ordenado
          for(int n_vezes = 0; n_vezes < len; n_vezes++)
          {
               for(int j = 0; j < (len-1); j++)
               {
                    Comparable temp = array[j + 1];
                    if( temp.compareTo(array[j])  < 0 )
                    {
                         array[j + 1] = array[j];
                         array[j] = temp;
                    }

               }
          }
     }

     public static Comparable[] le_array()
     {
          Comparable[]   array_comp;
          print("Insira conteúdo:\nATENÇÃO:\n-> Com inteiros positivos os algoritmos de ordenação irão conseguir trabalhar os valores como inteiros e não como strings\n-> Numeros com virgula ou sinais negativos o programa considera-os strings\n-> PARA PARAR O SCANNER TEM QUE PRESSIONAR 'Enter' SEM INTRODUZIR QUALQUER INPUT");
          
          String userinput = userInput();
          String st = userinput + ";";
          
          while ( !(userinput.compareTo("") == 0) ) 
          {
               userinput = userInput();
               st += userinput + ";";
          }
          // retira o ultimo ';' da string e forma uma array com o comando 'split'
          array_comp = st.substring(0, (st.length()-2) ).split(";");

          // Se as strings contem apenas digitos podemos converter
          return stringNumbers( array_comp );
     }
     public static void printArray(Object[] obj)
     {
          String st = "";
          for (int i = 0; i < obj.length; i++) 
          {
               st += obj[i] + ";";     
          }
          print( st.substring(0, st.length()-1) );
     }
     /*
          Private methods
      */
     private static Comparable[] stringNumbers( Comparable[] array )
     {
          int len_array          = array.length;
          String teste           = "[0-9]+";
          Comparable[] aux_array = new Comparable[array.length];

          for (int i = 0; i < len_array; i++) 
          {
               String ele = String.valueOf(array[i]); 
               if ( ele.matches(teste) ) 
               {
                    aux_array[i] = Integer.parseInt(ele);
               }
          }

          int preenchido = numElementos(aux_array);
          int tam_array  = aux_array.length;
          // se o array auxiliar estiver todo preenchido devolve-lo
          if( preenchido == tam_array)
          {
               return aux_array;
          }
          // caso contrario retornar o original
          else
          {
               return array;
          }
     }
     private static int numElementos( Comparable[] array )
     {
          int count = 0;
          for (int i = 0; i < array.length; i++) 
          {
               if ( !(String.valueOf(array[i]).compareTo("") == 0 ) && !(array[i] == null) ) 
               {
                    count += 1;
               }
          }
          return count;
     }
     private static String userInput()
     {
          Scanner scanner = new Scanner(System.in);
          String st = scanner.nextLine();
          return st;
     }
     private static void print(String val)
     {
          System.out.println(val);
     }
} 