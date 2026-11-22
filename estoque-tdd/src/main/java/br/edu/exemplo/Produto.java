package br.edu.exemplo;

public class Produto {
    private String nome;
    private int quantidade;

    public Produto(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
}