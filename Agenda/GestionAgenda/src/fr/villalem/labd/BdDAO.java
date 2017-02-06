/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villalem.labd;
import fr.villalem.usager.Usager;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Villalemons
 */
public class BdDAO {
    
    	//Variables
	private Connexion co = null;
	private ResultSet rs = null;
	
	//Constructeur
	public BdDAO(Connexion uneCo){
		this.co = uneCo;
		this.co.connect();
	}
	
	//Accès à la BD
	public void nomClients() throws SQLException{
		String request = "Select nom From Client";
		rs = co.query(request);
		while(rs.next()){
			System.out.println("Nom du client : "+rs.getString("nom"));
		}
	}
	
	public boolean authentification(String log, String pwd) throws SQLException{
		String request = "Select login, password From Usager Where login='"+log+"' And password='"+pwd+"'";
		rs = co.query(request);
		if(rs.next()){
                    //co.close();
                    return true;
		}
		return false;
	}
        
        public Usager getUsager(String login, String pwd) throws SQLException{
            String request = "SELECT * FROM Usager WHERE login='"+login+"' AND password='"+pwd+"'";
            rs = co.query(request);
            
            int identifiant = rs.getInt("idUsager");
            int admin = rs.getInt("administrateur");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            
            Usager unUsager = new Usager(identifiant, admin, nom, prenom);
            
            return unUsager;
        }
        
        public void ajoutSalle(String name, int superficie, String couleur, String comment){
            String quest = "INSERT INTO Salle(nomSalle, superficie, codeCouleur, descriptif) VALUES('"+name+"', "+superficie+", '"+couleur+"', '"+comment+"')";
            rs = co.query(quest);
            System.out.println("Insertion réussie");
        }
        
        public boolean checkErreurAjoutSalle(String nomsalle, String couleur) throws SQLException{
            //Comparaison avec les données de la BD pour déterminer les doublons
            String quest1 = "SELECT * FROM Salle WHERE nomSalle = '"+nomsalle+"'";
            String quest2 = "SELECT * FROM Salle WHERE codeCouleur = '"+couleur+"'";
            if((rs = co.query(quest1)).next() || (rs = co.query(quest2)).next()){
                return false;
            }
            return true;   
        }
        
        public boolean checkErreurModif(String nomTable, String nom) throws SQLException{
            String quest = "SELECT * FROM "+nomTable+" WHERE nomSalle = '"+nom+"'";
            if(co.query(quest).next()){
                return false;
            }
            return true;
        }
        
        public String[] getSalle() throws SQLException{
            String quest = "SELECT * FROM Salle";
            String quest1 = "SELECT COUNT(idSalle) From Salle";
            rs = co.query(quest1);
            int longueurTableau = rs.getInt("COUNT(idSalle)");
            //longueurTableau = Integer.parseInt();
            int i = 0;
            String[] nom = new String[longueurTableau];
            rs = co.query(quest);
            while(rs.next()){
               String name = rs.getString("nomSalle");
               nom[i] = name;
               i++;
            }        
            return nom;
        }
        
        public void MAJnom(String nomTable, String ancienNomChamps, String nouveauNomChamps){
            String quest = "UPDATE "+nomTable+" SET nom"+nomTable+" = '"+nouveauNomChamps+"' WHERE nom"+nomTable+" = '"+ancienNomChamps+"'";
            co.query(quest);
            System.out.println("UPDATE REUSSIE");
        }
        
        public boolean MAJcodeCouleur(String nomTable, String nomSalle, String hex) throws SQLException{
            String quest = "UPDATE "+nomTable+" SET codeCouleur = '"+hex+"' WHERE nom"+nomTable+" = '"+nomSalle+"'";
            String quest1 = "SELECT * FROM "+nomTable+" WHERE codeCouleur = '"+hex+"'";
            // SELECT * FROM Salle WHERE nomSalle = 'nomSalle' AND codeCouleur = 'hex';
            rs = co.query(quest1);
            if(rs.next()){
                return false;
            }
            else{
                co.query(quest);
                System.out.println("UPDATE REUSSIE");
                return true; 
            }   
        }
        
        public void delete(String nomTable, String nomSalle){
            String request = "DELETE FROM "+nomTable+" WHERE nom"+nomTable+" = '"+nomSalle+"'";
            co.query(request);
            System.out.println("DELETE REUSSIE");
        }
        
