package villalem.reservations;

import static villalem.gestion.GestionAgenda.rq;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Frame;
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

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelJour extends JPanel {
	
	private int hauteurLigne;
	private int largeurEvenement;
	private int decalageInitial = 50;
	private List<Evenement> listeEvenements;
	
    private final int largeurColonneHeure = 40;//largeur de la première colonne, en px
    private final int largeurColonneJour = 140;//largeur de chaque colonne jour, en px
    private final int espacementHeure = 40;//espacement vertical entre chaque heure, en px
    
    
	public PanelJour() {
		listeEvenements = new ArrayList<>();	
		this.setPreferredSize(new Dimension(1000, 1700));
		this.addMouseListener(new MouseAdapter() {
	    	   public void mouseClicked(MouseEvent e) {
	    		   for(Evenement evt:listeEvenements) {
	    			   if(evt.contain(e.getPoint())) {
	    				   affichePopup(evt);
	    			   }
	    		   }
	    	   }
	       }); 
	}	
	
	private void affichePopup(Evenement evt) {
    	String message ="";
		try {
			message = rq.getInfoEvenement(evt.getId());
			if(rq.getEstReservation(evt.getId())) {
				message = rq.getInfoReservation(evt.getId());
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		JOptionPane pane = new JOptionPane(message);
		JDialog d = pane.createDialog((Frame)null, "Information sur l'événement");
		
		d.setLocationRelativeTo(this.getParent());
		
		
		d.setVisible(true);
		//JOptionPane.showMessageDialog(this, message);
    }
	
	public void remplirAgenda(Calendar c) {
		try {
			Calendar cal2 = (Calendar)c.clone();
			largeurEvenement = this.getWidth()/3;
			hauteurLigne = this.getHeight()/(24-7); 
			ResultSet rs = rq.getEvenementsJour(c);
			int decalageEvenement = 0;
			while(rs.next()) {
				
				String[] strDebut = rs.getString("dateDebut").split("-");
          	  	String[] strFin = rs.getString("dateFin").split("-");
          	  
                Integer heureDebut = Integer.parseInt(rs.getString("heureDebut").split(":")[0]);
                Integer heureFin = Integer.parseInt(rs.getString("heureFin").split(":")[0]);
          
                Integer jourDebut = Integer.parseInt(rs.getString("dateDebut").split("-")[2]);
                Integer jourFin = Integer.parseInt(rs.getString("dateFin").split("-")[2]);
                
                int idEvenement = rs.getInt("evenement.id");
                String couleur = rq.getCouleurEvenement(idEvenement);
                System.out.println(heureDebut+"-"+heureFin);
                //listeEvenements.add(new Evenement(decalageInitial, (heureDebut-6)*hauteurLigne, 150, hauteurLigne*(heureFin-heureDebut), idEvenement, couleur));
                if(!(jourDebut.equals(jourFin))) {
                	if(((Integer)new Calendar.Builder().setDate(Integer.parseInt(strDebut[0]),Integer.parseInt(strDebut[1]),Integer.parseInt(strDebut[2])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))) {
            		   listeEvenements.add(new Evenement(decalageInitial, (heureDebut-6)*hauteurLigne, 150, hauteurLigne*(23-heureDebut), idEvenement, couleur));
                	} // commence ce jour et finit après
                	else if(!((Integer)new Calendar.Builder().setDate(Integer.parseInt(strDebut[0]),Integer.parseInt(strDebut[1]),Integer.parseInt(strDebut[2])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))
                			&& !((Integer)new Calendar.Builder().setDate(Integer.parseInt(strFin[0]),Integer.parseInt(strFin[1]),Integer.parseInt(strFin[2])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))) {
                		listeEvenements.add(new Evenement(decalageInitial, (7-6)*hauteurLigne, 150, hauteurLigne*(23-7), idEvenement, couleur));
        	   		} // commence avant et finit après ce jour
                	else if(((Integer)new Calendar.Builder().setDate(Integer.parseInt(strFin[0]),Integer.parseInt(strFin[1]),Integer.parseInt(strFin[2])).build().get(Calendar.DAY_OF_MONTH)).equals(cal2.get(Calendar.DAY_OF_MONTH))) {
                		listeEvenements.add(new Evenement(decalageInitial, (7-6)*hauteurLigne, 150, hauteurLigne*(heureFin-7), idEvenement, couleur));
                	} // commence avant et finit ce jour
                }
                else {
                	listeEvenements.add(new Evenement(decalageInitial, (heureDebut-6)*hauteurLigne, 150, hauteurLigne*(heureFin-heureDebut), idEvenement, couleur));
                }
                	 decalageEvenement++;
				
			}
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	@Override
    public void paintComponent(Graphics g) {
		int nbHeure = this.getHeight()/(24-7);
    	super.paintComponent(g);
        
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
        /**
         * méthode qui déssine les rectangles pour chaque évènement dans la liste:
         */   
        for (Evenement s : listeEvenements) {       	
        	s.draw(g);  
        	
        }
	}
	
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

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
