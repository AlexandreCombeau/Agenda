/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villalem.labd;
import fr.villalem.reservations.Evenement;
import fr.villalem.usager.Usager;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Villalemons
 */
public class BdDAO {

    	/*
        ========================================================================
                                    VARIABLES
        ========================================================================
        */
	private Connexion co = null;
	private ResultSet rs = null;

	/*
        ========================================================================
                                    CONSTRUCTEUR
        ========================================================================
        */
	public BdDAO(Connexion uneCo){
		this.co = uneCo;
		this.co.connect();
	}

        /***********************************************************************
                                    METHODES
        ************************************************************************

        ========================================================================
                                    SELECTIONS DE DONNEES
        ========================================================================
        */

        /**
         *
         * @param login Reçois le login de l'utilisateur
         * @param pwd Reçois le mot de passe de l'utilisateur
         * @return Retourne l'utilisateur en question avec ses informations
         * @throws SQLException
         */
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

        /**
         *
         * @param table Prend le nom de la table : Salle || Tache
         * @return Retourne toutes les Salles ou toutes les Taches
         * @throws SQLException
         */
        public String[] getSalleTacheEntiteFormule(String table) throws SQLException{
            String quest = "SELECT * FROM "+table;
            String quest1 = "SELECT COUNT(id"+table+") From "+table;
            rs = co.query(quest1);
            int longueurTableau = rs.getInt("COUNT(id"+table+")");
            int i = 0;
            String[] nom = new String[longueurTableau];
            rs = co.query(quest);
            while(rs.next()){
               String name = rs.getString("nom"+table);
               nom[i] = name;
               i++;
            }
            return nom;
        }
        
        /*public String[] getFormule() throws SQLException{
            String quest = "SELECT * FROM Formule";
            String quest1 = "SELECT COUNT(idFormule) FROM Formule";
            rs = co.query(quest1);
            int longueurTableau = rs.getInt("COUNT(idFormule)");
            int i = 0;
            String[] nom = new String[longueurTableau];
            rs = co.query(quest);
            while(rs.next()){
                String name = rs.getString("libelle");
                nom[i] = name;
                i++;
            }
            return nom;
        }
        
        public String[] getEntite() throws SQLException{
            String quest = "SELECT entite FROM Client";
            String quest1 = "SELECT COUNT(idFormule) FROM Formule";
            rs = co.query(quest1);
            int longueurTableau = rs.getInt("COUNT(idFormule)");
            int i = 0;
            String[] nom = new String[longueurTableau];
            rs = co.query(quest);
            while(rs.next()){
                String name = rs.getString("libelle");
                nom[i] = name;
                i++;
            }
            return nom;
        }*/

        /**
         *
         * @param table Prend le nom de la table : Salle || Tache
         * @param nom Prend le nom de la salle/tache concernée
         * @return Retourne un "Color" en RGB
         * @throws SQLException
         */
        public Color hex2Rgb(String table, String nom) throws SQLException {
            String quest = "SELECT codeCouleur FROM "+table+" WHERE nom"+table+" = '"+nom+"'";
            String hex = null;
            rs = co.query(quest);
            if(rs.next()){
                hex = rs.getString("codeCouleur");
            }
            return Color.decode(hex);
        }

        /**
         *
         * @return Retourne toutes les réservations
         */
        public ResultSet getReservations() {
            String request = "SELECT dateDebut, dateFin, nombrePersonnes, validation FROM Reservation";
            rs = co.query(request);
            return rs;
        }

        /**
         * @return Retourne les reservations d'une semaine donnée
         */
        public ResultSet getReservationsSemaine(Calendar cal) {

            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            //System.out.println(cal.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateDebut = sdf.format(cal.getTime());
            cal.add(Calendar.DATE, 7); // obtient le dernier jour de la semaine (un dimanche)
            String dateFin = sdf.format(cal.getTime());
            String request = "SELECT dateDebut, dateFin, nombrePersonnes, validation FROM Reservation WHERE dateDebut > '" + dateDebut +"' AND dateFin < '" + dateFin + "'";
            rs = co.query(request);
            return rs;
        }

        public ResultSet getReservationsJour(Calendar cal) throws SQLException {
            ArrayList<Evenement> ListeEvenements = new ArrayList<>();            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateDebut = sdf.format(cal.getTime());
            cal.add(Calendar.DATE, 1); // obtient le jour suivant
            String dateFin = sdf.format(cal.getTime());
            String request = "SELECT dateDebut, dateFin, nombrePersonnes, validation FROM Reservation WHERE dateDebut > '" + dateDebut +"' AND dateFin < '" + dateFin + "'";
            cal.add(Calendar.DATE, -1); // reviens à la date de base
            rs = co.query(request);
            return rs;
        }


        /**
         *
         * @param nom Prend le nom de l'utilisateur
         * @param prenom Prend le prénom de l'utilisateur
         * @return Retourne les informations sur le mail, le login et le mot de passe d'une personne sous forme d'un tableau
         * @throws SQLException
         */
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

        /**
         *
         * @return Retourne tout les utilisateur (nom + prenom dans une varaible)
         * @throws SQLException
         */
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

