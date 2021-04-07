package br.mackenzie.ps2.acessobd;
import java.sql.*;

public class App {
    public static void main( String[] args ) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_bancario"; 
        String user = "root";
        String pwd = "";
        System.out.println("Início da consulta!");
        Connection conexao = DriverManager.getConnection(url, user, pwd);
        System.out.println("Conectado!");
        String sql = "SELECT * FROM contas";
        PreparedStatement stm = conexao.prepareStatement(sql); 
        ResultSet rs = stm.executeQuery(); 
        while(rs.next()) {
            System.out.println("\nNúmero da conta: " + rs.getLong("nro_conta"));
            System.out.println("Saldo: " + rs.getBigDecimal("saldo"));
        }
        conexao.close();
        System.out.println("\nFim da consulta!");
    }
}

