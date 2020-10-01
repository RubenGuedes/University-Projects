package Exame_Desportivo;

public class Autenticacao
{
    private String id;
    private String password;
    private String chip;
    private boolean autorizado;

    /**
     *
     * @param id
     * @param password
     * @param chip
     * @param autorizado
     */
    public Autenticacao(
            String id,
            String password,
            String chip,
            boolean autorizado)
    {
        this.id = id;
        this.password = password;
        this.chip = chip;
        this.autorizado = autorizado;
    }

    public Autenticacao(){
        this("0", "000aaa", "0000a", false);
    }

    /**
     *
     * @param id
     */
    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }
    public  String getPassword(){
        return password;
    }

    /**
     *
     * @param chip
     */
    public void setChip(String chip){
        this.chip = chip;
    }
    public  String getChip(){
        return chip;
    }

    /**
     *
     * @param autorizado
     */
    public void setAutorizado(boolean autorizado){
        this.autorizado = autorizado;
    }
    public  boolean getAutorizado(){
        return autorizado;
    }
}
