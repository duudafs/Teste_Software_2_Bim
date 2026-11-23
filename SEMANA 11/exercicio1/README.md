# Lista prática — Testes de integração com Spring Boot e JUnit 5

**Tecnologias:** Java 17, Maven, Spring Boot 3.x, Spring Data JPA, H2, JUnit 5 e MockMvc.

**Orientação:** usar o projeto-base fornecido e completar os métodos TODO das classes de teste, sem mocks para `ProdutoRepository` ou `ProdutoService`.

## Execução

```
mvn test
```

---

## Exercício 1 — Integração entre Service, Repository e banco H2

**Objetivo:** verificar que as regras de cadastro e atualização se integram à persistência real.

**Classe de teste:** `ProdutoServiceIntegrationTest`

| Caso | Método | Entrada | Verificação | Resultado |
|---|---|---|---|---|
| CT01 — Cadastro válido | `deveCadastrarERecuperarProduto` | Teclado, preço 150.0 | Produto recebe ID e é encontrado no repositório com nome e preço corretos | ✅ |
| CT02 — Nome inválido | `naoDevePersistirProdutoComNomeEmBranco` | Nome em branco, preço 80.0 | Lança `IllegalArgumentException` e a contagem de registros não aumenta | ✅ |
| CT03 — Atualização (desafio) | `deveAtualizarPrecoPersistido` | Mouse por 80.0, atualizado para 95.0 | A consulta ao repositório retorna preço 95.0 | ✅ |

**Camadas exercitadas:** `ProdutoService` → `ProdutoRepository` → banco H2.

### Discussão

**Por que a consulta com `repository.findById()` fornece uma evidência diferente de verificar apenas o objeto retornado pelo serviço?**
O objeto retornado pelo serviço prova apenas que o método devolveu algo. Ele poderia ter sido montado em memória sem nunca ser gravado. A consulta pelo repositório comprova que o registro existe na persistência, com os valores corretos. No CT03, o teste também chama `entityManager.flush()` e `entityManager.clear()` antes da busca. Assim, a alteração é gravada no banco e o cache do JPA é limpo, e o `findById` busca o dado no H2 em vez de devolver o objeto que já estava em memória.

**O que `@Transactional` faz ao término de cada teste?**
O Spring abre uma transação no início de cada método de teste e a desfaz (rollback) ao final. Os registros criados não permanecem no banco, então cada teste começa com o mesmo estado e não interfere nos demais, independentemente da ordem de execução.

---

## Exercício 2 — Integração HTTP com MockMvc

**Objetivo:** testar o fluxo Controller → Service → Repository → H2 por meio de requisições HTTP simuladas.

**Classe de teste:** `ProdutoControllerIntegrationTest`

| Caso | Método | Requisição | Verificação | Resultado |
|---|---|---|---|---|
| CT04 — Cadastro via API | `deveCadastrarProdutoPelaApi` | `POST /produtos` com `{"nome":"Mouse","preco":80.0}` | HTTP 200; JSON com ID numérico, nome Mouse e preço 80.0 | ✅ |
| CT05 — Consulta via API | `deveBuscarProdutoPelaApi` | `POST /produtos`, extração do ID com `ObjectMapper`/`JsonNode`, `GET /produtos/{id}` | HTTP 200; JSON da consulta corresponde ao produto cadastrado | ✅ |

**Camadas exercitadas:** MockMvc → `ProdutoController` → `ProdutoService` → `ProdutoRepository` → banco H2.

### Discussão

**Quais componentes são efetivamente exercitados?**
A cadeia completa da aplicação: o roteamento do Spring MVC (`DispatcherServlet`), a conversão do JSON da requisição para `ProdutoRequest` (Jackson), o `ProdutoController`, o `ProdutoService` com suas validações, o `ProdutoRepository` (Spring Data JPA), o Hibernate e o banco H2. Depois, o caminho de volta, com a conversão de `Produto` para JSON na resposta.

**Por que o MockMvc não precisa iniciar um servidor HTTP real?**
O MockMvc chama o `DispatcherServlet` diretamente, no mesmo processo do teste, com requisições e respostas simuladas em memória. Não abre porta nem passa pela rede. O teste fica mais rápido, mas todo o processamento web do Spring continua sendo executado.

**Qual é a diferença entre esse teste e um teste unitário do controller com dependências simuladas?**
No teste unitário, o `ProdutoService` seria substituído por um mock, e só o controller seria verificado isoladamente. Esse tipo de teste não detecta erros de persistência, de mapeamento JPA ou de integração entre as camadas. O teste de integração usa os componentes reais e encontra falhas que só aparecem quando as camadas trabalham juntas. Em troca, é mais lento, e quando falha é mais difícil identificar a camada exata do problema.

---

## Isolamento dos testes

- **Sem mocks:** `ProdutoService` e `ProdutoRepository` são os beans reais do Spring, injetados com `@Autowired`.
- **Banco em memória:** os dados são gravados no H2, que é criado ao iniciar os testes e descartado ao final.
- **Rollback por teste:** com `@Transactional`, cada método de teste tem sua transação desfeita ao final, então nenhum teste depende dos dados de outro.

## Resultado da execução

```
mvn test

[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```