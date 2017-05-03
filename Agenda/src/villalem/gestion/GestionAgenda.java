/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalem.gestion;


import static villalem.factures.Devis.creerDevis;
import static villalem.factures.Factures.creerFacture;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;

import villalem.labd.*;


/**
 *
 * @author Villalemons
 */
public class GestionAgenda {
	
    public static Connexion connexion = new Connexion("db/Agenda.db");
    public static BdDAO rq = new BdDAO(connexion);
    
    public static void main(String[] args) throws SQLException, IOException {
        
        new ITconnexion().setVisible(true);
        
        //creerFacture();
        //creerDevis();
        //connexion.close();
    }
    
}
