/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so2.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author rp
 */
public class Avaliacao 
{
    Statement stm;

    public Avaliacao(Statement stm)
    {
        this.stm = stm;
    }
    
    public void close() throws SQLException
    {
        stm.close();
    }
    
    // Registar um aluno
    public void registarAluno(int numero, int telefone, String nome, String bi) throws SQLException
    {
        String insert_query = String.format(
                "INSERT INTO aluno VALUES (%d, '%s', '%s', %d)",
                numero, nome, bi, telefone
        );
        
        stm.executeUpdate(insert_query);
    }
    
    // Registar uma disciplina
    public void registarDisciplina(String codigo, String nome, String descricao) throws SQLException
    {
        String insert_query = String.format(
                "INSERT INTO disciplina VALUES ('%s', '%s', '%s')",
                codigo, nome, descricao
        );
        
        stm.executeUpdate(insert_query);
    }
    
    // inscrever um aluno numa disciplina
    public void inscreverAluno(int numero_aluno, String codigo_disciplina) throws SQLException
    {
        // Ver se aluno existe
        String select_aluno = String.format(
                "SELECT numero FROM aluno WHERE numero = '%s'",
                Integer.toString(numero_aluno)
        );
        ResultSet rsAluno = stm.executeQuery(select_aluno);
        String aluno = "";
        while(rsAluno.next())
                aluno= rsAluno.getString("nome");
        
        //////////////////////////////////////////
        
        // Ver se disciplina existe
        String select_disciplina = String.format(
                "SELECT codigo FROM disciplina WHERE codigo = '%s'",
                codigo_disciplina
        );
        ResultSet rsDisciplina = stm.executeQuery(select_disciplina);
        
        String disciplina = "";
        while(rsDisciplina.next())
            disciplina = rsDisciplina.getString("codigo");
        
        //////////////////////////////////////////

        if (disciplina.compareTo("") != 0 && aluno.compareTo("") != 0) 
        {
            // Adicionar a informação em sql
            String insert_query = String.format(
                    "INSERT INTO inscrito VALUES ('%s', '%s')",
                    Integer.toString(numero_aluno), codigo_disciplina
            );
            stm.executeUpdate(insert_query);
        }
    }
    
    // listar os alunos
    public ArrayList<Aluno> lista_alunos() throws SQLException
    {
        ArrayList<Aluno> arr = new ArrayList<>();
        
        // Dados aluno
        int numero, telefone;
        String nome, bi;
        
        // query
        String select_alunos = String.format(
                "SELECT * FROM aluno"
        );
        ResultSet rs = stm.executeQuery(select_alunos);
        
        while(rs.next())
        {
            numero = rs.getInt("numero"); 
            telefone = rs.getInt("telefone");
            nome = rs.getString("nome");
            bi = rs.getString("bi");
            
            arr.add(
                    new Aluno(numero, telefone, nome, bi)
            );
        }
        return arr;
    }
    
    // listar disciplinas
    public ArrayList<Disciplina> lista_disciplinas() throws SQLException
    {
        ArrayList<Disciplina> arr = new ArrayList<>();
        
        // Dados disciplina
        String  codigo,
                nome,
                descricao;
        
        // query
        String select_disciplina = String.format(
                "SELECT * FROM disciplina"
        );
        ResultSet rs = stm.executeQuery(select_disciplina);
        
        while(rs.next())
        {
            codigo = rs.getString("codigo");
            nome = rs.getString("nome");
            descricao = rs.getString("descricao");
            
            arr.add(
                new Disciplina(codigo, nome, descricao)
            );
        }
        return arr;
    }
    
    // listar inscrições
    public ArrayList<Inscrito> lista_inscritos() throws SQLException
    {
        ArrayList<Inscrito> arr = new ArrayList<>();
        
        // Dados inscrito
        String codigo,
               numero;
        
        // query
        String select_inscrito = String.format(
                "SELECT * FROM inscrito"
        );
        ResultSet rs = stm.executeQuery(select_inscrito);
        
        while(rs.next())
        {
            codigo = rs.getString("código da disciplina");
            numero = rs.getString("número do aluno");
            
            arr.add(
                    new Inscrito(numero, codigo)
            );
        }
        return arr;
    }
    
    // listar os alunos inscritos na disciplina com o código AAAAA
    public ArrayList<Aluno> lista_inscritos_codigo(String codigo_dis) throws SQLException
    {
        ArrayList<Aluno> arr = new ArrayList<>();
        
        // query
        String select_cod = String.format(
                "SELECT *"
              + "FROM aluno"
              + "WHERE numero IN "
                        + "(SELECT 'número do aluno'"
                        +  "FROM inscritos"
                        +  "WHERE 'código da disciplina = %s);",
                codigo_dis
        );
        ResultSet rs = stm.executeQuery(select_cod);
        
        while(rs.next())
        {
            int numero = rs.getInt("numero"),
                telefone = rs.getInt("telefone");
            String  nome = rs.getString("nome"),
                    bi = rs.getString("bi");
            
            arr.add(
                new Aluno(numero, telefone, nome, bi)
            );
        }
        return arr;
    }
    
    // listar disciplinas a que o aluno X está inscrito
    public ArrayList<String> listar_displinas_aluno(int numero) throws SQLException
    {
        ArrayList<String> arr = new ArrayList<>();
        
        String query = String.format(
                "SELECT nome "
              + "FROM disciplina "
              + "WHERE codigo IN "
                        + "(SELECT 'código da disciplina'"
                        +  "FROM inscritos"
                        +  "WHERE 'número do aluno' = %d);",
                numero
        );
        
        
        
        TODO -> alterar a tabela inscritos para que o numero de aluno seja inteiro;
        
        
        
        
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) 
            arr.add( rs.getString("nome") );
        
        return arr;
    }
}
