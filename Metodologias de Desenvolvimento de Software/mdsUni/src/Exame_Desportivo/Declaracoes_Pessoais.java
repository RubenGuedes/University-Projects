package Exame_Desportivo;

public class Declaracoes_Pessoais {
    private boolean internado;
    private boolean operado;
    private boolean perda_cons;
    private boolean lesao_desp;
    private boolean hab_alcool_tabaco;
    private boolean consome;
    private boolean medicamentos;
    private boolean alergico;
    private boolean doencas_pulmunares;
    private boolean doenca_digestivo;
    private boolean doenca_coracao;
    private boolean doenca_renal;
    private boolean doenca_ossea;
    private boolean diabetes;
    private boolean doenca_sangue;
    private boolean doenca_mental;
    private boolean doenca_pele;
    private boolean doenca_nao_mencionada;
    private boolean realizou_exame_medico;
    private String 	resultado_exame;

    /**
     *
     * @param internado
     * @param operado
     * @param perda_cons
     * @param lesao_desp
     * @param hab_alcool_tabaco
     * @param consome
     * @param medicamentos
     * @param alergico
     * @param doencas_pulmunares
     * @param doenca_digestivo
     * @param doenca_coracao
     * @param doenca_renal
     * @param doenca_ossea
     * @param diabetes
     * @param doenca_sangue
     * @param doenca_pele
     * @param doenca_nao_mencionada
     * @param realizou_exame_medico
     * @param resultado_exame
     */
    public Declaracoes_Pessoais(boolean internado, boolean operado, boolean perda_cons, boolean lesao_desp, boolean hab_alcool_tabaco, boolean consome, boolean medicamentos, boolean alergico, boolean doencas_pulmunares, boolean doenca_digestivo, boolean doenca_coracao, boolean doenca_renal, boolean doenca_ossea, boolean diabetes, boolean doenca_sangue, boolean doenca_pele, boolean doenca_nao_mencionada, boolean realizou_exame_medico, String resultado_exame) {
        this.internado = internado;
        this.operado = operado;
        this.perda_cons = perda_cons;
        this.lesao_desp = lesao_desp;
        this.hab_alcool_tabaco = hab_alcool_tabaco;
        this.consome = consome;
        this.medicamentos = medicamentos;
        this.alergico = alergico;
        this.doencas_pulmunares = doencas_pulmunares;
        this.doenca_digestivo = doenca_digestivo;
        this.doenca_coracao = doenca_coracao;
        this.doenca_renal = doenca_renal;
        this.doenca_ossea = doenca_ossea;
        this.diabetes = diabetes;
        this.doenca_sangue = doenca_sangue;
        this.doenca_mental = doenca_mental;
        this.doenca_pele = doenca_pele;
        this.doenca_nao_mencionada = doenca_nao_mencionada;
        this.realizou_exame_medico  = realizou_exame_medico;
        this.resultado_exame = resultado_exame;
    }
    public Declaracoes_Pessoais(){

        this(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,null);
    }

    public boolean getInternado() {
        return this.internado;
    }

    /**
     *
     * @param internado
     */
    public void setInternado(boolean internado) {
        this.internado = internado;
    }

    public boolean getOperado() {
        return this.operado;
    }

    /**
     *
     * @param operado
     */
    public void setOperado(boolean operado) {
        this.operado = operado;
    }

    public boolean getPerda_cons() {
        return this.perda_cons;
    }

    /**
     *
     * @param perda_cons
     */
    public void setPerda_cons(boolean perda_cons) {
        this.perda_cons = perda_cons;
    }

    public boolean getLesao_desp() {
        return this.lesao_desp;
    }

    /**
     *
     * @param lesao_desp
     */
    public void setLesao_desp(boolean lesao_desp) {
        this.lesao_desp = lesao_desp;
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

    public boolean getConsome() {
        return this.consome;
    }

    /**
     *
     * @param consome
     */
    public void setConsome(boolean consome) {
        this.consome = consome;
    }

    public boolean getMedicamentos() {
        return this.medicamentos;
    }

    /**
     *
     * @param medicamentos
     */
    public void setMedicamentos(boolean medicamentos) {
        this.medicamentos = medicamentos;
    }

    public boolean getAlergico() {
        return this.alergico;
    }

    /**
     *
     * @param alergico
     */
    public void setAlergico(boolean alergico) {
        this.alergico = alergico;
    }

    public boolean getDoencas_pulmunares() {
        return this.doencas_pulmunares;
    }

    /**
     *
     * @param doencas_pulmunares
     */
    public void setDoencas_pulmunares(boolean doencas_pulmunares) {
        this.doencas_pulmunares = doencas_pulmunares;
    }

    public boolean getDoenca_digestivo() {
        return this.doenca_digestivo;
    }

    /**
     *
     * @param doenca_digestivo
     */
    public void setDoenca_digestivo(boolean doenca_digestivo) {
        this.doenca_digestivo = doenca_digestivo;
    }

    public boolean getDoenca_coracao() {
        return this.doenca_coracao;
    }

    /**
     *
     * @param doenca_coracao
     */
    public void setDoenca_coracao(boolean doenca_coracao) {
        this.doenca_coracao = doenca_coracao;
    }

    public boolean getDoenca_renal() {
        return this.doenca_renal;
    }

    /**
     *
     * @param doenca_renal
     */
    public void setDoenca_renal(boolean doenca_renal) {
        this.doenca_renal = doenca_renal;
    }

    public boolean getDoenca_ossea() {
        return this.doenca_ossea;
    }

    /**
     *
     * @param doenca_ossea
     */
    public void setDoenca_ossea(boolean doenca_ossea) {
        this.doenca_ossea = doenca_ossea;
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

    public boolean getDoenca_sangue() {
        return this.doenca_sangue;
    }

    /**
     *
     * @param doenca_sangue
     */
    public void setDoenca_sangue(boolean doenca_sangue) {
        this.doenca_sangue = doenca_sangue;
    }

    public boolean getDoenca_mental() {
        return this.doenca_mental;
    }

    /**
     *
     * @param doenca_mental
     */
    public void setDoenca_mental(boolean doenca_mental) {
        this.doenca_mental = doenca_mental;
    }

    public boolean getDoenca_pele() {
        return this.doenca_pele;
    }

    /**
     *
     * @param doenca_pele
     */
    public void setDoenca_pele(boolean doenca_pele) {
        this.doenca_pele = doenca_pele;
    }

    public boolean getDoenca_nao_mencionada() {
        return this.doenca_nao_mencionada;
    }

    /**
     *
     * @param doenca_nao_mencionada
     */
    public void setDoenca_nao_mencionada(boolean doenca_nao_mencionada) {
        this.doenca_nao_mencionada = doenca_nao_mencionada;
    }

    public boolean getRealizou_exame_medico() {
        return this.realizou_exame_medico;
    }

    /**
     *
     * @param realizou_exame_medico
     */
    public void setRealizou_exame_medico(boolean realizou_exame_medico) {
        this.realizou_exame_medico = realizou_exame_medico;
    }

    public String getResultado_exame() {
        return this.resultado_exame;
    }

    /**
     *
     * @param resultado_exame
     */
    public void setResultado_exame(String resultado_exame) {
        this.resultado_exame = resultado_exame;
    }

}
