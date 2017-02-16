package fr.villalem.reservations;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static gestionagenda.GestionAgenda.rq;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
  
public class PanelAgenda extends JPanel {
  SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
  Date date = new Date();
   //créé une liste d'evenements de la semaine à afficher plus tard sous forme de rectangles:
  public ArrayList<Evenement> ListeEvenements = new ArrayList<>();
  
  public PanelAgenda() {
        super();
        this.setOpaque(true);
        this.setBackground(Color.red);
        //ListeEvenements.add(new Evenement(40, 40, 60, 120));//test
        Calendar cal = Calendar.getInstance();
      try {
          remplirTableau(cal);
      } catch (SQLException ex) {
          Logger.getLogger(PanelAgenda.class.getName()).log(Level.SEVERE, null, ex);
      }
        
  }
  private Calendar cal = Calendar.getInstance();
  private void remplirTableau(Calendar c) throws SQLException {
      Calendar cal2 = (Calendar)cal.clone();
      cal2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//met le calendrier à Lundi
      for (int i=0;i<7;i++) {
          ResultSet rs = rq.getReservationsJour(cal2);
        try {
              while(rs.next()) {
                  String dtDebut = new String();
                  String dtFin = new String();
                  dtDebut = rs.getObject("dateDebut").toString().substring(11,13);
                  dtFin = rs.getObject("dateFin").toString().substring(11,13);
                  int dtDebutInt = Integer.parseInt(dtDebut);
                  int dtFinInt = Integer.parseInt(dtFin);
                  System.out.println( (dtFinInt-6)*30 );
                  ListeEvenements.add(new Evenement(40+i*120, (dtDebutInt-6)*30, 60, (dtFinInt-dtDebutInt)*30));
              }
          } catch (SQLException ex) {
              Logger.getLogger(PanelAgenda.class.getName()).log(Level.SEVERE, null, ex);
          }
        cal2.add(Calendar.DATE, 1);
      }
      
  }
  
  public void setDate(Date date) {
    this.date = date;
  }
  public void paintComponent(Graphics g) {
    //cette méthode construit l'arrière plan de l'agenda, avec une colonne par jour de la semaine
    setBackground(Color.white);
    this.repaint();
    //Antialiasing pour lisser les chiffres et lettres éventuels:
    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    int hauteurFenetre = this.getHeight();
    g.drawRect(0, 0, 40, hauteurFenetre);//première colonne où on placera les heures de 7 à 23h
    for(int i=7; i<24; i++) { //affiche l'heure de 7 à 23h
        String heure = Integer.toString(i);
        g.drawString(heure + ":00", 0, (i-6)*30);//une heure = 30px
    }
    for(int i=0; i<7; i++) { //affiche une colonne pour chaque jour de la semaine
        g.drawRect(i*120+40, 0, 120, hauteurFenetre);
    }
    
    for (Evenement s : ListeEvenements) {
        s.draw(g);
    }
    

    
  } 


}