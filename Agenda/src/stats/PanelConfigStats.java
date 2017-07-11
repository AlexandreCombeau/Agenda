
package stats;

import static gestionagenda.GestionAgenda.rq;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;

/**
 * Cette classe a pour objectif la creation de statistique modulaire
 * On va pouvoir modifier, rajouter des contraintes sur des requetes SQL
 * On part d'une requete de base, generale que l'on va complexifie en ajoutant des contraintes
 * 
 * Les contraintes sont fixes, ce sont des valeurs possible de champs de la base de donnees ( a part les contraintes de temps)
 * 
 * Il manque :
 * 		- la jointure dynamique des tables de la base de donnees, quand on ajoute des contraites dans la requetes il faut que toutes les tables soient dans la meme selection
 * 		  on peut faire ses jointures grace a un graphe representant la base de donnees avec comme arretes les clefs faisant les liens
 * 		  toutes les jointures ne sont pas forcements logique et compatible pour que la stat soit correct
 * 		- la possibilite de retire la selection de contraintes de temps
 * 		- rendre ca un peut plus user friendly
 * @author Alexandre
 *
 */
public class PanelConfigStats extends JPanel implements ActionListener{
	
	private Map<String, Method> requetes; // map qui contient la liste des enonces de requetes associes a des methodes
	private JComboBox<String> comboBox;
	private JPanel panel;
	private JCalendar calendrierDebut;
	private JCalendar calendrierFin;
	private JLabel lbEnonceRequete;
	private JLabel lbDateDebut;
	private JLabel lbDateFin;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JLabel lblTable;
	private JLabel lblValeur;
	private JLabel lblValeur_1;
	private String enonceSQL;
	private JButton btnAjoutContrainte;
	private GridBagConstraints gbRepere;
	private List<WrappingClass> list; // liste qui contient les lignes de contraintes
	private JButton btnGenereStats;
	

	/**
	 * Cette classe va empaqueter les trois comboBox d'une ligne dans une meme classe	
	 */
	public class WrappingClass {
		private JComboBox<String> table;
		private JComboBox<String> element;
		private JComboBox<String> valeur;
		// On ajoute la ligne car elle seul permet de differencier les lignes lors de l'actionPerformed
		private Integer row;
		
		public WrappingClass(JComboBox<String> table, JComboBox<String> element, JComboBox<String> valeur, Integer row) {
			this.table = table;
			this.element = element;
			this.valeur = valeur;
			this.row = row;
		}
		public JComboBox<String> getTable() {
			return table;
		}
		public JComboBox<String> getElement() {
			return element;
		}
		public JComboBox<String> getValeur() {
			return valeur;
		}
		public Integer getRow() {
			return row;
		}
		public void setTable(JComboBox<String> table) {
			this.table = table;
		}
		public void setElement(JComboBox<String> element) {
			this.element = element;
		}
		public void setValeur(JComboBox<String> valeur) {
			this.valeur = valeur;
		}
		public void setRow(Integer row) {
			this.row = row;
		}
	}
	
