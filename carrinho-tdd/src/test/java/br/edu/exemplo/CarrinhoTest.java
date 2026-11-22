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
}