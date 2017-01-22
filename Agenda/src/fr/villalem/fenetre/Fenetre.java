package fr.villalem.fenetre;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	public Fenetre() {
		this.setTitle("Agenda");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null); //centre la fenetre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ferme l'application en fermant la fenetre
		
		JPanel contenu = new JPanel();
		contenu.setBackground(Color.WHITE);
		this.setContentPane(contenu);
		
		this.setVisible(true); // affiche la fenetre
	}
}
