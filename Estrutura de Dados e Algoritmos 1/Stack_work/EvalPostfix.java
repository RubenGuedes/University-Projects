import java.util.StringTokenizer;
import java.util.Scanner;
class EvalPostfix
{
	/*
		Variables
	 */
	private String 				finalvalue;
	private String 				store_operations;
	private StringTokenizer		st_tok_recov;
	private StringTokenizer 	st_tokenizer;
	private ArrayStack<String> 	stack;
	/*
		Constructor
			-> EvalPostfix(boolean verbose)
			-> EvalPostfix(String st, boolean verbose)
			-> EvalPostfix(String st, int tamanho, boolean verbose)
	 */
	public EvalPostfix(boolean verbose)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira uma expressão para ser avaliada: ");
		String st = scanner.nextLine();

		stack 			= new ArrayStack<>(10);
		st_tokenizer 	= new StringTokenizer(st);
		st_tok_recov	= new StringTokenizer(st); // Use in toString() only;

		store_operations	= "\nOperações:\n";
		finalvalue 			= expression_value();

		printStackOperations(verbose);
	}
	public EvalPostfix(String st, boolean verbose)
	{

		this(st, 10, verbose);
	}
	public EvalPostfix(String st, int tamanho, boolean verbose)
	{
		stack 		 = new ArrayStack<>(tamanho);
		st_tokenizer = new StringTokenizer(st);
		st_tok_recov = new StringTokenizer(st); // Use in toString() only;

		store_operations = "\nOperações:\n";
		finalvalue		 = expression_value();

		printStackOperations(verbose);
	}
	/*
						Methods
	 */
	/*
		getValue method
	 */
	public String getValue()
	{
		
		return finalvalue;
	}
	/*
		expression_value method
	 */
	public String expression_value()
	{
		while(st_tokenizer.hasMoreTokens())
		{
			String token = st_tokenizer.nextToken();
			if( !isOperator(token) )
			{
				stack.push(token);

				store_operations += "stack.push( "+ token +" );\n";
			}
			else
			{
				float op2 = Float.valueOf( stack.pop() );
				float op1 = Float.valueOf( stack.pop() );
				String resultado = operation(op1, op2, token);
				stack.push(  resultado );

				store_operations += "stack.pop();\n";
				store_operations += "stack.pop();\n";
				store_operations += "stack.push(" + String.valueOf(op1) + " " + token + " " + String.valueOf(op2) +");\n";
			}
		}
		return stack.top();
	}
	/*
		operation method
	 */
	public String operation(float val1, float val2, String operator)
	{
		Float value = 0.0f;
		switch( operator.charAt(0) ) // converte o caracter em char para comparar
		{
			case '+': 	value = val1 + val2;
						break;
			case '-':	value = val1 - val2;
						break;
			case '*':	value = val1 * val2;
						break;
			case '/':	value = val1 / val2;
						break; 
		}
		return value.toString();
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

		return "Postfix: "+ tokenizerTOString(st_tok_recov) +"\nAvaliação final: "+finalvalue;
	}

}