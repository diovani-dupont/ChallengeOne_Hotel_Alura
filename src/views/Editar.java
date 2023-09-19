package views;

import com.mchange.v1.util.ArrayUtils;
import com.toedter.calendar.JDateChooser;
import db.Hospede;
import db.HospedesDAO;
import db.Reservas;
import db.ReservasDAO;
import extras.DateManipulation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.Format;
import java.util.Date;

/**
 * A classe Editar fornece uma interface gráfica para editar informações relacionadas a reservas e hóspedes.
 */
@SuppressWarnings("serial")
public class Editar extends JFrame {
    // Componentes da GUI
    private JPanel contentPane;
    public static JTextField txtValor;
    public static JDateChooser txtDataE;
    public static JDateChooser txtDataS;
    public static JComboBox<String> txtFormaPagamento;
    int xMouse, yMouse;
    private JLabel labelExit;
    private JLabel lblValorSimbolo;
    private JLabel labelAtras;

    // Variáveis de data
    private Date hoje = new Date();

    private Date dataEntrada;
    private Date dataSaida;

    // Componentes relacionados ao hóspede
    private JTextField txtNome;
    private JTextField txtSobrenome;
    private JTextField txtTelefone;
    private JDateChooser txtDataN;
    private JComboBox<Format> txtNacionalidade;

    // Modelos de dados
    private Reservas reser;
    private Hospede hos;

    // DAOs para interação com banco de dados
    private ReservasDAO rDAO = new ReservasDAO();
    private HospedesDAO hDAO = new HospedesDAO();

    // Ferramenta para manipulação de data
    private DateManipulation dm = new DateManipulation();

    // Array contendo as nacionalidades
    private String[] nacionalidades = {"Brasileira","Afegã", "Albanesa", "Alemã", "Andorrana", "Angolana", "Antiguana", "Argentina", "Armênia", "Australiana", "Austríaca", "Azerbaijanesa", "Bahamense", "Barenita", "Bangladeshi", "Barbadiana", "Belga", "Belizenha", "Beninense", "Bielo-russa", "Boliviana", "Bósnia", "Botsuanesa", "Bruneiana", "Búlgara", "Burquinense", "Burundinesa", "Butanesa", "Cabo-verdiana", "Camaronesa", "Cambojana", "Canadense", "Catari", "Cazaque", "Chadiana", "Chilena", "Chinesa", "Cipriota", "Colombiana", "Comorense", "Congolesa", "Norte-coreana", "Sul-coreana", "Costarriquenha", "Croata", "Cubana", "Dinamarquesa", "Dominiquense", "Egípcia", "Salvadorenha", "Escocesa", "Eslovaca", "Eslovena", "Espanhola", "Estadunidense", "Estoniana", "Etíope", "Fijiana", "Filipina", "Finlandesa", "Francesa", "Gabonesa", "Gambiana", "Ganense", "Georgiana", "Gibraltina", "Grega", "Grenadina", "Guatemalteca", "Guianense", "Guineana", "Guineense", "Haitiana", "Holandesa", "Hondurenha", "Húngara", "Iemenita", "Indiana", "Indonésia", "Iraniana", "Iraquiana", "Irlandesa", "Islandesa", "Israelense", "Italiana", "Jamaicana", "Japonesa", "Jordaniana", "Kiribatiana", "Kuwaitiana", "Laosiana", "Lesotense", "Letã", "Libanesa", "Liberiana", "Líbia", "Liechtensteiniense", "Lituana", "Luxemburguesa", "Macedônia", "Malgaxe", "Malásia", "Malauiana", "Maldívia", "Malinesa", "Maltesa", "Marroquina", "Mauriciana", "Mauritana", "Mexicana", "Mianmarense", "Micronésia", "Moçambicana", "Moldávia", "Monegasca", "Mongol", "Montenegrina", "Namíbia", "Nauruana", "Nepalesa", "Nicaraguense", "Nigeriana", "Norueguesa", "Neozelandesa", "Omanense", "Palauense", "Panamenha", "Papua-nova-guineense", "Paquistanesa", "Paraguaia", "Peruana", "Polonesa", "Portuguesa", "Queniana", "Quirguiz", "Romena", "Ruandesa", "Russa", "Salomônica", "Samoana", "Santa-lucense", "São-cristovense", "São-marinhense", "São-tomense", "Saudita", "Senegalesa", "Sérvia", "Seichelense", "Serra-leonesa", "Singapuriana", "Síria", "Somali", "Srilanquesa", "Suazi", "Sudanesa", "Sueca", "Suíça", "Surinamesa", "Tailandesa", "Taiwanesa", "Tajique", "Tanzaniana", "Tcheca", "Timorense", "Togolesa", "Tonganesa", "Trinitária", "Tunisiana", "Turcomena", "Turca", "Tuvaluana", "Ucraniana", "Ugandense", "Uruguaia", "Uzbeque", "Vanuatuense", "Vaticano", "Venezuelana", "Vietnamita", "Zambiana", "Zimbabuense"};

