# Conta Bancária — TDD

Projeto Maven com Java 17 e JUnit 5, desenvolvido com TDD (Test-Driven Development).

## Como executar

```
mvn test
```

## Ciclos TDD realizados

| # | Teste | Fase | Implementação |
|---|---|---|---|
| 1 | `deveDepositarValorNaConta` | RED → GREEN | `saldo += valor` |
| 2 | `deveAcumularDepositos` | GREEN direto | Já atendido pelo `+=` |
| 3 | `deveSacarValorDaConta` | RED → GREEN | `saldo -= valor` |
| 4 | `naoDevePermitirSaqueMaiorQueSaldo` | RED → GREEN | Validação `valor <= saldo` |
| 5 | — | REFACTOR | Removido construtor redundante e extraído `temSaldoSuficiente()` |


## Cobertura de código (JaCoCo)

| Métrica | Cobertura |
|---|---|
| Instruções | 100% |
| Branches | 100% |
| Linhas | 100% |
| Métodos | 100% |
| Classes | 100% |



O relatório fica em `target/site/jacoco/index.html`.