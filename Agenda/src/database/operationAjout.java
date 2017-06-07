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

public class operationAjout implements Ioperation{
	public void operationResa (String Datedebut, String Datefin, String Heuredebut, String Heurefin, int nbPersonne, double nbHeure, int idClient, int idFormule){
		rq.ajoutReservation(Datedebut, Datefin, Heuredebut, Heurefin, nbPersonne, nbHeure, idClient, idFormule);
	}
	
	public void operationSalleResa (int idReservation, int info){
		
		rq.ajoutSalleResa(idReservation, info);
		
	}
	
	public void operationChoix(int idReservation, List<Integer> choix) throws SQLException{
		for(int id : choix){
			rq.ajoutChoix(idReservation, id);
		}
	}
	
	public void operationUser(String nom, String prenom, String email, int admin, String login, String mdp){
		rq.ajoutUtilisateur(nom, prenom, email, admin, login, mdp);
	}
	
	public void operationTache(String nom, String hex, String comment, String dateDebut, String dateFin, String horaireDebut, String horaireFin){
		rq.ajoutTache(nom, hex, comment, dateDebut, dateFin, horaireDebut, horaireFin);
	}
	
	public void operationSalle(String name, int superficie, String couleur, String comment) throws SQLException{
		rq.ajoutSalle(name, superficie, couleur, comment);
	}
	
	public void operationClient(String nom, String prenom, String email, String phone, String adresse, String entite, String comment) throws SQLException{
		rq.ajoutClient(nom, prenom, email, phone, adresse, entite, comment);
	}

	@Override
	public void recevoirId(int id) {
		// TODO Auto-generated method stub
		
	}

}
