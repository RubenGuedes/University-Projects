package Exame_Desportivo;
public class Praticante_Desportivo extends Pessoa {
    private int exame_n;
    /**
     *
     * @param nome
     * @param nif
     * @param telemovel
     * @param exame_n
     */
    public Praticante_Desportivo(String nome, int nif, int telemovel, int exame_n) {
        super(nome, nif, telemovel);
        this.exame_n = exame_n;
    }
    public Praticante_Desportivo(){
        super(null, 0, 0);
        this.exame_n = 0;
    }

    public void setExame_n(int exame_n){
        this.exame_n = exame_n;
    }

    public int getExame_n(){
        return exame_n;
    }
}
