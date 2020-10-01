package Exame_Desportivo;

public class Exame_ORI {
    private boolean audicao_5m_sem_alteracoes;
    private boolean sinusite;

    /**
     *
     * @param audicao_5m_sem_alteracoes
     * @param sinusite
     */
    public Exame_ORI(boolean audicao_5m_sem_alteracoes, boolean sinusite) {
        this.audicao_5m_sem_alteracoes=audicao_5m_sem_alteracoes;
        this.sinusite=sinusite;
    }
    public Exame_ORI() {

        this(false,false);
    }

    public boolean getAudicao_5m_sem_alteracoes() {
        return this.audicao_5m_sem_alteracoes;
    }

    /**
     *
     * @param audicao_5m_sem_alteracoes
     */
    public void setAudicao_5m_sem_alteracoes(boolean audicao_5m_sem_alteracoes) {
        this.audicao_5m_sem_alteracoes = audicao_5m_sem_alteracoes;
    }

    public boolean getSinusite() {
        return this.sinusite;
    }

    /**
     *
     * @param sinusite
     */
    public void setSinusite(boolean sinusite) {
        this.sinusite = sinusite;
    }
}
