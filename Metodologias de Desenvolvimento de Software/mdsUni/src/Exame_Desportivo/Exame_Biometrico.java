package Exame_Desportivo;

public class Exame_Biometrico {
    private float peso;
    private float estatura;

    /**
     *
     * @param peso
     * @param estatura
     */
    public Exame_Biometrico(float peso, float estatura) {
        this.peso = peso;
        this.estatura = estatura;
    }
    public Exame_Biometrico() {

        this(0,0);
    }

    public float getPeso() {
        return this.peso;
    }

    /**
     *
     * @param peso
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getEstatura() {
        return estatura;
    }

    /**
     *
     * @param estatura
     */
    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }
}
