package Exame_Desportivo;
public class Centro_Medicina_Desportiva {
    private String local;

    /**
     *
     * @param local
     */
    public Centro_Medicina_Desportiva(String local) {
        this.local = local;
    }
    public Centro_Medicina_Desportiva(){
        this(null);
    }

    /**
     *
     * @param local
     */
    public void setLocal(String local) {
        this.local = local;
    }

    public String getLocal() {
        return local;
    }
}
