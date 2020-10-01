package Exame_Desportivo;
public class Recibo {
    private long   numero_recibo;
    private String nif;

    /**
     *
     * @param numero_recibo
     * @param nif
     */
    public Recibo(long numero_recibo, String nif) {
        this.numero_recibo = numero_recibo;
        this.nif = nif;
    }
    public Recibo(){
        this(0, null);
    }

    /**
     *
     * @param numero_recibo
     */
    public void setNumero_recibo(long numero_recibo) {
        this.numero_recibo = numero_recibo;
    }

    public long getNumero_recibo() {
        return numero_recibo;
    }

    /**
     *
     * @param nif
     */
    public void setNIF(String nif) {
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }
}
