package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Image;


/**
 * Classe que representa a janela principal do aplicativo.
 */
@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame {

    private JPanel contentPane;
    private JLabel labelExit;
    // Coordenadas do mouse para arrastar a janela
    int xMouse, yMouse;

    /**
     * Ponto de entrada principal para esta janela.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Construtor da classe MenuPrincipal.
     */
    public MenuPrincipal() {

        // Configuração da janela principal
        setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/new_img/aH-40px.png")));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 910, 537);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);

        // Configuração de painéis e componentes da tela
        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.window);
        panel.setBounds(0, 0, 910, 537);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel imagenFondo = new JLabel("");
        imagenFondo.setBounds(-25, 0, 732, 501);

        ImageIcon gifIcon = new ImageIcon(MenuPrincipal.class.getResource("/gifs/menu_1.gif"));
        Image resizedGif = gifIcon.getImage().getScaledInstance(520, 501, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon1 = new ImageIcon(resizedGif);

        imagenFondo.setIcon(scaledIcon1);
        panel.add(imagenFondo);

        JLabel logo = new JLabel("");
        logo.setBounds(575, 80, 250, 250);
        logo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/new_img/aH-250px.png")));
        panel.add(logo);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 500, 910, 37);
        panel_1.setBackground(new Color(255, 255, 255));
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblText = new JLabel("Desenvolvido por Diovani Dupont © 2023");
        lblText.setBounds(50, 7, 910, 25);
        lblText.setForeground(new Color(0, 0, 0));
        lblText.setFont(new Font("Roboto", Font.PLAIN, 16));
        lblText.setOpaque(false);
        panel_1.add(lblText);

        JLabel lblImage = new JLabel();
        lblImage.setBounds(0, 5, 910, 50);
        lblImage.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/new_img/rodape.png")));
        panel_1.add(lblImage);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 910, 36);
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
        header.setLayout(null);
        header.setBackground(new Color(255, 255, 255));
        panel.add(header);

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
        btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnexit.setBackground(new Color(255, 255, 255));
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));


        JPanel btnLogin = new JPanel();
        btnLogin.setBounds(660, 350, 90, 170);
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }
        });
        btnLogin.setLayout(null);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setBackground(SystemColor.window);
        panel.add(btnLogin);

        JLabel imageLogin = new JLabel("");
        imageLogin.setBounds(0, 0, 90, 90);
        btnLogin.add(imageLogin);
        imageLogin.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon originalIcon = new ImageIcon(MenuPrincipal.class.getResource("/new_img/login.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        imageLogin.setIcon(scaledIcon);

        JLabel lblTitulo = new JLabel("LOGIN");
        lblTitulo.setBounds(10, 90, 70, 34);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(82, 88, 80));
        lblTitulo.setFont(new Font("Somatype ExtBd", Font.PLAIN, 20));
        btnLogin.add(lblTitulo);
    }

    /**
     * Método chamado quando o mouse é pressionado no cabeçalho da janela.
     * @param "evt" Evento do mouse.
     */
    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    /**
     * Método chamado quando o mouse é arrastado no cabeçalho da janela.
     * @param "evt" Evento do mouse.
     */
    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
