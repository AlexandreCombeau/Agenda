package fr.villalem.clientfac;

import java.util.ArrayList;

public class Entite {
	
	/*
	 * DÉCLARATION DES VARIABLES
	 */
	private int idEntite;
	private ArrayList<String>lesLibelles;
	
	/*
	 * CONSTRUCTEUR
	 */
	public Entite(){
		this.lesLibelles = new ArrayList<String>();
		this.lesLibelles.add("Entreprise");
		this.lesLibelles.add("Association");
		this.lesLibelles.add("Usager");
	}
	
	/*
	 * MÉTHODES
	 */
	/**
	 * La procédure afficherEntite() affiche à l'écran toutes les entités actuellement créées.
	 */
	public void afficherEntite(){
		for(int i=0 ; i<this.lesLibelles.size() ; i++){System.out.println("Identifiant : "+(i+1)+", catégorie : "+lesLibelles.get(i));}
	}
	
	/**
	 * La procédure ajouterEntite(String msg) prend en paramétre une catégorie d'entité et l'ajoute à la liste des entités existantes. 
	 * @param msg
	 */
	public void ajouterEntite(String msg){
		boolean test = true;
		for(int i=0 ; i<lesLibelles.size() ; i++){ //Vérifie si l'entité n'existe pas déjà
			if(lesLibelles.get(i).equals(msg)){
				System.out.println("Entité déjà existante et portant le numéro : "+(i+1));
				test = false;
			}
		}
		if(test == true){ //Si l'entité n'existe pas 
			lesLibelles.add(msg); 
			System.out.println("Nouvelle entité ajoutée !");	
		}
	}
	
	/**
	 * La procédure supprimerEntite(String  msg) prend en paramétre une catégorie d'entité et l'a supprime de la liste des entités existantes.
	 * @param msg
	 */
	public void supprimerEntite(String msg){
		boolean test = false;
		for(int i=0 ; i<lesLibelles.size() ; i++){
			if(lesLibelles.get(i).equals(msg)){
				lesLibelles.remove(i);
				test = true;
				System.out.println("Entité supprimée avec succès !");
			}
		}
		if(test == false){ //Si l'entité n'existe pas 
			System.out.println("L'entité que vous tentez de supprimer n'existe pas.");
		}
	}
	
	public String getLibelleEntite(int n){
		return lesLibelles.get(n+1);
	}

	
	/*
	 * GETTERS
	 */
	public int getIdEntite() {return idEntite;}

	public ArrayList<String> getLesLibelles() {return lesLibelles;}
	
	
	/*
	 * SETTERS
	 */
	public void setIdEntite(int idEntite) {this.idEntite = idEntite;}

	public void setLesLibelles(ArrayList<String> lesLibelles) {this.lesLibelles = lesLibelles;}
	
}
