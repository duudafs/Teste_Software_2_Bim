package br.edu.exemplo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Os testes NÃO estão implementados.
 * ... (pode manter o comentário do professor)
 */
class CarrinhoTest {
    @Test
    void carrinhoNovoDeveTerTotalZero() {
        Carrinho carrinho = new Carrinho();
        assertEquals(0.0, carrinho.getTotal(), 0.001);
    }
    @Test
    void deveAdicionarUmProduto() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Teclado", 100.0));
        assertEquals(100.0, carrinho.getTotal(), 0.001);
    }
    @Test
    void deveSomarVariosProdutos() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Teclado", 100.0));
        carrinho.adicionar(new Produto("Mouse", 50.0));
        assertEquals(150.0, carrinho.getTotal(), 0.001);
    }
    @Test
    void deveRemoverProduto() {
        Carrinho carrinho = new Carrinho();
        Produto teclado = new Produto("Teclado", 100.0);
        Produto mouse = new Produto("Mouse", 50.0);
        carrinho.adicionar(teclado);
        carrinho.adicionar(mouse);

        carrinho.remover(mouse);

        assertEquals(100.0, carrinho.getTotal(), 0.001);
    }
    @Test
    void naoDevePermitirProdutoComPrecoNegativo() {
        Carrinho carrinho = new Carrinho();
        Produto invalido = new Produto("Teclado", -100.0);

        assertThrows(IllegalArgumentException.class,
                () -> carrinho.adicionar(invalido));
    }
    @Test
    void deveAplicarDescontoAcimaDe500() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Monitor", 600.0));
        assertEquals(540.0, carrinho.getTotal(), 0.001);
    }

    @Test
    void naoDeveAplicarDescontoEm499e99() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Produto", 499.99));
        assertEquals(499.99, carrinho.getTotal(), 0.001);
    }

    @Test
    void deveAplicarDescontoEmExatamente500() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Produto", 500.0));
        assertEquals(450.0, carrinho.getTotal(), 0.001);
    }

    @Test
    void deveAplicarDescontoEm500e01() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Produto", 500.01));
        assertEquals(450.009, carrinho.getTotal(), 0.001);
    }
}