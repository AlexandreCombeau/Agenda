package admin;

import static gestionagenda.GestionAgenda.rq;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import database.operationAjout;
import database.operationModif;

public class planningEnregistrement extends AbstractPlanning{
	public Ioperation Io;
	
	public static planningEnregistrement creerFenetre (int id){
		planningEnregistrement modif = new planningEnregistrement();
		modif.id=id;
		modif.Io = Ioperation.getIo(1);
		return modif;
	}
	
	public static planningEnregistrement creerFenetre (){
		planningEnregistrement modif = new planningEnregistrement();
		modif.id=0;
		modif.Io = Ioperation.getIo(0);
		return modif;
	}
	
	public planningEnregistrement(){
		super();
	}
	
	
	public void validation(int resadispo, String dateDebut, String dateFin, String horaireDebut, String horaireFin, String nbParticipants, double nbHeures, int idClient, int idFormule, int idinfosalle, List<Integer> listeOS) throws SQLException{
			if(resadispo!=id){  
        		
            
            if(id!=0){
        	    Io.recevoirId(id);
            }
            Io.operationResa(dateDebut,dateFin,horaireDebut+":00",horaireFin+":00",Integer.parseInt(nbParticipants),nbHeures, idClient, idFormule);
        
            int idReservation = rq.getIdByIdString("reservation", "idReservation", dateDebut, "dateDebut", idClient, "fkidClient");
        
            Io.operationSalleResa(idReservation, idinfosalle);
            Io.operationChoix(idReservation, listeOS);
            
            //SI IL EXISTE UNE DEUXIEME SALLE
            /*String existe = (String)cbSalle2.getSelectedItem();
            if(!(existe.equals("Aucune"))){
               String nomSalle2 = (String)cbSalle2.getSelectedItem();
            }*/
                JOptionPane.showMessageDialog(null, "Votre reservation a bien ete creee !");
        	}
        	else{
        		JOptionPane.showMessageDialog(null, "Salle deja reservee pour ce creneau");
        	}
	}
	
	
}
