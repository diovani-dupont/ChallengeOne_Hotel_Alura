package db;

import db.ConnectionFactory;

import java.sql.*;

public class InsertReserva implements AutoCloseable {

    private Connection con;
    private String dte;
    private String dts;
    private double val;
    private String forma_pag;
    private int id_insert;
    private PreparedStatement pStm;

    public int getId_insert() {
        return id_insert;
    }

    private int numeroReserva;

    public InsertReserva(Reservas r)  {

        //int id = r.getID_RES();
        dte = r.getDtEntrada();
        dts = r.getDtSaida();
        val = r.getValor();
        forma_pag = r.getForma_pag();
        numeroReserva = r.getNumeroReserva();
    }

    public void inserir() throws SQLException{

        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.con = connectionFactory.Conexao();

        con.setAutoCommit(false);
        pStm = con.prepareStatement("INSERT INTO reservas (ID_RES, DATA_ENTRADA, DATA_SAIDA, VALOR, PAGAMENTO) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        pStm.setInt(1, numeroReserva);
        pStm.setString(2, dte);
        pStm.setString(3, dts);
        pStm.setDouble(4, val);
        pStm.setString(5,forma_pag);

        pStm.executeUpdate();

        try (ResultSet rs = pStm.getGeneratedKeys()) {
            if (rs.next()) {
                id_insert = rs.getInt(1);
            }
        }
    }

    public void comita() throws SQLException {
        con.commit();
        con.close();
    }

    public void rollB() throws SQLException {
        con.rollback();
    }


    @Override
    public void close() throws Exception {
        con.close();
    }
}
