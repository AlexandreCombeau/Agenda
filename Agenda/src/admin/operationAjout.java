package admin;

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

	@Override
	public void recevoirId(int id) {
		// TODO Auto-generated method stub
		
	}

}
