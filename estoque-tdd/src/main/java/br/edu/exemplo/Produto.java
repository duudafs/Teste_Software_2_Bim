package br.edu.exemplo;

public class Produto {
    private final String nome;
    private int quantidade;

    public Produto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        if (isQuantidadeValida(quantidade)) {
            this.quantidade += quantidade;
        }
    }

    public void retirarEstoque(int quantidade) {
        if (temEstoqueSuficiente(quantidade)) {
            this.quantidade -= quantidade;
        }
    }

    private boolean isQuantidadeValida(int quantidade) {
        return quantidade > 0;
    }

    private boolean temEstoqueSuficiente(int quantidade) {
        return quantidade <= this.quantidade;
    }
}