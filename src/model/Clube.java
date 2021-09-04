package model;

import java.util.Collection;
import java.util.HashSet;

public class Clube {

	String nome;
	String pais;
	int jogos = 0;
	int golsPTotal;
	int golsCTotal;
	int totalGolsCasa;
	int totalGolsFora;
	int totalGols15Casa;
	int totalGols15Fora;
	int totalGols25Casa;
	int totalGols25Fora;
	double mediaMarcados;
	double mediaSofridos;
	double mediagolsCasa;
	double mediagolsFora;
	double percent15Casa;
	double percent15Fora;
	double percent25Casa;
	double percent25Fora;

	Collection<Partida> partidas = new HashSet<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getJogos() {
		return jogos;
	}

	public void setJogos() {
		this.jogos = this.jogos + 1;
	}

	public int getGolsPTotal() {
		return golsPTotal;
	}

	public void setGolsPTotal(int golsHt, int gols2T) {
		this.golsPTotal = golsPTotal + golsHt + gols2T;
	}

	public int getGolsCTotal() {
		return golsCTotal;
	}

	public void setGolsCTotal(int golsContraHt, int golsContra2T) {
		this.golsCTotal = golsCTotal + golsContraHt + golsContra2T;
	}

	public int getTotalGolsCasa() {
		return totalGolsCasa;
	}

	public void setTotalGolsCasa(int golsHtCasa, int golsHTFora, int gols2TCasa, int gols2TFora) {
		this.totalGolsCasa = totalGolsCasa + golsHtCasa + golsHTFora + gols2TCasa + gols2TFora;
	}

	public int getTotalGolsFora() {
		return totalGolsFora;
	}

	public void setTotalGolsFora(int golsHtCasa, int golsHTFora, int gols2TCasa, int gols2TFora) {
		this.totalGolsFora = totalGolsFora + golsHtCasa + golsHTFora + gols2TCasa + gols2TFora;
	}

	public int getTotalGols15Casa() {
		return totalGols15Casa;
	}

	public void setTotalGols15Casa() {
		this.totalGols15Casa = totalGols15Casa + 1;
	}

	public int getTotalGols15Fora() {
		return totalGols15Fora;
	}

	public void setTotalGols15Fora() {
		this.totalGols15Fora = totalGols15Fora + 1;
	}

	public int getTotalGols25Casa() {
		return totalGols25Casa;
	}

	public void setTotalGols25Casa() {
		this.totalGols25Casa = totalGols25Casa + 1;
	}

	public int getTotalGols25Fora() {
		return totalGols25Fora;
	}

	public void setTotalGols25Fora() {
		this.totalGols25Fora = totalGols25Fora + 1;
	}

	public double getMediaMarcados() {
		return mediaMarcados;
	}

	public void setMediaMarcados() {
		this.mediaMarcados = (double) this.golsPTotal / (double) this.jogos;
	}

	public double getMediaSofridos() {
		return mediaSofridos;
	}

	public void setMediaSofridos() {
		this.mediaSofridos = (double) this.golsCTotal / (double) this.jogos;
	}

	public double getMediagolsCasa() {
		return mediagolsCasa;
	}

	public void setMediagolsCasa() {
		this.mediagolsCasa = (double) this.totalGolsCasa / (double) this.jogos;
	}

	public double getMediagolsFora() {
		return mediagolsFora;
	}

	public void setMediagolsFora() {
		this.mediagolsFora = (double) this.totalGolsFora / (double) this.jogos;
	}

	public double getPercent15Casa() {
		return percent15Casa;
	}

	public void setPercent15Casa() {
		this.percent15Casa = ((double) this.totalGols15Casa / (double) this.jogos)*100;
	}

	public double getPercent15Fora() {
		return percent15Fora;
	}

	public void setPercent15Fora() {
		this.percent15Fora = ((double) this.totalGols15Fora / (double) this.jogos)*100;
	}

	public double getPercent25Casa() {
		return percent25Casa;
	}

	public void setPercent25Casa() {
		this.percent25Casa = ((double) this.totalGols25Casa / (double) this.jogos)*100;
	}

	public double getPercent25Fora() {
		return percent25Fora;
	}

	public void setPercent25Fora() {
		this.percent25Fora = ((double) this.totalGols25Fora / (double) this.jogos)*100;
	}

	public void adicionarPartida(Partida partida) {
		partidas.add(partida);
	}

	@Override
	public String toString() {
		return "Clube [nome=" + nome + ", pais=" + pais + ", jogos=" + jogos + ", golsPTotal=" + golsPTotal
				+ ", golsCTotal=" + golsCTotal + ", totalGolsCasa=" + totalGolsCasa + ", totalGolsFora=" + totalGolsFora
				+ ", totalGols15Casa=" + totalGols15Casa + ", totalGols15Fora=" + totalGols15Fora + ", totalGols25Casa="
				+ totalGols25Casa + ", totalGols25Fora=" + totalGols25Fora + ", mediaMarcados=" + mediaMarcados
				+ ", mediaSofridos=" + mediaSofridos + ", mediagolsCasa=" + mediagolsCasa + ", mediagolsFora="
				+ mediagolsFora + ", percent15Casa=" + percent15Casa + ", percent15Fora=" + percent15Fora
				+ ", percent25Casa=" + percent25Casa + ", percent25Fora=" + percent25Fora + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clube other = (Clube) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}

}
