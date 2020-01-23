// package Trabalho;
import java.util.LinkedList;
import java.util.Random;

class Campo
{
	//---------------Variaveis--------------------
	private int 				tamanho; 		 // Tamanho do campo
	private int					quantidade;		 // Quantidade de cores diferentes que podem aparecer no campo
	private double 				pontuacao_total; // Pontuação total do jogo
	private Peca[][] 			camp;  			 // Campo que irá conter os objetos "Peca"
	private LinkedList<Cor>		disponiveis;	 // Tem a quantidade de cores que podem aparecer no campo

	//---------------Construtores-----------------
	public Campo(int tamanho, int quantidade)
	{
		this.tamanho 		= tamanho;
		this.quantidade 	= quantidade;
		pontuacao_total 	= 0;
		camp 				= new Peca[tamanho][tamanho]; 
		disponiveis			= new LinkedList<>();
		preencher_campo(); // quando instanciada a classe "Campo" queremos que o mapa esteja LOGO criado
	}
	// Construtor Default
	public Campo()
	{
		this(10, 4); // tamanho = 10, quantidade = 4
	}
	//------------------------------------------------------------
	// 			  	       TODOS OS METODOS:
	//
	//	-> Metodos para criar uma LinkedList da classe "Cor" com, segundo a quantidade definida pelo jogador, as cores que podem aparecer no campo
	//
	//		void	disponiveis_toString();		// print da matriz com as cores que vão aparecer no campo
	//		void	linked_disponiveis();		// cria a matriz com as cores que vão aparecer no campo
	//
	//	-> Metodos para "manipular/obter" a matriz ou algo dela
	//
	//		void	null_pos(int x, int y); 			 		// null numa posição
	//		void 	null_positions( Coordenadas p ); 		 	// null em várias posições 
	//		void	cor_pos(int x, int y);				 		// atribui uma cor QUALQUER numa certa posicao
	//		Cor		get_color( int x, int y);			 		// devolve a cor que esta numa certa posicao
	//		int		get_num_color(int x, int y)					// devolve o número correspondente à cor
	//		void	colocar_cores_disponiveis(int x, int y);	// consuante as CORES DISPONIVEIS, colocá-las numa certa posicao do campo 
	//		void 	preencher_campo();							// Vai colocar NULL consuante os coordenadas presentes em p
	//		int		get_tamanho()								// Devolve o tamanho da linha ou coluna (tanto faz)
	//		void	print_campo();								// Função com o objetivo de dar print do mapa na consola (AUXILIAR APENAS)
	//
	//	-> Metodos para pesquisar e atribuir pontuação
	//		
	//		double	get_pontuacao_conjunto(int n_cores); // devolve a pontuação de um determinado conjunto
	//		double	get_pontuacao_total();				 // devolve a pontuacao total do jogo
	//		void	pesquisar(int x, int y);			 // pesquisa pelo conjunto e eliminando-o e colocando novas pecas
	//		void	baixar_pecas();						 // função para mover as pecas para baixo no mapa
	//		void	preencher_espacos_null();			 // função que vai preencher todos os espaços que estão a "NULL"
	//		boolean pecas_disp();						 // funcao que devolve true caso haja jogas no mapa caso contrário false

	//------------------------------------------------------------

	// print da matriz com as cores que podem aparecer no jogo
	public void disponiveis_toString(){
		System.out.println( disponiveis.toString() );
	}

	// Metodos para criar um array com a quantidade de cores aleatorias que PODEM aparecer no campo
	private void linked_disponiveis()
	{
		Peca p = new Peca();
		int i = 0;
		while( i < quantidade )
		{
			p.cor_peca();
			if( !disponiveis.contains( p.show_color() ) )
			{
				disponiveis.add( p.show_color() );
				i++;
			}
		}
	}

	// Colocar "NULL" numa posição (x, y)
	private void null_pos(int x, int y){
		Peca p = new Peca();
		p.cor_null();
		camp[y][x] = p;
	}

	// Coloca "NULL" em várias posições da matriz, para isso recebe como argumento um array bidimensional com as coordenadas.
	private void null_positions(Coordenadas p)
	{
		int i = 0;
		while( !p.coordenadas_vazias(i) ){
			int[] j = p.get_coordenadas(i);
			null_pos(  j[0],  j[1] );
			i++;
		}
	}
	// Colocar uma peca numa posição (x, y)
	private void cor_pos(int x, int y){
		Peca p = new Peca();
		
		while( !disponiveis.contains( p.show_color() ) ){
			p.cor_peca();
		}
		camp[y][x] = p;
	}

