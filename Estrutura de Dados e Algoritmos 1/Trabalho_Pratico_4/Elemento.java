/**
 * Elemento
 */
public class Elemento<E>
{
    /**
     * Variable
     */
    E elemento;
    boolean removed;
    /**
     * Constructor
     */
    public Elemento()
    {
        this(null);
    }
    public Elemento(E elemento)
    {
        removed = false;
        this.elemento = elemento;
    }
    //////////////////////////////
    /**
     * @return elemento
     */
    public E getElemento()
    {
        return elemento;
    }
    //////////////////////////////
    public void setElemento(E elemento)
    {
        this.elemento = elemento;
    }
    //////////////////////////////
    //////////////////////////////
    @Override
    public String toString()
    {
        return elemento + "";
    }
    //////////////////////////////
    //////////////////////////////
    public void setRemoved(boolean removed)
    {
        this.removed = removed;
    }
    //////////////////////////////
    //////////////////////////////
    public boolean getRemoved()
    {
        return removed;
    }
    //////////////////////////////
}