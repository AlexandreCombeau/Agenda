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
	public void recevoirId(int id);
}
