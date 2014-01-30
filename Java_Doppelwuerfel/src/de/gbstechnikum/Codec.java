package de.gbstechnikum;

public interface Codec {

	public String kodiere(String klartext);
	public String dekodiere(String geheimtext);
	public String gibLosung();
	public void setzteLosung(String schluessel);
}
