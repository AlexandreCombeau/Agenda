package stats;

import static gestionagenda.GestionAgenda.rq;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Cette classe gere l'affichage des statistiques	
 * @author Alexandre
 *
 */
public class PanelStats extends JFrame {
	
	/**
	 * Cette classe va changer la couleur des cellules de la JTable
	 * C'est une modification du rendu des cellules de la table, on va defenir la couleur de chaque cellules de la table
	 * Ici chaque cellule est blanche
	 *
	 */
	public class MyRenderer extends DefaultTableCellRenderer { 
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) { 
		    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
		    c.setBackground(new Color(255, 255, 255)); 
		    return c; 
	    } 
	} 
	
	/**
	 * Cette classe represente une statistique
	 * On a cree cette classe afin de mieux presenter les stats dans la jtable en separant les valeurs des enonces
	 * On compacte ces deux informations dans un meme objet car on ne peut recuperer qu'une valeur avec Function<T,K>
	 * 
	 */
	public class Stats {
		public Stats(String nom, String valeur) {
			this.nom = nom;
			this.valeur = valeur;
		}
		public String nom;
		public String valeur;
	}
	
	/**
	 * Cet Objet est le conteneur de la JFrame
	 * Il permet d'utiliser des barres de defilements
	 */
	private JScrollPane scroll;
	
	/**
	 * Cet Objet est celui qui va contenir les donnees des statistiques
	 */
	private JTable table;
	
	/*
	 * Cette liste contient toutes les requetes SQL de stats
	 * Chaque fonction va prendre un connection et renvoyer une Stats qui est compose d'un nom et d'une valeur
	 */
	private List<Function<Connection, Stats>> statsStatics;
	
	
	public PanelStats() {
		// On instancie la liste de stats puis on la met a jour avec la fonction ajoutStats
		statsStatics = new ArrayList<>();
		this.ajoutStats();

		
		Vector<String> colonnes = new Vector<>();
		colonnes.add("Intitule");
		colonnes.add("Valeur");
		
		Vector<Vector<String>> donnees = new Vector<Vector<String>>();
		
		for(Function<Connection,Stats> i : statsStatics) {
			Stats s = i.apply(rq.getConnection().getConnection());
			donnees.add(new Vector<String>(Arrays.asList(s.nom,s.valeur)));
		}
		
		/*
		 * JTable peut etre remplie a son instanciation en fournissant les donnees
		 * Mais la seul option simple et pratique est d'utiliser Vector car il s'agit comme une version evolue d'un tableau classique
		 * Les autres constructeurs son soit trop contraignant, utiliser des tableau par exemple, soit impliquent de creer d'autres classes supplementaires (regarder la javadoc correspondante)
		 */
		
		table = new JTable(donnees, colonnes);
		scroll = new JScrollPane(table);
		
		MyRenderer myRenderer = new MyRenderer(); 
		// on definit le renderer en mettant le type de donnees contenu des les coloones ainsi que la classe qui va faire le rendu
		table.setDefaultRenderer(Object.class, myRenderer);
		
		this.add(scroll);
		//scroll.add(this);
		table.setFillsViewportHeight(true); // la JTable va prendre tout l'espace disponible
		table.setShowGrid(false); 
		table.setDragEnabled(false);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false); // empeche de rearranger les colonnes
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		this.pack();
		this.setVisible(true);
	}
	

	/*
	 * C'est dans cette methode que l'on va ajouter les implementations qui vont être stockées dans la liste stats
	 * 
	 * Comment ajouter une stats :
	 * 
	 * statsStatics.add( co -> { 
	 * 	'calcul de la stats, requetes ...' 
	 *  return new Stats(nom,valeur)
	 * });
	 */
	private void ajoutStats() {
		
		statsStatics.add( co -> {
			String valeur = "";
			try {
				ResultSet rs = co.prepareStatement("Select AVG(nbHeures) from reservation").executeQuery();
				if(rs.next()) {
					valeur = rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return new Stats("Nombre d'heure moyen d'une reservation ", valeur);
		});
		
		statsStatics.add( co -> {
			String valeur = "";
			try {
				ResultSet rs = co.prepareStatement("Select AVG(nbHeures) from client, reservation where reservation.fkidClient = client.idClient and client.entite = 'Entreprise'").executeQuery();
				if(rs.next()) {
					valeur = rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return new Stats("Nombre d'heure moyen d'une reservation où le client est une entreprise ", valeur);
		});
		
		statsStatics.add( co -> {
			String valeur = "";
			try {
				ResultSet rs = co.prepareStatement("Select AVG(nbHeures), client.idClient from client, reservation where reservation.fkidClient = client.idClient group by client.idClient").executeQuery();
				while(rs.next()) 
					valeur += rs.getString(1)+"-";
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return new Stats("Nombre d'heure moyen de reservation des clients",valeur);
		});
			
	}

}
