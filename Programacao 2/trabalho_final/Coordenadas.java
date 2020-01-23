class Coordenadas
{
    private int[][] coords;     // Array Bidimensional com as coordenadas que 
    private int     tamanho;    // Tamanho do array bidimensinal
    private int     posicao;    // Posição no array bidimensional

    //---------------Construtores-----------------
    public Coordenadas(int tamanho)
    {
        this.tamanho = tamanho;
        coords       = new int[tamanho][3];
        posicao      = 0;
    }

    public Coordenadas(){
        this(100); // tamanho = 100; (default)
    }

    //--------------- Métodos -----------------

    // adicionar coordenadas vindas da classe Coordenadas
    public void add_coor(int[][] c, int len){
        for(int i = 0; i < len ; i++){
            set_coordenadas(c[i][0], c[i][1] );
        }
    }

    // À medida que há espaços vazios vai-se incrementar as coordenadas
    public void set_coordenadas(int y, int x)
    {
        while( coords[posicao][2] != 0){
            posicao = (posicao+1) % tamanho;
        }
        coords[posicao][0] = y;
        coords[posicao][1] = x;
        coords[posicao][2] = 1; // se o espaço está a ser usado (1) caso contrário (0)
        posicao = (posicao+1) % tamanho;
    }

    // devolve as coordenadas de uma certa posição
    public int[] get_coordenadas(int pos)
    {
        int[] i = { coords[pos][1], coords[pos][0] };
        return i;
    }

    // remove as coordenadas de uma certa posição
    public void remove_coordenadas(int pos)
    {
        coords[pos][0] = 0;
        coords[pos][1] = 0;
        coords[pos][2] = 0; // se o espaço está a ser usado (1) caso contrário (0)
    }

    // remove todas as coordenadas
    public void remove_tudo()
    {
        for(int i = 0; i < tamanho; i ++){
            remove_coordenadas(i);
        }
    }

    // espaco vazio
    public boolean coordenadas_vazias(int pos){
        return coords[pos][2] == 0;
    }
    
    // se as coordenadas tiverem no array devolve true caso contrário false
    public boolean in_coordenadas(int x, int y)
    {
        boolean t = false;
        for(int i = 0; i < tamanho; i++)
        {
            if( (coords[i][0] == y) && (coords[i][1] == x) && coords[i][2] != 0 )
            {
                t = true;
                break;
            }
        }
        return t;
    }

    // retorna a posição
    public int get_tamanho(){
        return tamanho;
    }
}