        /*
        ========================================================================
                                    FIN SELECTIONS DONNEES /!\
        ========================================================================

        ========================================================================
                                    CHECK (VÉRIFICATION)
        ========================================================================
        */

        /**
         *
         * @param log Prend le login de l'usager
         * @param pwd Prend le mot de passe de l'usager
         * @return TRUE si l'authentification réussie sinon FALSE
         * @throws SQLException
         */
        public boolean authentification(String log, String pwd) throws SQLException{
		String request = "Select login, password From Usager Where login='"+log+"' And password='"+pwd+"'";
		rs = co.query(request);
		if(rs.next()){
                    //co.close();
                    return true;
		}
		return false;
	}

        /**
         *
         * @param nomTable Prend le nom de la table concernée : Salle || Tache
         * @param nom Prend le nom de la salle ou de la tache concernée
         * @param couleur Prend le code couleur HEXADECIMAL
         * @return TRUE si le nom ou le code couleur n'existe pas sinon FALSE
         * @throws SQLException
         */
        public boolean checkErreurAjout(String nomTable, String nom, String couleur) throws SQLException{
            //Comparaison avec les données de la BD pour déterminer les doublons
            String quest1 = "SELECT * FROM "+nomTable+" WHERE nom"+nomTable+" = '"+nom+"'";
            String quest2 = "SELECT * FROM Salle, Tache WHERE Salle.codeCouleur = '"+couleur+"' OR Tache.codeCouleur = '"+couleur+"'";
            if((rs = co.query(quest1)).next() || (rs = co.query(quest2)).next()){
                return false;
            }
            return true;
        }

        /**
         *
         * @param nomTable Prend le nom de la table concernée : Salle || Tache
         * @param nom Prend le nom de la salle ou de la tache concernée
         * @return TRUE si le nom n'existe pas sinon FALSE
         * @throws SQLException
         */
        public boolean checkErreurModif(String nomTable, String nom) throws SQLException{
            String quest = "SELECT * FROM "+nomTable+" WHERE nom"+nomTable+" = '"+nom+"'";
            if(co.query(quest).next()){
                return false;
            }
            return true;
        }


        /**
         *
         * @param dateDebut Prend la date de début de l'événement
         * @param dateFin Prend la date de fin de l'événement
         * @return TRUE si une autre réservation se passe au même moment sinon FALSE
         * @throws SQLException
         */
        public boolean autreReservation(String dateDebut, String dateFin) throws SQLException {
            /**
             * (Le ET est prioritaire sur le OU)
             * La requete vérifie si la réservation en paramètre commence après ou en meme temps 
             * qu'une autre ET commence strictement avant que l'autre finisse,
             * OU inversement, qu'une autre réservation commence après le début de notre réservation,
             * ET commence avant la fin de celle-ci
             * La requete sera utile pour gérer plusieurs options qui sont prise à la même date
             */
            String request = "SELECT * FROM Reservation WHERE '" + dateDebut + "' >= dateDebut"
                    + " AND '" + dateDebut + "' < dateFin "
                    + " OR dateDebut >= '" + dateDebut + "'"
                    + " AND dateDebut < '" + dateFin + "'";
            rs = co.query(request);
            return rs.next(); //si rs.next() est vrai, c'est que la requete a retourné au moins 1 résultat
        }

        /**
         *
         * @param nom Prend le nom de l'utilisateur
         * @param prenom Prend le prénom de l'utilisateur
         * @param login Prend le login de l'utilisateur
         * @param mdp Prend le mot de passe de l'utilisateur
         * @return
         * @throws SQLException
         */
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

        /**
         *
         * @param email Prend l'email de l'utilisateur
         * @return TRUE si l'email est disponible sinon FALSE
         * @throws SQLException
         */
        public boolean checkUtilisateurEmail(String email) throws SQLException{
            String request = "SELECT * FROM Usager WHERE mail = '"+email+"'";
            rs = co.query(request);
            if(rs.next()){
                return false;
            }
            return true;
        }

        /**
         *
         * @param login Prend le login de l'usager
         * @return TRUE si le login n'est pas utilisé sinon FALSE
         * @throws SQLException
         */
        public boolean checkUtilisateurLogin(String login) throws SQLException{
            String request = "SELECT * FROM Usager WHERE login = '"+login+"'";
            rs = co.query(request);
            if(rs.next()){
                return false;
            }
            return true;
        }

        /*
        ========================================================================
                                    FIN CHECK
        ========================================================================

        ========================================================================
                                    MISES A JOUR
        ========================================================================
        */

        /**
         *
         * @param nomTable Prend le nom de la table concernée : Salle || Tache
         * @param ancienNomChamps Prend l'ancien nom de l'attribut concerné
         * @param nouveauNomChamps Prend le nouveau nom de l'attribut concerné
         */
        public void MAJnom(String nomTable, String ancienNomChamps, String nouveauNomChamps){
            String quest = "UPDATE "+nomTable+" SET nom"+nomTable+" = '"+nouveauNomChamps+"' WHERE nom"+nomTable+" = '"+ancienNomChamps+"'";
            co.query(quest);
            System.out.println("UPDATE REUSSIE");
        }

