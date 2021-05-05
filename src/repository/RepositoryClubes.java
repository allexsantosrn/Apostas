package repository;

import java.util.Collection;
import java.util.HashSet;

import model.Clube;

public class RepositoryClubes {

	Collection<Clube> clubes = new HashSet<>();

	public void adicionarTime(Clube clube) {

		if (clubes.contains(clube)) {
			System.out.println("Clube já se encontra adicionado ao sistema!");
		}

		else {
			clubes.add(clube);
			System.out.println("Clube adicionado com sucesso!");
		}
	}

	public void consultarTime(String nome) {

		if (hasTimeByNome(nome)) {

			for (Clube clube : clubes) {

				if (clube.getNome().equals(nome)) {
					System.out.println("Consulta realizada com sucesso!");
					System.out.println(clube);					
				}

			}
		}

		else {

			System.out.println("Não foram localizados clubes com o nome informado!");
		}
	}

	public boolean hasTimeByNome(String nome) {

		boolean existe = false;

		for (Clube clube : clubes) {

			if (clube.getNome().equals(nome)) {
				existe = true;
			}			

		}

		return existe;
	}
	
	public Clube returnClubeByNome(String nome) {
		
		Clube clubex = new Clube();
		
		for (Clube clube : clubes) {

			if (clube.getNome().equals(nome)) {
				clubex = clube;
			}			

		}
		
		return clubex;
		
	}

	public Collection<Clube> getClubes() {
		return clubes;
	}

	
}
