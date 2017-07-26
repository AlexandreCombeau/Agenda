package admin;

import static gestionagenda.GestionAgenda.rq;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import database.operationAjout;
import database.operationModif;

public class planningNotRec extends AbstractPlanning{
	
	public static planningNotRec creerFenetre (int id){
		planningNotRec modif = new planningNotRec();
		modif.id=id;

		return modif;
	}
	
	public static planningNotRec creerFenetre (){
		planningNotRec modif = new planningNotRec();
		modif.id=0;

		return modif;
	}
	
	public planningNotRec(){
		super();
	}
	
	
	public void validation(int resadispo, String[] date, String horaireDebut, String horaireFin, String nbParticipants, double nbHeures, int idClient, int idFormule, int[] idinfosalle, String[][][] OS) throws SQLException{
		 
		if(resadispo!=id){  
        		
            
            
                JOptionPane.showMessageDialog(null, "Votre reservation a bien ete creee !");
        	}
        	else{
        		JOptionPane.showMessageDialog(null, "Salle deja reservee pour ce creneau");
        	}
	}
	
	
}
