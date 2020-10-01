package Exame_Desportivo;
public class Pessoa
{
    private String nome;
    private int nif;
    private int telemovel;

    /**
     *
     * @param nome
     * @param nif
     * @param telemovel
     */
    public Pessoa(
            String nome,
            int nif,
            int telemovel) {
        this.nome = nome;
        this.nif = nif;
        this.telemovel = telemovel;
    }

    public Pessoa(){
        this(null, 0, 0);
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    /**
     *
     * @param nif
     */
    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getNif() {
        return this.nif;
    }

    /**
     *
     * @param telemovel
     */
    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public int getTelemovel() {
        return this.telemovel;
    }
}
