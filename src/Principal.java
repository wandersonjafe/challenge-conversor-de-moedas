import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaMoeda consulta = new ConsultaMoeda();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== Conversor de Moedas ===");
            System.out.println("1 - USD → BRL ");
            System.out.println("2 - EUR → BRL ");
            System.out.println("3 - BRL → USD ");
            System.out.println("4 - ARS → BRL ");
            System.out.println("5 - BRL → EUR ");
            System.out.println("6 - GBP → BRL ");
            System.out.println("0 - Sair ");
            System.out.print("Escolha a opção: ");
            opcao = leitura.nextInt();

            if (opcao == 0) {
                System.out.println("Aplicação finalizada!");
                break;
            }

            System.out.println("Informe o valor que deseja converter: ");
            String valorTexto = leitura.next().replace(",", ".");
            double valor = Double.parseDouble(valorTexto);


            String de = "";
            String para = "";

            switch (opcao) {
                case 1 -> { de = "USD"; para = "BRL"; }
                case 2 -> { de = "EUR"; para = "BRL"; }
                case 3 -> { de = "BRL"; para = "USD"; }
                case 4 -> { de = "ARS"; para = "BRL"; }
                case 5 -> { de = "BRL"; para = "EUR"; }
                case 6 -> { de = "GBP"; para = "BRL"; }
                default -> {
                    System.out.println("Opção inválida!");
                    continue;
                }
            }

            try {
                double convertido = consulta.buscarCotacao(de, para) * valor;
                System.out.printf("Valor convertido: %.2f %s%n", convertido, para);
            } catch (RuntimeException e) {
                System.out.println("Erro na conversão: " + e.getMessage());
            }
        }
    }
}
