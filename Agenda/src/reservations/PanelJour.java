package reservations;

import static gestionagenda.GestionAgenda.rq;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JPanel;

/**
 * Cette classe represente le panel de la vue jour qui est affiché dans le panel central de ITAgenda
 * @author Alexandre
 *
 */
public class PanelJour extends JPanel {
	
	/**
	 * Correspond à la hauteur d'une ligne, c'est à dire l'espacement entre deux heure, en px
	 */
	private int hauteurLigne;
	
	/**
	 * Correspond à la lageur de la representation graphique d'un evenement, en px
	 */
	private int largeurEvenement;
	
	/**
	 * C'est un petit decalage initial pour bien separer les evenements de l'affichage des heures, en px
	 */
	private int decalageInitial = 50;
	
	/**
	 * Liste contenant tous les evenements de la journée
	 */
	private List<Evenement> listeEvenements;
	
	/**
	 * Largeur de la première colonne, en px
	 */
    private final int largeurColonneHeure = 40;
    
    /**
     * Largeur de chaque colonne jour, en px
     */
    private final int largeurColonneJour = 140;
    
    /**
     * Espacement vertical entre chaque heure, en px
     */
    private final int espacementHeure = 40;
    
    
	public PanelJour() {
		listeEvenements = new ArrayList<>();	
		this.setPreferredSize(new Dimension(950, 1400));
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// on parcours la liste des evenements et on execute la méthode affichePopup du premier evenement qui remplit la condition dans le filtre
				listeEvenements.stream().filter(evt->evt.contain(e.getPoint())).findFirst().get().affichePopup();
			}
       	}); 
	}	
	
	/**
	 * Cette methode remplit la liste des evenements que le panel va afficher
	 * @param c
	 */
	public void remplirAgenda(Calendar c) {
		try {
			Calendar cal2 = (Calendar)c.clone();
			largeurEvenement = this.getWidth()/4;
			hauteurLigne = this.getHeight()/(24-7); 
			ResultSet rs = null;
			int decalageEvenement = 50;
			for(int i=0;i<2;i++) { // la boucle va d'abord rechercher les reservations puis les taches 
				if(i==0) {
					rs = rq.getReservationsJour(cal2);
				}
				else {
					rs = rq.getTachesJour(cal2);
				}
				
				while(rs.next()) {
					
					String[] strDebut = rs.getString("dateDebut").split("-");
	          	  	String[] strFin = rs.getString("dateFin").split("-");
	          	  
	                Integer heureDebut = Integer.parseInt(rs.getString("heureDebut").split(":")[0]);
	                Integer heureFin = Integer.parseInt(rs.getString("heureFin").split(":")[0]);
	          
	                Integer jourDebut = Integer.parseInt(rs.getString("dateDebut").split("-")[0]);
	                Integer jourFin = Integer.parseInt(rs.getString("dateFin").split("-")[0]);
	                
	                // decalageMinuteDebut/Fin vont decaler l'evenements d'une demi-ligne afin de representer les possibles demi-heure  
	                int decalageMinuteDebut = ((Integer)Integer.parseInt(rs.getString("heureDebut").split(":")[1])) > 0 ? espacementHeure/2: 0; // on test la valeur des minutes de la colonne heure
			        int decalageMinuteFin = ((Integer)Integer.parseInt(rs.getString("heureFin").split(":")[1])) > 0 ? espacementHeure/2: 0; // aide syntaxe : int x = ( test ) ? valeur si true : valeur si false; 
	                int idEvenement = rs.getInt(1);
	                
	                int typeEvenement;
	                String couleur = "";
	                if(i==0) {
	                	couleur = rq.getCouleurReservation(idEvenement);
	                	typeEvenement = Evenement.Reservation; // cela permet à un evenement savoir si c'est une reservation ou une tache
	                }
	                else {
	                	couleur = rq.getCouleurTache(idEvenement);
	                	
	                	typeEvenement = Evenement.Tache;
	                }
	                //listeEvenements.add(new Evenement(decalageInitial, (heureDebut-6)*hauteurLigne, 150, hauteurLigne*(heureFin-heureDebut), idEvenement, couleur));
	               
	                if(!(jourDebut.equals(jourFin))) {
	                	if(((Integer)new Calendar.Builder().setDate(Integer.parseInt(strDebut[2]),Integer.parseInt(strDebut[1]),Integer.parseInt(strDebut[0])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))) {
	            		   listeEvenements.add(new Evenement(decalageInitial+decalageEvenement, ((heureDebut-6)*hauteurLigne)+decalageMinuteDebut, largeurEvenement, (hauteurLigne*(23-heureDebut))+decalageMinuteFin-decalageMinuteDebut, idEvenement, couleur, typeEvenement));
	                	} // commence ce jour et finit après
	                	else if(!((Integer)new Calendar.Builder().setDate(Integer.parseInt(strDebut[2]),Integer.parseInt(strDebut[1]),Integer.parseInt(strDebut[0])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))
	                			&& !((Integer)new Calendar.Builder().setDate(Integer.parseInt(strFin[2]),Integer.parseInt(strFin[1]),Integer.parseInt(strFin[0])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))) {
	                		listeEvenements.add(new Evenement(decalageInitial+decalageEvenement, ((7-6)*hauteurLigne)+decalageMinuteDebut, largeurEvenement, (hauteurLigne*(23-7))+decalageMinuteFin-decalageMinuteDebut, idEvenement, couleur, typeEvenement));
	        	   		} // commence avant et finit après ce jour
	                	else if(((Integer)new Calendar.Builder().setDate(Integer.parseInt(strFin[2]),Integer.parseInt(strFin[1]),Integer.parseInt(strFin[0])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))) {
	                		listeEvenements.add(new Evenement(decalageInitial+decalageEvenement, ((7-6)*hauteurLigne)+decalageMinuteDebut, largeurEvenement, (hauteurLigne*(heureFin-7))+decalageMinuteFin-decalageMinuteDebut, idEvenement, couleur, typeEvenement));
	                	} // commence avant et finit ce jour
	                }
	                else {
	                	listeEvenements.add(new Evenement(decalageInitial+decalageEvenement, ((heureDebut-6)*hauteurLigne)+decalageMinuteDebut, largeurEvenement, (hauteurLigne*(heureFin-heureDebut))+decalageMinuteFin-decalageMinuteDebut, idEvenement, couleur, typeEvenement));
	                }
	                	 decalageEvenement+=200; // on decale les evenements
				}
			}
			
 		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cette methode va repeindre le composant
	 * C'est elle qui va dessiner l'affichage des jours
	 */
	@Override
    public void paintComponent(Graphics g) {
		int nbHeure = this.getHeight()/(24-7);
    	super.paintComponent(g); // cette ligne est necessaire sur windows pour eviter un bug graphique
        setBackground(Color.white);
        g.setColor(Color.BLACK);
        //Antialiasing pour lisser les chiffres et lettres éventuels:
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);       
        for(int i=7; i<24; i++) { //affiche une colonne pour chaque jour de la semaine
        	g.drawRect(0, (i-7)*nbHeure, this.getWidth()-1, nbHeure);
        }
        for(int i=7; i<24; i++) { //affiche l'heure de 7 à 23h
            String heure = Integer.toString(i);
            g.drawString(heure + ":00", 0, (i-6)*nbHeure);//une heure = 30px           	
        }
        g.setColor(Color.DARK_GRAY);
        // méthode qui déssine les rectangles pour chaque évènement dans la liste:
        for (Evenement s : listeEvenements)      	
        	s.draw(g);  
	}

	public int getHauteurLigne() {
		return hauteurLigne;
	}


	public int getLargeurEvenement() {
		return largeurEvenement;
	}


	public List<Evenement> getListeEvenements() {
		return listeEvenements;
	}


	public int getLargeurColonneHeure() {
		return largeurColonneHeure;
	}


	public int getLargeurColonneJour() {
		return largeurColonneJour;
	}


	public int getEspacementHeure() {
		return espacementHeure;
	}


	public void setHauteurLigne(int hauteurLigne) {
		this.hauteurLigne = hauteurLigne;
	}


	public void setLargeurEvenement(int largeurEvenement) {
		this.largeurEvenement = largeurEvenement;
	}


	public void setListeEvenements(List<Evenement> listeEvenements) {
		this.listeEvenements = listeEvenements;
	}
}
