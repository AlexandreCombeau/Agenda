package admin;

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

public interface Ioperation {
	public void operationResa (String Datedebut, String Datefin, String Heuredebut, String Heurefin, int nbPersonne, double nbHeure, int idClient, int idFormule);
	public void operationSalleResa (int idReservation, int info) throws SQLException;
	public void operationChoix(int idReservation, List<Integer> choix) throws SQLException;
	public void operationUser(String nom, String prenom, String email, int admin, String login, String mdp);
	public void operationTache(String nom, String hex, String comment, String dateDebut, String dateFin, String horaireDebut, String horaireFin);
	public void operationSalle(String name, int superficie, String couleur, String comment) throws SQLException;
	public void operationClient(String nom, String prenom, String email, String phone, String adresse, String entite, String comment) throws SQLException;
	public void recevoirId(int id);
}
