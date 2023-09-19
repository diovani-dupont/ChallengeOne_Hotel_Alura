package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import db.Hospede;
import db.InsertHospede;
import db.InsertReserva;
import db.Reservas;
import db.ReservasDAO;

import javax.swing.text.AbstractDocument;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.Format;

import java.util.Random;

/**
 * Esta classe representa o registro de um hóspede.
 * Fornecendo interface gráfica para inserir informações relevantes sobre o hóspede.
 */

@SuppressWarnings("serial")
public class RegistroHospede extends JFrame {


    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtSobrenome;
    private JTextField txtTelefone;
    private JTextField txtNreserva;
    private JDateChooser txtDataN;
    private JComboBox<Format> txtNacionalidade;
    private JLabel labelExit;
    private JLabel labelAtras;
    int xMouse, yMouse;

    private Reservas reser;

    /**
     * Verifica se o telefone fornecido está no formato válido.
     *
     * @param "telefone" O número de telefone a ser verificado.
     * @return Retorna verdadeiro se o telefone for válido, caso contrário, retorna falso.
     */
    private boolean isTelefoneValido(String telefone) {
        return telefone.matches("\\(\\d{2}\\)\\d{5}-\\d{4}");
    }

    /**
     * Construtor para a classe RegistroHospede.
     *
     * @param "r" Uma instância da classe Reservas.
     */
    public RegistroHospede(Reservas r) {
        reser = r;

        setIconImage(new ImageIcon("src/new_img/logo-50px.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 634);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.text);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setUndecorated(true);
        contentPane.setLayout(null);


        JPanel header = new JPanel();
        header.setBounds(-54, 0, 910, 36);
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

        JPanel btnexit = new JPanel();
        btnexit.setBounds(857, 0, 53, 36);
        contentPane.add(btnexit);
        btnexit.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                MenuPrincipal principal = new MenuPrincipal();
                principal.setVisible(true);
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.white);

        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(SystemColor.black);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        header.setLayout(null);
        header.setBackground(SystemColor.text);
        header.setOpaque(false);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ReservasView reservas = new ReservasView();
                reservas.setVisible(true);
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(new Color(82, 89, 81));
                labelAtras.setForeground(Color.white);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(new Color(82, 89, 81));
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setForeground(Color.WHITE);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        txtNome = new JTextField();
        txtNome.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNome.setBounds(560, 135, 285, 33);
        txtNome.setBackground(Color.WHITE);
        txtNome.setColumns(10);
        txtNome.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtNome);

        txtSobrenome = new JTextField();
        txtSobrenome.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtSobrenome.setBounds(560, 204, 285, 33);
        txtSobrenome.setColumns(10);
        txtSobrenome.setBackground(Color.WHITE);
        txtSobrenome.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtSobrenome);

        txtDataN = new JDateChooser();
        txtDataN.setBounds(560, 278, 285, 36);
        txtDataN.getCalendarButton().setIcon(new ImageIcon("src/new_img/icon-reservas.png"));
        txtDataN.getCalendarButton().setBackground(new Color(82, 89, 81));
        txtDataN.setDateFormatString("yyyy-MM-dd");

