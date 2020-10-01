package Exame_Desportivo;
public class Exames_Complementares_Diagnostico
{
    private boolean ecg_normal;
    private boolean rad_torax_normal;
    private String  outros;

    /**
     *
     * @param ecg_normal
     * @param rad_torax_normal
     * @param outros
     */
    public Exames_Complementares_Diagnostico(boolean ecg_normal, boolean rad_torax_normal, String outros) {
        this.ecg_normal = ecg_normal;
        this.rad_torax_normal = rad_torax_normal;
        this.outros = outros;
    }
    public Exames_Complementares_Diagnostico(){
        this(false, false, null);
    }

    public void setEcg_normal(boolean ecg_normal) {
        this.ecg_normal = ecg_normal;
    }

    public boolean getEcg_normal() {
        return ecg_normal;
    }

    /**
     *
     * @param rad_torax_normal
     */
    public void setRad_torax_normal(boolean rad_torax_normal) {
        this.rad_torax_normal = rad_torax_normal;
    }

    public boolean getRad_torax_normal() {
        return rad_torax_normal;
    }

    /**
     *
     * @param outros
     */
    public void setOutros(String outros) {
        this.outros = outros;
    }

    public String getOutros() {
        return outros;
    }
}
