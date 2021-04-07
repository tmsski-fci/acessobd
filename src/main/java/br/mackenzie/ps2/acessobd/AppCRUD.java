package br.mackenzie.ps2.acessobd;
import static javax.swing.JOptionPane.*;
import java.math.BigDecimal;
import java.sql.*;

public class AppCRUD {
    static PreparedStatement stmCreate, stmRead, stmUpdate, stmDelete;
    static Connection conexao = null;
    static void setup() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_bancario"; 
        conexao = DriverManager.getConnection(url, "root", "");
        stmCreate = conexao.prepareStatement("INSERT INTO contas VALUES (?,?)");
        stmRead = conexao.prepareStatement("SELECT * FROM contas");
        stmUpdate = conexao.prepareStatement("UPDATE contas SET saldo=? WHERE nro_conta=?");
        stmDelete = conexao.prepareStatement("DELETE FROM contas WHERE nro_conta=?");
    }
    static void close() throws SQLException {
        conexao.close();
    }
    static void create() throws SQLException {
        String s = showInputDialog("Número para a nova conta: ");
        Long nro = Long.parseLong(s);
        stmCreate.setLong(1, nro);
        s = showInputDialog("Saldo da nova conta: ");
        BigDecimal saldo = new BigDecimal(s);
        stmCreate.setBigDecimal(2, saldo);
        int r = stmCreate.executeUpdate();
        showMessageDialog(null, "Número de registros criados: " + r);
    }
    static void readAll() throws SQLException {   
        ResultSet rs = stmRead.executeQuery(); 
        String msg = "";
        while(rs.next()) {
            msg += "Conta " + rs.getLong("nro_conta") + " - R$ " + rs.getBigDecimal("saldo") + "\n";
        } 
        showMessageDialog(null, msg);
    }
    static void update() throws SQLException {
        String s = showInputDialog("Número da conta a ser alterada: ");
        Long nro = Long.parseLong(s);
        stmUpdate.setLong(2, nro);
        s = showInputDialog("Novo saldo paraa conta: ");
        BigDecimal saldo = new BigDecimal(s);
        stmUpdate.setBigDecimal(1, saldo);
        int r = stmUpdate.executeUpdate();
        showMessageDialog(null, "Número de registros alterados: " + r);        
    }
    static void delete() throws SQLException { 
        String s = showInputDialog("Número da conta a ser apagada: ");
        Long nro = Long.parseLong(s);
        stmDelete.setLong(1, nro);
        int r = stmDelete.executeUpdate();
        showMessageDialog(null, "Número de registros apagados: " + r);
    }
    public static void main( String[] args ) throws SQLException {
        setup();
        boolean sair = false;
        while (!sair) {
            String op = showInputDialog("(1) Create\n(2) Read All\n(3) Update\n(4) Delete\n(5) Exit");
            switch(op) {
                case "1": create(); break;
                case "2": readAll(); break;
                case "3": update(); break;
                case "4": delete(); break;
                case "5": sair = true; break;
                default: showMessageDialog(null, "Opção inválida!");
            }
        }
        close();
    }
}

