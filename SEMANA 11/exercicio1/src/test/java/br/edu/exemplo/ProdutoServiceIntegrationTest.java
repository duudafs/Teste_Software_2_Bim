package br.edu.exemplo;

import br.edu.exemplo.model.Produto;
import br.edu.exemplo.repository.ProdutoRepository;
import br.edu.exemplo.service.ProdutoService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProdutoServiceIntegrationTest {

    @Autowired ProdutoService service;
    @Autowired ProdutoRepository repository;
    @Autowired EntityManager entityManager;

    // CT01 — Cadastro válido
    @Test
    void deveCadastrarERecuperarProduto() {
        Produto salvo = service.cadastrar("Teclado", 150.0);

        assertNotNull(salvo.getId());

        Produto encontrado = repository.findById(salvo.getId()).orElseThrow();
        assertEquals("Teclado", encontrado.getNome());
        assertEquals(150.0, encontrado.getPreco(), 0.001);
    }

    // CT02 — Nome inválido

    @Test
    void naoDevePersistirProdutoComNomeEmBranco() {
        long quantidadeAntes = repository.count();

        assertThrows(IllegalArgumentException.class,
                () -> service.cadastrar("   ", 80.0));

        assertEquals(quantidadeAntes, repository.count());
    }
    // CT03 — Atualização (desafio)
    @Test
    void deveAtualizarPrecoPersistido() {
        // TODO DESAFIO: cadastrar, atualizar preço e buscar novamente no repositório.
    }
}