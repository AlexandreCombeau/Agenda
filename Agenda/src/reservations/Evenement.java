
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservations;

import static gestionagenda.GestionAgenda.rq;

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
import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.JOptionPane;


/**
 * Cette classe represente un evenement inscris dans l'agenda 
 * Un evenement est une representation graphique 
 * Il possède des coordonnees ainsi que d'autre attribut du même type qui permettent sa representation 
 * @author Alexandre
 *
 */
public class Evenement extends JComponent {
	
	/**
	 * Id permettant la serialization de cette classe
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attribut static qui definit la valeur d'une reservation
	 */
	public final static int Reservation = 1;
	
	/**
	 * Attribut static qui definit la valeur d'une tache
	 */
    public final static int Tache = 0;
    
    /**
     * Definit le type de cette classe, soit c'est une reservation soit c'est une tache, 
     * on utilisera @see {@link Evenement}{@link #Reservation} ou @see {@link Evenement}{@link #Reservation}
     */
    private int typeEvenement;
    
    /**
     * ID de la reservation ou tache dans la base de données, ce champs permet de faire des requêtes dans celle-ci
     */
    private Integer id;
    
    /**
     * Contient la valeur hexadecimal d'une couleur
     * Cette couleur est être celle de la representation graphique
     */
    private String couleur;
    
    /**
     * Cette element va representer sur l'agenda la durée ainsi que le type de l'evenement
     */
    private Graphics2D rectangle;

    public Evenement(int x, int y, int width, int height, int id, String couleur, int typeEvenement) {
        this.setBounds(x, y, width, height);
        this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setLocation(x, y);	
        this.id = id;       
        this.couleur = couleur;
        this.typeEvenement = typeEvenement;
        }

    
    /**
     * Cette methode affiche une petite fenetre qui indique toutes les informations utile selon un id
     */
    public void affichePopup() {
		String message ="";
		try {
			switch(typeEvenement) { // les informations affichés sont differentes en fonction du type de l'evenement
			case Reservation:
				message = ConvertisseurInfos.convertReservation(rq.getInfosReservation(id)); 
				break;
			case Tache:
				message = ConvertisseurInfos.convertTache(rq.getInfosTache(id));
				break;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this.getParent(), message);
    }

    /**
     * Cette methode va representer l'evenement sur l'agenda 
     * @param g
     */
    public void draw(Graphics g) {
    	super.paintComponent(g);
    	rectangle = (Graphics2D) g;
    	rectangle.setColor(Color.decode(couleur));
    	rectangle.fillRect(getX(), getY(), getWidth(), getHeight());
    	rectangle.setStroke(new BasicStroke(1));        
    	rectangle.setColor(new Color(0, 0, 0));
    	// syntaxe : rectangle.drawRect( coord x, coord y, largeur, hauteur )
    	rectangle.drawRect(getX(), getY(), getWidth(), getHeight()); 
    	try {
	    	switch(typeEvenement) {
	    	case Reservation :	
	    		rectangle.drawString(rq.getPrenomClient(id), this.getX()+3, this.getY()+15);
	    		rectangle.drawString(rq.getNomClient(id), this.getX()+3, this.getY()+25);
	    		break;
	    	case Tache : 
	    		rectangle.drawString(rq.getNomTache(id), this.getX()+3, this.getY()+15);
	    		rectangle.drawString(rq.getCommentTache(id), this.getX()+3, this.getY()+25);
	    		break;
	    	}
    	}catch (SQLException e){
    		e.printStackTrace();
    	}
    		      
    } 
    
    /**
     * Cette methode test si un Point p est dans contenu dans ce composant
     * @param p
     * @return
     */
    public boolean contain(Point p){
        return (this.getLocation().getX() < p.getX() && this.getLocation().getY() < p.getY() &&
                this.getLocation().getX() + this.getWidth() > p.getX()  &&
                this.getLocation().getY() + this.getHeight() > p.getY());
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
