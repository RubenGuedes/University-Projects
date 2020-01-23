import java.util.Random;

class Peca
{
	private Cor cor;
	private int num;
	
	//------------------ Construtor -------------------
	public Peca(){
		cor = Cor.NULL;
		num = 9;
	}
	//-------------------- Métodos --------------------
	
	// Devolve a cor
	public Cor show_color(){
		return cor;
	} 
	// Devolve o numero correspondente à cor
	public int show_color_num(){
		return num;
	}

	// colocar a variavel de classe com NULL
	public void cor_null(){
		cor = Cor.NULL;
		num = 9;
	}
	
	// Coloca a variavel de classe com uma cor aleatoria
	public void cor_peca(){
		switch( numero_random() ){
			case 0: cor = Cor.VERDE;
					num = 0;
					break; // Verde
			case 1: cor = Cor.VERMELHO;
					num = 1;
					break; // Vermelho
			case 2: cor = Cor.PRETO;
					num = 2;
					break; // Preto
			case 3: cor = Cor.CASTANHO;
					num = 3;
					break; // Castanho
			case 4: cor = Cor.AZUL;
					num = 4;
					break; // Azul
			case 5: cor = Cor.ROXO;
					num = 5;
					break; // Roxo
			case 6: cor = Cor.ROSA;
					num = 6;
					break; // Rosa
			case 7: cor = Cor.CINZA;
					num = 7;
					break; // Cinza
			case 8: cor = Cor.BRONZE;
					num = 8;
					break; // Bronze
		}
	}
	// Gera número aleatório
	public int numero_random(){
		Random num_random = new Random();
		return num_random.nextInt(9);
	}
}