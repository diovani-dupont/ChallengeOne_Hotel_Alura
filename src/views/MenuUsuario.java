package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.SystemColor;
import javax.swing.JSeparator;

/**
 * Janela do menu principal do usuário.
 * Esta classe representa a janela do menu principal do usuário, onde são exibidas as principais opções de interação com o sistema.
 */
@SuppressWarnings("serial")
public class MenuUsuario extends JFrame {

    private JPanel contentPane;
    int xMouse, yMouse;
    private JLabel labelExit;
    private JLabel labelRegistro;

    /**
     * Ponto de entrada da aplicação.
     * @param "args" Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuUsuario frame = new MenuUsuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Construtor para criar a janela MenuUsuario.
     */
    public MenuUsuario() {
        setIconImage(new ImageIcon("src/new_img/aH-40px.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 944, 609);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });

        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(255, 255, 255));
        panelMenu.setBounds(0, 0, 257, 609);
        contentPane.add(panelMenu);
        panelMenu.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 572, 950, 37);
        panel_1.setBackground(new Color(82, 88, 80));
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblImage = new JLabel();
        lblImage.setBounds(0, 5, 950, 50);
        lblImage.setIcon(new ImageIcon(MenuUsuario.class.getResource("/new_img/rodape.png")));
        panel_1.add(lblImage);

        JPanel btnBuscar = new JPanel();
        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBuscar.setBackground(new Color(245, 134, 52));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnBuscar.setBackground(new Color(82, 89, 81));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Buscar buscar = new Buscar();
                buscar.setVisible(true);
                dispose();
            }
        });

        btnBuscar.setBounds(0, 312, 257, 56);
        btnBuscar.setBackground(new Color(82, 89, 81));
        btnBuscar.setLayout(null);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelMenu.add(btnBuscar);

        JLabel lblBusquedaDeReservas = new JLabel("Buscar");
        lblBusquedaDeReservas.setIcon(new ImageIcon("src/new_img/pessoas.png"));
        lblBusquedaDeReservas.setBounds(25, 11, 200, 34);
        lblBusquedaDeReservas.setHorizontalAlignment(SwingConstants.LEFT);
        lblBusquedaDeReservas.setForeground(new Color(255, 255, 255));
        lblBusquedaDeReservas.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnBuscar.add(lblBusquedaDeReservas);


        JLabel logo = new JLabel("");
        logo.setBounds(50, 58, 150, 150);
        panelMenu.add(logo);
        logo.setIcon(new ImageIcon("src/new_img/aH-150px.png"));

        JPanel btnRegistro = new JPanel();
        btnRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRegistro.setBackground(new Color(245, 134, 52));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnRegistro.setBackground(new Color(82, 89, 81));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                ReservasView reservas = new ReservasView();
                reservas.setVisible(true);
                dispose();
            }
        });
        btnRegistro.setBounds(0, 255, 257, 56);
        btnRegistro.setBackground (new Color(82, 89, 81));
        panelMenu.add(btnRegistro);
        btnRegistro.setLayout(null);
        btnRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelRegistro = new JLabel("Registro de reservas");
        labelRegistro.setIcon(new ImageIcon("src/new_img/reservado.png"));
        labelRegistro.setForeground(SystemColor.text);
        labelRegistro.setBounds(25, 11, 205, 34);
        labelRegistro.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelRegistro.setHorizontalAlignment(SwingConstants.LEFT);
        btnRegistro.add(labelRegistro);

        JSeparator separator = new JSeparator();
        separator.setBounds(26, 219, 201, 2);
        panelMenu.add(separator);
        header.setLayout(null);
        header.setBackground(new Color(255, 255, 255));
        header.setBounds(0, 0, 944, 36);

        contentPane.add(header);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.red);
                labelExit.setForeground(new Color(255, 255, 255));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(new Color(255, 255, 255));
                labelExit.setForeground(Color.black);
            }
        });

        btnexit.setLayout(null);
        btnexit.setBackground(new Color(255, 255, 255));
        btnexit.setBounds(891, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel panelFecha = new JPanel();
        panelFecha.setBackground(new Color(82, 89, 81));
        panelFecha.setBounds(256, 84, 688, 121);
        contentPane.add(panelFecha);
        panelFecha.setLayout(null);

        JLabel lblTituloPrincipal = new JLabel("Sistema de reservas Hotel Alura");
        lblTituloPrincipal.setBounds(180, 11, 356, 42);
        panelFecha.add(lblTituloPrincipal);
        lblTituloPrincipal.setForeground(new Color(255, 255, 255));
        lblTituloPrincipal.setFont(new Font("Roboto", Font.PLAIN, 24));

        JLabel labelFecha = new JLabel("New label");
        labelFecha.setBounds(188, 64, 350, 36);
        panelFecha.add(labelFecha);
        labelFecha.setForeground(new Color(255, 255, 255));
        labelFecha.setFont(new Font("Roboto", Font.PLAIN, 33));
        Date fechaActual = new Date();
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
        labelFecha.setText("Hoje é dia " + fecha);

        JLabel lbltitulo = new JLabel("Bem-vindo");
        lbltitulo.setFont(new Font("Roboto", Font.BOLD, 24));
        lbltitulo.setBounds(302, 234, 147, 46);
        contentPane.add(lbltitulo);

        String textoDescripcion = "<html><body>Sistema de reservas de hotéis. Controle e gerencie de forma otimizada e fácil <br> o fluxo de reservas e hóspedes do hotel   </body></html>";
        JLabel labelDescripcion_0 = new JLabel(textoDescripcion);
        labelDescripcion_0.setFont(new Font("Roboto", Font.PLAIN, 17));

        labelDescripcion_0.setBounds(312, 291, 598, 66);
        contentPane.add(labelDescripcion_0);

        String textoDescricao1 = "<html><body> Esta ferramenta permitirá que você mantenha um controle completo e detalhado de suas reservas e hóspedes, você terá acesso a ferramentas especiais para tarefas específicas como:</body></html>";
        JLabel labelDescricao_1 = new JLabel(textoDescricao1);
        labelDescricao_1.setFont(new Font("Roboto", Font.PLAIN, 17));
        labelDescricao_1.setBounds(311, 345, 569, 88);
        contentPane.add(labelDescricao_1);

        JLabel labelDescricao_2 = new JLabel("- Registro de Reservas e Hóspedes");
        labelDescricao_2.setFont(new Font("Roboto", Font.PLAIN, 17));
        labelDescricao_2.setBounds(312, 444, 295, 27);
        contentPane.add(labelDescricao_2);

        JLabel labelDescricao_3 = new JLabel("- Edição de Reservas e Hóspedes existentes");
        labelDescricao_3.setFont(new Font("Roboto", Font.PLAIN, 17));
        labelDescricao_3.setBounds(312, 482, 355, 27);
        contentPane.add(labelDescricao_3);

        JLabel labelDescricao_4 = new JLabel("- Excluir todos os tipos de registros");
        labelDescricao_4.setFont(new Font("Roboto", Font.PLAIN, 17));
        labelDescricao_4.setBounds(312, 520, 295, 27);
        contentPane.add(labelDescricao_4);

    }

    /**
     * Manipulador de evento para detectar onde o mouse foi pressionado no header.
     * @param "evt" Evento de mouse.
     */
    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    /**
     * Manipulador de evento para permitir o arrasto da janela usando o header.
     * @param "evt" Evento de mouse.
     */
    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

}