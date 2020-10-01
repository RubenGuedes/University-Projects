package paral04;

/**
 *
 * @author jsaias
 */
public class PrimosThreadPool extends Thread {
    int inicio;
    int fim;
    
    public PrimosThreadPool() {
        super();
        this.inicio= 0;
        this.fim= 10;
    }
    public PrimosThreadPool(int inicio, int fim) {
        super();
        this.inicio= inicio;
        this.fim= fim;
    }    
    
    @Override
    public void run() 
    {
        Primos p= new Primos(inicio,fim);
        p.go();
    }
    
    public static void main(String[] args)
    {
        PrimosThreadPool p1 = new PrimosThreadPool(    0,   50000);
        PrimosThreadPool p2 = new PrimosThreadPool( 50000, 100000);
        PrimosThreadPool p3 = new PrimosThreadPool(100000, 150000);
        PrimosThreadPool p4 = new PrimosThreadPool(150000, 200000);
        
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}
