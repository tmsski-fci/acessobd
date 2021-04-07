package br.mackenzie.ps2.acessobd;
import static javax.swing.JOptionPane.*;
import java.math.BigDecimal;
import java.sql.*;

public class AppUpdate {
    static PreparedStatement stm = null;
    static Connection conexao = null;
    static void setup() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_bancario"; 
        conexao = DriverManager.getConnection(url, "root", "");
        stm = conexao.prepareStatement("UPDATE contas SET saldo=? WHERE nro_conta=?");
    }
    static void close() throws SQLException {
        conexao.close();
    }
    public static void main( String[] args ) throws SQLException {
        setup();
        String s = showInputDialog("Número da conta a ser alterada: ");
        Long nro = Long.parseLong(s);
        stm.setLong(2, nro);
        s = showInputDialog("Novo saldo para a conta: ");
        BigDecimal saldo = new BigDecimal(s);
        stm.setBigDecimal(1, saldo);
        int r = stm.executeUpdate();
        showMessageDialog(null, "Número de registros alterados: " + r);
        close();
    }
}

