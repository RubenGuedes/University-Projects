package Exame_Desportivo;
public class Exame_Medico_Desportivo {
    private int numero_exame;
    private String data;
    private String tipo_exame;

    /**
     *
     * @param numero_exame
     * @param data
     * @param tipo_exame
     */
    public Exame_Medico_Desportivo(
            int    numero_exame,
            String data,
            String tipo_exame) {
        this.numero_exame = numero_exame;
        this.data = data;
        this.tipo_exame = tipo_exame;
    }
    public Exame_Medico_Desportivo(){
        this(0, null, null);
    }

    /**
     *
     * @param numero_exame
     */
    public void setNumero_exame(int numero_exame) {
        this.numero_exame = numero_exame;
    }

    public int getNumero_exame() {
        return numero_exame;
    }

    /**
     *
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    /**
     *
     * @param tipo_exame
     */
    public void setTipo_exame(String tipo_exame) {
        this.tipo_exame = tipo_exame;
    }

    public String getTipo_exame() {
        return tipo_exame;
    }
}
