package br.edu.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProdutoTest {
    @Test
    void produtoDeveIniciarComEstoqueZero() {
        Produto produto = new Produto("Teclado");

        assertEquals(0, produto.getQuantidade());
    }

}