        public Color hex2Rgb(String nomSalle) throws SQLException {
            String quest = "SELECT codeCouleur FROM Salle WHERE nomSalle = '"+nomSalle+"'";
            String hex = null;
            rs = co.query(quest);
            if(rs.next()){
                hex = rs.getString("codeCouleur");
            }
            return Color.decode(hex);
           }
        
        public ResultSet getReservations() {
            String request = "SELECT dateDebut, dateFin, nombrePersonnes, validation FROM Reservation";
            rs = co.query(request);
            return rs;
        }
        
        public boolean autreReservation(String dateDebut, String dateFin) throws SQLException {
            String request = "SELECT * FROM Reservation WHERE '" + dateDebut + "' >= dateDebut"
                    + " AND '" + dateDebut + "' < dateFin "
                    + " OR dateDebut >= '" + dateDebut + "'"
                    + " AND dateDebut < '" + dateFin + "'";
            rs = co.query(request);
            if (rs.next()) {
                return true;
            }
            return false;
        }
        
        public void ajoutUtilisateur(String nom, String prenom, String email, int admin, String login, String mdp){
            String request = "INSERT INTO Usager(nom, prenom, mail, administrateur, login, password) VALUES('"+nom+"','"+prenom+"','"+email+"',"+admin+",'"+login+"','"+mdp+"')";
            rs = co.query(request);
            System.out.println("Insertion du nouvel utilisateur "+prenom+" "+nom+" réussie !");
        }
        
        public boolean checkUtilisateur(String nom, String prenom, String login, String mdp) throws SQLException{
            String request = "SELECT * FROM Usager WHERE nom = '"+nom+"' AND prenom = '"+prenom+"'";
            rs = co.query(request);
            if(rs.next()){
                //Si il y a déjà un utilisateur avec le même nom et prénom, nous allons comparer ses identifiants
                if(rs.getString("login").equals(login) && rs.getString("password").equals(mdp)){
                   return false; 
                }
            }
            return true;
        }
        
        public boolean checkUtilisateurEmail(String email) throws SQLException{
            String request = "SELECT * FROM Usager WHERE mail = '"+email+"'";
            rs = co.query(request);
            if(rs.next()){
                return false;
            }
            return true;
        }
        
        public boolean checkUtilisateurLogin(String login) throws SQLException{
            String request = "SELECT * FROM Usager WHERE login = '"+login+"'";
            rs = co.query(request);
            if(rs.next()){
                return false;
            }
            return true;
        }
        
        public String[] getUtilisateur(String nom, String prenom) throws SQLException{
            String quest = "SELECT * FROM Usager WHERE nom = '"+nom+"' AND prenom = '"+prenom+"'";
            String[] user = new String[5];
            rs = co.query(quest);
            if(rs.next()){
               String email = rs.getString("mail");
               user[0] = email;
               String login = rs.getString("login");
               user[1] = login;
               String mdp = rs.getString("password");
               user[2] = mdp;  
            }        
            return user;
        }
        
        public String[] getNomUtilisateur() throws SQLException{
            String quest = "SELECT nom, prenom FROM Usager";
            String quest1 = "SELECT COUNT(idUsager) From Usager";
            rs = co.query(quest1);
            int longueurTableau = rs.getInt("COUNT(idUsager)");
            //longueurTableau = Integer.parseInt();
            int i = 0;
            String[] nom = new String[longueurTableau];
            rs = co.query(quest);
            while(rs.next()){
               String name = rs.getString("prenom")+" "+rs.getString("nom");
               nom[i] = name;
               i++;
            }        
            return nom;
        }
        
        public void MAJlogin(String ancienLogin, String nouveauLogin){
            String quest = "UPDATE Usager SET login = '"+nouveauLogin+"' WHERE login = '"+ancienLogin+"'";
            rs = co.query(quest);
            System.out.println("UPDATE REUSSIE");
        }
        
        public void MAJmdp(String ancienMdp, String nouveauMdp){
            String quest = "UPDATE Usager SET password = '"+nouveauMdp+"' WHERE password = '"+ancienMdp+"'";
            rs = co.query(quest);
            System.out.println("UPDATE REUSSIE");
        }
        
        public void MAJmail(String ancienMail, String nouveauMail){
            String quest = "UPDATE Usager SET mail = '"+nouveauMail+"' WHERE mail = '"+ancienMail+"'";
            rs = co.query(quest);
            System.out.println("UPDATE REUSSIE");
        }
    
}
