import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaMoeda consulta = new ConsultaMoeda();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== Conversor de Moedas ===");
            System.out.println("1 - ARS → BRL ");
            System.out.println("2 - BOB → BRL ");
            System.out.println("3 - BRL → CLP ");
            System.out.println("4 - CLP → BRL ");
            System.out.println("5 - COP → USD ");
            System.out.println("6 - USD → BRL ");
            System.out.println("0 - Sair ");
            System.out.print("Escolha a opção: ");
            opcao = leitura.nextInt();

            if (opcao == 0) {
                System.out.println("Aplicação finalizada!");
                break;
            }

            System.out.print("Informe o valor que deseja converter: ");
            String valorTexto = leitura.next().replace(",", ".");
            double valor = Double.parseDouble(valorTexto);

            String de = "";
            String para = "";

            switch (opcao) {
                case 1 -> { de = "ARS"; para = "BRL"; }
                case 2 -> { de = "BOB"; para = "BRL"; }
                case 3 -> { de = "BRL"; para = "CLP"; }
                case 4 -> { de = "CLP"; para = "BRL"; }
                case 5 -> { de = "COP"; para = "USD"; }
                case 6 -> { de = "USD"; para = "BRL"; }
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
