package app;

import entity.Consulta;
import entity.DadosUsuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cadastro do usuário (1 só)
        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();

        System.out.println("Digite seu email:");
        String email = scanner.nextLine();

        System.out.println("Digite sua idade:");
        int idade = Integer.parseInt(scanner.nextLine());

        System.out.println("Digite seu endereço:");
        String endereco = scanner.nextLine();

        System.out.println("Digite seu telefone:");
        String telefone = scanner.nextLine();

        DadosUsuario usuario = new DadosUsuario(nome, email, idade, endereco, telefone);
        ArrayList<Consulta> consultas = new ArrayList<>();

        int opcao;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Mostrar dados");
            System.out.println("2 - Alterar nome");
            System.out.println("3 - Excluir dados");
            System.out.println("4 - Marcar consulta");
            System.out.println("5 - Lista de consultas");
            System.out.println("6 - Remarcar Consulta");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    usuario.exibir();
                    break;

                case 2:
                    System.out.println("Digite o novo nome:");
                    String novoNome = scanner.nextLine();
                    usuario.setNome(novoNome);
                    System.out.println("Nome alterado!");
                    break;

                case 3:
                    usuario.excluirDados();
                    System.out.println("Dados excluídos!");
                    break;

                case 4:
                    System.out.println("\n--- Agendamento de Consulta ---");

                    System.out.println("Digite a data da consulta:");
                    String dataConsulta = scanner.nextLine();

                    System.out.println("Digite o horário da consulta:");
                    String horarioConsulta = scanner.nextLine();

                    System.out.println("Digite o nome do medico:");
                    String nomeMedico = scanner.nextLine();

                    System.out.println("Digite a especialidade:");
                    String especialidade = scanner.nextLine();

                    Consulta novaConsulta = new Consulta(dataConsulta, horarioConsulta, nomeMedico, especialidade);
                    consultas.add(novaConsulta);

                    System.out.println("\n--- COMPROVANTE DE AGENDAMENTO ---");
                    System.out.println("Paciente: " + usuario.getNome());
                    System.out.println("Email: " + usuario.getEmail());
                    System.out.println("Data da consulta: " + dataConsulta);
                    System.out.println("Horário: " + horarioConsulta);
                    System.out.println("Médico: " + nomeMedico);
                    System.out.println("Especialidade: " + especialidade);
                    System.out.println("Endereço cadastrado: " + usuario.getEndereco());
                    System.out.println("----------------------------------");
                    break;

                case 5:
                    if (consultas.isEmpty()) {
                        System.out.println("\nNão há consultas.");
                    } else {
                        System.out.println("\n--- Consultas Marcadas ---");
                        for (Consulta c2 : consultas) {
                            System.out.println(c2);
                        }
                    }
                    break;

                case 6:
                    System.out.println("\n--- Reagendamento de Consulta ---");

                    System.out.println("Digite a nova data da consulta:");
                    String novaDataConsulta = scanner.nextLine();

                    System.out.println("Digite o novo horário da consulta:");
                    String novoHorarioConsulta = scanner.nextLine();

                    System.out.println("Digite o nome do especialista:");
                    String nomeEspecialista = scanner.nextLine();

                    System.out.println("Digite a especialidade:");
                    String novaEspecialidade = scanner.nextLine();

                    Consulta consultaRemarcada = new Consulta(
                            novaDataConsulta, novoHorarioConsulta, nomeEspecialista, novaEspecialidade
                    );
                    consultas.add(consultaRemarcada);

                    System.out.println("\n--- COMPROVANTE DE REAGENDAMENTO ---");
                    System.out.println("\n--- Sua consulta foi remarcada com sucesso ---");
                    System.out.println("Paciente: " + usuario.getNome());
                    System.out.println("Email: " + usuario.getEmail());
                    System.out.println("Data da consulta: " + novaDataConsulta);
                    System.out.println("Horário: " + novoHorarioConsulta);
                    System.out.println("Médico/Especialista: " + nomeEspecialista);
                    System.out.println("Especialidade: " + novaEspecialidade);
                    System.out.println("Endereço cadastrado: " + usuario.getEndereco());
                    System.out.println("------------------------------------------------------");
                    break;

                case 0:
                    System.out.println("Fechando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);

        scanner.close();
    }
}