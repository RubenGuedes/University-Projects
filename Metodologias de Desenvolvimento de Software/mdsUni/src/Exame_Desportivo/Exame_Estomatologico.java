package Exame_Desportivo;
public class Exame_Estomatologico {
    private boolean sem_carie;
    private boolean caries_nao_tratadas;

    /**
     *
     * @param sem_carie
     * @param caries_nao_tratadas
     */
    public Exame_Estomatologico(boolean sem_carie, boolean caries_nao_tratadas) {
        this.sem_carie = sem_carie;
        this.caries_nao_tratadas = caries_nao_tratadas;
    }
    public Exame_Estomatologico(){
        this(false, false);
    }

    /**
     *
     * @param sem_carie
     */
    public void setSem_carie(boolean sem_carie) {
        this.sem_carie = sem_carie;
    }

    public boolean getSem_carie() {
        return sem_carie;
    }

    /**
     *
     * @param caries_nao_tratadas
     */
    public void setCaries_nao_tratadas(boolean caries_nao_tratadas) {
        this.caries_nao_tratadas = caries_nao_tratadas;
    }

    public boolean getCaries_nao_tratadas() {
        return caries_nao_tratadas;
    }
}
