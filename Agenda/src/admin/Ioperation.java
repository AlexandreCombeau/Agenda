package admin;

import java.sql.SQLException;
import java.util.List;

import database.operationAjout;
import database.operationModif;

public interface Ioperation {
	public void operationResa (String Datedebut, String Datefin, String Heuredebut, String Heurefin, int nbPersonne, double nbHeure, int idClient, int idFormule);
	public void operationSalleResa (int[][] nbPersonnes, int idReservation, int[][] info,String[][] horaireDebut, String[][] horaireFin, String[] date, int nbDate, double[][] nbHeures, int[][] formule) throws SQLException;
	public void operationChoix(String[][][] choix, int[] salleresa) throws SQLException;
	public void operationUser(String nom, String prenom, String email, int admin, String login, String mdp);
	public void operationTache(String nom, int type, String comment, String dateDebut, String dateFin, String horaireDebut, String horaireFin);
	public void operationSalle(String name, int superficie, String couleur, String comment) throws SQLException;
	public void operationClient(String nom, String prenom, String email, String phone, String adresse, String entite, String comment) throws SQLException;
	public void operationTypeTache(String nom, String couleur);
	public void recevoirId(int id);
	
	
	public static Ioperation getIo(int i){
		if (i==0) return new operationAjout();
		else if (i==1) return new operationModif();
		else return null;
	}
}
