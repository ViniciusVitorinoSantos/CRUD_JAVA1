package br.com.evilcorp;

import java.time.LocalDate;

public class Pessoa {
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;

    // Construtor sem id (para inserção, por exemplo)
    public Pessoa(String nome, LocalDate dataNascimento, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    // Construtor com id (para quando precisar recuperar dados do banco)
    public Pessoa(int id, String nome, LocalDate dataNascimento, String email) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void exibirDados() {
    }
}
