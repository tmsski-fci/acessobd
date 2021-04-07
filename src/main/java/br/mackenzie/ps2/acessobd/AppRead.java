package br.mackenzie.ps2.acessobd;
import java.sql.*;
import static javax.swing.JOptionPane.*;

public class AppRead {
    static PreparedStatement stm = null;
    static Connection conexao = null;
    static void setup() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_bancario"; 
        conexao = DriverManager.getConnection(url, "root", "");
        stm = conexao.prepareStatement("SELECT * FROM contas WHERE nro_conta=?");
    }
    static void close() throws SQLException {
        conexao.close();
    }
    public static void main( String[] args ) throws SQLException {
        setup();
        String s = showInputDialog("Entre o número da conta: ");
        Long nro = Long.parseLong(s);
        stm.setLong(1, nro);
        ResultSet rs = stm.executeQuery(); 
        if (rs.next()) {
            showMessageDialog(null, "Seu saldo é de R$ " + rs.getBigDecimal("saldo"));
        }
        else {
            showMessageDialog(null, "Não encontrei a conta de número " + nro);
        }
        close();
    }
}

