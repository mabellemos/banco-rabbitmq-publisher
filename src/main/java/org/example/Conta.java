package org.example;

public abstract class Conta {
    private String nomeCliente;
    private double saldo;

    public Conta(String nomeCliente, double saldo) {
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    void saque(double valor){
        if(valor <= 0){
            throw new IllegalArgumentException("O valor fornecido deve ser maior que 0");
        }

        if(saldo < valor){
            throw new IllegalArgumentException("Saldo em conta insuficiente");
        }

        saldo -= valor;

    }

    void deposito(double valor){
        if(valor <= 0){
            throw new IllegalArgumentException("O valor fornecido deve ser maior que 0");
        }

        saldo += valor;

    }

}