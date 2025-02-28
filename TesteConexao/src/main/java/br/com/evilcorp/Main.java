
package br.com.evilcorp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class TesteConexao {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/cadastro_pessoas";
        String usuario = "root"; // ou o usuário que você utiliza
        String senha = "";       // senha vazia, se for o caso

        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");
            conexao.close();
        } catch (SQLException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
        }
    }
}
