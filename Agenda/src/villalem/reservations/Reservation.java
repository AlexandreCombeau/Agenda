package villalem.reservations;

import java.sql.ResultSet;
import java.sql.SQLException;



public class Reservation {
	
	private String heureDebut;
	private String heureFin;
	
	private String dateDebut;
	private String dateFin;
	
	private String nomClient;
	private String typeActivité;
	private String nomEntreprise;
	private String salles;
	
	
	private Integer idReservation;
	private int estValide;
	private int nbPersonnes;
	private int nbHeures;
	private int idClient;
	private int idFormules;
	private int idOptions;
	
	public Reservation(ResultSet rs) {
		try {
			this.heureDebut = rs.getString("heureDebut");
			this.heureFin = rs.getString("heureFin");
			this.dateDebut = rs.getString("dateDebut");
			this.dateFin = rs.getString("dateFin");
			this.idReservation = rs.getInt("reservation");
			this.estValide = rs.getInt("estValide");
			this.nbPersonnes = rs.getInt("nbPersonnes");//
			this.nbHeures = rs.getInt("nbHeures");//
			this.idClient = rs.getInt("client");
			this.idFormules = rs.getInt("formule");
			this.nomClient = rs.getString("clients.nom")+" "+rs.getString("clients.prenom");
			this.typeActivité = rs.getString("clients.entite");
			this.nomEntreprise = "";
			this.salles = rs.getString("salles.nom");
			this.idOptions = rs.getInt("options");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void init(String idReservation) {
		
	}
	
	/**
	 * Exemple de presentation :
	 * 
	 * Colin - Developpement personnel - Association *en gras et de la couleur de la salle
	 * Date :
	 * 			Samedi 6 Avril 2017, 9h30 - 17h
	 * Salle :
	 * 			Studio
	 * Descriptif :
	 * 			blablablablablabla
	 * 			option 1, option 2
	 * 
	 * 
	 */
	public String toString() {
		String str="";
		
		/*
		String titre = this.nomClient+" - "+this.typeActivité+" - "+this.nomEntreprise+"\n";
		String contenu = "Date :\n\t"+dateDebut+", "+heureDebut;
		if(dateDebut != dateFin)
			contenu+=" à "+dateFin+", "+heureFin;
		contenu+="\n";
		String salle = salles+"\n";
		String desctription = optionLibelle+"\n\t"+optionDescriptif; 
		
		str+=titre+contenu+salle+desctription;*/
		return this.idReservation.toString();
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public String getNomClient() {
		return nomClient;
	}

	public String getTypeActivité() {
		return typeActivité;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public String getSalles() {
		return salles;
	}

	public Integer getIdReservation() {
		return idReservation;
	}

	public int getEstValide() {
		return estValide;
	}

	public int getNbPersonnes() {
		return nbPersonnes;
	}

	public int getNbHeures() {
		return nbHeures;
	}

	public int getIdClient() {
		return idClient;
	}

	public int getIdFormules() {
		return idFormules;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public void setTypeActivité(String typeActivité) {
		this.typeActivité = typeActivité;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public void setSalles(String salles) {
		this.salles = salles;
	}

	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}

	public void setEstValide(int estValide) {
		this.estValide = estValide;
	}

	public void setNbPersonnes(int nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}

	public void setNbHeures(int nbHeures) {
		this.nbHeures = nbHeures;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public void setIdFormules(int idFormules) {
		this.idFormules = idFormules;
	}
	


	
	
	


}
