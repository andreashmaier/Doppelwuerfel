package de.kuglix;

import java.util.Arrays;

public class WuerfelKuglix implements Codec {

	private String losung;

	private char[] perm(String losung1) {
		char[] lw = losung1.toCharArray();
		Arrays.sort(lw);
		int[] perm = new int[losung1.length()];
		for (int i = 0; i < losung1.length(); i++) {
			for (int j = i + 1; j < losung1.length(); j++) {
				if (losung1.charAt(i) > losung1.charAt(j)) {
					perm[i]++;
				} else {
					perm[j]++;
				}
			}
		}
		System.out.println(Arrays.toString(perm));

		return lw;
	}

	@Override
	public String kodiere(String klartext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dekodiere(String geheimtext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gibLosung() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setzteLosung(String schluessel) {
		// TODO Auto-generated method stub

	}
}