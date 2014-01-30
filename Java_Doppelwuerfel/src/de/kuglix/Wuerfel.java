package de.kuglix;

import java.util.Arrays;

public class Wuerfel implements Codec {

	private String losung;

	@Override
	public String kodiere(String klartext) {
		klartext = klartext.trim();
		char[] lw = losung.toCharArray();
		Arrays.sort(lw);
		int[] permutation = new int[this.losung.length()];
		for (int i = 0; i < lw.length; i++) {
			int j = 0;
			while (losung.charAt(i) != lw[j]) {
				j++;
			}
			permutation[i] = j;
			lw[j] = '\0';

		}
		int[] invers = this.invertiere(permutation);
		StringBuilder geheim = new StringBuilder();
		for (int i = 0; i < invers.length; i++) {
			int j = 0;
			while (invers[i] + j < klartext.length()) {
				geheim.append(klartext.charAt(invers[i] + j));
				j += losung.length();
			}
		}
		return geheim.toString();
	}

	@Override
	public String dekodiere(String geheimtext) {
		char[] lw = losung.toCharArray();
		Arrays.sort(lw);
		int[] permutation = new int[this.losung.length()];
		for (int i = 0; i < lw.length; i++) {
			int j = 0;
			while (losung.charAt(i) != lw[j]) {
				j++;
			}
			permutation[i] = j;
			lw[j] = '\0';
		}

		int n = geheimtext.length() / losung.length();
		int b = geheimtext.length() % losung.length();
		int[] vektor = new int[losung.length()];

		for (int i = 0; i < permutation.length; i++) {
			if (i < b) {
				vektor[permutation[i]] = n + 1;
			} else {
				vektor[permutation[i]] = n;
			}
		}

		StringBuilder klartext = new StringBuilder(geheimtext);
		int position = 0;
		for (int i = 0; i < geheimtext.length(); i++) {
			for (int j = 0; j < permutation[i % losung.length()]; j++) {
				position += vektor[j];
			}
			position += i / losung.length();
			klartext.setCharAt(i, geheimtext.charAt(position));
			position = 0;
		}

		return klartext.toString();

	}

	@Override
	public String gibLosung() {
		return losung;
	}

	@Override
	public void setzteLosung(String schluessel){
		this.losung = schluessel.toLowerCase().trim();
	}

	private int[] invertiere(int[] permutation) {
		int[] invers = new int[permutation.length];
		for (int i = 0; i < permutation.length; i++) {
			int j = 0;
			while (permutation[j] != i) {
				j++;
			}
			invers[i] = j;
		}

		return invers;
	}
	
	private static int[] vertausche(int[] array) {
		int[] invers = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			invers[array[i]] = i;
		}
		return invers;
	}

	public static void main(String[] args) {
//
//		Wuerfel w1 = new Wuerfel();
//		String text = "eintreffendersendungverspaetetneuerterminfolgt";
//		String text2 = "steuenelaoewecsvarhrtruidstidfereeedgetelzlieoaeazeseaiiesiejbbwcnhirziernzbveslrnoswsuseugtgeeeennemnfbhinahfennetbeutsocgvhueekjstgdahhneignruidtitlbstbaidnrensgupacehmlemiereeatcmnerlbninlln";
//		w1.setzteLosung("Vergnuegen");
//		String geheim = w1.dekodiere(text2);
//		w1.setzteLosung("Programmierung");
//		System.out.println(w1.dekodiere(geheim));

		int[] test = {7,2,4,8,0,6,10,9,1,5,3};
		int[] invers = vertausche(test);
		for (int i : invers) {
			System.out.print(i + " ");
		}
	}

}
