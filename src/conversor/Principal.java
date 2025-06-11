package conversor;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        conversor.ConsultaMoeda consulta = new conversor.ConsultaMoeda();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== Conversor de Moedas ===");
            System.out.println("1 - USD → BRL");
            System.out.println("2 - BRL → USD");
            System.out.println("3 - ARS → BRL");
            System.out.println("4 - BRL → ARS");
            System.out.println("5 - CLP → BRL");
            System.out.println("6 - BRL → COP");
            System.out.println("0 - Sair");
            System.out.print("Escolha a opção: ");

            if (leitura.hasNextInt()) {
                opcao = leitura.nextInt();
            } else {
                System.out.println("Opção inválida!");
                leitura.next();
                continue;
            }

            if (opcao == 0) {
                System.out.println("Aplicação finalizada!");
                break;
            }

            System.out.print("Informe o valor que deseja converter: ");
            String valorTexto = leitura.next().replace(",", ".");
            double valor;

            try {
                valor = Double.parseDouble(valorTexto);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Tente novamente.");
                continue;
            }

            String de = "";
            String para = "";

            switch (opcao) {
                case 1 -> { de = "USD"; para = "BRL"; }
                case 2 -> { de = "BRL"; para = "USD"; }
                case 3 -> { de = "ARS"; para = "BRL"; }
                case 4 -> { de = "BRL"; para = "ARS"; }
                case 5 -> { de = "CLP"; para = "BRL"; }
                case 6 -> { de = "BRL"; para = "COP"; }
                default -> {
                    System.out.println("Opção inválida!");
                    continue;
                }
            }

            try {
                double taxa = consulta.buscarCotacao(de, para);
                double convertido = converter(valor, taxa);
                System.out.printf("Valor convertido: %.2f %s%n", convertido, para);
            } catch (RuntimeException e) {
                System.out.println("Erro na conversão: " + e.getMessage());
            }
        }
    }

    public static double converter(double valorOriginal, double taxa) {
        return valorOriginal * taxa;
    }
}
