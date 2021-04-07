package br.mackenzie.ps2.acessobd;
import static javax.swing.JOptionPane.*;
import java.math.BigDecimal;
import java.sql.*;

public class AppCreate {
    static PreparedStatement stm = null;
    static Connection conexao = null;
    static void setup() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_bancario"; 
        conexao = DriverManager.getConnection(url, "root", "");
        stm = conexao.prepareStatement("INSERT INTO contas VALUES (?,?)");
    }
    static void close() throws SQLException {
        conexao.close();
    }
    public static void main( String[] args ) throws SQLException {
        setup();
        String s = showInputDialog("Número para a nova conta: ");
        Long nro = Long.parseLong(s);
        stm.setLong(1, nro);
        s = showInputDialog("Saldo da nova conta: ");
        BigDecimal saldo = new BigDecimal(s);
        stm.setBigDecimal(2, saldo);
        int r = stm.executeUpdate();
        showMessageDialog(null, "Número de registros criados: " + r);
        close();
    }
}

