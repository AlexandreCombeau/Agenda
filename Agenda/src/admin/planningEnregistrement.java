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
	
	
	public void validation(int resadispo, String[] date, String[][] horaireDebut, String[][] horaireFin, String nbParticipants, double[][] nbHeures, int idClient, int[][] formule, int[][] idinfosalle, String[][][] OS, int[][] nbPersonnes) throws SQLException{
			if(resadispo==0 || resadispo==id){  
				//int[] idsalleresa = null;
            
            if(id!=0){
        	    Io.recevoirId(id);
        	    //idsalleresa = rq.getListedIntsById("salleResa", "idSalleResa", id, "fkidReservation");
            }
            System.out.println(horaireDebut[0][0]+" "+horaireFin[0][0]);
            Io.operationResa(date[0],date[nbDates-1],horaireDebut[0][0]+":00",horaireFin[0][0]+":00",Integer.parseInt(nbParticipants),nbHeures[0][0], idClient, formule[0][0]);
              
            int idReservation = rq.getIdByIdString("reservation", "idReservation", date[0], "dateDebut", idClient, "fkidClient");
            
            //int[] idsalleresa = rq.getListedIntsById("salleResa", "idSalleResa", idReservation, "fkidReservation");
            
            
            int[] idSR= new int[nbDates*3];
            //for(int j=0;j<nbDates;++j){
            //for (int i=0;i<3;++i){
            	
            	//if(idinfosalle[i][j]!=0){
            	//Io.operationSalleResa(nbPersonnes, idReservation, idinfosalle);
            	//List<Integer> listeOS = new ArrayList<Integer>();
            	//for(int j=0; j<12;++j){
            		//System.out.println("i= "+i);
            		//System.out.println("j= "+j);
            		//System.out.println(OS[j][i]);
            		//if(!OS[j][i].equals("Aucune")){
            		//OS[j][i]=(Integer.toString(rq.getIdOptionService(OS[j][i])));
            		//System.out.println(OS[j][i]);
            		//}
            		
            	//}
            	
            	//Io.operationSalleResa(nbPersonnes, idReservation, idinfosalle[i]);
            	
            	//}
            	
            //}
            
            
            Io.operationSalleResa(nbPersonnes, idReservation, idinfosalle, horaireDebut, horaireFin, date, nbDates, nbHeures, formule);
            for(int j=0;j<nbDates;++j){
            	for (int i=0;i<3;++i){
            		if(idinfosalle[i][j]!=0){
            			idSR[i+j*3]=rq.getIdByTwoIntOneString("salleResa", "idSallesResa", idReservation, "fkidReservation", idinfosalle[i][j], "fkidInfoSalle", date[j], "date");
            			System.out.println((i+j*3)+": "+idSR[i+j*3]);
            		}
            	}
            }
            for(int i=0; i<12;++i)for(int j=0;j<3;++j)for(int k=0;k<365;++k)if(!OS[i][j][k].equals("Aucune"))System.out.println(OS[i][j][k]);
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
