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

import javax.swing.JFrame;
import javax.swing.JPanel;
  
public class PanelAgenda extends JPanel {
  SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
  Date date = new Date();
   //créé une liste d'evenements de la semaine à afficher plus tard sous forme de rectangles:
  public ArrayList<Evenement> ListeEvenements = new ArrayList<Evenement>();
  
  public PanelAgenda() {
        super();
        this.setOpaque(true);
        this.setBackground(Color.red);
        ListeEvenements.add(new Evenement(20, 20, 60, 60));
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
        g.drawString(heure + ":00", 0, (i-6)*30);
    }
    for(int i=0; i<7; i++) { //affiche une colonne pour chaque jour de la semaine
        g.drawRect(i*120+40, 0, 120, hauteurFenetre);
    }
    
    for (Evenement s : ListeEvenements) {
        s.draw(g);
    }
    
    Calendar cal = Calendar.getInstance();
    ResultSet rs = rq.getReservationsLundi(cal);
    
  } 


}