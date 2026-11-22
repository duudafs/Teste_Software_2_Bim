package br.edu.exemplo;

public class ContaBancaria {
    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        if (temSaldoSuficiente(valor)) {
            saldo -= valor;
        }
    }

    private boolean temSaldoSuficiente(double valor) {
        return valor <= saldo;
    }
}