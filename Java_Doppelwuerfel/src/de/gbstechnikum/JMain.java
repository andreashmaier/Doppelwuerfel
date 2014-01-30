package de.gbstechnikum;

import java.util.HashMap;

import javax.swing.SwingUtilities;

public class JMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new CodecGUI(new Wuerfel(), new Wuerfel());
			}
		});
		
	
	}

}
