package fr.villalem.clientfac;

public class Facture {
	
	/*
	 * DÉCLARATION DES VARIABLES
	 */
	private int idFacture, idClient, idReservation; //Incrémenter l'id soit même
	private float montant;
	private String dateFacturation;
	//Voir pour avoir une variable "file" pour générer le document txt / odt en pdf
	
	/*
	 * CONSTRUCTEURS
	 */
	
	//Sans paramétres
	public Facture(){
		
	}
	
	/**
	 * Constructeur prenant tout les paramétres
	 * @param montant
	 * @param dateFacturation
	 */
	public Facture(float montant, String dateFacturation){
		this.montant = montant;
		this.dateFacturation = dateFacturation;
	}
	
	/*
	 * METHODES
	 */
	@Override
	public String toString() {return "Facture [id=" + idFacture + ", montant=" + montant + ", dateFacturation=" + dateFacturation + "]";}
	

	/*
	 * GETTERS
	 */
	public int getId() {return idFacture;}

	public float getMontant() {return montant;}

	public String getDateFacturation() {return dateFacturation;}
	
	/*
	 * SETTERS
	 */
	public void setId(int id) {this.idFacture = id;}

	public void setMontant(float montant) {this.montant = montant;}

	public void setDateFacturation(String dateFacturation) {this.dateFacturation = dateFacturation;}
	
	
	
	

}
