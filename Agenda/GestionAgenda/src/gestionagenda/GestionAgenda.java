/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionagenda;


import static fr.villalem.factures.Devis.creerDevis;
import static fr.villalem.factures.Factures.creerFacture;
import fr.villalem.labd.*;
import java.sql.SQLException;


/**
 *
 * @author Villalemons
 */
public class GestionAgenda {

    public static Connexion connexion = new Connexion("db/Agenda.db");
    public static BdDAO rq = new BdDAO(connexion);
    
    public static void main(String[] args) throws SQLException {
        
        //new ITconnexion().setVisible(true);
        //creerFacture();
	creerDevis();
        //connexion.close();
    }
    
}
