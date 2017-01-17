package fr.villalem.clientfac;

public class Client {
	
	/*
	 * DÉCLARATION DES VARIABLES
	 */
	private int id;
	private String nom, prenom, entite, telephoneFix, telephoneMobile ,email;
	
	/*
	 * CONSTRUCTEURS
	 */
	//Constructeur vide
	public Client(){
		
	}
	
	/**
	 * Constructeur demandant tout les paramétres
	 * @param nom
	 * @param prenom
	 * @param entite
	 * @param telephoneFix
	 * @param telephoneMobile
	 * @param email
	 */
	public Client(String nom, String prenom, String entite, String telephoneFix, String telephoneMobile, String email){
		this.nom = nom;
		this.prenom = prenom;
		this.entite = entite;
		this.telephoneFix = telephoneFix;
		this.telephoneMobile = telephoneMobile;
		this.email = email;
	}
	
	/*
	 * METHODES
	 */
	@Override
	public String toString() {return "Client [nom=" + nom + ", prenom=" + prenom + ", entite=" + entite + ", telephoneFix=" + telephoneFix+ ", telephoneMobile=" + telephoneMobile + ", email=" + email + "]";}
	
	/*
	 * GETTERS
	 */
	public int getId() {return id;}

	public String getNom() {return nom;}

	public String getPrenom() {return prenom;}

	public String getEntite() {return entite;}

	public String getTelephoneFix() {return telephoneFix;}

	public String getTelephoneMobile() {return telephoneMobile;}

	public String getEmail() {return email;}

	
	/*
	 * SETTERS
	 */
	public void setId(int id) {this.id = id;}

	public void setNom(String nom) {this.nom = nom;}

	public void setPrenom(String prenom) {this.prenom = prenom;}

	public void setEntite(String entite) {this.entite = entite;}

	public void setTelephoneFix(String telephoneFix) {this.telephoneFix = telephoneFix;}

	public void setTelephoneMobile(String telephoneMobile) {this.telephoneMobile = telephoneMobile;}

	public void setEmail(String email) {this.email = email;}
	

}
