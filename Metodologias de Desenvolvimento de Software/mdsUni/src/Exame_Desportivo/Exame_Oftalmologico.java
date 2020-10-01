package Exame_Desportivo;
public class Exame_Oftalmologico {
    private int acuidade_vis_sem_correcao;
    private int acuidade_vis_com_correcao;
    private float miopia;
    private float outros;

    /**
     *
     * @param acuidade_vis_sem_correcao
     * @param acuidade_vis_com_correcao
     * @param miopia
     * @param outros
     */
    public Exame_Oftalmologico(	int acuidade_vis_sem_correcao,
                                   int acuidade_vis_com_correcao,
                                   float miopia,
                                   float outros) {
        this.acuidade_vis_sem_correcao = acuidade_vis_sem_correcao;
        this.acuidade_vis_com_correcao = acuidade_vis_com_correcao;
        this.miopia = miopia;
        this.outros = outros;
    }
    public Exame_Oftalmologico(){
        this(0, 0, 0.0f, 0.0f);
    }

    /**
     *
     * @param acuidade_vis_sem_correcao
     */
    public void setAcuidade_vis_sem_correcao(int acuidade_vis_sem_correcao) {
        this.acuidade_vis_sem_correcao = acuidade_vis_sem_correcao;
    }

    public int getAcuidade_vis_sem_correcao() {
        return acuidade_vis_sem_correcao;
    }

    /**
     *
     * @param acuidade_vis_com_correcao
     */
    public void setAcuidade_vis_com_correcao(int acuidade_vis_com_correcao) {
        this.acuidade_vis_com_correcao = acuidade_vis_com_correcao;
    }

    public int getAcuidade_vis_com_correcao() {
        return acuidade_vis_com_correcao;
    }

    /**
     *
     * @param miopia
     */
    public void setMiopia(float miopia) {
        this.miopia = miopia;
    }

    public float getMiopia() {
        return miopia;
    }

    /**
     *
     * @param outros
     */
    public void setOutros(float outros) {
        this.outros = outros;
    }

    public float getOutros() {
        return outros;
    }
}
