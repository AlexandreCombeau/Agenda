package admin;

import static gestionagenda.GestionAgenda.rq;

import java.sql.SQLException;
import java.util.ArrayList;
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
	
	
	public void validation(int resadispo, String dateDebut, String dateFin, String horaireDebut, String horaireFin, String nbParticipants, double nbHeures, int idClient, int idFormule, int[] idinfosalle, String[][] OS, int[] nbPersonnes) throws SQLException{
			if(resadispo==0 || resadispo==id){  
				//int[] idsalleresa = null;
            
            if(id!=0){
        	    Io.recevoirId(id);
        	    //idsalleresa = rq.getListedIntsById("salleResa", "idSalleResa", id, "fkidReservation");
            }
            
            Io.operationResa(dateDebut,dateFin,horaireDebut+":00",horaireFin+":00",Integer.parseInt(nbParticipants),nbHeures, idClient, idFormule);
              
            int idReservation = rq.getIdByIdString("reservation", "idReservation", dateDebut, "dateDebut", idClient, "fkidClient");
            
            //int[] idsalleresa = rq.getListedIntsById("salleResa", "idSalleResa", idReservation, "fkidReservation");
            
            
            int[] idSR= new int[3];
            for (int i=0;i<3;++i){
            	
            	if(idinfosalle[i]!=0){
            	//Io.operationSalleResa(nbPersonnes, idReservation, idinfosalle);
            	//List<Integer> listeOS = new ArrayList<Integer>();
            	for(int j=0; j<12;++j){
            		//System.out.println("i= "+i);
            		//System.out.println("j= "+j);
            		//System.out.println(OS[j][i]);
            		if(!OS[j][i].equals("Aucune")){
            		//OS[j][i]=(Integer.toString(rq.getIdOptionService(OS[j][i])));
            		//System.out.println(OS[j][i]);
            		}
            		
            	}
            	
            	//Io.operationSalleResa(nbPersonnes, idReservation, idinfosalle[i]);
            	
            	}
            	
            }
            
            Io.operationSalleResa(nbPersonnes, idReservation, idinfosalle);
            for (int i=0;i<3;++i){
            	if(idinfosalle[i]!=0){
            		idSR[i]=rq.getIdByTwoInt("salleResa", "idSallesResa", idReservation, "fkidReservation", idinfosalle[i], "fkidInfoSalle");
            	}
            }
            
			Io.operationChoix(OS, idSR);
            //Io.operationChoix(idReservation, listeOS);
            
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
