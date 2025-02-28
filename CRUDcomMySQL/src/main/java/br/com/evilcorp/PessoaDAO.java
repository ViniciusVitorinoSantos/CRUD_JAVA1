package br.com.evilcorp;

import java.sql.*;
import java.util.ArrayList;

public class PessoaDAO {
    private Connection conexao;

    public PessoaDAO() {
        try {
            // Cria a conexão com o banco de dados
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro_pessoas", "root", "");
            System.out.println("Conexão estabelecida!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE (Inserir Pessoa)
    public void inserirPessoa(Pessoa pessoa) {
        String sql = "INSERT INTO pessoas (nome, data_nascimento, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setDate(2, Date.valueOf(pessoa.getDataNascimento())); // data_nascimento no formato "yyyy-MM-dd"
            stmt.setString(3, pessoa.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (Listar Pessoas)
    public ArrayList<Pessoa> listarPessoas() {
        ArrayList<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoas";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                // Supondo que o método getDataNascimento da classe Pessoa seja do tipo LocalDate,
                // fazemos a conversão:
                Date dataNascimentoSql = rs.getDate("data_nascimento");
                java.time.LocalDate dataNascimento = dataNascimentoSql.toLocalDate();
                String email = rs.getString("email");

                // Supondo que exista um construtor em Pessoa que aceite esses parâmetros:
                Pessoa pessoa = new Pessoa(id, nome, dataNascimento, email);
                lista.add(pessoa);

                // Opcional: imprimir os dados para teste
                System.out.println("ID: " + id + ", Nome: " + nome + ", Data de Nascimento: " + dataNascimento + ", Email: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter dados: " + e.getMessage());
        }
        return lista;
    }

    // UPDATE (Atualizar Pessoa)
    public void atualizarPessoa(int id, Pessoa pessoa) {
        String sql = "UPDATE pessoas SET nome = ?, data_nascimento = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setDate(2, Date.valueOf(pessoa.getDataNascimento()));
            stmt.setString(3, pessoa.getEmail());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE (Excluir Pessoa)
    public void excluirPessoa(int id) {
        String sql = "DELETE FROM pessoas WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
