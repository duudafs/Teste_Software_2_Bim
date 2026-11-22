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
}