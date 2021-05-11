package repository;

import java.util.Collection;
import java.util.HashSet;

import model.Partida;

public class RepositoryPartidas {

	Collection<Partida> partidas = new HashSet<>();

	public void adicionarPartida(Partida partida) {

		partidas.add(partida);
	}

	public Collection<Partida> getPartidas() {
		return partidas;
	}

}
