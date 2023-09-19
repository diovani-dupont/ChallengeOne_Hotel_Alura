package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class LoginDAO {

    private Connection con;

    public LoginDAO() throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        con = connectionFactory.Conexao();

    }

    public String[] selectUser(String user) throws SQLException {
        String[] resultado = new String[2];
        resultado[0] = user;
        String sql = "SELECT PASS FROM users WHERE USUARIO = ?";
        try (PreparedStatement pStm = con.prepareStatement(sql)) {
            pStm.setString(1, user);
            pStm.execute();
            try (ResultSet res = pStm.getResultSet()) {
                if (res.next()) {
                    resultado[1] = res.getString(1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }

}
