import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanelaJogo extends JFrame implements ActionListener
{
    // --------------- Variaveis ---------------
    private final int FRAME_LARGURA = 2000;
    private final int FRAME_ALTURA  = 1200;
    private final int FRAME_X       = 110;
    private final int FRAME_Y       = 100;

    private JButton pesquisar;
    private JButton novo;

    private JLabel fundo;
    private JLabel texto_x;
    private JLabel texto_y;
    private JLabel pesquisa;
    private JLabel pontuacao;

    private JTextField inserir_x;
    private JTextField inserir_y;

    private JPanel container = new JPanel(null);

    private Campo campo;

    private int tamanho;
    private int x;
    private int y;
    
    // ---------------- Construtor -----------------
    public JanelaJogo(int tamanho, int cores)
    {

        if(tamanho == 0)   tamanho = 10;
        if(cores   == 0)   cores   = 4;
        this.tamanho = tamanho;

        campo = new Campo(tamanho, cores);

        contentor();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }




    // ------------------ Métodos ------------------

    // inserir tudo no container
    public void contentor()
    {
        container.setLayout( null );

        janela();
        campo.print_campo();
        all_pecas(tamanho);
        textos();
        x_espaco();
        y_espaco();
        search();
        new_game();
        fundo();

        add(container);

    }

    // Fundo
    public void fundo(){
        fundo = new JLabel( new ImageIcon( "Fundo2.jpeg" ) );
        fundo.setSize(FRAME_LARGURA, FRAME_ALTURA);

        container.add(fundo);
    }

    // Estruturar a janela
    public void janela(){
        setSize(FRAME_LARGURA, FRAME_ALTURA);
        setResizable(true);
        setTitle("Jogo");
        setLocation(FRAME_X, FRAME_Y);
    }

    // Textos                             
    public void textos(){
        // Pontuação
        pontuacao = new JLabel("Pontuação: 0");
        pontuacao.setFont( new Font("Serif", Font.PLAIN, 24) );
        pontuacao.setBounds(1700, 20, 300, 50);
        pontuacao.setForeground(Color.BLACK);
        // pesquisar
        pesquisa = new JLabel("Pesquisar:");
        pesquisa.setFont( new Font("Serif", Font.PLAIN, 24) );
        pesquisa.setBounds(1700, 90, 200, 50);
        pesquisa.setForeground(Color.BLACK);
        // x
        texto_x = new JLabel("x:");
        texto_x.setFont( new Font("Serif", Font.PLAIN, 24) );
        texto_x.setBounds(1700, 140, 40, 50);
        texto_x.setForeground(Color.BLACK);
        // y
        texto_y = new JLabel("y:");
        texto_y.setFont( new Font("Serif", Font.PLAIN, 24) );
        texto_y.setBounds(1700, 190, 40, 50);
        texto_y.setForeground(Color.BLACK);

        container.add(pontuacao);
        container.add(pesquisa);
        container.add(texto_x);
        container.add(texto_y);
    }

    // JLabel para inserir o x pretendido               
    public void x_espaco(){
        inserir_x = new JTextField("0");
        inserir_x.setColumns(2);
        inserir_x.setFont( new Font("Serif", Font.PLAIN, 12) );
        inserir_x.setBounds(1740, 150, 50, 30);
        inserir_x.setBackground(Color.WHITE);
        container.add(inserir_x);
        inserir_x.addActionListener(this);
    }

    // JLabel para inserir o y pretendido
    public void y_espaco(){
        inserir_y = new JTextField("0");
        inserir_y.setColumns(2);
        inserir_y.setFont( new Font("Serif", Font.PLAIN, 12) );
        inserir_y.setBounds(1740, 200, 50, 30);
        inserir_y.setBackground(Color.WHITE);
        container.add(inserir_y);
        inserir_y.addActionListener(this);
    }

    // botão pesquisar                      
    public void search(){
        pesquisar = new JButton("Pesquisar");
        pesquisar.setBackground(Color.WHITE);
        pesquisar.setBounds(1700, 250, 250, 40);
        container.add(pesquisar);
        pesquisar.addActionListener(this);
    }

    // botão novo jogo(volta para o main)   
    public void new_game(){
        novo = new JButton("Menu Principal");
        novo.setBackground(Color.WHITE);
        novo.setBounds(1700, 300, 250, 40);
        container.add(novo);
        novo.addActionListener(this);
    }

    // Método Evento
    public void actionPerformed(ActionEvent event)
    {
        if(     event.getSource() == inserir_x){
            x = Integer.parseInt( inserir_x.getText() );
        }
        else if(event.getSource() == inserir_y){
            y = Integer.parseInt( inserir_y.getText() );
        }
        else if(event.getSource() == pesquisar)
        {
            pressionar_pesquisar();
        }
        else if(event.getSource() == novo){
            setVisible(false);
            
            JanelaMain main = new JanelaMain();
            main.setVisible(true);
        }
    }

    // pressionar o botão pesquisar
    public void pressionar_pesquisar()
    {
        campo.pesquisar(y, x);
        all_pecas(tamanho);
        pontuacao.setText("Pontuação: " + Double.toString(campo.get_pontuacao_total() ));

        // Funções para dar print
        campo.print_campo();
    }

    // painel para o mapa
    public void all_pecas(int tama){
        int sqrt = (int) Math.sqrt(tama/ 2);
        int len =  ( 140 / sqrt ) ;
        int x = 10, y = 10;

        for(int i = 0; i < tama; i++){
            for(int j = 0; j < tama; j++)
            {
                int num_peca = campo.get_num_color(j, i);

                // Peca para adicionar ao container
                JLabel p = new JLabel();
                p = peca( num_peca );
                p.setSize( len, len);
                p.setLocation(x, y);
                container.add(p);

                x += len + 10;
            }
            x = 10;
            y += len + 10;
        }
    }

    // Devolve o ficheiro respetivo à cor
    public JLabel peca(int c)
    {
        JLabel fundos = null;
        switch (c) {
            case 0: fundos =    new JLabel( new ImageIcon("Verde.jpg") );
                                break; // Verde
            case 1: fundos =    new JLabel( new ImageIcon("Vermelho.jpg") );
                                break; // Vermelho
            case 2: fundos =    new JLabel( new ImageIcon("Preto.jpg") );
                                break; // Preto
            case 3: fundos =    new JLabel( new ImageIcon("Castanho.jpg") );
                                break; // Castanho
            case 4: fundos =    new JLabel( new ImageIcon("Azul.jpg") );
                                break; // Azul
            case 5: fundos =    new JLabel( new ImageIcon("Roxo.jpg") );
                                break; // Roxo
            case 6: fundos =    new JLabel( new ImageIcon("Rosa.jpg") );
                                break; // Rosa
            case 7: fundos =    new JLabel( new ImageIcon("Cinza.jpg") );
                                break; // Cinza
            case 8: fundos =    new JLabel( new ImageIcon("Bronze.jpg") );
                                break; // Bronze
        }
        return fundos;
    }
    
}