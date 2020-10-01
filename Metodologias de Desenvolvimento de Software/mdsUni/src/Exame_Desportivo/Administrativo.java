package Exame_Desportivo;
public class Administrativo extends Pessoa{
    private String id_especial;

    /**
     *
     * @param nome
     * @param nif
     * @param telemovel
     * @param id_especial
     */
    public Administrativo(
            String id_especial,
            String nome,
            int nif,
            int telemovel){
        super(nome, nif, telemovel);
        this.id_especial = id_especial;
    }
    public Administrativo(){
        super(null, 0, 0);
        this.id_especial = null;
    }

    public void setId_especial(String id_especial){
        this.id_especial = id_especial;
    }

    public String getId_especial(){
        return id_especial;
    }
}
