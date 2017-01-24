/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionagenda;

import fr.villalem.labd.*;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Villalemons
 */
public class GestionAgenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connexion connexion = new Connexion("db/Agenda.db");
	BdDAO rq = new BdDAO(connexion);
		
	Scanner sc = new Scanner(System.in);
	System.out.println("Veuillez vous authentifier: ");
	System.out.println("*********************************************");
	System.out.println("Votre nom : ");
	String nom = sc.nextLine();
	System.out.println("Votre prénom : ");
	String prenom = sc.nextLine();
	if(rq.authentification(nom, prenom)){
		System.out.println("Vous êtes connecté "+nom+" "+prenom);
	}
	else{
		System.err.println("Erreur lors de l'authentification");
	}
		
        //connexion.close();
    }
    
}
