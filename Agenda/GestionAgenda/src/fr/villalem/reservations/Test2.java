/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villalem.reservations;

/**
 *
 * @author alexis
 */
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Test2 extends JPanel { 
  public void paintComponent(Graphics g){
    g.setColor(Color.ORANGE);
    g.fillRect(200, 160, 75, 150);
    
  }               
}
