/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villalem.reservations;

import static gestionagenda.GestionAgenda.rq;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexis
 */
public class PanelAgenda extends javax.swing.JPanel {

    public ArrayList<Evenement> ListeEvenements = new ArrayList<>();
    private final Calendar cal = Calendar.getInstance();
    private final int largeurColonneHeure = 40;//largeur de la première colonne, en px
    private final int largeurColonneJour = 140;//largeur de chaque colonne jour, en px
    private final int espacementHeure = 30;//espacement vertical entre chaque heure, en px
    private final int largeurEven = 60;
    /**
     * Creates new form PanelAgenda
     */
    public PanelAgenda() {
        initComponents();
        //remplirTableau(cal); // rempli le tableau avec les reservations, à commenter pour éviter le bug d'affichage
    }
    /**
     * remplirTableau ne fonctionne que quand l'appli est lancée. Pour éditer
     * le ITAgenda en mode Design, il faut mettre la fonction en commentaire,
     * puis relancer NetBeans.
     * Sinon, une erreur d'affichage sera lancée, il faut l'ignorer et ne pas
     * toucher au design.
     * 
     * @param c
     */
    // in work <tag>
    
    public void remplirAgenda(Calendar c) { 
      //copie le calendrier, sinon le calendrier 'c' changerait de date à cause de sa manipulation dans BdDAO.java avec rq.getReservationsJour()
      Calendar cal2 = (Calendar)c.clone();
     
      cal2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//met le calendrier à Lundi pour commencer la semaine
      for (int i=0;i<7;i++) { //pour chaque jour de la semaine (de 0 à 6)
          ResultSet rs; //initialise le ResultSet
          
          try {
              rs = rq.getReservationsJour(cal2);
              if(rs != null) { // on test pour determiner si il existe une reservation pour la date de cal2
            try {
                  while(rs.next()) { //obtient chaque réservation de la journée
                      //obtient l'heure de début et de fin (il faudra faire en sorte d'inclure les minutes)
                      String dtDebut = rs.getObject("heureDebut").toString().split(":")[0];
                      String dtFin = rs.getObject("heureFin").toString().split(":")[0];
                      
                      String jourDebut = rs.getObject("dateDebut").toString().split("-")[0];
                      String jourFin = rs.getObject("dateFin").toString().split("-")[0];
                      System.out.println(dtDebut+"\t"+dtFin);
                      
                      //transforme en int:
                      Integer jourDebutInt =  Integer.parseInt(jourDebut);
                      Integer jourFinInt = Integer.parseInt(jourFin);
                      
                      int dtDebutInt = Integer.parseInt(dtDebut); 
                      int dtFinInt = Integer.parseInt(dtFin);
                      System.out.println( (dtFinInt-6)*espacementHeure ); //à supprimer                                            
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
                      ListeEvenements.add(new Evenement(1+largeurColonneHeure+i*largeurColonneJour,
                        (dtDebutInt-6)*espacementHeure, largeurEven, (dtFinInt-dtDebutInt)*espacementHeure));                          
                  }
              } catch (SQLException ex) {
                  Logger.getLogger(PanelAgenda.class.getName()).log(Level.SEVERE, null, ex);
              }
              }
                      } catch (SQLException ex) {
                  Logger.getLogger(PanelAgenda.class.getName()).log(Level.SEVERE, null, ex);
              }
        //ici, on incrémente le calendrier d'un jour pour obtenir les réservation du jour suivant.
        cal2.add(Calendar.DATE, 1);
      } 

  }
    /*
    public void remplirAgenda(Calendar c) {
        //copie le calendrier, sinon le calendrier 'c' changerait de date à cause de sa manipulation dans BdDAO.java avec rq.getReservationsJour()
      Calendar cal2 = (Calendar)c.clone();
      cal2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//met le calendrier à Lundi pour commencer la semaine
      
      ResultSet rs = rq.getReservationsSemaine(cal2);
      if(rs != null) {
          try {
              while(rs.next()) {
                  
              }
          } catch (SQLException ex) {
              Logger.getLogger(PanelAgenda.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }
*/

    @Override
    public void paintComponent(Graphics g) {
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
        for(int i=0; i<7; i++) { //affiche une colonne pour chaque jour de la semaine
            g.drawRect(i*largeurColonneJour+largeurColonneHeure, 0, largeurColonneJour, hauteurFenetre);
        }
        g.setColor(Color.DARK_GRAY);
        /**
         * méthode qui déssine les rectangles pour chaque évènement dans la liste:
         */
        for (Evenement s : ListeEvenements) {
            s.draw(g);
        }
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