    /**
     * Construtor para criar uma janela de edição baseada no ID e tipo (reserva ou hóspede).
     *
     * @param "id" O ID da reserva ou hóspede.
     * @param "tipo" O tipo de informação a ser editada (0 para reserva, 1 para hóspede).
     * @throws "SQLException" Em caso de erro na comunicação com o banco de dados.
     */
    public Editar(int id, int tipo) throws SQLException {
        super("Editar Reserva e Hóspede");
        setIconImage(new ImageIcon("src/new_img/aH-40px.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 560);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(82, 89, 81));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);

        if(tipo == 0){ //reserva
            try {
                reser = rDAO.selectReserva(id);
                hos = hDAO.selectHospedeIdReserva(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if(tipo == 1){ //hospede
            try {
                hos = hDAO.selectHospede(id);
                reser = rDAO.selectReserva(hos.getIdHos());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Inicialização e configuração dos componentes da GUI

        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 910, 560);
        contentPane.add(panel);
        panel.setLayout(null);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(82, 89, 81));
        separator_1_2.setBounds(68, 195, 289, 2);
        separator_1_2.setBackground(new Color(82, 89, 81));
        panel.add(separator_1_2);

        JSeparator separator_1_3 = new JSeparator();
        separator_1_3.setForeground(new Color(82, 89, 81));
        separator_1_3.setBackground(new Color(82, 89, 81));
        separator_1_3.setBounds(68, 453, 289, 2);
        panel.add(separator_1_3);

        JSeparator separator_1_1 = new JSeparator();
        separator_1_1.setForeground(new Color(82, 89, 81));
        separator_1_1.setBounds(68, 281, 289, 11);
        separator_1_1.setBackground(new Color(82, 89, 81));
        panel.add(separator_1_1);

        txtDataE = new JDateChooser();
        txtDataE.getCalendarButton().setBackground(new Color(82, 89, 81));
        txtDataE.getCalendarButton().setIcon(new ImageIcon("src/new_img/icon-reservas.png"));
        txtDataE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
        txtDataE.setBounds(68, 161, 289, 35);
        txtDataE.getCalendarButton().setBounds(268, 0, 21, 33);
        txtDataE.setBackground(Color.WHITE);
        txtDataE.setBorder(new LineBorder(new Color(82, 89, 81)));
        txtDataE.setDateFormatString("yyyy-MM-dd");
        txtDataE.setFont(new Font("Roboto", Font.PLAIN, 18));

        System.out.println("Data de entrada:" + reser.getDtEntrada());
        txtDataE.setDate(dm.StringDoDate(reser.getDtEntrada()));

        panel.add(txtDataE);

        lblValorSimbolo = new JLabel("R$");
        lblValorSimbolo.setVisible(false);
        lblValorSimbolo.setBounds(121, 332, 17, 25);
        lblValorSimbolo.setForeground(new Color(82, 89, 81));
        lblValorSimbolo.setFont(new Font("Roboto", Font.BOLD, 17));

        panel.add(lblValorSimbolo);

        JLabel lblCheckIn = new JLabel("DATA DE CHECK IN");
        lblCheckIn.setForeground(new Color(82, 89, 81));
        lblCheckIn.setBounds(68, 136, 250, 14);
        lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 17));
        panel.add(lblCheckIn);

        JLabel lblCheckOut = new JLabel("DATA DE CHECK OUT");
        lblCheckOut.setForeground(new Color(82, 89, 81));
        lblCheckOut.setBounds(68, 221, 250, 14);
        lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 17));
        panel.add(lblCheckOut);

        txtDataS = new JDateChooser();
        txtDataS.getCalendarButton().setIcon(new ImageIcon("src/new_img/icon-reservas.png"));
        txtDataS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
        txtDataS.setBounds(68, 246, 289, 35);
        txtDataS.getCalendarButton().setBounds(267, 1, 21, 31);
        txtDataS.setBackground(Color.WHITE);
        txtDataS.setFont(new Font("Roboto", Font.PLAIN, 18));

        System.out.println("Data de saida:" + reser.getDtSaida());
        System.out.println(dm.StringDoDate(reser.getDtSaida()));
        txtDataS.setDate(dm.StringDoDate(reser.getDtSaida()));

        txtDataS.setDateFormatString("yyyy-MM-dd");
        txtDataS.getCalendarButton().setBackground(new Color(82, 89, 81));
        txtDataS.setBorder(new LineBorder(new Color(255, 255, 255), 0));
        panel.add(txtDataS);

        txtValor = new JTextField();
        txtValor.setBackground(SystemColor.text);
        txtValor.setHorizontalAlignment(SwingConstants.CENTER);
        txtValor.setForeground(Color.BLACK);
        txtValor.setBounds(78, 328, 100, 33);
        txtValor.setEditable(false);
        txtValor.setFont(new Font("Roboto Black", Font.BOLD, 20));
        txtValor.setBorder(BorderFactory.createEmptyBorder());
        panel.add(txtValor);
        txtValor.setColumns(10);
        txtValor.setText(String.valueOf(reser.getValor()));

        JLabel lblValor = new JLabel("VALOR DA RESERVA (R$199 a Diária)");
        lblValor.setForeground(SystemColor.textInactiveText);
        lblValor.setBounds(72, 303, 300, 14);
        lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 17));
        panel.add(lblValor);

