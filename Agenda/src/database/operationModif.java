package database;

import static gestionagenda.GestionAgenda.rq;
import reservations.Evenement;
import usager.Usager;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import admin.Ioperation;

public class operationModif implements Ioperation{
	int id;
	public void operationResa (String Datedebut, String Datefin, String Heuredebut, String Heurefin, int nbPersonne, double nbHeure, int idClient, int idFormule){
		
			
			rq.MAJReservation(id, Datedebut, Datefin, Heuredebut, Heurefin, nbPersonne, nbHeure, idClient, idFormule);
		
		
	}
	
	public void operationSalleResa (int[] nbPersonnes, int idReservation, int[] info) throws SQLException{
		rq.deleteSalleResa(idReservation);
		for(int i=0;i<3;++i){
			if(nbPersonnes[i]!=0){
				rq.ajoutSalleResa(nbPersonnes[i], idReservation, info[i]);
			}
		}
		
		
	}
	
	public void operationChoix(int idReservation, String[][] choix, int[] salleresa) throws SQLException{
		rq.deleteChoix(idReservation);
		for(int i=0;i<3;++i){
			if(salleresa[i]!=0){
				for(int j=0;j<12;++j){
					System.out.println(choix[j][i]);
					if(!choix[j][i].equals("Aucune")){
						rq.ajoutChoix(idReservation, Integer.parseInt(choix[j][i]), salleresa[i]);
					}
				}
			}
		}
	}
	
	public void operationUser(String nom, String prenom, String email, int admin, String login, String mdp){
		rq.MAJUtilisateur(id, nom, prenom, email, admin, login, mdp);
	}
	
	public void operationTache(String nom, int type, String comment, String dateDebut, String dateFin, String horaireDebut, String horaireFin){
		rq.MAJTache(id,nom, type, comment, dateDebut, dateFin, horaireDebut, horaireFin);
	}
	
	public void operationSalle(String name, int superficie, String couleur, String comment) throws SQLException{
		rq.MAJSalle(id, name, superficie, couleur, comment);
	}
	
	public void operationClient(String nom, String prenom, String email, String phone, String adresse, String entite, String comment) throws SQLException{
		rq.MAJClient(id, nom, prenom, email, phone, adresse, entite, comment);
	}
	
	public void operationTypeTache(String nom, String couleur){
		rq.MAJTypeTache(id, nom, couleur);
	}
	
	@Override
	public void recevoirId(int id) {
		this.id=id;
	}

}