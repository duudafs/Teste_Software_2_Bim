## Implementação com TDD

A classe `Produto` foi construída incrementalmente, um requisito por vez, seguindo o ciclo RED → GREEN → REFACTOR.

| # | Requisito | Teste | Fase | Implementação |
|---|---|---|---|---|
| 1 | Estoque inicial zero | `produtoDeveIniciarComEstoqueZero` | RED → GREEN | Criada a classe `Produto` com construtor e `getQuantidade()` |
| 2 | Adicionar unidades | `deveAdicionarProdutoAoEstoque` | RED → GREEN | `adicionarEstoque()` soma a quantidade |
| 3 | Acumular entradas | `deveAcumularQuantidadeEmEstoque` | GREEN direto | Já atendido pela soma do requisito 2 |
| 4 | Retirar unidades | `deveRetirarProdutoDoEstoque` | RED → GREEN | `retirarEstoque()` subtrai a quantidade |
| 5 | Não permitir estoque negativo | `naoDeveRetirarQuantidadeMaiorQueEstoque` | RED → GREEN | Retirada só ocorre se houver estoque suficiente |
| 6 | Não permitir entrada negativa | `naoDeveAdicionarQuantidadeNegativa` | RED → GREEN | Entrada só ocorre se a quantidade for maior que zero |
| 6 | Não permitir entrada zero | `naoDeveAdicionarQuantidadeZero` | GREEN direto | Teste extra: o requisito 6 também proíbe zero |
| — | Nome do produto | `deveArmazenarNomeDoProduto` | RED → GREEN | Teste extra: criado `getNome()` |
| — | Refatoração | — | REFACTOR | Regras extraídas para `isQuantidadeValida()` e `temEstoqueSuficiente()`; `nome` tornou-se `final` |

### Decisões

- Operações inválidas (retirada maior que o estoque, entrada negativa ou zero) são ignoradas e não alteram a quantidade, conforme os requisitos 5 e 6.
- Foram adicionados dois testes além dos fornecidos: entrada igual a zero (prevista no requisito 6, mas não testada) e armazenamento do nome (descrito no enunciado).

## Cobertura de código (JaCoCo)

| Métrica | Cobertura |
|---|---|
| Instruções | 100% |
| Branches | 100% |
| Linhas | 100% |
| Métodos | 100% |
| Classes | 100% |

O build exige 100% de cobertura de linhas e branches (`jacoco:check`). Se a cobertura cair, `mvn test` falha.

O relatório é gerado em `target/site/jacoco/index.html` após executar:

```
mvn test
```