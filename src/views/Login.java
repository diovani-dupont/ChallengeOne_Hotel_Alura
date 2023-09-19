package views;

import db.LoginDAO;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

/**
 * Esta classe representa a tela de login da aplicação.
 */
public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    int xMouse, yMouse;
    private JLabel labelExit;

    /**
     * Método principal para iniciar a tela de login.
     *
     * @param "args" Argumentos do método principal.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Construtor para criar a tela de login.
     */
    public Login() {
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 788, 527);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 788, 527);
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 213, 128));
        panel_1.setBounds(484, 0, 304, 527);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel imgHotel = new JLabel("");
        imgHotel.setBounds(0, 0, 304, 538);
        panel_1.add(imgHotel);
        imgHotel.setIcon(new ImageIcon("src/new_img/img-hotel-login.png"));

        JPanel btnexit = new JPanel();
        btnexit.setBounds(251, 0, 53, 36);
        panel_1.add(btnexit);
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(new Color(252, 113, 56));
                labelExit.setForeground((SystemColor.window));;
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(new Color(255, 213, 128));
                labelExit.setForeground(new Color(82, 88, 80));;
            }
        });
        btnexit.setBackground(new Color(255, 213, 128));
        btnexit.setLayout(null);
        btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setForeground(new Color(82, 88, 80));;
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);

        txtUsuario = new JTextField();

        txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtUsuario.setText("");
        txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtUsuario.setForeground(new Color(82, 88, 80));
        txtUsuario.setBounds(65, 256, 324, 32);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(82, 88, 80));
        separator.setBounds(65, 292, 324, 2);
        panel.add(separator);

        JLabel labelTitulo = new JLabel("LOGIN");
        labelTitulo.setForeground(new Color(82, 88, 80));
        labelTitulo.setFont(new Font("Somatype ExtBd", Font.PLAIN, 35));
        labelTitulo.setBounds(196, 150, 89, 35);
        panel.add(labelTitulo);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(new Color(82, 88, 80));
        separator_1.setBounds(65, 393, 324, 2);
        panel.add(separator_1);

        txtSenha = new JPasswordField();
        txtSenha.setText("");

        txtSenha.setForeground(new Color(82, 88, 80));
        txtSenha.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtSenha.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtSenha.setBounds(65, 353, 324, 32);
        panel.add(txtSenha);

        JLabel LabelUsuario = new JLabel("Usuário:");
        LabelUsuario.setForeground(new Color(82, 88, 80));
        LabelUsuario.setFont(new Font("Somatype ExtBd", Font.PLAIN, 20));
        LabelUsuario.setBounds(65, 219, 107, 26);
        panel.add(LabelUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(new Color(82, 88, 80));
        lblSenha.setFont(new Font("Somatype ExtBd", Font.PLAIN, 20));
        lblSenha.setBounds(65, 316, 140, 26);
        panel.add(lblSenha);

        JPanel btnLogin = new JPanel();
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(252, 113, 56));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(new Color(82, 88, 80));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Login();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnLogin.setBackground(new Color(82, 88, 80));
        btnLogin.setBounds(65, 431, 122, 44);
        panel.add(btnLogin);
        btnLogin.setLayout(null);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel lblEntrar = new JLabel("ENTRAR");
        lblEntrar.setBounds(0, 0, 122, 44);
        btnLogin.add(lblEntrar);
        lblEntrar.setForeground(SystemColor.controlLtHighlight);
        lblEntrar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEntrar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JLabel logo = new JLabel("");
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setIcon(new ImageIcon("src/new_img/logo-50px.png"));
        logo.setBounds(65, 65, 48, 59);
        panel.add(logo);

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
        header.setBackground(SystemColor.window);
        header.setBounds(0, 0, 784, 36);
        panel.add(header);
        header.setLayout(null);
    }

    /**
     * Tenta efetuar login usando as informações fornecidas pelo usuário.
     *
     * @throws "SQLException" Exceção lançada se houver problemas ao se conectar com o banco de dados.
     */
    private void Login() throws SQLException {
        LoginDAO loginD = new LoginDAO();
        String[] usersDB = loginD.selectUser(txtUsuario.getText());
        String senha_digitada = new String(txtSenha.getPassword());

        if (usersDB[1] != null && txtUsuario.getText().equals(usersDB[0]) && senha_digitada.equals(usersDB[1])) {
            MenuUsuario menu = new MenuUsuario();
            menu.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario ou Senha não válidos");
        }
    }

    /**
     * Método acionado ao pressionar o mouse no cabeçalho.
     *
     * @param "evt" O evento do mouse.
     */
    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    /**
     * Método acionado ao arrastar o mouse com o botão pressionado no cabeçalho.
     *
     * @param "evt" O evento do mouse.
     */
    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}