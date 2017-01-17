package fr.villalem.reservations;

public class Reservation {
	
	/*
	 * DÉCLARATION DES VARIABLES
	 */
	private int idReservation; //incrémenter soit même
	private boolean valide; //Sert à savoir si une réservation est définitive
	private String dateDebut, dateFin;
	private int nombrePersonne; //Sert à savoir le nombre de personne pour ensuite connaitre la capacité
	
	/*
	 * CONSTRUCTEURS
	 */
	
	//Sans paramétres
	public Reservation(){
		
	}
	
	public Reservation(boolean valide, String dateDebut, String dateFin, int nombrePersonne){
		this.valide = valide;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nombrePersonne = nombrePersonne;
	}

	
	/*
	 * MÉTHODES
	 */
	
	@Override
	public String toString() {return "Reservation [idReservation=" + idReservation + ", valide=" + valide + ", dateDebut=" + dateDebut+ ", dateFin=" + dateFin + ", nombrePersonne=" + nombrePersonne + "]";}
	
	/*
	 * GETTERS
	 */
	public int getIdReservation() {return idReservation;}

	public boolean isValide() {return valide;}

	public String getDateDebut() {return dateDebut;}

	public String getDateFin() {return dateFin;}

	public int getNombrePersonne() {return nombrePersonne;}

	/*
	 * SETTERS
	 */
	public void setIdReservation(int idReservation) {this.idReservation = idReservation;}

	public void setValide(boolean valide) {this.valide = valide;}

	public void setDateDebut(String dateDebut) {this.dateDebut = dateDebut;}

	public void setDateFin(String dateFin) {this.dateFin = dateFin;}

	public void setNombrePersonne(int nombrePersonne) {this.nombrePersonne = nombrePersonne;}
	
}
