/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalem.reservations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
/**
 *
 * @author Villalemons
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Evenement extends JComponent {
    private int x;
    private int y;
    private int width;
    private int height;
    private Integer id;
    private String couleur;
    private Graphics2D rectangle;

    public Evenement(int x, int y, int width, int height, int id, String couleur) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setLocation(x, y);	
        this.id = id;       
        this.couleur = couleur;
    }

    public void draw(Graphics g) {
    	super.paintComponent(g);
    	rectangle = (Graphics2D) g;  	
    	rectangle.setColor(Color.decode(couleur));
    	rectangle.fillRect(getX(), getY(), getWidth(), getHeight());
    	rectangle.setStroke(new BasicStroke(1));        
    	rectangle.setColor(new Color(0, 0, 0));
    	rectangle.drawRect(getX(), getY(), getWidth(), getHeight());        
    	rectangle.drawString(id.toString(), x+20, y+15);   	      
    } 
    
    public boolean contain(Point p){
        return (this.getLocation().getX() < p.getX() && this.getLocation().getY() < p.getY() &&
                this.getLocation().getX() + this.getWidth() > p.getX()  &&
                this.getLocation().getY() + this.getHeight() > p.getY());
    }
    
    public String toString() {
		return ""; 	
    }
  

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

	public Graphics2D getRectangle() {
		return rectangle;
	}

	public void setRectangle(Graphics2D g2) {
		this.rectangle = g2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
