package view;

import java.util.Scanner;

import controller.ControllerPrincipal;

public class Home {

	public static void main(String[] args) {
		
		int option;
		
		Scanner input = new Scanner(System.in);		

		ControllerPrincipal controller = new ControllerPrincipal();

		do {

			System.out.println("Seja bem-vindo!!");
			System.out.println("1-Cadastrar Time");
			System.out.println("2-Consultar Time");
			System.out.println("3-Cadastrar Partida");
			System.out.println("4-Importar Times");
			System.out.println("5-Exportar Times");
			System.out.print("Escolha uma opção: ");
			option = input.nextInt();

			switch (option) {

			case 1:
				controller.cadastrarTime();
				break;

			case 2:
				controller.consultarTime();
				break;

			case 3:
				controller.cadastrarPartidas();
				break;
				
			case 4:
				controller.importarClubes();
				break;
				
			case 5:
				controller.exportarClubes();
				break;

			default:
				System.out.println("Opção inválida. Escolha uma opção entre 0 e 10!!!");
				break;
			}

		}

		while (option != 0);
		System.out.println("Encerrando...");
		input.close();

	}

}
