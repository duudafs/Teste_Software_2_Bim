package br.edu.exemplo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public double getTotal() {
        double total = 0;
        for (Produto p : produtos) {
            total += p.getPreco();
        }
        return total;
    }
}