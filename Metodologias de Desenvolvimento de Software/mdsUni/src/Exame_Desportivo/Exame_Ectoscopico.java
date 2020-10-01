package Exame_Desportivo;

public class Exame_Ectoscopico {
    private boolean desenv_normal;
    private boolean alter_derma;
    private boolean escoliose;
    private boolean dismetria_membros;
    private boolean genus_valgus;
    private boolean pe_plano;
    private boolean varizes;
    private boolean outros;

    /**
     *
     * @param desenv_normal
     * @param alter_derma
     * @param escoliose
     * @param dismetria_membros
     * @param genus_valgus
     * @param pe_plano
     * @param varizes
     * @param outros
     */
    public Exame_Ectoscopico(boolean desenv_normal, boolean alter_derma, boolean escoliose, boolean dismetria_membros, boolean genus_valgus, boolean pe_plano, boolean varizes, boolean outros) {
        this.desenv_normal = desenv_normal;
        this.alter_derma = alter_derma;
        this.escoliose = escoliose;
        this.dismetria_membros = dismetria_membros;
        this.genus_valgus = genus_valgus;
        this.pe_plano = pe_plano;
        this.varizes = varizes;
        this.outros = outros;
    }
    public Exame_Ectoscopico() {

        this(false,false,false,false,false,false,false,false);
    }

    public boolean getDesenv_Normal() {
        return desenv_normal;
    }

    /**
     *
     * @param Desenv_Normal
     */
    public void setDesenv_Normal(boolean Desenv_Normal) {
        desenv_normal = Desenv_Normal;
    }

    public boolean getAlter_derma() {
        return this.alter_derma;
    }

    /**
     *
     * @param alter_derma
     */
    public void setAlter_derma(boolean alter_derma) {
        this.alter_derma = alter_derma;
    }

    public boolean getEscoliose() {
        return this.escoliose;
    }

    /**
     *
     * @param escoliose
     */
    public void setEscoliose(boolean escoliose) {
        this.escoliose = escoliose;
    }

    public boolean getDismetria_membros() {
        return this.dismetria_membros;
    }

    /**
     *
     * @param dismetria_membros
     */
    public void setDismetria_membros(boolean dismetria_membros) {
        this.dismetria_membros = dismetria_membros;
    }

    public boolean getGenus_valgus() {
        return this.genus_valgus;
    }

    /**
     *
     * @param genus_valgus
     */
    public void setGenus_valgus(boolean genus_valgus) {
        this.genus_valgus = genus_valgus;
    }

    public boolean getPe_plano() {
        return this.pe_plano;
    }

    /**
     *
     * @param pe_plano
     */
    public void setPe_plano(boolean pe_plano) {
        this.pe_plano = pe_plano;
    }

    public boolean getVarizes() {
        return this.varizes;
    }

    /**
     *
     * @param varizes
     */
    public void setVarizes(boolean varizes) {
        this.varizes = varizes;
    }

    public boolean getOutros() {
        return this.outros;
    }

    /**
     *
     * @param outros
     */
    public void setOutros(boolean outros) {
        this.outros = outros;
    }
}
