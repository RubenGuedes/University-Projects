/**
 * LinearHashTable
 */
public class LinearHashTable<Anytype> extends HashTable<Anytype>
{
    public LinearHashTable()
    {
        super();
    }
    public LinearHashTable( int size )
    {
        super( size );
    }
    
    public int procPos( Anytype x )
    {
        int pos = ((int) Math.abs( x.hashCode() )) % super.tamanho;
        Elemento<Anytype> elem = array_elementos[pos];
        while( (elem != null) && !elem.getRemoved() )
        {
            if (elem.getElemento().equals(x)) 
            {
                return pos;    
            }
            pos =  (pos + 1) % tamanho;
            elem = array_elementos[pos];

        }
        return pos;
    }
}