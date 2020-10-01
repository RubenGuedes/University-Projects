package Exame_Desportivo;
public class Medicos extends Pessoa{
    private String especialidade;

    /**
     *
     * @param nome
     * @param nif
     * @param telemovel
     * @param especialidade
     */
    public Medicos(String nome, int nif, int telemovel, String especialidade) {
        super(nome, nif, telemovel);
        this.especialidade = especialidade;
    }
    public Medicos(){
        super(null, 0, 0);
        this.especialidade = null;
    }

    /**
     *
     * @param especialidade
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }
}
