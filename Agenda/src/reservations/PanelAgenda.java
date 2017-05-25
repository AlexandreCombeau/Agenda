
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservations;

import static gestionagenda.GestionAgenda.rq;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Cette classe represente la vue semaine de l'agenda 
 * @author alexis
 */
public class PanelAgenda extends javax.swing.JPanel {

	/**
	 * Liste qui contient tous les evenements d'une semaine
	 */
    public List<Evenement> listeEvenements = new ArrayList<>();
    
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
    private final int espacementHeure = 30;
    
    /**
     * Largeur d'un evenement, en px
     */
    private final int largeurEven = 60;

    public PanelAgenda() {
    	initComponents();       
    	this.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
    			Collections.reverse(listeEvenements); // on inverse l'ordre de verification des evenements car graphiquement les derniers de la list sont ceux au premier plan
    												  // cette inversion permet donc que si on clique sur un evenement c'est bien celui sur lequel on clique qui va être appeler et non celui en dessous
    			if(listeEvenements.stream().filter(evt->evt.contain(e.getPoint())).findFirst().isPresent()) { // on test si l'evenement qui à capter notre clic existe bien
    				listeEvenements.stream().filter(evt->evt.contain(e.getPoint())).findFirst().get().affichePopup(); // on recupère cette evenement et on l'affiche
    			}
    		}
    	});        
    }
    
    /**
     * remplirTableau ne fonctionne que quand l'appli est lancée. Pour éditer
     * le ITAgenda en mode Design, il faut mettre la fonction en commentaire,
     * puis relancer NetBeans.
     * Sinon, une erreur d'affichage sera lancée, il faut l'ignorer et ne pas
     * toucher au design.
     * @param c
     */
   
    
    public void remplirAgenda(Calendar c) { 
      //copie le calendrier, sinon le calendrier 'c' changerait de date à cause de sa manipulation dans BdDAO.java avec rq.getReservationsJour()
      Calendar cal2 = (Calendar)c.clone();
    
      ResultSet rs = null; //initialise le ResultSet     
	  cal2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//met le calendrier à Lundi pour commencer la semaine   	  
      for (int i=0;i<7;i++) { //pour chaque jour de la semaine (de 0 à 6)
    	  int decalageEvenement = 3; // decale les evenemets sur le cote afin de pouvoir les differencier
    	  for(int j=0;j<2;j++) { 
    		  try {
    			  if(j==0) 
    				  rs = rq.getReservationsJour(cal2);
    			  else 
    				  rs = rq.getTachesJour(cal2);
				
    		  } catch (SQLException e) {
					e.printStackTrace();
				}
	          
	          if(rs != null) { // on test pour determiner si il existe une reservation pour la date de cal2
	        	  try {
				      while(rs.next()) { //obtient chaque réservation de la journée
				          //obtient l'heure de début et de fin (il faudra faire en sorte d'inclure les minutes)
				    	  String[] strDebut = rs.getString("dateDebut").split("-");
				    	  String[] strFin = rs.getString("dateFin").split("-");
				    	  
				          Integer heureDebut = Integer.parseInt(rs.getString("heureDebut").split(":")[0]);
				          Integer heureFin = Integer.parseInt(rs.getString("heureFin").split(":")[0]);
				    
				          Integer jourDebut = Integer.parseInt(rs.getString("dateDebut").split("-")[0]);
				          Integer jourFin = Integer.parseInt(rs.getString("dateFin").split("-")[0]);
				          
				          int decalageMinuteDebut = ((Integer)Integer.parseInt(rs.getString("heureDebut").split(":")[1])) > 0 ? espacementHeure/2: 0;
				          int decalageMinuteFin = ((Integer)Integer.parseInt(rs.getString("heureFin").split(":")[1])) > 0 ? espacementHeure/2: 0;
				          int idEvenement = rs.getInt(1);
				          int typeEvenement;
			              String couleur = "";
			              if(j==0) {
			            	  couleur = rq.getCouleurReservation(idEvenement);
			            	  typeEvenement = Evenement.Reservation;
			              }
			              else {
			            	  couleur = rq.getCouleurTache(idEvenement);
			            	  typeEvenement = Evenement.Tache;
			              }
				         // System.out.println(idEvenement+"-"+couleur+"-"+heureDebut+"-"+heureFin+"-"+jourDebut+"-"+jourFin);
				          /**
				           * Pour chaque reservation, créé un évènement qui est en fait un rectangle
				           * dont les coordonnées correspondent à l'heure de début et de fin, ainsi
				           * qu'à la journée.
				           * --pour l'abscisse 'x': le 1 est pour décaler d'un pixel, largeurColonneHeure est
				           * pour tenir compte de la premiere colonne, et i*largeurColonneJour est pour décaler
				           * de la largeur de chaque colonne 'jour'. Donc pour le lundi, celà vaut 0, car i=0.
				           * --pour l'ordonnée 'y': on enlève 6 à l'heure de début, car le graphique commence à 6h du matin
				           * (et non 7h, le 6 correspond à y=0). On multiplie par espacementHeure pour le faire déscendre.
				           * --La largeur est fixé arbitrairement pour l'instant.
				           * --La hauteur correspond à la différence entre l'heure de début et de fin, fois espacementHeure.
				          **/     
				          Evenement evenement = null;
				          if(!(jourDebut.equals(jourFin))) {
				        	 if(((Integer)new Calendar.Builder().setDate(Integer.parseInt(strDebut[2]),Integer.parseInt(strDebut[1]),Integer.parseInt(strDebut[0])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))) {
				        		 evenement = new Evenement(1+largeurColonneHeure+i*largeurColonneJour+decalageEvenement,
				                      ((heureDebut-6)*espacementHeure)+decalageMinuteDebut, largeurEven, ((23 - heureDebut)*espacementHeure)+decalageMinuteFin-decalageMinuteDebut,idEvenement,couleur, typeEvenement);
				        	  
				              } // commence ce jour et finit après
				              else if(!((Integer)new Calendar.Builder().setDate(Integer.parseInt(strDebut[2]),Integer.parseInt(strDebut[1]),Integer.parseInt(strDebut[0])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))
				            		  && !((Integer)new Calendar.Builder().setDate(Integer.parseInt(strFin[2]),Integer.parseInt(strFin[1]),Integer.parseInt(strFin[0])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))) {
				            	  evenement = new Evenement(1+largeurColonneHeure+i*largeurColonneJour+decalageEvenement,
				                         ((7-6)*espacementHeure)+decalageMinuteDebut, largeurEven, ((23 - 7)*espacementHeure)+decalageMinuteFin-decalageMinuteDebut,idEvenement,couleur, typeEvenement);  
				              } // commence avant et finit après ce jour
				              else if(((Integer)new Calendar.Builder().setDate(Integer.parseInt(strFin[2]),Integer.parseInt(strFin[1]),Integer.parseInt(strFin[0])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))) {
				            	  evenement = new Evenement(1+largeurColonneHeure+i*largeurColonneJour+decalageEvenement,
				                          ((7-6)*espacementHeure)+decalageMinuteDebut, largeurEven, ((heureFin - 7)*espacementHeure)+decalageMinuteFin-decalageMinuteDebut,idEvenement,couleur, typeEvenement);
				              } // commence avant et finit ce jour
				          }
				          
				          else {
				        	  evenement = new Evenement(1+largeurColonneHeure+i*largeurColonneJour+decalageEvenement,
				                     ((heureDebut-6)*espacementHeure)+decalageMinuteDebut, largeurEven, ((heureFin - heureDebut)*espacementHeure)+decalageMinuteFin-decalageMinuteDebut,idEvenement,couleur, typeEvenement);                          
				          } // commence et fini le même jour
				          decalageEvenement+=30;
				          listeEvenements.add(evenement);
				      }
				  } catch (SQLException ex) {
				      Logger.getLogger(PanelAgenda.class.getName()).log(Level.SEVERE, null, ex);
				  }
	          }
    	  }
        cal2.add(Calendar.DATE, 1);
      }
      

  }

    /**
     * Cette methode va dessiner les elements de ce panel
     */
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        //cette méthode construit l'arrière plan de l'agenda, avec une colonne par jour de la semaine
        setBackground(Color.white);
        g.setColor(Color.BLACK);
        //Antialiasing pour lisser les chiffres et lettres éventuels:
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int hauteurFenetre = this.getHeight();
        g.drawRect(0, 0, largeurColonneHeure, hauteurFenetre);//première colonne où on placera les heures de 7 à 23h
        for(int i=7; i<24; i++) { //affiche l'heure de 7 à 23h
            String heure = Integer.toString(i);
            g.drawString(heure + ":00", 0, (i-6)*espacementHeure);//une heure = 30px
        }
        for(int i=0; i<7; i++) //affiche une colonne pour chaque jour de la semaine
            g.drawRect(i*largeurColonneJour+largeurColonneHeure, 0, largeurColonneJour, hauteurFenetre);
        
        g.setColor(Color.DARK_GRAY);
        //méthode qui déssine les rectangles pour chaque évènement dans la liste:
        for (Evenement s : listeEvenements)    	
        	s.draw(g);  
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

