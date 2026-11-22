package br.edu.exemplo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private static final double VALOR_MINIMO_DESCONTO = 500.0;
    private static final double PERCENTUAL_DESCONTO = 0.10;

    private List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        produtos.add(produto);
    }

    public void remover(Produto produto) {
        produtos.remove(produto);
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (Produto p : produtos) {
            subtotal += p.getPreco();
        }
        return subtotal;
    }

    public double getTotal() {
        double subtotal = getSubtotal();
        if (subtotal >= VALOR_MINIMO_DESCONTO) {
            return subtotal * (1 - PERCENTUAL_DESCONTO);
        }
        return subtotal;
    }
}