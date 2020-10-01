package Exame_Desportivo;

public class Antecedente_Pessoais {
    private boolean cirurgia;
    private boolean perda_consc;
    private boolean traumatismo;
    private boolean palpitacoes;
    private boolean cardiopatias;
    private boolean hipertensao_arterial;
    private boolean doencas_digestivas;
    private boolean asma;
    private boolean hepatite;
    private boolean diabete;
    private boolean epilepsia;
    private boolean hab_alcool_tabaco;
    private boolean vacinas_atualizadas;
    private boolean outros;

    /**
     *
     * @param cirurgia
     * @param perda_cons
     * @param traumatismo
     * @param palpitacoes
     * @param cardiopatias
     * @param hipertensao_arterial
     * @param doencas_digestivas
     * @param asma
     * @param hepatite
     * @param diabetes
     * @param epilepsia
     * @param hab_alcool_tabaco
     * @param vacinas_atualizadas
     * @param outros
     */
    public Antecedente_Pessoais(boolean cirurgia, boolean perda_cons, boolean traumatismo, boolean palpitacoes, boolean cardiopatias, boolean hipertensao_arterial, boolean doencas_digestivas, boolean asma, boolean hepatite, boolean diabetes, boolean epilepsia, boolean hab_alcool_tabaco, boolean vacinas_atualizadas, boolean outros) {
        this.cirurgia = cirurgia;
        this.perda_consc = perda_consc;
        this.traumatismo = perda_consc;
        this.palpitacoes = palpitacoes;
        this.cardiopatias = cardiopatias;
        this.hipertensao_arterial = hipertensao_arterial;
        this.doencas_digestivas = doencas_digestivas;
        this.asma = asma;
        this.hepatite = hepatite;
        this.diabete = diabete;
        this.epilepsia = epilepsia;
        this.hab_alcool_tabaco = hab_alcool_tabaco;
        this.vacinas_atualizadas = vacinas_atualizadas;
        this.outros = outros;
    }
    public Antecedente_Pessoais() {

        this(false,false,false,false,false,false,false,false,false,false,false,false,false,false);
    }

    public boolean getCirurgia() {
        return this.cirurgia;
    }

    /**
     *
     * @param cirurgia
     */
    public void setCirurgia(boolean cirurgia) {
        this.cirurgia = cirurgia;
    }

    public boolean getPerda_consc() {
        return this.perda_consc;
    }

    /**
     *
     * @param perda_consc
     */
    public void setPerda_consc(boolean perda_consc) {
        this.perda_consc = perda_consc;
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

    public boolean getPalpitacoes() {
        return this.palpitacoes;
    }

    /**
     *
     * @param palpitacoes
     */
    public void setPalpitacoes(boolean palpitacoes) {
        this.palpitacoes = palpitacoes;
    }

    public boolean getCardiopatias() {
        return this.cardiopatias;
    }

    /**
     *
     * @param cardiopatias
     */
    public void setCardiopatias(boolean cardiopatias) {
        this.cardiopatias = cardiopatias;
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

    public boolean getDoencas_digestivas() {
        return this.doencas_digestivas;
    }

    /**
     *
     * @param doencas_digestivas
     */
    public void setDoencas_digestivas(boolean doencas_digestivas) {
        this.doencas_digestivas = doencas_digestivas;
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

    public boolean getHepatite() {
        return this.hepatite;
    }

    /**
     *
     * @param hepatite
     */
    public void setHepatite(boolean hepatite) {
        this.hepatite = hepatite;
    }

    public boolean getDiabete() {
        return this.diabete;
    }

    /**
     *
     * @param diabete
     */
    public void setDiabete(boolean diabete) {
        this.diabete = diabete;
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

    public boolean getHab_alcool_tabaco() {
        return this.hab_alcool_tabaco;
    }

    /**
     *
     * @param hab_alcool_tabaco
     */
    public void setHab_alcool_tabaco(boolean hab_alcool_tabaco) {
        this.hab_alcool_tabaco = hab_alcool_tabaco;
    }

    public boolean getVacinas_atualizadas() {
        return this.vacinas_atualizadas;
    }

    /**
     *
     * @param vacinas_atualizadas
     */
    public void setVacinas_atualizadas(boolean vacinas_atualizadas) {
        this.vacinas_atualizadas = vacinas_atualizadas;
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
