import java.util.Scanner;

class Jogo
{
	public static void main( String[] args )
	{
		consola();
		// grafica();
	}

	// jogar atraves da consola
	public static void consola(){
		Scanner scanner = new Scanner(System.in);
		Campo campo;
		int x, y, tamanho = 0, cores = 0;
		
		System.out.print("Qual é o tamanho do mapa: ");
		tamanho = scanner.nextInt();
		System.out.print("Qual é a quantidade de cores: ");
		cores = scanner.nextInt();
		campo = new Campo(tamanho, cores);

		// colocar o campo com jogadas possiveis
		while( !campo.pecas_disp() ){
			campo.preencher_campo();
		}

		// Jogar enquanto o campo tiver jogadas possiveis
		while( campo.pecas_disp() )
		{
			campo.print_campo();

			System.out.println("Escolher posições");
			System.out.print("x = ");
			x = scanner.nextInt();
			System.out.print("y = ");
			y = scanner.nextInt();

			campo.pesquisar(y, x);
		}
		System.out.println("Terminou o Jogo");
		System.out.println("Pontuação Máxima: " + campo.get_pontuacao_total()+"\n");
		scanner.close();
	}

	// Jogar atraves da parte grafica
	public static void grafica(){
		JanelaMain j = new JanelaMain();
		j.setVisible(true);
	}
}