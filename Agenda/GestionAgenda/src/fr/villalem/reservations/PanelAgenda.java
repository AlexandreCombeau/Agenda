package fr.villalem.reservations;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
  
public class PanelAgenda extends JPanel {
  SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
  Date date = new Date();
  
  public PanelAgenda() {

        super();
        this.setOpaque(true);
        this.setBackground(Color.red);
  }
   

  public void setDate(Date date) {
    this.date = date;
  }
  public void paintComponent(Graphics g) {
    setBackground(Color.white);
    this.repaint();
    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    int hauteurFenetre = this.getHeight();
    g.drawRect(0, 0, 40, hauteurFenetre);
    for(int i=0; i<7; i++) {
        g.drawRect(i*120+40, 0, 120, hauteurFenetre);
    }
    for(int i=7; i<24; i++) {
        String heure = Integer.toString(i);
        g.drawString(heure + ":00", 0, (i-6)*30);
    }
    //((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    /*
    g.setColor(Color.white);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(Color.blue);
    
    g.drawString(month.format(date), 34, 36);
    g.setColor(Color.black);
    g.drawString(year.format(date), 235, 36);

    Calendar today = Calendar.getInstance();
    today.setTime(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.DATE, 1);
    cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK) + 1);
    for (int week = 0; week < 6; week++) {
      for (int d = 0; d < 7; d++) {
        Color col = Color.white;
          g.drawString(day.format(cal.getTime()), d * 30 + 46 + 4,
              week * 29 + 81 + 20);
        cal.add(Calendar.DATE, +1);
      }
    } */
  } 


}