        txtFormaPagamento = new JComboBox();
        txtFormaPagamento.setBounds(68, 417, 289, 38);
        txtFormaPagamento.setBackground(SystemColor.text);
        txtFormaPagamento.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
        txtFormaPagamento.setFont(new Font("Roboto", Font.PLAIN, 17));
        txtFormaPagamento.setModel(new DefaultComboBoxModel(new String[] {"Cartão de Crédito", "Cartão de Débito", "Dinheiro"}));
        int selecionadoFP = 99;
        if(reser.getForma_pag().equals("Cartão de Crédito")){
            selecionadoFP = 0;
        }else if(reser.getForma_pag().equals("Cartão de Débito")){
            selecionadoFP = 1;
        }else if(reser.getForma_pag().equals("Dinheiro")){
            selecionadoFP = 2;
        }
        txtFormaPagamento.setSelectedIndex(selecionadoFP);
        panel.add(txtFormaPagamento);

        JLabel lblFormaPago = new JLabel("FORMA DE PAGAMENTO");
        lblFormaPago.setForeground(SystemColor.textInactiveText);
        lblFormaPago.setBounds(68, 382, 300, 24);
        lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 17));
        panel.add(lblFormaPago);

        JLabel lblTitulo = new JLabel("EDITAR RESERVA E HOSPEDE");
        lblTitulo.setBounds(109, 60, 300, 42);
        lblTitulo.setForeground(new Color(82, 89, 81));
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
        panel.add(lblTitulo);

        txtNome = new JTextField();
        txtNome.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNome.setBounds(560, 65, 285, 33);
        //txtNome.setBounds(560, 135, 285, 33);
        txtNome.setBackground(Color.WHITE);
        txtNome.setColumns(10);
        txtNome.setBorder(BorderFactory.createEmptyBorder());
        txtNome.setText(hos.getNome());
        panel.add(txtNome);

        txtSobrenome = new JTextField();
        txtSobrenome.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtSobrenome.setBounds(560, 140, 285, 33);
        txtSobrenome.setColumns(10);
        txtSobrenome.setBackground(Color.WHITE);
        txtSobrenome.setBorder(BorderFactory.createEmptyBorder());

        txtSobrenome.setText(hos.getsNome());

        panel.add(txtSobrenome);

        txtDataN = new JDateChooser();
        txtDataN.setBounds(560, 228, 285, 36);
        txtDataN.getCalendarButton().setIcon(new ImageIcon("src/new_img/icon-reservas.png"));
        txtDataN.getCalendarButton().setBackground(new Color(82, 89, 81));
        txtDataN.setDateFormatString("yyyy-MM-dd");

        txtDataN.setDate(dm.StringDoDate(hos.getDataNasc()));

        panel.add(txtDataN);

        txtNacionalidade = new JComboBox();
        txtNacionalidade.setBounds(560, 310, 289, 36);
        txtNacionalidade.setBackground(SystemColor.text);
        txtNacionalidade.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtNacionalidade.setModel(new DefaultComboBoxModel(new String[] {"Brasileira","Afegã", "Albanesa", "Alemã", "Andorrana", "Angolana", "Antiguana", "Argentina", "Armênia", "Australiana", "Austríaca", "Azerbaijanesa", "Bahamense", "Barenita", "Bangladeshi", "Barbadiana", "Belga", "Belizenha", "Beninense", "Bielo-russa", "Boliviana", "Bósnia", "Botsuanesa", "Bruneiana", "Búlgara", "Burquinense", "Burundinesa", "Butanesa", "Cabo-verdiana", "Camaronesa", "Cambojana", "Canadense", "Catari", "Cazaque", "Chadiana", "Chilena", "Chinesa", "Cipriota", "Colombiana", "Comorense", "Congolesa", "Norte-coreana", "Sul-coreana", "Costarriquenha", "Croata", "Cubana", "Dinamarquesa", "Dominiquense", "Egípcia", "Salvadorenha", "Escocesa", "Eslovaca", "Eslovena", "Espanhola", "Estadunidense", "Estoniana", "Etíope", "Fijiana", "Filipina", "Finlandesa", "Francesa", "Gabonesa", "Gambiana", "Ganense", "Georgiana", "Gibraltina", "Grega", "Grenadina", "Guatemalteca", "Guianense", "Guineana", "Guineense", "Haitiana", "Holandesa", "Hondurenha", "Húngara", "Iemenita", "Indiana", "Indonésia", "Iraniana", "Iraquiana", "Irlandesa", "Islandesa", "Israelense", "Italiana", "Jamaicana", "Japonesa", "Jordaniana", "Kiribatiana", "Kuwaitiana", "Laosiana", "Lesotense", "Letã", "Libanesa", "Liberiana", "Líbia", "Liechtensteiniense", "Lituana", "Luxemburguesa", "Macedônia", "Malgaxe", "Malásia", "Malauiana", "Maldívia", "Malinesa", "Maltesa", "Marroquina", "Mauriciana", "Mauritana", "Mexicana", "Mianmarense", "Micronésia", "Moçambicana", "Moldávia", "Monegasca", "Mongol", "Montenegrina", "Namíbia", "Nauruana", "Nepalesa", "Nicaraguense", "Nigeriana", "Norueguesa", "Neozelandesa", "Omanense", "Palauense", "Panamenha", "Papua-nova-guineense", "Paquistanesa", "Paraguaia", "Peruana", "Polonesa", "Portuguesa", "Queniana", "Quirguiz", "Romena", "Ruandesa", "Russa", "Salomônica", "Samoana", "Santa-lucense", "São-cristovense", "São-marinhense", "São-tomense", "Saudita", "Senegalesa", "Sérvia", "Seichelense", "Serra-leonesa", "Singapuriana", "Síria", "Somali", "Srilanquesa", "Suazi", "Sudanesa", "Sueca", "Suíça", "Surinamesa", "Tailandesa", "Taiwanesa", "Tajique", "Tanzaniana", "Tcheca", "Timorense", "Togolesa", "Tonganesa", "Trinitária", "Tunisiana", "Turcomena", "Turca", "Tuvaluana", "Ucraniana", "Ugandense", "Uruguaia", "Uzbeque", "Vanuatuense", "Vaticano", "Venezuelana", "Vietnamita", "Zambiana", "Zimbabuense"}));

        int opcaoNac;
        opcaoNac = ArrayUtils.indexOf(nacionalidades, hos.getNacionalidade());
        txtNacionalidade.setSelectedIndex(opcaoNac);
        panel.add(txtNacionalidade);

        JLabel lblNome = new JLabel("NOME");
        lblNome.setBounds(562, 50, 253, 14);
        lblNome.setForeground(SystemColor.textInactiveText);
        lblNome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblNome);

        JLabel lblSobrenome = new JLabel("SOBRENOME");
        lblSobrenome.setBounds(560, 125, 255, 14);
        lblSobrenome.setForeground(SystemColor.textInactiveText);
        lblSobrenome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblSobrenome);

        JLabel lblDataN = new JLabel("DATA DE NASCIMENTO");
        lblDataN.setBounds(560, 206, 255, 14);
        lblDataN.setForeground(SystemColor.textInactiveText);
        lblDataN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblDataN);

        JLabel lblNacionalidade = new JLabel("NACIONALIDADE");
        lblNacionalidade.setBounds(560, 286, 255, 14);
        lblNacionalidade.setForeground(SystemColor.textInactiveText);
        lblNacionalidade.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblNacionalidade);

        JLabel lblTelefone = new JLabel("TELEFONE");
        lblTelefone.setBounds(562, 366, 253, 14);
        lblTelefone.setForeground(SystemColor.textInactiveText);
        lblTelefone.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtTelefone.setBounds(560, 384, 285, 33);
        txtTelefone.setColumns(10);
        txtTelefone.setBackground(Color.WHITE);
        txtTelefone.setBorder(BorderFactory.createEmptyBorder());

        txtTelefone.setText(hos.getTelefone());

        panel.add(txtTelefone);

        JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA: " + reser.getID_RES());
        lblNumeroReserva.setBounds(560, 454, 253, 14);
        lblNumeroReserva.setForeground(SystemColor.textInactiveText);
        lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
        panel.add(lblNumeroReserva);

        JSeparator separator_1_2_x = new JSeparator();
        separator_1_2.setBounds(560, 98, 289, 2);
        separator_1_2.setForeground(new Color(82, 89, 81));
        separator_1_2.setBackground(new Color(82, 89, 81));
        panel.add(separator_1_2_x);

        JSeparator separator_1_2_1 = new JSeparator();
        separator_1_2_1.setBounds(560, 173, 289, 2);
        separator_1_2_1.setForeground(new Color(82, 89, 81));
        separator_1_2_1.setBackground(new Color(82, 89, 81));
        panel.add(separator_1_2_1);

        JSeparator separator_1_2_2 = new JSeparator();
        separator_1_2_2.setBounds(560, 264, 289, 2);
        separator_1_2_2.setForeground(new Color(82, 89, 81));
        separator_1_2_2.setBackground(new Color(82, 89, 81));
        panel.add(separator_1_2_2);

        JSeparator separator_1_2_3 = new JSeparator();
        separator_1_2_3.setBounds(560, 316, 289, 2);
        separator_1_2_3.setForeground(new Color(82, 89, 81));
        separator_1_2_3.setBackground(new Color(82, 89, 81));
        panel.add(separator_1_2_3);

        JSeparator separator_1_2_4 = new JSeparator();
        separator_1_2_4.setBounds(560, 417, 289, 2);
        separator_1_2_4.setForeground(new Color(82, 89, 81));
        separator_1_2_4.setBackground(new Color(82, 89, 81));
        panel.add(separator_1_2_4);

        JSeparator separator_1_2_5 = new JSeparator();
        separator_1_2_5.setBounds(560, 489, 289, 2);
        separator_1_2_5.setForeground(new Color(82, 89, 81));
        separator_1_2_5.setBackground(new Color(82, 89, 81));
        panel.add(separator_1_2_5);

        JPanel btnexit = new JPanel();
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
                btnexit.setBackground(new Color(82, 89, 81));
                labelExit.setForeground(Color.white);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(new Color(82, 89, 81));
        btnexit.setBounds(860, 0, 53, 36);
        panel.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setForeground(Color.WHITE);
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

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
        header.setBackground(Color.WHITE);
        panel.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Buscar buscar = new Buscar();
                buscar.setVisible(true);
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(82, 89, 81));
                labelAtras.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(new Color(82, 89, 81));
        separator_1.setBounds(68, 362, 289, 2);
        separator_1.setBackground(new Color(82, 89, 81));
        panel.add(separator_1);

        JPanel btnProximo = new JPanel();
        btnProximo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnProximo.setBackground(new Color(245, 134, 52));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnProximo.setBackground(new Color(82, 89, 81));
            }
            @Override
            public void mouseClicked(MouseEvent e) {

                if (Editar.txtDataE.getDate() != null && Editar.txtDataS.getDate() != null && txtNome.getText() != null && txtSobrenome.getText() != null && txtDataN.getDate() != null && txtTelefone.getText() != null ) {

                    dataEntrada = txtDataE.getDate();
                    dataSaida = txtDataS.getDate();

                    boolean prossegue = true;

                    if(verificaDataMenor(dataSaida, dataEntrada)){
                        System.out.println("Ok data de saida maior que a data de entrada");
                    }else{
                        JOptionPane.showMessageDialog(panel, "INSIRA UMA DATA DE SAÍDA VÁLIDA (DATA QUE SEJA MAIOR QUE A DATA DE ENTRADA)", "DATA DE SAÍDA INVÁLIDA",
                                JOptionPane.WARNING_MESSAGE);
                        txtDataS.setDate(null);
                        prossegue = false;
                    }

                    if(prossegue){
                        int res = diferencaDias(dataEntrada, dataSaida);
                        System.out.println("Diferenca de dias = " + res);
                        txtValor.setText(String.valueOf(res * 199));
                    }


                    try{
                        Reservas novaReserva = new Reservas(reser.getID_RES(), dm.DateToString(txtDataE.getDate()), dm.DateToString(txtDataS.getDate()), Double.parseDouble(txtValor.getText()), txtFormaPagamento.getSelectedItem().toString());

                        rDAO.updateReg(novaReserva);

                        Hospede novoHosp = new Hospede(hos.getIdHos(),txtNome.getText(),txtSobrenome.getText(),dm.DateToString(txtDataN.getDate()),txtNacionalidade.getSelectedItem().toString(),txtTelefone.getText(),hos.getId_res());

                        hDAO.updateReg(novoHosp);

                        JOptionPane.showMessageDialog(null, "Registros editados com Sucesso.");

                        Buscar buscar = new Buscar();
                        buscar.setVisible(true);
                        dispose();
                    }catch (SQLException ex){
                        throw new RuntimeException(ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Deve preencher todos os campos.");
                }
            }
        });
        btnProximo.setLayout(null);
        btnProximo.setBackground(new Color(82, 89, 81));
        btnProximo.setBounds(168, 493, 122, 35);
        panel.add(btnProximo);
        btnProximo.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblSeguinte = new JLabel("SALVAR");
        lblSeguinte.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeguinte.setForeground(Color.WHITE);
        lblSeguinte.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblSeguinte.setBounds(0, 0, 122, 35);
        btnProximo.add(lblSeguinte);

        JPanel btnCancelar = new JPanel();
        btnCancelar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnCancelar.setBackground(new Color(245, 134, 52));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnCancelar.setBackground(new Color(82, 89, 81));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Buscar buscar = new Buscar();
                buscar.setVisible(true);
                dispose();
            }
        });
        btnCancelar.setLayout(null);
        btnCancelar.setBackground(new Color(82, 89, 81));
        btnCancelar.setBounds(338, 493, 122, 35);
        panel.add(btnCancelar);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblCancelar = new JLabel("CANCELAR");
        lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
        lblCancelar.setForeground(Color.WHITE);
        lblCancelar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblCancelar.setBounds(0, 0, 122, 35);
        btnCancelar.add(lblCancelar);
    }

    /**
     * Método responsável por capturar a posição inicial do mouse quando pressionado.
     * Esta posição é utilizada posteriormente para mover a janela.
     *
     * @param "evt" Evento gerado ao pressionar o mouse.
     */
    private void headerMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    /**
     * Método responsável por mover a janela ao arrastar o mouse.
     * Utiliza a posição inicial capturada pelo método headerMousePressed para calcular a nova posição.
     *
     * @param "evt" Evento gerado ao arrastar o mouse.
     */
    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    /**
     * Método que verifica se uma data é anterior a outra.
     *
     * @param "Date d1" Primeira data a ser comparada.
     * @param "Date d2" Segunda data a ser comparada.
     * @return Retorna verdadeiro se a primeira data (d1) é anterior à segunda (d2).
     */
    private boolean verificaDataMenor(Date d1, Date d2){

        if(d1.getYear() < d2.getYear()){
            return false;
        }else{
            if(d1.getMonth() < d2.getMonth()){
                return false;
            }else if(d1.getMonth() == d2.getMonth()){
                if(d1.getDate() <= d2.getDate()){
                    return false;
                }
            }
        }

        return true;
    }

    private int diferencaDias(Date d1, Date d2){
        Date novaD1 = new Date(d1.getYear(), d1.getMonth(), d1.getDate());
        Date novaD2 = new Date(d2.getYear(), d2.getMonth(), d2.getDate());
        int contador = 0;

        while (!novaD1.equals(novaD2)){
            contador++;
            novaD1.setDate(novaD1.getDate()+1);
        }

        return contador;
    }
}