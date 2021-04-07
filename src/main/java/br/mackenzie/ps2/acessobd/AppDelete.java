package br.mackenzie.ps2.acessobd;
import static javax.swing.JOptionPane.*;
import java.sql.*;

public class AppDelete {
    static PreparedStatement stm = null;
    static Connection conexao = null;
    static void setup() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_bancario"; 
        conexao = DriverManager.getConnection(url, "root", "");
        stm = conexao.prepareStatement("DELETE FROM contas WHERE nro_conta=?");
    }
    static void close() throws SQLException {
        conexao.close();
    }
    public static void main( String[] args ) throws SQLException {
        setup();
        String s = showInputDialog("Número da conta a ser apagada: ");
        Long nro = Long.parseLong(s);
        stm.setLong(1, nro);
        int r = stm.executeUpdate();
        showMessageDialog(null, "Número de registros apagados: " + r);
        close();
    }
}

