package Exame_Desportivo;
public class Exame_CardioCirculRespi {
    private boolean pulso_radial;
    private boolean pulso_femural;
    private boolean ausc_cardiaca_normal;
    private boolean ausc_pulm_normal;
    private float     freq_cardiaca;
    private float     p_arterial;

    /**
     *
     * @param pulso_radial
     * @param pulso_femural
     * @param ausc_cardiaca_normal
     * @param ausc_pulm_normal
     * @param freq_cardiaca
     * @param p_arterial
     */
    public Exame_CardioCirculRespi(
            boolean pulso_radial,
            boolean pulso_femural,
            boolean ausc_cardiaca_normal,
            boolean ausc_pulm_normal,
            float freq_cardiaca,
            float p_arterial) {
        this.pulso_radial         = pulso_radial;
        this.pulso_femural        = pulso_femural;
        this.ausc_cardiaca_normal = ausc_cardiaca_normal;
        this.ausc_pulm_normal     = ausc_pulm_normal;
        this.freq_cardiaca        = freq_cardiaca;
        this.p_arterial           = p_arterial;
    }
    public Exame_CardioCirculRespi(){
        this(false, false, false, false, 0, 0);
    }

    /**
     *
     * @param pulso_radial
     */
    public void setPulso_radial(boolean pulso_radial) {
        this.pulso_radial = pulso_radial;
    }

    public boolean getPulso_radial() {
        return pulso_radial;
    }

    /**
     *
     * @param pulso_femural
     */
    public void setPulso_femural(boolean pulso_femural) {
        this.pulso_femural = pulso_femural;
    }

    public boolean getPulso_femural() {
        return pulso_femural;
    }

    /**
     *
     * @param ausc_cardiaca_normal
     */
    public void setAusc_cardiaca_normal(boolean ausc_cardiaca_normal) {
        this.ausc_cardiaca_normal = ausc_cardiaca_normal;
    }

    public boolean getAusc_cardiaca_normal() {
        return ausc_cardiaca_normal;
    }

    /**
     *
     * @param ausc_pulm_normal
     */
    public void setAusc_pulm_normal(boolean ausc_pulm_normal) {
        this.ausc_pulm_normal = ausc_pulm_normal;
    }

    public boolean getAusc_pulm_normal() {
        return ausc_pulm_normal;
    }

    /**
     *
     * @param freq_cardiaca
     */
    public void setFreq_cardiaca(float freq_cardiaca) {
        this.freq_cardiaca = freq_cardiaca;
    }

    public float getFreq_cardiaca() {
        return freq_cardiaca;
    }

    /**
     *
     * @param p_arterial
     */
    public void setP_arterial(float p_arterial) {
        this.p_arterial = p_arterial;
    }

    public float getP_arterial() {
        return p_arterial;
    }
}
