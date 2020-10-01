package so2.db;


import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

/**
 *
 * @author jsaias
 */
public class TesteAcessoBD 
{
    public static String get_date()
    {
        return LocalDateTime.now().toString();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception 
    {
        // coloque os argumentos
        final String host = "", 
                     db = "", 
                     user = "",
                     pw = "";
        
        // NOTA: se isto nao fosse uma domonstacao, nao PODIA ter configuracoes no codigo fonte!!!
        PostgresConnector pc = new PostgresConnector( host, db, user, pw );

        // estabelecer a ligacao ao SGBD
        pc.connect();
        /*
        // Statement -> Objecto que representa uma instrução para uma base de dados.
        Statement stmt = pc.getStatement();

        // Operações sobre a base de dados
	Avaliacao avaliacao = new Avaliacao(stmt);

        avaliacao.registarAluno(0, 0, "Ruben", "bi1");
        
        // fechar o statement
        avaliacao.close();
        */
        // desligar do SGBD:
        pc.disconnect();
    }


}
