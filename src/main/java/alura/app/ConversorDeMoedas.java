package alura.app;

import alura.model.Moeda;
import alura.service.ImplementaApi;

import java.util.Scanner;

public class ConversorDeMoedas {
    private final Scanner scanner = new Scanner(System.in);
    private final ImplementaApi api = new ImplementaApi();

    public void iniciar() {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            if (opcao == 7) break;

            Moeda moeda = obterMoedas(opcao);
            if (moeda == null) {
                System.out.println("Opção inválida!");
                continue;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            double taxa = api.obterTaxaDeCambio(moeda.getBase(), moeda.getDestino());
            double convertido = valor * taxa;

            System.out.printf("Valor convertido: %.2f %s%n%n", convertido, moeda.getDestino());
        }

        System.out.println("Programa encerrado.");
    }

    private void exibirMenu() {
        System.out.println("Seja bem-vindo/a ao Conversor de Moeda");
        System.out.println("1) Dólar -> Peso argentino");
        System.out.println("2) Peso argentino -> Dólar");
        System.out.println("3) Dólar -> Real brasileiro");
        System.out.println("4) Real brasileiro -> Dólar");
        System.out.println("5) Dólar -> Peso colombiano");
        System.out.println("6) Peso colombiano -> Dólar");
        System.out.println("7) Sair");
        System.out.println("Escolha uma opção válida:");
    }

    private Moeda obterMoedas(int opcao) {
        return switch (opcao) {
            case 1 -> new Moeda("USD", "ARS");
            case 2 -> new Moeda("ARS", "USD");
            case 3 -> new Moeda("USD", "BRL");
            case 4 -> new Moeda("BRL", "USD");
            case 5 -> new Moeda("USD", "COP");
            case 6 -> new Moeda("COP", "USD");
            default -> null;
        };
    }
}
