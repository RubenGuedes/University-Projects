/**
 * HashTable
 */
abstract class HashTable<AnyType> 
{   
    ///////////////////////////////////////////

    //              Variable

    ///////////////////////////////////////////
    protected Elemento<AnyType>[] array_elementos;
    protected int                 ocupado;
    protected int                 tamanho;
    ///////////////////////////////////////////

    //              Constructor 1282

    ///////////////////////////////////////////
    public HashTable( )
    {
        this(13); 
    }
    public HashTable( int tamanho )
    {
        this.ocupado         = 0;
        this.tamanho         = tamanho;
        this.array_elementos = new Elemento[tamanho];
    }
    ///////////////////////////////////////////

    //              Methods

    ///////////////////////////////////////////
    protected abstract int procPos(AnyType s);

    public void inserir( AnyType x )
    {
        int     pos     = procPos(x);
        AnyType elem    = procurar( x );

        if( elem == null  )
        {
            rehash();
            array_elementos[pos]        = new Elemento<AnyType>(x);
            this.ocupado                = (ocupado + 1) % tamanho;
        }
    }
    public void remove( AnyType x )
    {
        int     pos     = procPos(x);
        AnyType elem    = procurar( x );

        if ( elem != null ) 
        {
            this.array_elementos[pos].setRemoved(true);;    
            this.ocupado = (ocupado-1) % tamanho;
        }
    }

    public void alocarTabela( int dim )
    {
        // Conteúdo antigo
        Elemento<AnyType>[] arr_aux     = array_elementos;
        int                 tam_antigo  = tamanho;

        // novo conteúdo
        if ( !isPrime( dim) ) 
        {
            nextPrime( dim );
        }
        this.tamanho         = dim;
        this.array_elementos = new Elemento[dim];

        for (int i = 0; i < tam_antigo; i++) 
        {
            Elemento<AnyType> elem = arr_aux[i];
            if (    elem != null    ) 
            {
                inserir(    elem.getElemento()   );    
            } 
        }
    }

    public AnyType procurar(AnyType val)
    {
        int pos    = procPos(val); 
        AnyType value = (array_elementos[pos] == null ) ? null : array_elementos[pos].getElemento() ;       
        return value;
    }

    public void rehash( )
    {   
        if (factorCarga() >= 0.5f ) 
        {
            int novo_comp  = tamanho * 2;
            alocarTabela(novo_comp);
        }
    }

    public int ocupados()
    {
        return ocupado;
    }

    public float factorCarga()
    {
        return (float) ocupado / tamanho;
    }

    public void tornarVazia()
    {
        this.ocupado    = 0;
        array_elementos = new Elemento[tamanho];
    }

    public void print()
    {
        Elemento<AnyType> elem = null;
        for (int i = 0; i < tamanho; i++) 
        {
            elem = array_elementos[i];
            if ( elem != null && !elem.getRemoved() ) 
                System.out.print( elem.toString() + " " );
        }
        System.out.println("");
    }
    ////////////////////////////////////////

    //           Private Methods

    ////////////////////////////////////////
    protected int nextPrime( int n )
    {
        while ( !isPrime(n) ) 
        {
            n++;    
        }
        return n;
    }

    protected boolean isPrime( int number )
    {
        int count = 0;
        int numb  = 1;
        while (numb <= number) 
        {
            if (number % numb == 0) 
            {
                count++;
            }
            numb++;
        }
        return (count == 2) ? true : false;
    }
}