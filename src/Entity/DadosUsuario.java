package Entity;

import java.util.Scanner;

public class DadosUsuario {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();

        System.out.println("Digite seu email:");
        String email = scanner.nextLine();

        System.out.println("Digite sua idade:");
        String idade = scanner.nextLine();

        System.out.println("Digite seu endereço:");
        String endereco = scanner.nextLine();

        System.out.println("\n--- Dados Cadastrados ---");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Idade: " + idade);
        System.out.println("Endereço: " + endereco);

        System.out.println("\nO que deseja fazer?");
        System.out.println("1 - Alterar nome");
        System.out.println("2 - Excluir dados");
        System.out.println("3 - Manter dados");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {

            System.out.println("Digite o novo nome:");
            nome = scanner.nextLine();

            System.out.println("\n--- Dados Atualizados ---");
            System.out.println("Nome: " + nome);
            System.out.println("Email: " + email);
            System.out.println("Idade: " + idade);
            System.out.println("Endereço: " + endereco);

        } else if (opcao == 2) {

            nome = "";
            email = "";
            idade = "";
            endereco = "";

            System.out.println("\nDados excluídos.");

        } else {

            System.out.println("\nDados mantidos sem alterações.");

        }

        scanner.close();
    }
}