	// Retorna a Cor de uma determinada posição do campo
	public Cor	get_color(int x, int y){
		return camp[y][x].show_color();
	}

	// Retorna o número da cor correspondente
	public int get_num_color(int x, int y){
		return camp[y][x].show_color_num();
	}
	
	// Consuante as cores disponiveis inseri-las no campo
	private void colocar_cores_disponiveis(int x, int y)
	{
		Peca p	= new Peca();
		while( !disponiveis.contains( p.show_color()) )
		{
			p.cor_peca();		 	
			camp[x][y] = p;
		}
	}

	// Preenche todo a matriz com peças
	public void preencher_campo()
	{
		linked_disponiveis();
		for(int x = 0; x < tamanho; x++){
			for(int y = 0; y < tamanho; y++)
			{
				colocar_cores_disponiveis(x, y);
			}
		}
	}

	// Devolve o tamanho da linha ou da coluna, como são iguais tanto faz
	public int	get_tamanho(){
		return tamanho;
	}

	// Print do mapa
	public void print_campo(){
		for (int i = 0; i <tamanho ; i++) {
			System.out.print("|");
			for (int j = 0; j <tamanho ; j++) {
				System.out.printf("%8s|",camp[i][j].show_color() );
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	// Pontuação atribuida a um conjunto de uma certa cor
	private double get_pontuacao_conjunto( int n_cores ){
		return Math.pow(n_cores, 2);
	}

	// Print da pontuação parcial
	public void print_pontuacao_parcial(Double pont){
		System.out.println("Pontuação Parcial: " + pont);
	}

	// Retorna a pontuação total do jogo 
	public double get_pontuacao_total()
	{
		return pontuacao_total;
	}

	// Funcao que implementa um algoritmo para pesquisar um conjunto de uma mesma cor eliminando-o
	public void	pesquisar(int x, int y)
	{
		int 		coord_x				= x; // guarda a coordenada "x" para poder voltar atras
		int 		coord_y				= y; // guarda a coordenada "y" para poder voltar atras 
		int 		n_cores_encontradas = 0; // conta o numero de cores iguais em toda a pesquisa
		double 		pontua				= 0; // pontuação do conunto encontrado
		boolean		pos_usada			= false; // se a posição escolhida já foi contada devolve true(para não contar mais)

		Cor 		c 					= get_color(y, x); 		// Cor que o jopgador selecionou
		Coordenadas coordenadas 		= new Coordenadas(); 	// para receber todas as coordenadas vindas de crd
		int[][] 	crd 				= new int[tamanho][2]; 	// para registar as coordenadas consuante uma direção
		int 		pos 				= 0;					// posição no array "crd" 
		

		// PESQUISAR PARA ESQUERDA
		while((coord_x-1) >= -1){
			if( get_color(coord_y, coord_x) == c ){		
				crd[pos][1] = coord_y;
				crd[pos][0] = coord_x; 

				coord_x--;
				pos++;
			}else{
				break;
			}
		}
		if( pos >= 3){
			pos_usada = true;
			n_cores_encontradas += pos;
			coordenadas.add_coor(crd, pos); 	// adicionar as coordenadas
		}
		pos = 0;
		coord_x = x;

		// PESQUISAR PARA CIMA
		while((coord_y-1) >= -1){
			if( get_color(coord_y, coord_x) == c ){
				crd[pos][1] = coord_y;
				crd[pos][0] = coord_x;

				coord_y--;
				pos++;
			}else{
				break;
			}
		}
		if( pos >= 3){
			if(pos_usada == true){
				n_cores_encontradas += pos - 1;
				coordenadas.add_coor(crd, pos);
			}
			pos_usada = true;
			n_cores_encontradas += pos;
			coordenadas.add_coor(crd, pos);
		}
		pos = 0;
		coord_y = y;

		// PESQUISAR PARA DIREITA
		while((coord_x+1) <= tamanho){
			if( get_color(coord_y, coord_x) == c ){
				crd[pos][1] = coord_y;
				crd[pos][0] = coord_x;

				coord_x++;
				pos++;
			}else{
				break;
			}
		}
		if( pos >= 3){
			if(pos_usada == true){
				n_cores_encontradas += pos - 1;
				coordenadas.add_coor(crd, pos);
			}
			pos_usada = true;
			n_cores_encontradas += pos;
			coordenadas.add_coor(crd, pos);
		}
		pos = 0;
		coord_x = x;

		// PESQUISAR PARA BAIXO
		while((coord_y+1) <= tamanho){
			if( get_color(coord_y, coord_x) == c ){
				crd[pos][1] = coord_y;
				crd[pos][0] = coord_x;

				coord_y++;
				pos++;
			}else{
				break;
			}
		}
		if( pos >= 3){
			if(pos_usada == true){
				n_cores_encontradas += pos - 1;
				coordenadas.add_coor(crd, pos);
			}
			n_cores_encontradas += pos;
			coordenadas.add_coor(crd, pos);
		}
		pos = 0;
		coord_y = y;

		// Colocar os espaços com null
		null_positions(coordenadas);

		// baixar peças
		baixar_pecas();

		// preencher os espaços vazios do campo com pecas de cor aleatoria
		preencher_espacos_null();

		// atribui a pontuação ao conjunto de cores encontradas
		pontua = get_pontuacao_conjunto(n_cores_encontradas);
		print_pontuacao_parcial(pontua);
		pontuacao_total += pontua;
	}

	// Algortimo para baixar as peças
	private void baixar_pecas()
	{
		// de baixo para cima
		for (int y = tamanho-1; y >= 0; y--) 
		{
			// esquerda para a direita (normal)
			for (int x = 0; x < tamanho; x++)
			{
				// Se for uma posição nula ir subindo até encontrar uma peca
				if( camp[y][x].show_color_num() == 9)
				{
					int y_auxiliar = y; // variavel auxiliar
					while(y_auxiliar >= 0)
					{
						// Se encontrar espaço nulo
						if(camp[y_auxiliar][x].show_color_num() == 9)
						{
							// apenas subir
							y_auxiliar--;
						}
						else
						{
							camp[y][x] = camp[y_auxiliar][x];
							null_pos(x, y_auxiliar);
							break;
						}
					}
				}
			}
		}
	}

	// Quando encontrar um espaço null colocar uma peça aleatória
	private void preencher_espacos_null(){
		for (int y = 0; y < tamanho; y++)     
		{
			for (int x = 0; x < tamanho; x++) 
			{
				if( camp[y][x].show_color_num() == 9 )
				{ // se houver espaço null ou zero
					cor_pos(x, y);
				}
			}
		}
	}

	// Ver se ha peças disponiveis
	public boolean pecas_disp(){
		boolean tem = false;
		int conteudo = 0;
		int pos = 0;
		for (int y = 0; y < tamanho && !tem; y++) 
		{
			for (int x = 0; x < tamanho && !tem; x++) 
			{
				Cor c = camp[y][x].show_color();
				int coord_x = x; 
				int coord_y = y;
				// PESQUISAR PARA ESQUERDA
				while((coord_x-1) >= -1){
					if( get_color(coord_y, coord_x) == c ){					
						coord_x--;
						pos++;
					}else{
						break;
					}
				}
				if( pos >= 3){
					conteudo = 1;
				}
				pos = 0;
				coord_x = x;
			
				// PESQUISAR PARA CIMA
				while((coord_y-1) >= -1){
					if( get_color(coord_y, coord_x) == c ){					
						coord_y--;
						pos++;
					}else{
						break;
					}
				}
				if( pos >= 3){
					conteudo = 1;
				}
				pos = 0;
				coord_y = y;
			
				// PESQUISAR PARA DIREITA
				while((coord_x+1) <= tamanho){
					if( get_color(coord_y, coord_x) == c ){
						coord_x++;
						pos++;
					}else{
						break;
					}
				}
				if( pos >= 3){
					conteudo = 1;
				}
				pos = 0;
				coord_x = x;
			
				// PESQUISAR PARA BAIXO
				while((coord_y+1) <= tamanho){
					if( get_color(coord_y, coord_x) == c ){					
						coord_y++;
						pos++;
					}else{
						break;
					}
				}
				if( pos >= 3){
					conteudo = 1;
				}
				pos = 0;

				if(conteudo != 0){
					tem = true;
				}
			}
		}
		return tem;
	}	
}