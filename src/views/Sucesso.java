package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

/**
 * A janela de diálogo que indica o sucesso de um registro.
 * Esta janela mostra uma imagem e uma mensagem indicando que um registro foi adicionado com sucesso.
 */
@SuppressWarnings("serial")
public class Sucesso extends JDialog {

    private final JPanel contentPanel = new JPanel();

    /**
     * Método principal para testar a janela de diálogo.
     *
     * @param "args" argumentos da linha de comando.
     */
    public static void main(String[] args) {
        try {
            Sucesso dialog = new Sucesso();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cria a janela de diálogo.
     * <p>Configura o layout, ícones e outros componentes da janela.</p>
     */
    public Sucesso() {
        setIconImage(new ImageIcon("src/new_img/aH-40px.png").getImage());
        setBounds(100, 100, 394, 226);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(SystemColor.control);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        contentPanel.setLayout(null);
        {
            JLabel lblNewLabel = new JLabel("");
            lblNewLabel.setIcon(new ImageIcon("src/new_img/Ha-100px.png"));
            lblNewLabel.setBounds(123, 11, 100, 100);
            contentPanel.add(lblNewLabel);
        }
        {
            JLabel lblNewLabel_1 = new JLabel("REGISTRO INSERIDO COM SUCESSO");
            lblNewLabel_1.setForeground(new Color (82, 88, 80));
            lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
            lblNewLabel_1.setBounds(27, 122, 350, 21);
            contentPanel.add(lblNewLabel_1);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    /**
                     * Fecha a janela atual e abre o menu do usuário ao clicar no botão OK.
                     *
                     * @param "e" O evento de ação.
                     */
                    public void actionPerformed(ActionEvent e) {
                        dispose();//serve para fechar a janela atual

                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}
