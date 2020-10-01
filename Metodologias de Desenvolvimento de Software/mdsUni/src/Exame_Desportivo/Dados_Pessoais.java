package Exame_Desportivo;

public class Dados_Pessoais {
    private int exame_n;
    private String data_exame;
    private String nome;
    private String nascimento;
    private String nacionalidade;
    private String morada;
    private String c_postal;
    private String localidade;
    private int telemovel;
    private String clube;
    private String modalidade;
    private String escalao;
    private String nome_medico;

    /**
     *
     * @param exame_n
     * @param data_exame
     * @param nome
     * @param nascimento
     * @param morada
     * @param c_postal
     * @param localidade
     * @param telemovel
     * @param clube
     * @param modalidade
     * @param escalao
     * @param nome_medico
     */
    public Dados_Pessoais(int exame_n, String data_exame, String nome, String nascimento, String morada, String c_postal, String localidade, int telemovel, String clube, String modalidade, String escalao, String nome_medico) {
        this.exame_n = exame_n;
        this.data_exame = data_exame;
        this.nome = nome;
        this.nascimento = nascimento;
        this.nacionalidade = nacionalidade;
        this.morada = morada;
        this.c_postal = c_postal;
        this.localidade = localidade;
        this.telemovel = telemovel;
        this.clube = clube;
        this.modalidade = modalidade;
        this.escalao = escalao;
        this.nome_medico = nome_medico;
    }
    public Dados_Pessoais() {

        this(0,null,null,null,null,null,null,0,null,null,null,null);
    }

    public int getExame_n() {
        return this.exame_n;
    }

    /**
     *
     * @param Exame_n
     */
    public void setExame_n(int Exame_n) {
        this.exame_n = Exame_n;
    }

    public String getData_exame() {
        return this.data_exame;
    }

    /**
     *
     * @param data_exame
     */
    public void setData_exame(String data_exame) {
        this.data_exame = data_exame;
    }

    public String getNome_medico() {
        return this.nome_medico;
    }

    /**
     *
     * @param nome_medico
     */
    public void setNome_medico(String nome_medico) {
        this.nome_medico = nome_medico;
    }

    public String getNascimento() {
        return this.nascimento;
    }

    /**
     *
     * @param nascimento
     */
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getNacionalidade() {
        return this.nacionalidade;
    }

    /**
     *
     * @param nacionalidade
     */
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getMorada() {
        return this.morada;
    }

    /**
     *
     * @param morada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getC_postal() {
        return this.c_postal;
    }

    /**
     *
     * @param c_postal
     */
    public void setC_postal(String c_postal) {
        this.c_postal = c_postal;
    }

    /**
     *
     * @param localidade
     */
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getLocalidade() {
        return this.localidade;
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

    /**
     *
     * @param clube
     */
    public void setClube(String clube) {
        this.clube = clube;
    }

    public String getClube() {
        // TODO - implement Dados_Pessoais.getClube
        throw new UnsupportedOperationException();
    }

    public String getModalidade() {
        return this.modalidade;
    }

    /**
     *
     * @param modalidade
     */
    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getEscalao() {
        return this.escalao;
    }

    /**
     *
     * @param escalao
     */
    public void setEscalao(String escalao) {
        this.escalao = escalao;
    }

    /**
     *
     * @param nome_medico
     */
    public void setNomeMedico(String nome_medico) {
        this.nome_medico = nome_medico;
    }

    public String getNomeMedico() {
        return nome_medico;
    }
}
