package Exame_Desportivo;

public class Antecedente_Familiares {

    private boolean doenca_cardio;
    private boolean hipertensao_arterial;
    private boolean traumatismo;
    private boolean morte_subita;
    private boolean asma;
    private boolean diabetes;
    private boolean epilepsia;
    private boolean tumores;
    private boolean doencas_hematologicas;
    private boolean outros;

    /**
     *
     * @param doenca_cardio
     * @param hipertensao_arterial
     * @param traumatismo
     * @param morte_subite
     * @param asma
     * @param diabetes
     * @param epilepsia
     * @param tumores
     * @param doencas_hematologicas
     * @param outros
     */
    public Antecedente_Familiares(boolean doenca_cardio, boolean hipertensao_arterial, boolean traumatismo, boolean morte_subite, boolean asma, boolean diabetes, boolean epilepsia, boolean tumores, boolean doencas_hematologicas, boolean outros) {
        this.doenca_cardio = doenca_cardio;
        this.hipertensao_arterial = hipertensao_arterial;
        this.traumatismo = traumatismo;
        this.morte_subita = morte_subita;
        this.asma = asma;
        this.diabetes = diabetes;
        this.epilepsia = epilepsia;
        this.tumores = tumores;
        this.doencas_hematologicas = tumores;
        this.outros = outros;
    }
    public Antecedente_Familiares() {

        this(false,false,false,false,false,false,false,false,false, false);
    }

    public boolean getDoenca_cardio() {
        return this.doenca_cardio;
    }

    /**
     *
     * @param doenca_cardio
     */
    public void setDoenca_cardio(boolean doenca_cardio) {
        this.doenca_cardio = doenca_cardio;
    }

    public boolean getHipertensao_arterial() {
        return this.hipertensao_arterial;
    }

    /**
     *
     * @param hipertensao_arterial
     */
    public void setHipertensao_arterial(boolean hipertensao_arterial) {
        this.hipertensao_arterial = hipertensao_arterial;
    }

    public boolean getTraumatismo() {
        return this.traumatismo;
    }

    /**
     *
     * @param traumatismo
     */
    public void setTraumatismo(boolean traumatismo) {
        this.traumatismo = traumatismo;
    }

    public boolean getMorte_subita() {
        return this.morte_subita;
    }

    /**
     *
     * @param morte_subita
     */
    public void setMorte_subita(boolean morte_subita) {
        this.morte_subita = morte_subita;
    }

    public boolean getAsma() {
        return this.asma;
    }

    /**
     *
     * @param asma
     */
    public void setAsma(boolean asma) {
        this.asma = asma;
    }

    public boolean getDiabetes() {
        return this.diabetes;
    }

    /**
     *
     * @param diabetes
     */
    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean getEpilepsia() {
        return this.epilepsia;
    }

    /**
     *
     * @param epilepsia
     */
    public void setEpilepsia(boolean epilepsia) {
        this.epilepsia = epilepsia;
    }

    public boolean getTumores() {
        return this.tumores;
    }

    /**
     *
     * @param tumores
     */
    public void setTumores(boolean tumores) {
        this.tumores = tumores;
    }

    public boolean getDoencas_hematologicas() {
        return this.doencas_hematologicas;
    }

    /**
     *
     * @param doencas_hematologicas
     */
    public void setDoencas_hematologicas(boolean doencas_hematologicas) {
        this.doencas_hematologicas = doencas_hematologicas;
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