        ((JTextField)txtDataN.getDateEditor().getUiComponent()).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JTextField)txtDataN.getDateEditor().getUiComponent()).setBackground(new Color(194, 194, 194));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JTextField)txtDataN.getDateEditor().getUiComponent()).setBackground(Color.WHITE);
            }
        });

        txtDataN.getCalendarButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                txtDataN.getCalendarButton().setBackground(new Color(245, 134, 52));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                txtDataN.getCalendarButton().setBackground(new Color(82, 89, 81));
            }
        });

        contentPane.add(txtDataN);

        txtNacionalidade = new JComboBox();
        txtNacionalidade.setBounds(560, 350, 289, 36);
        txtNacionalidade.setBackground(SystemColor.text);
        txtNacionalidade.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNacionalidade.setModel(new DefaultComboBoxModel(new String[] {"Brasileira","Afegã", "Albanesa", "Alemã", "Andorrana", "Angolana", "Antiguana", "Argentina", "Armênia", "Australiana", "Austríaca", "Azerbaijanesa", "Bahamense", "Barenita", "Bangladeshi", "Barbadiana", "Belga", "Belizenha", "Beninense", "Bielo-russa", "Boliviana", "Bósnia", "Botsuanesa", "Bruneiana", "Búlgara", "Burquinense", "Burundinesa", "Butanesa", "Cabo-verdiana", "Camaronesa", "Cambojana", "Canadense", "Catari", "Cazaque", "Chadiana", "Chilena", "Chinesa", "Cipriota", "Colombiana", "Comorense", "Congolesa", "Norte-coreana", "Sul-coreana", "Costarriquenha", "Croata", "Cubana", "Dinamarquesa", "Dominiquense", "Egípcia", "Salvadorenha", "Escocesa", "Eslovaca", "Eslovena", "Espanhola", "Estadunidense", "Estoniana", "Etíope", "Fijiana", "Filipina", "Finlandesa", "Francesa", "Gabonesa", "Gambiana", "Ganense", "Georgiana", "Gibraltina", "Grega", "Grenadina", "Guatemalteca", "Guianense", "Guineana", "Guineense", "Haitiana", "Holandesa", "Hondurenha", "Húngara", "Iemenita", "Indiana", "Indonésia", "Iraniana", "Iraquiana", "Irlandesa", "Islandesa", "Israelense", "Italiana", "Jamaicana", "Japonesa", "Jordaniana", "Kiribatiana", "Kuwaitiana", "Laosiana", "Lesotense", "Letã", "Libanesa", "Liberiana", "Líbia", "Liechtensteiniense", "Lituana", "Luxemburguesa", "Macedônia", "Malgaxe", "Malásia", "Malauiana", "Maldívia", "Malinesa", "Maltesa", "Marroquina", "Mauriciana", "Mauritana", "Mexicana", "Mianmarense", "Micronésia", "Moçambicana", "Moldávia", "Monegasca", "Mongol", "Montenegrina", "Namíbia", "Nauruana", "Nepalesa", "Nicaraguense", "Nigeriana", "Norueguesa", "Neozelandesa", "Omanense", "Palauense", "Panamenha", "Papua-nova-guineense", "Paquistanesa", "Paraguaia", "Peruana", "Polonesa", "Portuguesa", "Queniana", "Quirguiz", "Romena", "Ruandesa", "Russa", "Salomônica", "Samoana", "Santa-lucense", "São-cristovense", "São-marinhense", "São-tomense", "Saudita", "Senegalesa", "Sérvia", "Seichelense", "Serra-leonesa", "Singapuriana", "Síria", "Somali", "Srilanquesa", "Suazi", "Sudanesa", "Sueca", "Suíça", "Surinamesa", "Tailandesa", "Taiwanesa", "Tajique", "Tanzaniana", "Tcheca", "Timorense", "Togolesa", "Tonganesa", "Trinitária", "Tunisiana", "Turcomena", "Turca", "Tuvaluana", "Ucraniana", "Ugandense", "Uruguaia", "Uzbeque", "Vanuatuense", "Vaticano", "Venezuelana", "Vietnamita", "Zambiana", "Zimbabuense"
        }));
        contentPane.add(txtNacionalidade);

        JLabel lblNome = new JLabel("NOME");
        lblNome.setBounds(562, 119, 253, 14);
        lblNome.setForeground(SystemColor.textInactiveText);
        lblNome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblNome);

        JLabel lblSobrenome = new JLabel("SOBRENOME");
        lblSobrenome.setBounds(560, 189, 255, 14);
        lblSobrenome.setForeground(SystemColor.textInactiveText);
        lblSobrenome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblSobrenome);

        JLabel lblDataN = new JLabel("DATA DE NASCIMENTO");
        lblDataN.setBounds(560, 256, 255, 14);
        lblDataN.setForeground(SystemColor.textInactiveText);
        lblDataN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblDataN);

        JLabel lblNacionalidade = new JLabel("NACIONALIDADE");
        lblNacionalidade.setBounds(560, 326, 255, 14);
        lblNacionalidade.setForeground(SystemColor.textInactiveText);
        lblNacionalidade.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblNacionalidade);

        JLabel lblTelefone = new JLabel("TELEFONE");
        lblTelefone.setBounds(562, 406, 253, 14);
        lblTelefone.setForeground(SystemColor.textInactiveText);
        lblTelefone.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblTelefone);

        txtTelefone = new JTextField();
        ((AbstractDocument) txtTelefone.getDocument()).setDocumentFilter(new TelefoneDocumentFilter());
        txtTelefone.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtTelefone.setBounds(560, 424, 285, 33);
        txtTelefone.setColumns(10);
        txtTelefone.setBackground(Color.WHITE);
        txtTelefone.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtTelefone);

        JLabel lblTitulo = new JLabel("REGISTRO HÓSPEDE");
        lblTitulo.setBounds(580, 55, 300, 42);
        lblTitulo.setForeground(new Color(82, 89, 81));
        lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
        contentPane.add(lblTitulo);

        JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
        lblNumeroReserva.setBounds(560, 474, 253, 14);
        lblNumeroReserva.setForeground(SystemColor.textInactiveText);
        lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        contentPane.add(lblNumeroReserva);

        txtNreserva = new JTextField();
        txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNreserva.setBounds(560, 495, 285, 33);
        txtNreserva.setColumns(10);
        txtNreserva.setBackground(Color.WHITE);
        txtNreserva.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(txtNreserva);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setBounds(560, 170, 289, 2);
        separator_1_2.setForeground(new Color(82, 89, 81));
        separator_1_2.setBackground(new Color(82, 89, 81));
        contentPane.add(separator_1_2);

        JSeparator separator_1_2_1 = new JSeparator();
        separator_1_2_1.setBounds(560, 240, 289, 2);
        separator_1_2_1.setForeground(new Color(82, 89, 81));
        separator_1_2_1.setBackground(new Color(82, 89, 81));
        contentPane.add(separator_1_2_1);

        JSeparator separator_1_2_2 = new JSeparator();
        separator_1_2_2.setBounds(560, 314, 289, 2);
        separator_1_2_2.setForeground(new Color(82, 89, 81));
        separator_1_2_2.setBackground(new Color(82, 89, 81));
        contentPane.add(separator_1_2_2);

        JSeparator separator_1_2_3 = new JSeparator();
        separator_1_2_3.setBounds(560, 386, 289, 2);
        separator_1_2_3.setForeground(new Color(82, 89, 81));
        separator_1_2_3.setBackground(new Color(82, 89, 81));
        contentPane.add(separator_1_2_3);

        JSeparator separator_1_2_4 = new JSeparator();
        separator_1_2_4.setBounds(560, 457, 289, 2);
        separator_1_2_4.setForeground(new Color(82, 89, 81));
        separator_1_2_4.setBackground(new Color(82, 89, 81));
        contentPane.add(separator_1_2_4);

        JSeparator separator_1_2_5 = new JSeparator();
        separator_1_2_5.setBounds(560, 529, 289, 2);
        separator_1_2_5.setForeground(new Color(82, 89, 81));
        separator_1_2_5.setBackground(new Color(82, 89, 81));
        contentPane.add(separator_1_2_5);

        JPanel btnsalvar = new JPanel();
        btnsalvar.setBounds(723, 560, 122, 35);
        btnsalvar.addMouseListener(new MouseAdapter() {
            /**
             * Método chamado quando o mouse é clicado no botão "Salvar". Este método
             * verifica se o telefone inserido é válido, e, em seguida, tenta inserir a
             * reserva e os dados do hóspede no banco de dados.
             * @param "e" Evento do mouse.
             */
            @Override
            public void mouseClicked(MouseEvent e) {

                String telefone = txtTelefone.getText();
                if (!isTelefoneValido(telefone)) {
                    JOptionPane.showMessageDialog(RegistroHospede.this, "Por favor, insira um número de telefone válido no formato (00)00000-0000.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                InsertReserva novaReserva = new InsertReserva(reser);

                try {
                    novaReserva.inserir();
                    novaReserva.comita();

                    int novoID = novaReserva.getId_insert();

                    Hospede novoHospede = new Hospede(txtNome.getText(), txtSobrenome.getText(), txtTelefone.getText(), txtDataN.getDate(), novoID, String.valueOf(txtNacionalidade.getSelectedItem()));

                    InsertHospede novoHospedeInsert = new InsertHospede(novoHospede, novoID);

                    try {
                        novoHospedeInsert.inserir();
                        novoHospedeInsert.comita();

                        Sucesso dialogoDeSucesso = new Sucesso();
                        dialogoDeSucesso.setModal(true);
                        dialogoDeSucesso.setVisible(true); // Chama a janela customizada

                        MenuUsuario menu = new MenuUsuario();
                        menu.setVisible(true);
                        dispose();

                    } catch (SQLException ex) {
                        System.out.println("Erro ao inserir hospede");
                        novoHospedeInsert.rollB();
                        throw new RuntimeException(ex);
                    }

                } catch (SQLException ex) {
                    System.out.println("Erro ao inserir reserva");
                    try {
                        novaReserva.rollB();
                    } catch (SQLException exc) {
                        throw new RuntimeException(exc);
                    }
                    throw new RuntimeException(ex);
                }
            }


            @Override
            public void mouseEntered(MouseEvent e) {
                btnsalvar.setBackground(new Color(245, 134, 52));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnsalvar.setBackground(new Color(82, 89, 81));
            }
        });
        btnsalvar.setLayout(null);
        btnsalvar.setBackground(new Color(82, 89, 81));
        contentPane.add(btnsalvar);
        btnsalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel labelSalvar = new JLabel("SALVAR");
        labelSalvar.setHorizontalAlignment(SwingConstants.CENTER);
        labelSalvar.setForeground(Color.WHITE);
        labelSalvar.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelSalvar.setBounds(0, 0, 122, 35);
        btnsalvar.add(labelSalvar);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 489, 634);
        panel.setBackground(new Color(82, 89, 81));
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel imageFundo = new JLabel("");
        imageFundo.setBounds(0, 121, 479, 502);
        panel.add(imageFundo);
        imageFundo.setIcon(new ImageIcon("src/new_img/registro.png"));

        JLabel logo = new JLabel("");
        logo.setBounds(180, 40, 140, 140);
        panel.add(logo);
        logo.setIcon(new ImageIcon("src/new_img/Ha1-100px.png"));
    }

    private int gerarNumeroReservaUnico() {
        Random random = new Random();
        ReservasDAO reservasDAO;
        try {
            reservasDAO = new ReservasDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar ReservasDAO", e);
        }

        int numeroReserva;
        do {
            numeroReserva = random.nextInt(1000000);
        } while (reservasDAO.numeroReservaExiste(numeroReserva));

        return numeroReserva;
    }

    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

}