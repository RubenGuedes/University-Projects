package Exame_Desportivo;
public class Exame_Abdomen
{
    private boolean organomegalia;
    private String outros;

    /**
     * @param organomegalia
     * @param outros
     */
    public Exame_Abdomen(boolean organomegalia, String outros) {
        this.organomegalia = organomegalia;
        this.outros = outros;
    }

    public Exame_Abdomen() {

        this(false, null);
    }

    public boolean getOrganomegalia() {
        return organomegalia;
    }

    /**
     * @param organomegalia
     */
    public void setOrganomegalia(boolean organomegalia) {
        this.organomegalia = organomegalia;
    }

    public String getOutros() {
        return outros;
    }

    /**
     * @param outros
     */
    public void setOutros(String outros) {
        this.outros = outros;
    }
}
