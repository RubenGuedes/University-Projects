package Exame_Desportivo;

public class Exame_Genito_Urinario {
    private int menarca_idade;
    private boolean alt_ciclo_mentrual;
    private boolean outros;

    /**
     *
     * @param menarca_idade
     * @param alt_ciclo_menstrual
     * @param outros
     */
    public Exame_Genito_Urinario(int menarca_idade, boolean alt_ciclo_menstrual, boolean outros) {
        this.menarca_idade=menarca_idade;
        this.alt_ciclo_mentrual = alt_ciclo_menstrual;
        this.outros = outros;
    }
    public Exame_Genito_Urinario() {

        this(0, false,false);
    }

    public int getMenarca_idade() {
        return this.menarca_idade;
    }

    /**
     *
     * @param menarca_idade
     */
    public void setMenarca_idade(int menarca_idade) {
        this.menarca_idade = menarca_idade;
    }

    public boolean getAlt_ciclo_mentrual() {
        return this.alt_ciclo_mentrual;
    }

    /**
     *
     * @param alt_ciclo_mentrual
     */
    public void setAlt_ciclo_mentrual(boolean alt_ciclo_mentrual) {
        this.alt_ciclo_mentrual = alt_ciclo_mentrual;
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
