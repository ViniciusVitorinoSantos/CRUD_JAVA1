package br.com.evilcorp;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        PessoaDAO pessoaDAO = new PessoaDAO();

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Cadastrar Pessoa");
            System.out.println("2 - Listar Pessoas");
            System.out.println("3 - Atualizar Pessoa");
            System.out.println("4 - Excluir Pessoa");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1: // Cadastrar
                    System.out.print("Digite seu nome: ");
                    String nome = scanner.nextLine();

                    LocalDate dataNascimento = null;
                    while (dataNascimento == null) {
                        try {
                            System.out.print("Digite sua data de nascimento (dd/MM/yyyy): ");
                            String dataStr = scanner.nextLine();
                            dataNascimento = LocalDate.parse(dataStr, formatter);
                        } catch (Exception e) {
                            System.out.println("Formato inválido! Tente novamente.");
                        }
                    }

                    System.out.print("Digite seu e-mail: ");
                    String email = scanner.nextLine();

                    Pessoa novaPessoa = new Pessoa(nome, dataNascimento, email);
                    pessoaDAO.inserirPessoa(novaPessoa);
                    System.out.println("Pessoa cadastrada com sucesso!");
                    break;

                case 2: // Listar
                    ArrayList<Pessoa> lista = pessoaDAO.listarPessoas();
                    System.out.println("\n=== Lista de Pessoas ===");
                    for (Pessoa p : lista) {
                        p.exibirDados();
                    }
                    break;

                case 3: // Atualizar
                    System.out.print("Digite o ID da pessoa que deseja atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();

                    LocalDate novaData = null;
                    while (novaData == null) {
                        try {
                            System.out.print("Nova data de nascimento (dd/MM/yyyy): ");
                            String novaDataStr = scanner.nextLine();
                            novaData = LocalDate.parse(novaDataStr, formatter);
                        } catch (Exception e) {
                            System.out.println("Formato inválido! Tente novamente.");
                        }
                    }

                    System.out.print("Novo e-mail: ");
                    String novoEmail = scanner.nextLine();

                    Pessoa pessoaAtualizada = new Pessoa(novoNome, novaData, novoEmail);
                    pessoaDAO.atualizarPessoa(idAtualizar, pessoaAtualizada);
                    System.out.println("Pessoa atualizada!");
                    break;

                case 4: // Excluir
                    System.out.print("Digite o ID da pessoa que deseja excluir: ");
                    int idExcluir = scanner.nextInt();
                    pessoaDAO.excluirPessoa(idExcluir);
                    System.out.println("Pessoa removida!");
                    break;

                case 5: // Sair
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