	public PanelConfigStats() {
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 900));
		this.setLayout(new BorderLayout(0, 0));
		
		requetes = new HashMap<>();
		comboBox = new JComboBox<>();
		panel = new JPanel();
		lbEnonceRequete = new JLabel();
		list = new ArrayList<>();
		
		gbRepere = new GridBagConstraints();
		gbRepere.insets = new Insets(0, 0, 5, 5);
		gbRepere.gridx = 1;
		gbRepere.gridy = 5;
		
		this.add(comboBox, BorderLayout.NORTH);
		this.add(panel, BorderLayout.WEST);
		
		this.initMap();
		this.adding();
		
		GridBagLayout gbl_panel = new GridBagLayout();
		panel.setLayout(gbl_panel);
		
		btnGenereStats = new JButton("genere stats");
		btnGenereStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				genererStats();
				
			}
		});
		GridBagConstraints gbc_btnGenereStats = new GridBagConstraints();
		gbc_btnGenereStats.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenereStats.gridx = 0;
		gbc_btnGenereStats.gridy = 0;
		panel.add(btnGenereStats, gbc_btnGenereStats);
		
		
		
		lbEnonceRequete = new JLabel("Nombre d'heure moyen d'une reservation");
		GridBagConstraints gbc_lbEnonce = new GridBagConstraints();
		gbc_lbEnonce.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbEnonce.insets = new Insets(0, 0, 5, 5);
		gbc_lbEnonce.gridwidth = 2;
		gbc_lbEnonce.gridx = 1;
		gbc_lbEnonce.gridy = 1;
		panel.add(lbEnonceRequete, gbc_lbEnonce);
		lbDateDebut = new JLabel("du 21-07-2017");
		GridBagConstraints gbc_lbDateDebut = new GridBagConstraints();
		gbc_lbDateDebut.anchor = GridBagConstraints.NORTH;
		gbc_lbDateDebut.insets = new Insets(0, 0, 5, 5);
		gbc_lbDateDebut.gridx = 3;
		gbc_lbDateDebut.gridy = 1;
		panel.add(lbDateDebut, gbc_lbDateDebut);
		lbDateFin = new JLabel("au 22-07-2017");
		GridBagConstraints gbc_lbDateFin = new GridBagConstraints();
		gbc_lbDateFin.insets = new Insets(0, 0, 5, 0);
		gbc_lbDateFin.gridx = 4;
		gbc_lbDateFin.gridy = 1;
		panel.add(lbDateFin, gbc_lbDateFin);
		calendrierDebut = new JCalendar();
		
		calendrierDebut.setDate(Date.from(Instant.now()));
		GridBagConstraints gbc_calendrierDebut = new GridBagConstraints();
		gbc_calendrierDebut.anchor = GridBagConstraints.NORTHWEST;
		gbc_calendrierDebut.insets = new Insets(0, 0, 5, 5);
		gbc_calendrierDebut.gridx = 2;
		gbc_calendrierDebut.gridy = 3;
		panel.add(calendrierDebut, gbc_calendrierDebut);
		
		// permet d'ecouter les champs du calendrier afin de detecter un changement
		calendrierDebut.addPropertyChangeListener("calendar", e ->  {
	       final Calendar c = (Calendar) e.getNewValue();   
	       SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
	        lbDateDebut.setText(sf.format(c.getTime()));  
		});
		calendrierFin = new JCalendar();
		calendrierFin.setDate(Date.from(Instant.now()));
		GridBagConstraints gbc_calendrierFin = new GridBagConstraints();
		gbc_calendrierFin.insets = new Insets(0, 0, 5, 0);
		gbc_calendrierFin.anchor = GridBagConstraints.NORTHEAST;
		gbc_calendrierFin.gridx = 4;
		gbc_calendrierFin.gridy = 3;
		panel.add(calendrierFin, gbc_calendrierFin);
		
		lblTable = new JLabel("Table");
		GridBagConstraints gbc_lblTable = new GridBagConstraints();
		gbc_lblTable.insets = new Insets(0, 0, 5, 5);
		gbc_lblTable.gridx = 2;
		gbc_lblTable.gridy = 4;
		panel.add(lblTable, gbc_lblTable);
		
		lblValeur = new JLabel("Element");
		GridBagConstraints gbc_lblValeur = new GridBagConstraints();
		gbc_lblValeur.insets = new Insets(0, 0, 5, 5);
		gbc_lblValeur.gridx = 3;
		gbc_lblValeur.gridy = 4;
		panel.add(lblValeur, gbc_lblValeur);
		
		lblValeur_1 = new JLabel("Valeur");
		GridBagConstraints gbc_lblValeur_1 = new GridBagConstraints();
		gbc_lblValeur_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblValeur_1.gridx = 4;
		gbc_lblValeur_1.gridy = 4;
		panel.add(lblValeur_1, gbc_lblValeur_1);
		
		btnAjoutContrainte = new JButton("ajout contrainte");
		btnAjoutContrainte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajoutContrainte();
			}
		});
		GridBagConstraints gbc_btnAjoutContrainte = new GridBagConstraints();
		gbc_btnAjoutContrainte.insets = new Insets(0, 0, 0, 5);
		gbc_btnAjoutContrainte.gridx = 0;
		gbc_btnAjoutContrainte.gridy = 5;
		panel.add(btnAjoutContrainte, gbc_btnAjoutContrainte);
		
		JLabel lblO = new JLabel("O\u00F9 :");
		GridBagConstraints gbc_lblO = new GridBagConstraints();
		gbc_lblO.anchor = GridBagConstraints.WEST;
		gbc_lblO.insets = new Insets(0, 0, 0, 5);
		gbc_lblO.gridx = 1;
		gbc_lblO.gridy = 5;
		panel.add(lblO, gbc_lblO);
		
		calendrierFin.addPropertyChangeListener("calendar", e-> {
			final Calendar c = (Calendar) e .getNewValue();
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			lbDateFin.setText(sf.format(c.getTime()));
		});
		
		comboBox.addActionListener(this);
		comboBox.setActionCommand("comboBox");
		
		try {
			requetes.get(comboBox.getSelectedItem()).invoke(this);
			lbEnonceRequete.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		list.stream().map(WrappingClass::getTable).forEach(j->remplirTable(j));
	}
	
	/**
	 * Ajoute les enonces des requetes dans la comboBox en haut de la fenetre
	 */
	public void adding() {
		requetes.entrySet()
			.stream()
			.forEach(r -> comboBox.addItem(r.getKey()));
	}
	
	
	/**
	 * On initialise la map<String, Method> qui va associe une chaine de caractere a une methode
	 */
	public void initMap() {
		try {
			requetes.put("test1", this.getClass().getMethod("requete1"));
			requetes.put("test2", this.getClass().getMethod("requete2"));
			requetes.put("test3", this.getClass().getMethod("requete3"));
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();	
		}
	}
	
	/**
	 * Ces methodes sont là pour permettre une configuration des composant ajoutant des contraintes
	 * Mais aussi en mettant a jour la requete SQL correspondant a l'enonce
	 */
	public void requete1() {
		
		//this.lbEnonceRequete.setText("1");
		System.out.println(lbEnonceRequete.getText());
		enonceSQL = "select AVG(nbHeures) from reservation";
	}
	public void requete2() {
		
		//this.lbEnonceRequete.setText("2");	
		enonceSQL = "select AVG(nbHeures) from reservation";
	}
	public void requete3() {
		
		//this.lbEnonceRequete.setText("3");
		enonceSQL = "select AVG(nbHeures) from reservation";
	}
	
	/*
	 * Cette methode va ajouter une ligne de contrainte sur la requete
	 * Cette ligne est consitue d'une selection de table puis d'une colonne et enfin de la valeur voulu dans la colonne
	 */
	public void ajoutContrainte() {
		JComboBox<String> table = new JComboBox<>();
		table.addActionListener(this);
		
		JComboBox<String> element = new JComboBox<>();
		element.addActionListener(this);
		
		JComboBox<String> valeur = new JComboBox<>();
		valeur.addActionListener(this);
		
		// Ajout des lignes sur le panel
		gbRepere.gridx++;
		panel.add(table, gbRepere);
		gbRepere.gridx++;
		panel.add(element, gbRepere);
		gbRepere.gridx++;
		panel.add(valeur, gbRepere);
		
		// ajout des elements d'une ligne a une liste ce qui permet de garder une trace	
		list.add(new WrappingClass(table, element, valeur, gbRepere.gridy));
		list.stream()
			.map(WrappingClass::getTable)
			.filter(j->j.getItemCount()==0)
			.forEach(j->remplirTable(j));
		
		// on ajoute une actionCommand specifique a chaque comboBox afin de pouvoir gerer les actionPerformed de chacun de ses comboBox
		table.setActionCommand("table-"+gbRepere.gridy);
		element.setActionCommand("element-"+gbRepere.gridy);
		valeur.setActionCommand("valeur-"+gbRepere.gridy);
		
		gbRepere.gridy++;
		gbRepere.gridx = 1;
	}
	
	// fonction d'exemple qui permet de montrer comment parcourir une liste en java 8 et faire des test et des methodes dessus
	public void get1() {
		String str ="Select * from table where ";
		if(list.size() >0 ) {
			list.stream() //  cree un flux de donnees sur la liste list
				.map(WrappingClass::getValeur)  // convertie le flux de WrappingClasse en un flux d'un champs de WrappingClasse, ici une jcomboBox
				.filter(n->n.getItemCount()>0) // test sur la comboBox
				.map(JComboBox::getSelectedItem) // recupere l'item selectionne
				.map(Object::toString) // convertie l'item en string
				.filter(n->n.equals("1")) // test si l'item vaut "1"
				.findFirst() // prend le premier trouver
				.ifPresent(System.out::println); // si l'item trouve n'est pas null alors on le print
			
			for( WrappingClass i : list) {
				str+=i.getElement().getSelectedItem().toString()+"-";
			}
			System.out.println(str);
		}
	}
	
	/**
	 * Cette methode va remplir une comboBox avec les noms de tables de la base de donnees
	 * @param j
	 */
	public void remplirTable(JComboBox<String> j) {
		try {
			ResultSet rs = rq.getConnection().getConnection().getMetaData().getTables(null, null, "%", null);
			while(rs.next()) {
				j.addItem(rs.getString("TABLE_NAME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cette methode va remplir une comboBox avec les colonnes de la table passe en argument
	 * @param j
	 * @param table, nom de la table de la base de donnees
	 */
	public void remplirElement(JComboBox<String> j, String table) {
		try {
			ResultSet rs = rq.getConnection().getConnection().prepareStatement("Select * from "+table+"").executeQuery();
			int rowCount =	rs.getMetaData().getColumnCount();
			for(int i=1;i<=rowCount;i++) 
				j.addItem(rs.getMetaData().getColumnName(i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cette methode va remplir une comboBox avec les valeurs prise par une colonne d'une table de la base de donnees
	 * @param j
	 * @param table, nom de la table
	 * @param colonne, nom de la colonne
	 */
	public void remplirValeur(JComboBox<String> j,String table, String colonne) {
		try {
			ResultSet rs = rq.getConnection().getConnection().prepareStatement("Select * from "+table+"").executeQuery();
			while(rs.next())
				j.addItem(rs.getString(colonne));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Cette methode va constituer la requete SQL a partir des contraintes choisis par l'utilisateur 
	 */
	public void genererStats() {
		// on ecrit le test dans un objet ce qui va nous permettre d'appeler cette condition	
		Predicate<WrappingClass> estValide = w -> {
			return w.getElement().getItemCount() > 0 && w.getValeur().getItemCount() > 0;
		};
		
		// s doit recuperer l'enonce de la requete traduite en sql
		//String s ="select AVG(nbHeure)";
		String s = enonceSQL;
		if(list.size()>0) {
			String[] temp = enonceSQL.split("from");
			s = temp[0];
			String table = temp[1];
	 		// on parours l'objet afin de recuperer tous les champs selectionnees	
			s+=", ";
			for(WrappingClass i : list) {
				if(estValide.test(i))
					s+=i.getTable().getSelectedItem().toString()+"."+i.getElement().getSelectedItem().toString()+", ";
			} // select
			s = s.substring(0, s.length()-2);
			s+=" from ";
			s+=table+", ";
			for(WrappingClass i : list) {
				s+=i.getTable().getSelectedItem().toString()+",";
			} // from
			s = s.substring(0, s.length()-1);
			s+=" where 1";
			for(WrappingClass i : list) {
				if(estValide.test(i))
					s+=" AND "+i.getTable().getSelectedItem().toString()+"."+i.getElement().getSelectedItem().toString()+" = "+"'"+i.getValeur().getSelectedItem().toString()+"'";
			}
		}
		
		System.out.println(s);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand().split("-")[0]) {
		case "comboBox" : 
			try {
				// execute la methode associe a la chaine de caractere
				requetes.get(comboBox.getSelectedItem()).invoke(this);
				// on met a jour le texte
				lbEnonceRequete.setText(comboBox.getSelectedItem().toString());
				
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				e1.printStackTrace();
			}
			break;
			
			
		case "table": 
			
			String tableName = ((JComboBox)e.getSource()).getSelectedItem().toString();
			
			// on recupere la ligne de comboBox en question
			// on va parcourir la liste des comboBox puis effectuer un test permettant de recuperer le numero de la ligne et le comparant a notre boite
			// une fois notre boite recuperer on va pouvoir	effectuer les actions voulues
			WrappingClass w = list.stream()
									.filter(j->j.getRow().equals(Integer.parseInt(e.getActionCommand().split("-")[1])))
									.findFirst()
									.get();
				
			// on vide la comboBox avant de la remplir 
			if(w.getElement().getItemCount() > 0)
				w.getElement().removeAllItems();
			
			remplirElement(w.getElement(), tableName);
			break;
		
		case "element" :
			
			WrappingClass ww = list.stream()
									.filter(j->j.getRow().equals(Integer.parseInt(e.getActionCommand().split("-")[1])))
									.findFirst()
									.get();
			
			if(ww.getValeur().getItemCount() > 0 )
				ww.getValeur().removeAllItems();
			if(ww.getTable().getItemCount() > 0 && ww.getElement().getItemCount() > 0)
				remplirValeur(ww.getValeur(), ww.getTable().getSelectedItem().toString(), ww.getElement().getSelectedItem().toString());
			break;
		}
	}
}
