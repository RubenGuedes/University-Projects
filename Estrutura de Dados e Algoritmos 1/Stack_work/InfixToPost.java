import java.util.StringTokenizer;
import java.util.Scanner;
class InfixToPost
{
	/*
		Variables
	 */
	private String 				converted;
	private String 				str;
	private String 				store_operations;
	private StringTokenizer 	st_tokenizer;
	private StringTokenizer 	st_tok_recov;
	private ArrayStack<String> 	stack;

	/*
		Constructors
			-> InfixToPost(boolean verbose)
			-> InfixToPost(String st, boolean verbose) 
			-> InfixToPost(String st, int tamanho, boolean verbose)
	 */
	public InfixToPost(boolean verbose)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira uma expressão para ser convertida:");
		str 	 		= scanner.nextLine();

		stack 			= new ArrayStack<>(10);
		st_tokenizer 	= new StringTokenizer( str );
		st_tok_recov	= new StringTokenizer( str ); // Use in toString() only;

		store_operations 	= "\nOperações:\n";
		converted 			= convert();

		printStackOperations(verbose);
	}
	public InfixToPost(String st, boolean verbose)
	{

		this(st, 10, verbose);
	}
	public InfixToPost(String st, int tamanho, boolean verbose)
	{
		stack 				= new ArrayStack<>(tamanho);
		st_tokenizer 		= new StringTokenizer(st);
		st_tok_recov		= new StringTokenizer(st); // Use in toString() only;

		store_operations 	= "\nOperações:\n";
		converted 			= convert();

		printStackOperations(verbose);
	}
	/*
						Methods
	 */
	/*
		convert method
	 */
	public String convert()
	{
		String convertion = "";
		while( st_tokenizer.hasMoreTokens() )
		{
			String token = st_tokenizer.nextToken();
			// if is number
			if(  !isOperator( token )  )
			{
				convertion += token + " ";
			}
			// not a number
			else
			{
				// encontrou ")" vai removendo da stack até encontrar "("
				if( token.compareTo(")") == 0 )
				{
					while( !(stack.top().compareTo("(") == 0)  )
					{
						convertion += stack.pop() + " ";
						store_operations += "stack.pop();\n"; 
					}
					stack.pop();
					store_operations += "stack.pop();\n"; 
				}
				else
				{	// se a stack tiver vazia adiciona qualquer simbolo
					if(stack.empty())
					{
						stack.push(token);
						store_operations += "stack.push( " + token + " );\n";
					}
					else
					{	// se o token tiver prioridade adicionar na stack
						if ( ( priority( token ) > priority(stack.top())  ) ) 
						{
							stack.push(token);
							store_operations += "stack.push( " + token + " );\n";
						}
						// se for "(" adicionar à stack
						else if (token.compareTo("(") == 0) 
						{
							stack.push(token);	
							store_operations += "stack.push( " + token + " );\n";
						}
						// enquanto o operador que esta na stack tiver prioridade coloca-lo no output
						else
						{
							if(  priority(stack.top()) >= priority(token)  )
							{
								convertion += stack.pop() + " ";
								store_operations += "stack.pop();\n";
							}
							stack.push(token);
							store_operations += "stack.push( " + token + " );\n";
						}
					}
				}
			}
		}
		// remover os restantes operadores que possam estar na stack
		while(!stack.empty())
		{
			convertion += stack.pop() + " ";
		}
		return convertion;
	}
	/*
		getConverted method
	 */
	public String getConverted()
	{

		return converted;
	}
	/*
		priority method
	 */
	public int priority(String op)
	{
		int prio = 0;
		switch(op.charAt(0))
		{
			case '+':
			case '-': 	prio = 1;
						break; 
			case '*':
			case '/':	prio = 2;
						break;
		}
		return prio;
	}
	/*
		isOperator method
	 */
	public boolean isOperator(String op)
	{
		switch( op.charAt(0) ) // converte o caracter em char para comparar
		{
			case '+':
			case '-':
			case '*':
			case '/':
			case '(':
			case ')': 	return true; 
			default: 	return false;
		}
	}
	/*
		printStackOperations method
	 */
	public void printStackOperations(boolean b){
		if(b)
		{
			System.out.println(store_operations);
		}
	}
	/*
		tokenizerTOString method
	 */
	public String tokenizerTOString(StringTokenizer x)
	{
		String res = "";
		while(x.hasMoreTokens())
		{
			res += x.nextToken() + " ";
		}
		return res;
	}
	/*
		toString method
	 */
	public String toString()
	{	
		return "Infix: " + tokenizerTOString(st_tok_recov) +"\nPostfix: "+ converted;
	}
}