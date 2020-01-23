class Main
{
	public static void main(String[] args) 
	{
		//InfixToPost inf = new InfixToPost("( ( 10 / ( 5 + 3 ) ) * 6 )", 10, true);
		InfixToPost inf = new InfixToPost("3 * ( 5 * 6 ) / 7", 10, true);
		System.out.println( inf );
		
		EvalPostfix post = new EvalPostfix( inf.getConverted(), true ) ;
		System.out.println( post );		
	}
}