        /**
         *
         * @param nomTable Prend le nom de la table concernée : Salle || Tache
         * @param nom Prend le nom de la salle / tache
         * @param hex Prend le code couleur HEXADECIMAL de la salle / tache
         * @return TRUE si l'update a réussie sinon FALSE
         * @throws SQLException
         */
        public boolean MAJcodeCouleur(String nomTable, String nom, String hex) throws SQLException{
            String quest = "UPDATE "+nomTable+" SET codeCouleur = '"+hex+"' WHERE nom"+nomTable+" = '"+nom+"'";
            String quest1 = "SELECT * FROM "+nomTable+" WHERE codeCouleur = '"+hex+"'";
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

        /**
         *
         * @param ancienLogin Prend l'ancien login de l'usager
         * @param nouveauLogin Prend le nouveau login de l'usager
         */
        public void MAJlogin(String ancienLogin, String nouveauLogin){
            String quest = "UPDATE Usager SET login = '"+nouveauLogin+"' WHERE login = '"+ancienLogin+"'";
            co.query(quest);
            System.out.println("UPDATE REUSSIE");
        }

        /**
         *
         * @param ancienMdp Prend l'ancien mot de passe de l'usager
         * @param nouveauMdp Prend le nouveau mot de passe de l'usager
         */
        public void MAJmdp(String ancienMdp, String nouveauMdp){
            String quest = "UPDATE Usager SET password = '"+nouveauMdp+"' WHERE password = '"+ancienMdp+"'";
            co.query(quest);
            System.out.println("UPDATE REUSSIE");
        }

        /**
         *
         * @param ancienMail Prend l'ancien mail de l'usager
         * @param nouveauMail Prend le nouveau mail de l'usager
         */
        public void MAJmail(String ancienMail, String nouveauMail){
            String quest = "UPDATE Usager SET mail = '"+nouveauMail+"' WHERE mail = '"+ancienMail+"'";
            co.query(quest);
            System.out.println("UPDATE REUSSIE");
        }

        /*
        ========================================================================
                                    FIN MISES A JOUR
        ========================================================================

        ========================================================================
                                    INSERTION
        ========================================================================
        */

        /**
         *
         * @param name Prend le nom que la nouvelle salle aura
         * @param superficie Prend la superficie que la nouvelle salle aura
         * @param couleur Prend le code couleur HEXADECIMAL que la nouvelle salle aura
         * @param comment Prend le commentaire que la nouvelle salle aura
         */
        public void ajoutSalle(String name, int superficie, String couleur, String comment){
            String quest = "INSERT INTO Salle(nomSalle, superficie, codeCouleur, descriptif) VALUES('"+name+"', "+superficie+", '"+couleur+"', '"+comment+"')";
            co.query(quest);
            System.out.println("Insertion réussie");
        }

        /**
         *
         * @param name Prend le nom que la nouvelle tache aura
         * @param couleur Prend le code couleur HEXADECIMAL que la nouvelle tache aura
         * @param comment Prend le commentaire que la nouvelle tache aura
         */
        public void ajoutTache(String name, String couleur, String comment){
            String quest = "INSERT INTO Tache(nomTache, codeCouleur, descriptif) VALUES('"+name+"', '"+couleur+"', '"+comment+"')";
            co.query(quest);
            System.out.println("Insertion réussie");
        }

        /**
         *
         * @param nom Prend le nom du nouvel utilisateur
         * @param prenom Prend le prenom du nouvel utilisateur
         * @param email Prend l'email du nouvel utilisateur
         * @param admin Prend la valeur 1 si le nouvel utilisateur sera admin sinon 0
         * @param login Prend le login du nouvel utilisateur
         * @param mdp Prend le mot de passe du nouvel utilisateur
         */
        public void ajoutUtilisateur(String nom, String prenom, String email, int admin, String login, String mdp){
            String request = "INSERT INTO Usager(nom, prenom, mail, administrateur, login, password) VALUES('"+nom+"','"+prenom+"','"+email+"',"+admin+",'"+login+"','"+mdp+"')";
            rs = co.query(request);
            System.out.println("Insertion du nouvel utilisateur "+prenom+" "+nom+" réussie !");
        }

        /*
        ========================================================================
                                    FIN AJOUT
        ========================================================================

        ========================================================================
                                    SUPPRESSION
        ========================================================================
        */

        /**
         *
         * @param nomTable Prend le nom de la table concernée
         * @param nomSalle Prend le nom de l'attribut concerné
         */
        public void delete(String nomTable, String nomSalle){
            String request = "DELETE FROM "+nomTable+" WHERE nom"+nomTable+" = '"+nomSalle+"'";
            co.query(request);
            System.out.println("DELETE REUSSIE");
        }

        /**
         *
         * @param login Prend le login d'un utilisateur
         */
        public void deleteUser(String login){
            String quest = "DELETE FROM Usager WHERE login = '"+login+"'";
            co.query(quest);
            System.out.println("SUPPRESSION REUSSIE");
        }

        /*
        ========================================================================
                                    FIN SUPPRESSION
        ========================================================================
        */

}
