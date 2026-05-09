package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);

        final String queueName = "solicitacao-transacao";

        System.out.println("Informe o nome do cliente (Conta Corrente): ");
        String nomeC = tec.nextLine();

        System.out.println("Informe o saldo disponível: ");
        double saldoD = tec.nextDouble();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection;

        ContaCorrente contaCorrente = new ContaCorrente(nomeC, saldoD);

        try {
            String msg = "";

            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            System.out.println("\nInforme a operação: \n(1) - Saque\n(2) - Depósito");
            int opr = tec.nextInt();

            if(opr == 1){
                System.out.println("Informe o valor do saque: ");
                double saque = tec.nextDouble();

                contaCorrente.saque(saque);

                msg = """ 
                        --- Conta Corrente (Saque) --- 
                        \s
                        Cliente: %s
                        Saldo: R$ %.2f 
                        \s
                    """.formatted(contaCorrente.getNomeCliente(), contaCorrente.getSaldo());

            } else if (opr == 2){
                System.out.println("Informe o valor do depósito: ");
                double deposito = tec.nextDouble();

                contaCorrente.deposito(deposito);

                msg = """ 
                        --- Conta Corrente (Depósito) --- 
                        \s
                        Cliente: %s
                        Saldo: R$ %.2f 
                        \s
                    """.formatted(contaCorrente.getNomeCliente(), contaCorrente.getSaldo());
            } else {
                System.out.println("Opção inválida!");
            }

            channel.queueDeclare(queueName, true, false, false, null);
            channel.basicPublish("", queueName, null, msg.getBytes("UTF-8"));

            channel.close();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}