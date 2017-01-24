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
    
    public static Connexion connexion = new Connexion("db/Agenda.db");
    public static BdDAO rq = new BdDAO(connexion);
    
    public static void main(String[] args) throws SQLException {
        
        new ITconnexion().setVisible(true);
		
        //connexion.close();
    }
    
}
