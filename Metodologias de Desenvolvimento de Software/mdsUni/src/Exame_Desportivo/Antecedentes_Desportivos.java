package Exame_Desportivo;

public class Antecedentes_Desportivos {
    private boolean retomara_atividade_fis;
    private boolean desporto_federado;
    private boolean desporto_regular;
    private String treinos_semanais;

    /**
     *
     * @param retomara_atividade_fis
     * @param desporto_federado
     * @param desporto_regular
     * @param treinos_semanais
     */
    public Antecedentes_Desportivos(boolean retomara_atividade_fis, boolean desporto_federado, boolean desporto_regular, String treinos_semanais) {
        this.retomara_atividade_fis = retomara_atividade_fis;
        this.desporto_federado = desporto_federado;
        this.desporto_regular = desporto_regular;
        this.treinos_semanais = treinos_semanais;
    }
    public Antecedentes_Desportivos() {

        this(false,false,false,null);
    }

    public boolean getDesporto_federado() {
        return this.desporto_federado;
    }

    /**
     *
     * @param desporto_federado
     */
    public void setDesporto_federado(boolean desporto_federado) {
        this.desporto_federado = desporto_federado;
    }

    public boolean getRetomara_atividade_fis() {
        return this.retomara_atividade_fis;
    }

    /**
     *
     * @param retomara_atividade_fis
     */
    public void setRetomara_atividade_fis(boolean retomara_atividade_fis) {
        this.retomara_atividade_fis = retomara_atividade_fis;
    }

    public boolean getDesporto_regular() {
        return this.desporto_regular;
    }

    /**
     *
     * @param desporto_regular
     */
    public void setDesporto_regular(boolean desporto_regular) {
        this.desporto_regular = desporto_regular;
    }

    public String getTreinos_semanais() {
        return this.treinos_semanais;
    }

    /**
     *
     * @param treinos_semanais
     */
    public void setTreinos_semanais(String treinos_semanais) {
        this.treinos_semanais = treinos_semanais;
    }
}
