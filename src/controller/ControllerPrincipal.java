package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

import model.Clube;
import model.Partida;
import repository.RepositoryClubes;

public class ControllerPrincipal {

	Scanner input = new Scanner(System.in);
	RepositoryClubes repositoryClubes = new RepositoryClubes();

	public void cadastrarTime() {

		Clube clube = new Clube();

		System.out.print("Informe o país da equipe: ");
		String pais = input.nextLine();
		System.out.print("Informe o nome da equipe: ");
		String nome = input.nextLine();

		clube.setPais(pais);
		clube.setNome(nome);

		repositoryClubes.adicionarTime(clube);

	}

	public void consultarTime() {

		System.out.print("Informe o nome da equipe: ");
		String nome = input.nextLine();

		repositoryClubes.consultarTime(nome);

	}

	public void cadastrarPartidas() {

		System.out.print("Informe o nome da equipe que está jogando em casa: ");
		String nomeCasa = input.next();

		System.out.print("Informe o nome de equipe visitante: ");
		String nomeVisitante = input.next();

		if (!repositoryClubes.hasTimeByNome(nomeCasa)) {
			System.out.println("Equipe da casa não localizada!");
		}

		else if (!repositoryClubes.hasTimeByNome(nomeVisitante)) {
			System.out.println("Equipe visitante não localizada!");
		}

		else if (nomeCasa.equals(nomeVisitante)) {

			System.out.println("Os times são iguais. Informe times diferentes!");
		}

		else {

			Partida partida = new Partida();

			System.out.print("Informe o num. de gols do time da casa no HT: ");
			int golsHtCasa = input.nextInt();

			System.out.print("Informe o num. de gols do time visitante no HT: ");
			int golsHTFora = input.nextInt();

			System.out.print("Informe o num. de gols do time da casa no 2T: ");
			int gols2TCasa = input.nextInt();

			System.out.print("Informe o num. de gols do time visitante no 2T: ");
			int gols2TFora = input.nextInt();

			partida.setTotalGols(golsHtCasa, golsHTFora, gols2TCasa, gols2TFora);
			partida.setGolsCasa(golsHtCasa, gols2TCasa);
			partida.setGolsFora(golsHTFora, gols2TFora);
			partida.setPlacarHT(golsHtCasa, golsHTFora);
			partida.setPlacar2T(gols2TCasa, gols2TFora);
			partida.setPlacarFT(golsHtCasa, golsHTFora, gols2TCasa, gols2TFora);

			partida.setMediagolsCasa(repositoryClubes.returnClubeByNome(nomeCasa).getMediagolsCasa());
			partida.setMediagolsCasa(repositoryClubes.returnClubeByNome(nomeVisitante).getMediagolsFora());
			partida.setMediagolsCasa(repositoryClubes.returnClubeByNome(nomeCasa).getPercent15Casa());
			partida.setMediagolsCasa(repositoryClubes.returnClubeByNome(nomeVisitante).getPercent15Fora());
			partida.setMediagolsCasa(repositoryClubes.returnClubeByNome(nomeCasa).getPercent25Casa());
			partida.setMediagolsCasa(repositoryClubes.returnClubeByNome(nomeVisitante).getPercent25Fora());

			partida.setCasa(repositoryClubes.returnClubeByNome(nomeCasa));
			partida.setFora(repositoryClubes.returnClubeByNome(nomeVisitante));

			repositoryClubes.returnClubeByNome(nomeCasa).setJogos();
			repositoryClubes.returnClubeByNome(nomeVisitante).setJogos();
			repositoryClubes.returnClubeByNome(nomeCasa).setGolsPTotal(golsHtCasa, gols2TCasa);
			repositoryClubes.returnClubeByNome(nomeVisitante).setGolsPTotal(golsHTFora, gols2TFora);
			repositoryClubes.returnClubeByNome(nomeCasa).setGolsCTotal(golsHTFora, gols2TFora);
			repositoryClubes.returnClubeByNome(nomeVisitante).setGolsCTotal(golsHtCasa, gols2TCasa);
			repositoryClubes.returnClubeByNome(nomeCasa).setTotalGolsCasa(golsHtCasa, golsHTFora, gols2TCasa,
					gols2TFora);
			repositoryClubes.returnClubeByNome(nomeCasa).setTotalGolsFora(golsHtCasa, golsHTFora, gols2TCasa,
					gols2TFora);
			repositoryClubes.returnClubeByNome(nomeCasa).setMediaMarcados();
			repositoryClubes.returnClubeByNome(nomeVisitante).setMediaMarcados();
			repositoryClubes.returnClubeByNome(nomeCasa).setMediaSofridos();
			repositoryClubes.returnClubeByNome(nomeVisitante).setMediaSofridos();
			repositoryClubes.returnClubeByNome(nomeCasa).setMediagolsCasa();
			repositoryClubes.returnClubeByNome(nomeVisitante).setMediagolsFora();

			if ((golsHtCasa + golsHTFora + gols2TCasa + gols2TFora) > 1) {

				repositoryClubes.returnClubeByNome(nomeCasa).setTotalGols15Casa();
				repositoryClubes.returnClubeByNome(nomeVisitante).setTotalGols15Fora();

			}

			if ((golsHtCasa + golsHTFora + gols2TCasa + gols2TFora) > 2) {

				repositoryClubes.returnClubeByNome(nomeCasa).setTotalGols25Casa();
				repositoryClubes.returnClubeByNome(nomeVisitante).setTotalGols25Fora();
			}

			repositoryClubes.returnClubeByNome(nomeCasa).adicionarPartida(partida);
			repositoryClubes.returnClubeByNome(nomeVisitante).adicionarPartida(partida);

			System.out.println("Teste: " + partida.getCasa().getGolsPTotal());
			System.out.println("Teste: " + partida.getCasa().getMediaMarcados());

		}

	}

	public void importarClubes() {

		String path = "c:\\Temp\\in2.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			line = br.readLine();

			while (line != null) {

				Clube clube = new Clube();

				String[] vect = line.split(",");
				String pais = vect[0];
				String nome = vect[1];

				clube.setPais(pais);
				clube.setNome(nome);

				repositoryClubes.adicionarTime(clube);

				line = br.readLine();
			}

		}

		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
			// e.printStackTrace();
		}

	}

	public void exportarClubes() {

		try {
			File file = new File("c:\\Temp\\in2.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter pw = new PrintWriter(file);
			pw.print("País");
			pw.print(",");
			pw.println("Clube");			
						
			Collection<Clube> clubes = new HashSet<>();
			
			clubes = repositoryClubes.getClubes();
			
			for (Clube clube : clubes) {
				
				pw.print(clube.getPais());
				pw.print(",");
				pw.println(clube.getNome());				
			}

			pw.close();
			System.out.println("Done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
