package model;

import java.time.LocalDate;

public class Partida {

	LocalDate data; // Data da partida
	Clube casa; // Time da casa
	Clube fora; // Time visitante
	int totalGols; // Total de gols da partida
	int golsCasa; // Total de gols do time da casa
	int golsFora; // Total de gols do time visitante
	double mediagolsCasa;
	double mediagolsAway;
	double percent15Casa;
	double percent15Fora;
	double percent25Casa;
	double percent25Fora;
	String placarHT;
	String placar2T;
	String placarFT;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Clube getCasa() {
		return casa;
	}

	public void setCasa(Clube casa) {
		this.casa = casa;
	}

	public Clube getFora() {
		return fora;
	}

	public void setFora(Clube fora) {
		this.fora = fora;
	}

	public int getTotalGols() {
		return totalGols;
	}

	public void setTotalGols(int golsHtCasa, int golsHTFora, int gols2TCasa, int gols2TFora) {
		this.totalGols = golsHtCasa + golsHtCasa + gols2TCasa + gols2TFora;
	}

	public int getGolsCasa() {
		return golsCasa;
	}

	public void setGolsCasa(int golsHtCasa, int gols2TCasa) {
		this.golsCasa = golsHtCasa + gols2TCasa;
	}

	public int getGolsFora() {
		return golsFora;
	}

	public void setGolsFora(int golsHTFora, int gols2TFora) {
		this.golsFora = golsHTFora + gols2TFora;
	}

	public double getMediagolsCasa() {
		return mediagolsCasa;
	}

	public void setMediagolsCasa(double mediagolsCasa) {
		this.mediagolsCasa = mediagolsCasa;
	}

	public double getMediagolsAway() {
		return mediagolsAway;
	}

	public void setMediagolsAway(double mediagolsAway) {
		this.mediagolsAway = mediagolsAway;
	}

	public double getPercent15Casa() {
		return percent15Casa;
	}

	public void setPercent15Casa(double percent15Casa) {
		this.percent15Casa = percent15Casa;
	}

	public double getPercent15Fora() {
		return percent15Fora;
	}

	public void setPercent15Fora(double percent15Fora) {
		this.percent15Fora = percent15Fora;
	}

	public double getPercent25Casa() {
		return percent25Casa;
	}

	public void setPercent25Casa(double percent25Casa) {
		this.percent25Casa = percent25Casa;
	}

	public double getPercent25Fora() {
		return percent25Fora;
	}

	public void setPercent25Fora(double percent25Fora) {
		this.percent25Fora = percent25Fora;
	}

	public String getPlacarHT() {
		return placarHT;
	}

	public void setPlacarHT(int golsHtCasa, int golsHtFora) {
		String htCasa = String.valueOf(golsHtCasa);
		String htFora = String.valueOf(golsHtFora);
		this.placarHT = htCasa + "-" + htFora;
	}

	public String getPlacar2T() {
		return placar2T;
	}

	public void setPlacar2T(int gols2tCasa, int gols2tFora) {
		String tCasa = String.valueOf(gols2tCasa);
		String tFora = String.valueOf(gols2tFora);
		this.placar2T = tCasa + "-" + tFora;
	}

	public String getPlacarFT() {
		return placarFT;
	}

	public void setPlacarFT(int golsHtCasa, int golsHtFora, int gols2tCasa, int gols2tFora) {
		String ftCasa = String.valueOf(golsHtCasa + gols2tCasa);
		String ftFora = String.valueOf(golsHtFora + gols2tFora);
		this.placarFT = ftCasa + "-" + ftFora;
	}

	@Override
	public String toString() {
		return "Partida [data=" + data + ", casa=" + casa + ", fora=" + fora + ", totalGols=" + totalGols
				+ ", golsCasa=" + golsCasa + ", golsFora=" + golsFora + ", mediagolsCasa=" + mediagolsCasa
				+ ", mediagolsAway=" + mediagolsAway + ", percent15Casa=" + percent15Casa + ", percent15Fora="
				+ percent15Fora + ", percent25Casa=" + percent25Casa + ", percent25Fora=" + percent25Fora
				+ ", placarHT=" + placarHT + ", placar2T=" + placar2T + ", placarFT=" + placarFT + "]";
	}

}
