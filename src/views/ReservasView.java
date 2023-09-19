package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import db.Reservas;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Date;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import static java.lang.String.valueOf;

/**
 * Representa a tela de reservas, onde os usuários podem escolher datas de check-in e check-out,
 * ver o valor total e escolher uma forma de pagamento.
 */
@SuppressWarnings("serial")
public class ReservasView extends JFrame {

    // Declaração de variáveis de instância
    private JPanel contentPane;
    public static JTextField txtValor;
    public static JDateChooser txtDataE;
    public static JDateChooser txtDataS;
    public static JComboBox<String> txtFormaPagamento;
    int xMouse, yMouse;
    private JLabel labelExit;
    private JLabel lblValorSimbolo;
    private JLabel labelAtras;

    private Date hoje = new Date();

    private Date dataEntrada;
    private Date dataSaida;

    /**
     * Método main para inicializar a tela.
     * @param "args" Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReservasView frame = new ReservasView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Construtor padrão para ReservasView.
     */
    public ReservasView() {
        super("Reserva");
        setIconImage(new ImageIcon("src/new_img/aH-40px.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 560);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.control);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 910, 560);
        contentPane.add(panel);
        panel.setLayout(null);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(82, 88, 80));
        separator_1_2.setBounds(68, 195, 289, 2);
        separator_1_2.setBackground(new Color(82, 88, 80));
        panel.add(separator_1_2);

        JSeparator separator_1_3 = new JSeparator();
        separator_1_3.setForeground(new Color(82, 88, 80));
        separator_1_3.setBackground(new Color(82, 88, 80));
        separator_1_3.setBounds(68, 453, 289, 2);
        panel.add(separator_1_3);

        JSeparator separator_1_1 = new JSeparator();
        separator_1_1.setForeground(new Color(82, 88, 80));
        separator_1_1.setBounds(68, 281, 289, 11);
        separator_1_1.setBackground(new Color(82, 88, 80));
        panel.add(separator_1_1);

        txtDataE = new JDateChooser();
        txtDataE.getCalendarButton().setBackground(new Color(82, 89, 81));
        txtDataE.getCalendarButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                txtDataE.getCalendarButton().setBackground(new Color(245, 134, 52));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                txtDataE.getCalendarButton().setBackground(new Color(82, 89, 81));
            }
        });
        txtDataE.getCalendarButton().setBackground(new Color(82, 89, 81));
        txtDataE.getCalendarButton().setIcon(new ImageIcon("src/new_img/icon-reservas.png"));
        txtDataE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
        txtDataE.setBounds(68, 161, 289, 35);
        txtDataE.getCalendarButton().setBounds(268, 0, 21, 33);
        txtDataE.setBackground(Color.WHITE);
        txtDataE.setBorder(new LineBorder(new Color(82, 89, 81)));
        txtDataE.setDateFormatString("yyyy-MM-dd");
        txtDataE.setFont(new Font("Roboto", Font.PLAIN, 18));
        panel.add(txtDataE);

        lblValorSimbolo = new JLabel("R$");
        lblValorSimbolo.setVisible(false);
        lblValorSimbolo.setBounds(121, 332, 17, 25);
        lblValorSimbolo.setForeground((new Color(82, 89, 81)));
        lblValorSimbolo.setFont(new Font("Roboto", Font.BOLD, 17));

        panel.add(lblValorSimbolo);

        JLabel lblCheckIn = new JLabel("DATA DE CHECK IN");
        lblCheckIn.setForeground((new Color(82, 89, 81)));
        lblCheckIn.setBounds(68, 136, 250, 14);
        lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 17));
        panel.add(lblCheckIn);

        JLabel lblCheckOut = new JLabel("DATA DE CHECK OUT");
        lblCheckOut.setForeground((new Color(82, 89, 81)));
        lblCheckOut.setBounds(68, 221, 250, 14);
        lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 17));
        panel.add(lblCheckOut);

        txtDataS = new JDateChooser();
        txtDataS.getCalendarButton().setBackground(new Color(82, 89, 81));
        txtDataS.getCalendarButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                txtDataS.getCalendarButton().setBackground(new Color(245, 134, 52));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                txtDataS.getCalendarButton().setBackground(new Color(82, 89, 81));
            }
        });
        txtDataS.getCalendarButton().setIcon(new ImageIcon("src/new_img/icon-reservas.png"));
        txtDataS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
        txtDataS.setBounds(68, 246, 289, 35);
        txtDataS.getCalendarButton().setBounds(267, 1, 21, 31);
        txtDataS.setBackground(Color.WHITE);
        txtDataS.setFont(new Font("Roboto", Font.PLAIN, 18));
        txtDataS.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {


                if(txtDataE.getDate() != null && txtDataS.getDate() != null) {

                    dataEntrada = txtDataE.getDate();
                    dataSaida = txtDataS.getDate();

                    boolean prossegue = true;
                    if(verificaDataMenor(dataEntrada,hoje)){
                        System.out.println("Ok data de entrada maior que a data de hoje");
                    }else{
                        JOptionPane.showMessageDialog(panel, "INSIRA UMA DATA DE ENTRADA VÁLIDA (DATA QUE SEJA MAIOR QUE O DIA DE HOJE)", "DATA DE ENTRADA INVÁLIDA",
                                JOptionPane.WARNING_MESSAGE);
                        prossegue = false;
                        txtDataE.setDate(null);
                    }

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

                        txtValor.setText("R$" + (res * 199));
                    }

                }

            }
        });
        txtDataS.setDateFormatString("yyyy-MM-dd");
        txtDataS.getCalendarButton().setBackground(new Color(82, 89, 81));
        txtDataS.setBorder(new LineBorder(new Color(82, 89, 81), 0));
        panel.add(txtDataS);

        txtValor = new JTextField();
        txtValor.setBackground(SystemColor.text);
        txtValor.setHorizontalAlignment(SwingConstants.CENTER);
        txtValor.setForeground(Color.BLACK);
        txtValor.setBounds(78, 328, 100, 33);
        txtValor.setEditable(false);
        txtValor.setFont(new Font("Roboto Black", Font.BOLD, 20));
        txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        panel.add(txtValor);
        txtValor.setColumns(10);

        JLabel lblValor = new JLabel("VALOR TOTAL DA RESERVA");
        lblValor.setForeground(SystemColor.textInactiveText);
        lblValor.setBounds(72, 303, 300, 14);
        lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 17));
        panel.add(lblValor);

        txtFormaPagamento = new JComboBox();
        txtFormaPagamento.setBounds(68, 417, 289, 38);
        txtFormaPagamento.setBackground(SystemColor.text);
        txtFormaPagamento.setBorder(new LineBorder(new Color(82, 89, 81), 1, true));
        txtFormaPagamento.setFont(new Font("Roboto", Font.PLAIN, 17));
        txtFormaPagamento.setModel(new DefaultComboBoxModel(new String[] {"Cartão de Crédito", "Cartão de Débito", "Dinheiro"}));
        panel.add(txtFormaPagamento);

        JLabel lblFormaPago = new JLabel("FORMA DE PAGAMENTO");
        lblFormaPago.setForeground(SystemColor.textInactiveText);
        lblFormaPago.setBounds(68, 382, 300, 24);
        lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 17));
        panel.add(lblFormaPago);

        JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
        lblTitulo.setBounds(109, 60, 300, 42);
        lblTitulo.setForeground(new Color(82, 88, 80));
        lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
        panel.add(lblTitulo);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(428, 0, 482, 560);
        panel_1.setBackground(new Color(255, 213, 128));
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel logo = new JLabel("");
        logo.setBounds(190, 35, 128, 128);
        panel_1.add(logo);
        logo.setIcon(new ImageIcon("src/new_img/Ha-125px.png"));

        JLabel imagenFondo = new JLabel("");
        imagenFondo.setBounds(35, 140, 500, 409);
        panel_1.add(imagenFondo);
        imagenFondo.setBackground(Color.WHITE);
        imagenFondo.setIcon(new ImageIcon("src/new_img/reservas-img-3.png"));

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
                labelExit.setForeground(Color.black);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(new Color(255, 213, 128));
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(new Color(255, 213, 128));
        btnexit.setBounds(429, 0, 53, 36);
        panel_1.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setForeground(Color.black);
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
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(82, 88, 80));
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
        separator_1.setForeground(new Color(82, 88, 80));
        separator_1.setBounds(68, 362, 289, 2);
        separator_1.setBackground(new Color(82, 88, 80));
        panel.add(separator_1);

        JPanel btnProximo = new JPanel();
        btnProximo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (ReservasView.txtDataE.getDate() != null && ReservasView.txtDataS.getDate() != null) {

                    int diasReservados = diferencaDias(dataEntrada, dataSaida);
                    double valorDiaria = 199;
                    double valorTotal = diasReservados * valorDiaria;

                    JOptionPane.showMessageDialog(null, "Dias reservados: " + diasReservados + "\nValor da diária: R$" + valorDiaria + "\nValor total da reserva: R$" + valorTotal);

                    Reservas r = new Reservas(dataEntrada, dataSaida, txtValor.getText(), String.valueOf(txtFormaPagamento.getSelectedItem()));

                    RegistroHospede registro = new RegistroHospede(r);
                    registro.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Deve preencher todos os campos.");
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnProximo.setBackground(new Color(245, 134, 52));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnProximo.setBackground(new Color(82, 89, 81));
            }
        });
        btnProximo.setLayout(null);
        btnProximo.setBackground(new Color(82, 89, 81));
        btnProximo.setBounds(238, 493, 122, 35);
        panel.add(btnProximo);
        btnProximo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JLabel lblSeguinte = new JLabel("PRÓXIMO");
        lblSeguinte.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeguinte.setForeground(Color.WHITE);
        lblSeguinte.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblSeguinte.setBounds(0, 0, 122, 35);
        btnProximo.add(lblSeguinte);
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

    /**
     * Verifica se a primeira data fornecida é anterior à segunda.
     * @param "d1" Primeira data.
     * @param "d2" Segunda data.
     * @return Verdadeiro se data1 for anterior a data2, falso caso contrário.
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

    /**
     * Calcula a diferença em dias entre duas datas.
     * @param "d1" Data de entrada ou check-in.
     * @param "d2" Data de saída ou check-out.
     * @return Número de dias entre as duas datas.
     */
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