package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

import model.Clube;
import model.Partida;
import repository.RepositoryClubes;
import repository.RepositoryPartidas;

public class ControllerPrincipal {

	Scanner input = new Scanner(System.in);
	RepositoryClubes repositoryClubes = new RepositoryClubes();
	RepositoryPartidas repositoryPartidas = new RepositoryPartidas();

	public void cadastrarTime() {

		Clube clube = new Clube();

		System.out.print("Informe o pa�s da equipe: ");
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

		System.out.print("Informe o nome da equipe que est� jogando em casa: ");
		String nomeCasa = input.next();

		System.out.print("Informe o nome de equipe visitante: ");
		String nomeVisitante = input.next();

		if (!repositoryClubes.hasTimeByNome(nomeCasa)) {
			System.out.println("Equipe da casa n�o localizada!");
		}

		else if (!repositoryClubes.hasTimeByNome(nomeVisitante)) {
			System.out.println("Equipe visitante n�o localizada!");
		}

		else if (nomeCasa.equals(nomeVisitante)) {

			System.out.println("Os times s�o iguais. Informe times diferentes!");
		}

		else {

			Partida partida = new Partida();

			System.out.print("Informe a data da partida: ");
			String data = input.next();

			System.out.print("Informe o num. de gols do time da casa no FT: ");
			int golsFTCasa = input.nextInt();

			System.out.print("Informe o num. de gols do time visitante no FT: ");
			int golsFTFora = input.nextInt();

			System.out.print("Informe o num. de gols do time da casa no HT: ");
			int golsHtCasa = input.nextInt();

			System.out.print("Informe o num. de gols do time visitante no HT: ");
			int golsHTFora = input.nextInt();

			int gols2TCasa = golsFTCasa - golsHtCasa;
			int gols2TFora = golsFTFora - golsHTFora;

			// Setando a data da partida.
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(data, formatter);
			partida.setData(date);

			// Setando os times da casa e visitante.
			partida.setCasa(repositoryClubes.returnClubeByNome(nomeCasa));
			partida.setFora(repositoryClubes.returnClubeByNome(nomeVisitante));

			// Setando o placar HT, 2T e FT.
			partida.setPlacarHT(golsHtCasa, golsHTFora);
			partida.setPlacar2T(gols2TCasa, gols2TFora);
			partida.setPlacarFT(golsHtCasa, golsHTFora, gols2TCasa, gols2TFora);

			// Setando o total de gols na partida.
			partida.setTotalGols(golsHtCasa, golsHTFora, gols2TCasa, gols2TFora);

			// Setando o total de gols do time de casa e fora.
			partida.setGolsCasa(golsHtCasa, gols2TCasa);
			partida.setGolsFora(golsHTFora, gols2TFora);

			// Setando m�dia gols Casa, Fora.
			partida.setMediagolsCasa(repositoryClubes.returnClubeByNome(nomeCasa).getMediagolsCasa());
			partida.setMediagolsAway(repositoryClubes.returnClubeByNome(nomeVisitante).getMediagolsFora());

			// Setando m�dias atuais de gols Casa, Fora em percentuais.
			partida.setPercent15Casa(repositoryClubes.returnClubeByNome(nomeCasa).getPercent15Casa());
			partida.setPercent15Fora(repositoryClubes.returnClubeByNome(nomeVisitante).getPercent15Fora());
			partida.setPercent25Casa(repositoryClubes.returnClubeByNome(nomeCasa).getPercent25Casa());
			partida.setPercent25Fora(repositoryClubes.returnClubeByNome(nomeVisitante).getPercent25Fora());
			
			//Setando m�dias individuais dos times.
			partida.setMediaTotalGolsPCasa(repositoryClubes.returnClubeByNome(nomeCasa).getMediaMarcados());
			partida.setMediaTotalGolsCCasa(repositoryClubes.returnClubeByNome(nomeCasa).getMediaSofridos());
			partida.setMediaTotalGolsPFora(repositoryClubes.returnClubeByNome(nomeVisitante).getMediaMarcados());
			partida.setMediaTotalGolsCFora(repositoryClubes.returnClubeByNome(nomeVisitante).getMediaSofridos());				

			repositoryClubes.returnClubeByNome(nomeCasa).setJogos();
			repositoryClubes.returnClubeByNome(nomeVisitante).setJogos();
			repositoryClubes.returnClubeByNome(nomeCasa).setGolsPTotal(golsHtCasa, gols2TCasa);
			repositoryClubes.returnClubeByNome(nomeVisitante).setGolsPTotal(golsHTFora, gols2TFora);
			repositoryClubes.returnClubeByNome(nomeCasa).setGolsCTotal(golsHTFora, gols2TFora);
			repositoryClubes.returnClubeByNome(nomeVisitante).setGolsCTotal(golsHtCasa, gols2TCasa);
			repositoryClubes.returnClubeByNome(nomeCasa).setTotalGolsCasa(golsHtCasa, golsHTFora, gols2TCasa,
					gols2TFora);
			repositoryClubes.returnClubeByNome(nomeVisitante).setTotalGolsFora(golsHtCasa, golsHTFora, gols2TCasa,
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

			repositoryClubes.returnClubeByNome(nomeCasa).setPercent15Casa();
			repositoryClubes.returnClubeByNome(nomeCasa).setPercent25Casa();
			repositoryClubes.returnClubeByNome(nomeVisitante).setPercent15Fora();
			repositoryClubes.returnClubeByNome(nomeVisitante).setPercent25Fora();

			repositoryClubes.returnClubeByNome(nomeCasa).adicionarPartida(partida);
			repositoryClubes.returnClubeByNome(nomeVisitante).adicionarPartida(partida);

			repositoryPartidas.adicionarPartida(partida);
		}

	}

	public void importarClubes() {

		String path = "c:\\Temp\\in.txt";

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
			File file = new File("c:\\Temp\\in.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter pw = new PrintWriter(file);
			pw.print("Pa�s");
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

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void importarPartidas() {

		String path = "c:\\Temp\\in3.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			line = br.readLine();

			while (line != null) {

				Partida partida = new Partida();

				String[] vect = line.split(",");
				String data = vect[0];
				String casa = vect[1];
				String fora = vect[2];
				String FTCasa = vect[3];
				String FTFora = vect[4];
				String HTCasa = vect[5];
				String HTFora = vect[6];

				int golsFTCasa = Integer.parseInt(FTCasa);
				int golsFTFora = Integer.parseInt(FTFora);

				int golsHtCasa = Integer.parseInt(HTCasa);
				int golsHtFora = Integer.parseInt(HTFora);

				int gols2TCasa = golsFTCasa - golsHtCasa;
				int gols2TFora = golsFTFora - golsHtFora;

				// Setando data da partida.
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate date = LocalDate.parse(data, formatter);
				partida.setData(date);

				// Setando os times da casa e visitante.
				partida.setCasa(repositoryClubes.returnClubeByNome(casa));
				partida.setFora(repositoryClubes.returnClubeByNome(fora));

				// Setando o placar HT, 2T e FT.
				partida.setPlacarHT(golsHtCasa, golsHtFora);
				partida.setPlacar2T(gols2TCasa, gols2TFora);
				partida.setPlacarFT(golsHtCasa, golsHtFora, gols2TCasa, gols2TFora);

				// Setando o total de gols na partida.
				partida.setTotalGols(golsHtCasa, golsHtFora, gols2TCasa, gols2TFora);

				// Setando o total de gols do time de casa e fora.
				partida.setGolsCasa(golsHtCasa, gols2TCasa);
				partida.setGolsFora(golsHtFora, gols2TFora);

				// Setando m�dia gols Casa, Fora.
				partida.setMediagolsCasa(repositoryClubes.returnClubeByNome(casa).getMediagolsCasa());
				partida.setMediagolsAway(repositoryClubes.returnClubeByNome(fora).getMediagolsFora());

				// Setando m�dias atuais de gols Casa, Fora em percentuais.
				partida.setPercent15Casa(repositoryClubes.returnClubeByNome(casa).getPercent15Casa());
				partida.setPercent15Fora(repositoryClubes.returnClubeByNome(fora).getPercent15Fora());
				partida.setPercent25Casa(repositoryClubes.returnClubeByNome(casa).getPercent25Casa());
				partida.setPercent25Fora(repositoryClubes.returnClubeByNome(fora).getPercent25Fora());
				
				//Setando m�dias individuais dos times.
				partida.setMediaTotalGolsPCasa(repositoryClubes.returnClubeByNome(casa).getMediaMarcados());
				partida.setMediaTotalGolsCCasa(repositoryClubes.returnClubeByNome(casa).getMediaSofridos());
				partida.setMediaTotalGolsPFora(repositoryClubes.returnClubeByNome(fora).getMediaMarcados());
				partida.setMediaTotalGolsCFora(repositoryClubes.returnClubeByNome(fora).getMediaSofridos());

				repositoryClubes.returnClubeByNome(casa).setJogos();
				repositoryClubes.returnClubeByNome(fora).setJogos();
				repositoryClubes.returnClubeByNome(casa).setGolsPTotal(golsHtCasa, gols2TCasa);
				repositoryClubes.returnClubeByNome(fora).setGolsPTotal(golsHtFora, gols2TFora);
				repositoryClubes.returnClubeByNome(casa).setGolsCTotal(golsHtFora, gols2TFora);
				repositoryClubes.returnClubeByNome(fora).setGolsCTotal(golsHtCasa, gols2TCasa);
				repositoryClubes.returnClubeByNome(casa).setTotalGolsCasa(golsHtCasa, golsHtFora, gols2TCasa,
						gols2TFora);
				repositoryClubes.returnClubeByNome(fora).setTotalGolsFora(golsHtCasa, golsHtFora, gols2TCasa,
						gols2TFora);
				repositoryClubes.returnClubeByNome(casa).setMediaMarcados();
				repositoryClubes.returnClubeByNome(fora).setMediaMarcados();
				repositoryClubes.returnClubeByNome(casa).setMediaSofridos();
				repositoryClubes.returnClubeByNome(fora).setMediaSofridos();
				repositoryClubes.returnClubeByNome(casa).setMediagolsCasa();
				repositoryClubes.returnClubeByNome(fora).setMediagolsFora();

				if ((golsHtCasa + golsHtFora + gols2TCasa + gols2TFora) > 1) {

					repositoryClubes.returnClubeByNome(casa).setTotalGols15Casa();
					repositoryClubes.returnClubeByNome(fora).setTotalGols15Fora();

				}

				if ((golsHtCasa + golsHtFora + gols2TCasa + gols2TFora) > 2) {

					repositoryClubes.returnClubeByNome(casa).setTotalGols25Casa();
					repositoryClubes.returnClubeByNome(fora).setTotalGols25Fora();
				}

				repositoryClubes.returnClubeByNome(casa).setPercent15Casa();
				repositoryClubes.returnClubeByNome(casa).setPercent25Casa();
				repositoryClubes.returnClubeByNome(fora).setPercent15Fora();
				repositoryClubes.returnClubeByNome(fora).setPercent25Fora();

				repositoryClubes.returnClubeByNome(casa).adicionarPartida(partida);
				repositoryClubes.returnClubeByNome(fora).adicionarPartida(partida);

				repositoryPartidas.adicionarPartida(partida);

				line = br.readLine();
			}

		}

		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
			// e.printStackTrace();
		}

	}

	public void exportarPartidas() {

		try {
			File file = new File("c:\\Temp\\partidasout.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter pw = new PrintWriter(file);
			pw.print("Data");
			pw.print(",");
			pw.print("Casa");
			pw.print(",");
			pw.print("Visitante");
			pw.print(",");
			pw.print("GolsFTCasa");
			pw.print(",");
			pw.print("GolsFTFora");
			pw.print(",");
			pw.print("PlacarHT");
			pw.print(",");
			pw.print("PlacarFT");
			pw.print(",");
			pw.print("Percent1,5Casa");
			pw.print(",");
			pw.println("Percent1,5Fora");

			Collection<Partida> partidas = new HashSet<>();

			partidas = repositoryPartidas.getPartidas();

			for (Partida partida : partidas) {

				pw.print(partida.getData());
				pw.print(",");
				pw.print(partida.getCasa().getNome());
				pw.print(",");
				pw.print(partida.getFora().getNome());
				pw.print(",");
				pw.print(partida.getGolsCasa());
				pw.print(",");
				pw.print(partida.getGolsFora());
				pw.print(",");
				pw.print(partida.getPlacarFT());
				pw.print(",");
				pw.print(partida.getPlacarHT());
				pw.print(",");
				pw.println(partida.getPercent15Fora());

			}

			pw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void exportarPartidasOtimizado() {

		try {
			File file = new File("c:\\Temp\\partidasoutotimizado.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter pw = new PrintWriter(file);
			pw.print("Data");
			pw.print(",");
			pw.print("Casa");
			pw.print(",");
			pw.print("Visitante");
			pw.print(",");
			pw.print("GolsFTCasa");
			pw.print(",");
			pw.print("GolsFTFora");
			pw.print(",");
			pw.print("PlacarHT");
			pw.print(",");
			pw.print("PlacarFT");
			pw.print(",");
			pw.print("Percent1.5Casa");
			pw.print(",");
			pw.print("Percent1.5Fora");
			pw.print(",");
			pw.print("Percent2.5Casa");
			pw.print(",");
			pw.print("Percent2.5Fora");
			pw.print(",");
			pw.print("MediaGolsCasa");
			pw.print(",");
			pw.print("MediaGolsFora");
			pw.print(",");
			pw.print("MediaMarcadosCasa");
			pw.print(",");			
			pw.print("MediaSofridosCasa");
			pw.print(",");
			pw.print("MediaMarcadosFora");
			pw.print(",");			
			pw.println("MediaSofridosFora");			

			Collection<Partida> partidas = new HashSet<>();

			partidas = repositoryPartidas.getPartidas();

			for (Partida partida : partidas) {

				pw.print(partida.getData());
				pw.print(",");
				pw.print(partida.getCasa().getNome());
				pw.print(",");
				pw.print(partida.getFora().getNome());
				pw.print(",");
				pw.print(partida.getGolsCasa());
				pw.print(",");
				pw.print(partida.getGolsFora());
				pw.print(",");
				pw.print(partida.getPlacarFT());
				pw.print(",");
				pw.print(partida.getPlacarHT());
				pw.print(",");
				pw.print(partida.getPercent15Casa());
				pw.print(",");
				pw.print(partida.getPercent15Fora());
				pw.print(",");
				pw.print(partida.getPercent25Casa());
				pw.print(",");
				pw.print(partida.getPercent25Fora());
				pw.print(",");
				pw.print(partida.getMediagolsCasa());
				pw.print(",");
				pw.print(partida.getMediagolsAway());
				pw.print(",");
				pw.print(partida.getMediaTotalGolsPCasa());
				pw.print(",");
				pw.print(partida.getMediaTotalGolsCCasa());
				pw.print(",");
				pw.print(partida.getMediaTotalGolsPFora());
				pw.print(",");
				pw.println(partida.getMediaTotalGolsCFora());
			}

			pw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
