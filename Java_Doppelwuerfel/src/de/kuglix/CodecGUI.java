package de.kuglix;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class CodecGUI extends JFrame {

	private Codec c1, c2;
	private Container c;
	private JPanel jpNorth, jpNorthContent, jpCenter, jpSouth, jpSouthContent;
	private JTextArea jtAreaKlartext, jtAreaGeheimtext;
	private JTextField jtfLosungswort1, jtfLosungswort2;
	private JButton jbtnVerschluesseln;
	private JScrollPane jscrollKlartext, jscrollGeheim;

	public CodecGUI(Codec c1, Codec c2) {
		super("CodecGUI");
		this.c1 = c1;
		this.c2 = c2;
		this.initComponents();
		this.initEvents();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		// this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initComponents() {
		c = this.getContentPane();
		
		jpNorth = new JPanel();
		jpNorthContent = new JPanel();
		jpNorthContent.setBorder(new TitledBorder("Klartext"));
		jpNorthContent.setLayout(new BorderLayout());
		jtAreaKlartext = new JTextArea(10, 70);
		jtAreaKlartext.setLineWrap(true);
		jscrollKlartext = new JScrollPane(jtAreaKlartext);
//		jpNorthContent.add(new JLabel("Klartext: "), BorderLayout.NORTH);
		jpNorthContent.add(jscrollKlartext, BorderLayout.SOUTH);
		jpNorth.add(jpNorthContent);

		jpCenter = new JPanel();
		jtfLosungswort1 = new JTextField(20);
		jtfLosungswort2 = new JTextField(20);
		jbtnVerschluesseln = new JButton("Verschlüsseln");
		jpCenter.add(new JLabel("Losungswort 1"));
		jpCenter.add(jtfLosungswort1);
		jpCenter.add(jbtnVerschluesseln);
		jpCenter.add(new JLabel("Losungswort 2"));
		jpCenter.add(jtfLosungswort2);

		jpSouth = new JPanel();
		jpSouthContent = new JPanel();
		jpSouthContent.setBorder(new TitledBorder("Geheimtext"));
		jpSouthContent.setLayout(new BorderLayout());
		jtAreaGeheimtext = new JTextArea(10, 70);
		jtAreaGeheimtext.setLineWrap(true);
		jscrollGeheim = new JScrollPane(jtAreaGeheimtext);
//		jpSouthContent.add(new JLabel("Geheimtext: "), BorderLayout.NORTH);
		jpSouthContent.add(jscrollGeheim, BorderLayout.SOUTH);
		jpSouth.add(jpSouthContent);

		c.add(jpNorth, BorderLayout.NORTH);
		c.add(jpCenter);
		c.add(jpSouth, BorderLayout.SOUTH);
	}

	private void initEvents() {
		jbtnVerschluesseln.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkInput()) {
					c1.setzteLosung(jtfLosungswort1.getText());
					c2.setzteLosung(jtfLosungswort2.getText());
					String eingabeKlartext = jtAreaKlartext.getText();
					String eingabeGeheimtext = jtAreaGeheimtext.getText();
					if (eingabeGeheimtext.isEmpty()) {
						String geheim = c1.kodiere(eingabeKlartext);
						jtAreaGeheimtext.setText(c2.kodiere(geheim));
					} else if (eingabeKlartext.isEmpty()) {
						String klartext = c2.dekodiere(eingabeGeheimtext);
						jtAreaKlartext.setText(c1.dekodiere(klartext));
					}
				}
			}
		});
	}
	
	private boolean checkInput() {
		String losungswort1 = jtfLosungswort1.getText();
		String losungswort2 = jtfLosungswort2.getText();
		return !losungswort1.isEmpty() && !losungswort2.isEmpty();
	}
}
