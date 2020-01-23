import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JanelaMain extends JFrame implements ActionListener{

    private static final int FRAME_LARGURA = 800;
    private static final int FRAME_ALTURA  = 600;
    private static final int FRAME_X       = 680;
    private static final int FRAME_Y       = 400;

    private JButton next_button;    // botão para levar para a próxima página
    
    private JLabel  fundo;          // variavel para ir buscar o ficheiro imagem 
    private JLabel  bem_texto;      // texto de bem vindo
    private JLabel  tam_texto;      // texto de tamanho do campo
    private JLabel  n_cores_texto;  // Numero de cores que vai aparecer no campo
    private JLabel  atencao;

    private JTextField tam_v;       // variavel para guardar o tamanho da matriz campo
    private JTextField cor_v;       // variavel para guardar o numero de cores no jogo

    private int tamanh;             // variavel que vai definir o tamanho do mapa do jogo
    private int num_cores;          // variavel que representa a quantidade de cores que vão aparecer no jogo

    public JanelaMain()
    {        
        // Container
        Container container = getContentPane();
        container.setLayout( null );

        // Estas 7 linhas vão criar e colocar os objectos no container
        janela();
        set_atencao(container);
        textos(container);
        insert_tamanho(container);
        insert_cores(container);
        inserir_botao(this, container);
        fundo(container);

        // Para, ao fechar a janela o programa terminar
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Aviso ao Jogador
    public void set_atencao(Container cont){
        atencao = new JLabel("Logo após inserir o valor pressionar 'ENTER'");
        atencao.setFont( new Font("Serif", Font.BOLD, 22) );
        atencao.setSize(700, 40);
        atencao.setLocation(140, 10);
        atencao.setForeground(Color.red);

        cont.add(atencao);
    }

    // Estruturar a janela "JanelaMain"
    public void janela()
    {
        setTitle("Janela Principal");
        setLocation(FRAME_X, FRAME_Y);
        setSize(FRAME_LARGURA, FRAME_ALTURA);
        setResizable(false);
    }

    // Fundo da janela
    public void fundo(Container cont){
        fundo = new JLabel( new ImageIcon("Fundo.jpg") );
        fundo.setSize(FRAME_LARGURA, FRAME_ALTURA);

        // Adicionar ao container
        cont.add(fundo);
    }

    // Textos( Bem_Vindo, Tamanho e Quantidade de Cores)
    public void textos(Container cont){
        // Texto "Bem-Vindo"
        bem_texto = new JLabel("BEM-VINDO");
        bem_texto.setFont(new Font("Serif", Font.ITALIC, 40) );
        bem_texto.setSize(260, 100);
        bem_texto.setLocation(260, 80); 
        bem_texto.setForeground(Color.BLACK);
        
        // Texto "Tamanho"
        tam_texto = new JLabel("Tamanho:");
        tam_texto.setFont( new Font("tam", Font.PLAIN, 18) );
        tam_texto.setSize(100, 50);
        tam_texto.setLocation(100, 230);
        tam_texto.setForeground(Color.BLACK);

        // Texto "Quantidade de cores"
        n_cores_texto = new JLabel("Quantidade de Cores:");
        n_cores_texto.setFont( new Font("ncor", Font.PLAIN, 18) );
        n_cores_texto.setSize(200, 50);
        n_cores_texto.setLocation(100, 340);
        n_cores_texto.setForeground(Color.BLACK);
        
        // Adicionar ao Container
        cont.add(bem_texto);
        cont.add(tam_texto);
        cont.add(n_cores_texto);
    }

    // Caixa para o jogador inserir o tamanho do mapa
    public void insert_tamanho(Container cont){
        tam_v = new JTextField("0");
        tam_v.setColumns(2);
        tam_v.setFont(new Font("Serif", Font.BOLD, 16));
        tam_v.setBounds(100, 275, 200, 40);
        tam_v.setBackground(Color.WHITE);

        // Adicionar ao Container + evento source
        cont.add(tam_v);
        tam_v.addActionListener(this);
    }

    // Caixa para o jogador inserir o numero de cores que vão aparecer em jogo
    public void insert_cores(Container cont){
        cor_v = new JTextField("0");
        cor_v.setColumns(2);
        cor_v.setFont(new Font("Serif", Font.BOLD, 16));
        cor_v.setBounds(100, 385, 200, 40);
        cor_v.setBackground(Color.WHITE);

        cont.add(cor_v);
        cor_v.addActionListener(this);
    }

    // Instanciar o botão para ir para a próxima página
    public void inserir_botao(JanelaMain c, Container cont)
    {
        // Nome, Cor, Tamanho/Posição 
        next_button = new JButton("Next");
        next_button.setBackground(Color.orange);
        next_button.setBounds(340, 500,100, 40);

        // Evento
        cont.add(next_button);
        next_button.addActionListener(this);
    }

    // Método Evento
    public void actionPerformed(ActionEvent event)
    {  
        // Escrever o tamanho
        if( event.getSource() == tam_v )
        {
            tamanh = Integer.parseInt( tam_v.getText() );
        }

        // Escrever a quantidade de cores
        else if( event.getSource() == cor_v)
        {
            num_cores = Integer.parseInt( cor_v.getText() );
        }

        // Ao pressionar o botão "next"
        else if( event.getSource() instanceof JButton)
        {
            setVisible(false);

            JanelaJogo janelaJogo = new JanelaJogo(tamanh, num_cores);
            janelaJogo.setVisible(true);

        }
    }
}