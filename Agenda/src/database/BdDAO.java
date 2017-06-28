/*
 * To change this license header, 
choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import reservations.Evenement;
import usager.Usager;

import static gestionagenda.GestionAgenda.rq;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import admin.ITcreerReservation1;
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
        private Usager unUsager;

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
            while (rs.next() ) {
                int identifiant = rs.getInt("idUsager");
                int admin = rs.getInt("administrateur");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
            
                unUsager = new Usager(identifiant, admin, nom, prenom);
            }
            return unUsager;
        }


        /**
         *
         * @param table Prend le nom de la table : Salle || Tache
         * @return Retourne toutes les Salles ou toutes les Taches
         * @throws SQLException
         */
        public String[] getSalleTacheEntiteFormule(String table, String id) throws SQLException{
        	List<String> listeNom = new ArrayList<>();
        	rs = co.query("Select * from "+table+"");
        	while(rs.next()) {
        		listeNom.add(rs.getString(2));
        	}
        	return listeNom.toArray(new String[0]);
        }
        
        public int[] getOptions(int idResa) throws SQLException{
        	PreparedStatement request = co.getConnection().prepareStatement("Select optionService.idOptionsServices from optionService, choix where optionService.nature = 'service' && choix.fkidReservation=?");
        	
        	request.setInt(1, idResa);
        	System.out.println(request);
        	rs = request.executeQuery();
        	int[] ListedElements = new int [8]; 
        	
        	int i =0;
        	while(rs.next()){
				try {
					ListedElements[i]=rs.getInt("optionService.idOptionsServices");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	return ListedElements;
        }
        
        /**
        *
        * @param table Prend le nom de la table : Salle || Tache
        * @return Retourne toutes les Salles ou toutes les Taches
        * @throws SQLException
        */
        
        public String getStrById(String table, String idnom, String element, int id) throws SQLException{
        	PreparedStatement request = co.getConnection().prepareStatement("Select "+element+" from "+table+" where "+idnom+" = ?");
        	
        	request.setInt(1, id);
        	System.out.println(request);
        	rs = request.executeQuery();
        	if(rs.next())
        		return rs.getString(element);
        	return "";
        }
        
        public int getIntById(String table, String idnom, String element, int id) throws SQLException{
        	PreparedStatement request = co.getConnection().prepareStatement("Select "+element+" from "+table+" where "+idnom+" = ?");
        	
        	request.setInt(1, id);
        	System.out.println(request);
        	rs = request.executeQuery();
        	if(rs.next())
        		return rs.getInt(element);
        	return 0;
        }
        
        public int getIdByName(String table, String idnom, String nom, String nomname) throws SQLException{
        	PreparedStatement request = co.getConnection().prepareStatement("Select "+idnom+" from "+table+" where "+nomname+" = ?");
        	
        	request.setString(1, nom);
        	System.out.println(request);
        	rs = request.executeQuery();
        	if(rs.next())
        		return rs.getInt(idnom);
        	return 0;
        }
        
        public int getIdByTwoNames(String table, String idnom, String nom1, String nomname1, String nom2, String nomname2) throws SQLException{
        	PreparedStatement request = co.getConnection().prepareStatement("Select "+idnom+" from "+table+" where "+nomname1+" = ? AND "+nomname2+"= ?");
        	
        	request.setString(1, nom1);
        	request.setString(2, nom2);
        	System.out.println(request);
        	rs = request.executeQuery();
        	if(rs.next())
        		return rs.getInt(idnom);
        	return 0;
        }
        
        public int getIdByTwoInt(String table, String idnom, int nom1, String nomname1, int nom2, String nomname2) throws SQLException{
        	PreparedStatement request = co.getConnection().prepareStatement("Select "+idnom+" from "+table+" where "+nomname1+" = ? AND "+nomname2+"= ?");
        	
        	request.setInt(1, nom1);
        	request.setInt(2, nom2);
        	System.out.println(request);
        	rs = request.executeQuery();
        	if(rs.next())
        		return rs.getInt(idnom);
        	return 0;
        }
        
        public int getIdByIdString(String table, String idnom, String nom, String nomname, int id, String idname) throws SQLException{
        	PreparedStatement request = co.getConnection().prepareStatement("Select "+idnom+" from "+table+" where "+nomname+" = ? AND "+idname+"= ?");
        	
        	request.setString(1, nom);
        	request.setInt(2, id);
        	System.out.println(request);
        	rs = request.executeQuery();
        	if(rs.next())
        		return rs.getInt(idnom);
        	return 0;
        }
        
        public String getHourById(String table, String idnom, String element, int id) throws SQLException{
        	PreparedStatement request = co.getConnection().prepareStatement("Select "+element+" from "+table+" where "+idnom+" = ?");
        	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        	request.setInt(1, id);
        	System.out.println(request);
        	rs = request.executeQuery();
        	if(rs.next())
        		return formatter.format(rs.getTime(element));
        	return "";
        }
        
        public String[] getListedElements(String table, String element) throws SQLException {
        	List<String> ListedElements = new ArrayList<>(); 
        	PreparedStatement request = co.getConnection().prepareStatement("Select "+element+" from "+table+"");
        	
        	rs = request.executeQuery();
        	while(rs.next()) {
        		ListedElements.add(rs.getString(element));
        		
        	}
        	return ListedElements.toArray(new String[0]);
        }
        
        
        public String[] getListedElementsById(String table, String element, int id, String idnom) throws SQLException {
        	List<String> ListedElements = new ArrayList<>(); 
        	PreparedStatement request = co.getConnection().prepareStatement("Select "+element+" from "+table+" where "+idnom+"= ?");
        	request.setInt(1, id);
        	rs = request.executeQuery();
        	while(rs.next()) {
        		ListedElements.add(rs.getString(element));
        		
        	}
        	return ListedElements.toArray(new String[0]);
        }
        
        public String[] getListedElementsByName(String table, String element, String nom, String nomname) throws SQLException {
        	List<String> ListedElements = new ArrayList<>(); 
        	PreparedStatement request = co.getConnection().prepareStatement("Select "+element+" from "+table+" where "+nomname+"= ?");
        	request.setString(1, nom);
        	rs = request.executeQuery();
        	while(rs.next()) {
        		ListedElements.add(rs.getString(element));
        		
        	}
        	return ListedElements.toArray(new String[0]);
        }
        
        
       public String[] getElementByIdFromTable(String table, String id, String element) throws SQLException{
           
       String quest = "SELECT * FROM "+table;
           String quest1 = "SELECT COUNT(id"+id+") FROM "+table;
           rs = co.query(quest1);
           String[] nom = null ;
           while(rs.next()){
               int longueurTableau = rs.getInt("COUNT(id"+id+")");
               nom = new String[longueurTableau];
           }
           int i = 0;
           rs = co.query(quest);
           while(rs.next()){
              String name = rs.getString(element);
              nom[i] = name;
              i++;
           }
           return nom;
       }
       
       public int[] getUniqueListElementByIdFromTable(String table, String id, int idno, String element) throws SQLException{
           String quest = "SELECT "+element+" FROM "+table+" WHERE "+id+" = "+idno+"";
           String quest1 = "SELECT COUNT("+id+") FROM "+table+" WHERE "+id+" = "+idno+"";
           rs = co.query(quest1);
           int[] nom = null ;
           while(rs.next()){
               int longueurTableau = rs.getInt("COUNT("+id+")");
               nom = new int[longueurTableau];
           }
           int i = 0;
           rs = co.query(quest);
           while(rs.next()){
              int name = rs.getInt(element);
              nom[i] = name;
              i++;
           }
           return nom;   
       }
       
       public int[] getOptions(String table, String id, int idno, String element) throws SQLException{
           String quest = "SELECT "+element+" FROM "+table+" WHERE "+id+" = "+idno+" AND prixTTC>0";
           String quest1 = "SELECT COUNT("+id+") FROM "+table+" WHERE "+id+" = "+idno+"";
           rs = co.query(quest1);
           int[] nom = null ;
           while(rs.next()){
               int longueurTableau = rs.getInt("COUNT("+id+")");
               nom = new int[longueurTableau];
           }
           int i = 0;
           rs = co.query(quest);
           while(rs.next()){
              int name = rs.getInt(element);
              nom[i] = name;
              i++;
           }
           return nom;   
       }
       
       
       public double getUniqueDoubleByIdFromTable(String table, String id, int idno, String element) throws SQLException{
           String quest = "SELECT "+element+" FROM "+table+" WHERE "+id+" = "+idno+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               double spr = rs.getDouble(element);
               return spr;
           }
           } catch (SQLException ex) {
           	
           }
           return 0;
       }
       
       public int getUniqueIntByIdFromTable(String table, String id, int idno, String element) throws SQLException{
           String quest = "SELECT "+element+" FROM "+table+" WHERE "+id+" = "+idno+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               int spr = rs.getInt(element);
               return spr;
           }
           } catch (SQLException ex) {
           	
           }
           return 0;
       }
       
       public String getUniqueStringByIdFromTable(String table, String id, int idno, String element) throws SQLException{
           String quest = "SELECT "+element+" FROM "+table+" WHERE "+id+" = "+idno+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               String spr = rs.getString(element);
               return spr;
           }
           } catch (SQLException ex) {
           	
           }
           return null;
       }
        
       /**
        * 
        * @param user Prend le nom de l'utilisateur concernée
        * @return Retourne l'email de l'utilisateur en question
        * 
        */
       public String getEmail(int user){
           String quest = "SELECT mail FROM usager WHERE idUsager = "+user+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               String comment = rs.getString("mail");
               return comment;
           }
           } catch (SQLException ex){
           	
           }
           return null;
       }
       
       public String getAdresse(int id){
           String quest = "SELECT adresseFacturation FROM client WHERE idClient = "+id+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               String comment = rs.getString("adresseFacturation");
               return comment;
           }
           } catch (SQLException ex){
           	
           }
           return null;
       }
       
       public String getEmailClient(int id){
           String quest = "SELECT eMail FROM client WHERE idClient = "+id+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               String comment = rs.getString("eMail");
               return comment;
           }
           } catch (SQLException ex){
           	
           }
           return null;
       }
       
       public String getCommentClient(int id){
           String quest = "SELECT commentaire FROM client WHERE idClient = "+id+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               String comment = rs.getString("commentaire");
               return comment;
           }
           } catch (SQLException ex){
           	
           }
           return null;
       }
       
       public String getEntite(int id){
           String quest = "SELECT entite FROM client WHERE idClient = "+id+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               String comment = rs.getString("entite");
               return comment;
           }
           } catch (SQLException ex){
           	
           }
           return null;
       }
       
       public String getTelephone(int id){
           String quest = "SELECT telephone FROM client WHERE idClient = "+id+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               String comment = rs.getString("telephone");
               return comment;
           }
           } catch (SQLException ex){
           	
           }
           return null;
       }
       
       public String getNom(int user){
           String quest = "SELECT nom FROM usager WHERE idUsager = "+user+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               String comment = rs.getString("nom");
               return comment;
           }
           } catch (SQLException ex){
           	
           }
           return null;
       }
       
       public String getPrenom(int user){
           String quest = "SELECT prenom FROM usager WHERE idUsager = "+user+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               String comment = rs.getString("prenom");
               return comment;
           }
           } catch (SQLException ex){
           	
           }
           return null;
       }
       
       public String getLogin(int user){
           String quest = "SELECT login FROM usager WHERE idUsager = "+user+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               String comment = rs.getString("login");
               return comment;
           }
           } catch (SQLException ex){
           	
           }
           return null;
       }
       
       public String getMdp(int user){
           String quest = "SELECT password FROM usager WHERE idUsager = "+user+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               String comment = rs.getString("password");
               return comment;
           }
           } catch (SQLException ex){
           	
           }
           return null;
       }
       
       public int getAdmin(int user){
           String quest = "SELECT administrateur FROM usager WHERE idUsager = "+user+"";
           try{
           rs = co.query(quest);
           if(rs.next()){
               int comment = rs.getInt("administrateur");
               return comment;
           }
           } catch (SQLException ex){
           	
           }
           return -1;
       }
       
        /**
         * 
         * @param choix Prend "Option" ou "Service"
         * @return Retourne toutes les options ou tout les services
         * @throws SQLException 
         */
        public String[] getOptionService(String choix) throws SQLException{
            String quest = "SELECT libelle FROM optionService WHERE nature = '"+choix+"'";
            String quest1 = "SELECT COUNT(idOptionsServices) FROM optionService WHERE nature = '"+choix+"'";

            int longueurTableau = 0;
            
            rs = co.query(quest1);
            while(rs.next()){
                longueurTableau = rs.getInt("COUNT(idOptionsServices)");
            }
            String[] nom = new String[longueurTableau];
            int i = 0;
            rs = co.query(quest);
            while(rs.next()){
               String name = rs.getString("libelle");
               nom[i]=name;
               i++;
            }
            return nom;
        }
        
        /**
         * 
         * @param nomSalle Prend le nom de la salle concernée
         * @return Retourne la superficie de la salle en question
         * 
         */
        public int getSuperficieSalle(int idSalle) {
            String quest = "SELECT superficie FROM salle WHERE idSalle = "+idSalle+"";
            try{
            rs = co.query(quest);
            if(rs.next()){
                int spr = rs.getInt("superficie");
                return spr;
            }
            } catch (SQLException ex) {
            	
            }
            return 0;
        }
        
        public int getCapacite(int idInfoSalle) {
            String quest = "SELECT capacite FROM infosalle WHERE idInfoSalle = "+idInfoSalle+"";
            try{
            rs = co.query(quest);
            if(rs.next()){
                int spr = rs.getInt("capacite");
                return spr;
            }
            } catch (SQLException ex) {
            	
            }
            return 0;
        }
        
        
        
        /**
         * 
         * @param nomSalle Prend le nom de la salle concernée
         * @return Retourne le descriptif de la salle en question
         * 
         */
        public String getCommentSalle(String nomSalle){
            String quest = "SELECT descriptif FROM salle WHERE libelle = '"+nomSalle+"'";
            try{
            rs = co.query(quest);
            if(rs.next()){
                String comment = rs.getString("descriptif");
                return comment;
            }
            } catch (SQLException ex){
            	ex.printStackTrace();
            }
            return null;
        }
        
        /**
         * 
         * @param service Prend le service concerné
         * @return Retourne le descriptif de ce service
         * @throws SQLException 
         */
        public String getCommentService(String service) throws SQLException{
            String quest = "SELECT descriptif FROM optionService WHERE libelle = '"+service+"'";
            rs = co.query(quest);
            if(rs.next()){
                String comment = rs.getString("descriptif");
                return comment;
            }
            return null;
        }
        
        /**
         * 
         * @param nomSalle Prend le nom de la salle concernée
         * @param nomFormule Prend le nom de la formule concernée
         * @return retourne le tarif
         * @throws SQLException 
         */
        public double getTarifSalle(String nomSalle, String nomFormule) throws SQLException{
            String quest = "SELECT tarif FROM tarifSalle, Salle, Formule WHERE Salle.idSalle = tarifSalle.fkidSalle AND Formule.idFormule = tarifSalle.fkidFormule AND salle.libelle = '"+nomSalle+"' AND Formule.libelle = '"+nomFormule+"'";
            rs = co.query(quest);
            if(rs.next()){
                double tarif = rs.getDouble("tarif");
                return tarif;
            }
            return 0;
        }
        
        public double getTarifService(String nomservice) throws SQLException{
            String quest = "SELECT prixHT FROM optionService WHERE libelle = '"+nomservice+"'";
            rs = co.query(quest);
            if(rs.next()){
                double tarif = rs.getDouble("prixHT");
                return tarif;
            }
            return 0;
        }
        
        public double getTarifServiceint(int idservice) throws SQLException{
            String quest = "SELECT prixHT FROM optionService WHERE idOptionsServices = "+idservice+"";
            rs = co.query(quest);
            if(rs.next()){
                double tarif = rs.getDouble("prixHT");
                return tarif;
            }
            return 0;
        }

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
        
        public int getCouleur(String couleurHex) throws SQLException{
        	int idcouleur=0;
    	 rs = co.query("SELECT id FROM couleur WHERE codeHexa = '"+couleurHex+"'");
    	 while(rs.next()){
            idcouleur = rs.getInt("id");
            
         }
    	 return idcouleur;
        }

        /**
         *
         * @return Retourne toutes les réservations
         */
        public ResultSet getReservations() {
            String request = "SELECT dateDebut, dateFin, nbPersonnes, validation FROM reservation";
            rs = co.query(request);
            return rs;
        }
        
        public String[] getInfosTache(int idTache) throws SQLException {
        	List<String> infosTache = new ArrayList<>(); 
        	PreparedStatement request = co.getConnection().prepareStatement("Select * from tache where idTache = ?");
        	request.setInt(1, idTache);
        	rs = request.executeQuery();
        	if(rs.next()) {
        		infosTache.add("Date de début : "+rs.getString("dateDebut"));
        		infosTache.add("Date de fin : "+rs.getString("dateFin"));
        		infosTache.add("Heure de début : "+rs.getString("heureDebut"));
        		infosTache.add("Heure de fin : "+rs.getString("heureFin"));
        		infosTache.add("Nom de la tache : "+rs.getString("nomTache"));
        		infosTache.add("Description : \n\t"+rs.getString("descriptif"));
        	}
        	return infosTache.toArray(new String[0]);
        }
        
        public String getNomTache(int idTache) throws SQLException {
        	PreparedStatement request = co.getConnection().prepareStatement("Select nomTache from tache where idTache = ?");
        	request.setInt(1, idTache);
        	rs = request.executeQuery();
        	if(rs.next())
        		return rs.getString(1);
        	return " ";
        }
        
        public String getCommentTache(int idTache) throws SQLException {
        	PreparedStatement request = co.getConnection().prepareStatement("Select descriptif from tache where idTache = ?");
        	request.setInt(1, idTache);
        	rs = request.executeQuery();
        	if(rs.next())
        		return rs.getString(1);
        	return " ";
        }
        
        public String getCommentTache(String nomTache) throws SQLException {
        	PreparedStatement request = co.getConnection().prepareStatement("Select descriptif from tache where nomTache = ?");
        	request.setString(1, nomTache);
        	rs = request.executeQuery();
        	if(rs.next())
        		return rs.getString(1);
        	return " ";
        }

        /**
     * @param cal
         * @return Retourne les reservations d'une semaine donnée
         */
        public ResultSet getReservationsSemaine(Calendar cal) {

            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String dateDebut = sdf.format(cal.getTime());
            cal.add(Calendar.DATE, 7); // obtient le dernier jour de la semaine (un dimanche)
            String dateFin = sdf.format(cal.getTime());
            String request = "SELECT dateDebut, dateFin, nbPersonnes, valide FROM reservation WHERE dateDebut > '" + dateDebut +"' AND dateFin < '" + dateFin + "'";
            rs = co.query(request);
            return rs;
        }

        
        // in work <tag>
        public ResultSet getReservationsJour(Calendar cal) throws SQLException {           
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String dateDebut = sdf.format(cal.getTime());
            String dateFin = sdf.format(cal.getTime());
            String r = "Select * from reservation WHERE STR_TO_DATE(dateDebut, '%d-%m-%Y') <= STR_TO_DATE('"+dateDebut+"',  '%d-%m-%Y') AND STR_TO_DATE(dateFin, '%d-%m-%Y') >= STR_TO_DATE('"+dateFin+"',  '%d-%m-%Y')";
            rs = co.query(r);  
            return rs;
        }
        
        public ResultSet getTachesJour(Calendar cal) throws SQLException {         
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String dateDebut = sdf.format(cal.getTime());
            String dateFin = sdf.format(cal.getTime());
            String r = "Select * from tache WHERE STR_TO_DATE(dateDebut, '%d-%m-%Y') <= STR_TO_DATE('"+dateDebut+"',  '%d-%m-%Y') AND STR_TO_DATE(dateFin, '%d-%m-%Y') >= STR_TO_DATE('"+dateFin+"',  '%d-%m-%Y')";
            rs = co.query(r);
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
            String quest = "SELECT nom, prenom FROM usager";
            String quest1 = "SELECT COUNT(idUsager) FROM usager";
            rs = co.query(quest1);
            int longueurTableau = rs.getInt("COUNT(idUsager)");
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
        
    /**
     *
     * @return
     * @throws SQLException
     */
    public String[] getClients() throws SQLException{
            String quest = "SELECT nom, prenom FROM client";
            ArrayList<String> nom = new ArrayList<String>();
            int i = 0;
            rs = co.query(quest);
            while(rs.next()){
               String name = rs.getString("prenom")+" "+rs.getString("nom");
               nom.add(name);
               i++;
            }
            String nom2[] = new String[nom.size()];
            nom2 = nom.toArray(nom2);
            return nom2;
        }

        /**
         *
         * @param id
         * @return
         * @throws SQLException
         */
        public String[] getInfosClient(int id) throws SQLException{
            String quest = "SELECT nom, prenom, adresseFacturation, entite, telephone, eMail FROM client WHERE idClient='"+id+"'";
            rs = co.query(quest);
            ArrayList<String> client = new ArrayList<>(); 
            while (rs.next()){
                String nom = rs.getString("nom")+" "+rs.getString("prenom");
                String adresse = rs.getString("adresseFacturation");
                String entite = rs.getString("entite"); 
                String telephone = rs.getString("telephone");
                String eMail = rs.getString("eMail");
                client.add(nom);
                client.add(adresse);
                client.add(entite);
                client.add(telephone);
                client.add(eMail);   
            }
            String client2[] = new String[6];
            client2 = client.toArray(client2);
            return client2;
        }
        
        public String getElementByPrenomNom(String clientprenom, String clientnom, String id, String table){
            String quest = "SELECT "+id+" FROM "+table+" WHERE prenom = '"+clientprenom+"' AND nom = '"+clientnom+"'";
            try{
            rs = co.query(quest);
            if(rs.next()){
                String comment = rs.getString(""+id+"");
                return comment;
            }
            } catch (SQLException ex){
            	
            }
            return null;
        }
        
        public int getIdClient(String nom, String prenom) throws SQLException{
            String quest = "SELECT idClient FROM client WHERE nom='"+nom+"' AND prenom='"+prenom+"'";
            rs = co.query(quest);
            int idClient=5;
            while (rs.next()){
                idClient = rs.getInt("idClient");
            }
            return idClient;
        }
        
        public int getIdFormule(String nom) throws SQLException{
            String quest = "SELECT idFormule FROM formule WHERE libelle='"+nom+"'";
            rs = co.query(quest);
            int idFormule=5;
            while (rs.next()){
                idFormule = rs.getInt("idFormule");
            }
            return idFormule;
        }
        
        public int getIdSalle(String nom) throws SQLException{
            String quest = "SELECT idSalle FROM salle WHERE libelle='"+nom+"'";
            rs = co.query(quest);
            int idSalle=0;
            while (rs.next()){
                idSalle = rs.getInt("idSalle");
            }
            return idSalle;
        }
        
        public int getIdDisposition(String nom) throws SQLException{
            String quest = "SELECT idDisposition FROM disposition WHERE libelle='"+nom+"'";
            rs = co.query(quest);
            int idDispo=0;
            while (rs.next()){
                idDispo = rs.getInt("idDisposition");
            }
            return idDispo;
        }
        
        public String[] getnomsClients() throws SQLException{
            String quest = "SELECT nom FROM client";
            ArrayList<String> nom = new ArrayList<String>();
            int i = 0;
            rs = co.query(quest);
            while(rs.next()){
               String name = rs.getString("nom");
               nom.add(name);
               i++;
            }
            String nom2[] = new String[nom.size()];
            nom2 = nom.toArray(nom2);
            return nom2;
        }
        
        public String[] getprenomsClients() throws SQLException{
            String quest = "SELECT prenom FROM client";
            ArrayList<String> nom = new ArrayList<String>();
            int i = 0;
            rs = co.query(quest);
            while(rs.next()){
               String name = rs.getString("prenom");
               nom.add(name);
               i++;
            }
            String nom2[] = new String[nom.size()];
            nom2 = nom.toArray(nom2);
            return nom2;
        }
        
        public int getIdReservation(int idClient, String dateDe) throws SQLException{
            String quest = "SELECT idReservation FROM reservation WHERE fkidClient='"+idClient+"' AND dateDebut='"+dateDe+"'";
            rs = co.query(quest);
            int IdReservation=0;
            while (rs.next()){
                IdReservation = rs.getInt("idReservation");
                System.out.println(IdReservation);
            }
            return IdReservation;
        }
        
        public int getIdOptionService(String choix) throws SQLException{
            String quest = "SELECT idOptionsServices FROM optionService WHERE libelle='"+choix+"'";
            rs = co.query(quest);
            int IdOS=0;
            while (rs.next()){
                IdOS = rs.getInt("idOptionsServices");
                System.out.println(IdOS);
            }
            return IdOS;
        }
        
        public int getIdTache(String nom, String dateDebut) throws SQLException{
            String quest = "SELECT idTache FROM tache WHERE nomTache='"+nom+"' AND dateDebut='"+dateDebut+"'";
            rs = co.query(quest);
            int IdOS=0;
            while (rs.next()){
                IdOS = rs.getInt("idTache");
                System.out.println(IdOS);
            }
            return IdOS;
        }
        
        public String getCouleurReservation(int idReservation) throws SQLException {
        	PreparedStatement request = co.getConnection().prepareStatement("Select codeCouleur from salle, salleResa, infoSalle where fkidReservation = ? and fkidInfoSalle = idInfoSalle and fkidSalle = idSalle");
        	request.setInt(1, idReservation);
        	rs = request.executeQuery();
        	if(rs.next()) 
        		return rs.getString("codeCouleur");
        	return "";
        }
        
        public String getCouleurTache(int idTache) throws SQLException {
        	PreparedStatement request  = co.getConnection().prepareStatement("Select codeCouleur from tache where tache.idTache = ?");
        	request.setInt(1, idTache);
        	rs = request.executeQuery();
        	if(rs.next()) {
        		return rs.getString("codeCouleur");
        	}
        	return "";
        }
        
        public String getCouleurSalle(int idTache) throws SQLException {
        	PreparedStatement request  = co.getConnection().prepareStatement("Select codeCouleur from salle where salle.idSalle = ?");
        	request.setInt(1, idTache);
        	rs = request.executeQuery();
        	if(rs.next()) {
        		return rs.getString("codeCouleur");
        	}
        	return "";
        }
        
        public String getCommentSalle(int id) throws SQLException {
        	PreparedStatement request  = co.getConnection().prepareStatement("Select descriptif from salle where salle.idSalle = ?");
        	request.setInt(1, id);
        	rs = request.executeQuery();
        	if(rs.next()) {
        		return rs.getString("descriptif");
        	}
        	return "";
        }
    /**
     *
     * @param idReservation
     * @param laFormule
     * @return
     * @throws SQLException
     */
        public String[] getInfosReservation(int idReservation) throws SQLException{
            String heureF = "SELECT heureFin FROM reservation WHERE idReservation="+idReservation+"";
            String nom = "SELECT nom, prenom FROM client, reservation WHERE client.idClient=reservation.fkidClient AND reservation.idReservation="+idReservation+"";
            String heureD = "SELECT heureDebut  FROM reservation WHERE idReservation="+idReservation+"";
            String dateF = "SELECT dateFin FROM reservation WHERE idReservation="+idReservation+"";
            String nbP = "SELECT nbPersonnes  FROM reservation WHERE idReservation="+idReservation+"";
            String nbH = "SELECT nbHeures  FROM reservation WHERE idReservation="+idReservation+"";
            String dateD = "SELECT dateDebut FROM reservation WHERE idReservation="+idReservation+"";
            String service = "SELECT libelle FROM optionService, choix WHERE optionService.idOptionsServices=choix.fkidOptionsServices AND choix.fkidReservation="+idReservation+" AND optionService.nature='service'";
            String option = "SELECT libelle FROM optionService, choix WHERE optionService.idOptionsServices=choix.fkidOptionsServices AND choix.fkidReservation="+idReservation+" AND optionService.nature='option'";
            String formule = "SELECT libelle FROM formule, reservation WHERE formule.idFormule=reservation.fkidFormule AND reservation.idReservation="+idReservation+"";
            String salle = "SELECT libelle FROM salle, infoSalle, salleResa WHERE infoSalle.fkidSalle=salle.idSalle AND salleResa.fkidInfoSalle=infoSalle.idInfoSalle AND salleResa.fkidReservation="+idReservation+"";
            String disposition = "SELECT disposition.libelle FROM salleResa, disposition, infoSalle WHERE infoSalle.fkidDisposition=disposition.idDisposition AND salleResa.fkidInfoSalle=infoSalle.idInfoSalle AND salleResa.fkidReservation='"+idReservation+"'";

            ArrayList<String> infosReservation = new ArrayList<>();  
            

            rs = co.query(dateD);
            while(rs.next()){
                String DateDebut = rs.getString("dateDebut");
                infosReservation.add("Date de début: " +DateDebut); // 0
            }
            
            rs = co.query(dateF);
            while(rs.next()){
                String dateFin = rs.getString("dateFin");
                infosReservation.add("Date de fin : " +dateFin); // 1
            }
            
            rs = co.query(heureD);
            while(rs.next()){
                String heureDebut = rs.getString("heureDebut");
                infosReservation.add("Heure de début : "+heureDebut); // 2
            }
            
            rs = co.query(heureF);
            while(rs.next()){
                String heureFin = rs.getString("heureFin");
                infosReservation.add("Heure de fin : "+ heureFin); // 3
            }
            
            rs = co.query(service);
            ArrayList<String> lesServices = new ArrayList<>();  
            while(rs.next()){
                String leService = rs.getString("libelle");
                lesServices.add(leService);
            }
            if (!lesServices.isEmpty()){
                infosReservation.add("Service(s) : " +lesServices); // 3 -> n
            }
            else{
                infosReservation.add("Service(s) : aucun service"); // 4
            }
            
            
            rs = co.query(option);
            ArrayList<String> lesOptions = new ArrayList<>();  
            while(rs.next()){
                String lOption = rs.getString("libelle");
                lesOptions.add(lOption);
            }
            if (!lesOptions.isEmpty()){
                infosReservation.add("Option(s) : " +lesOptions); // n+1 -> n+m
            }
            else{
                infosReservation.add("Option(s) : aucune option");
            }
            
            
            rs = co.query(formule);
            while(rs.next()){
                String laformule = rs.getString("libelle");
                infosReservation.add("Formule : "+laformule); // n+m+1
            }
            
            rs = co.query(nbP);
            while(rs.next()){
                int nb = rs.getInt("nbPersonnes");
                String nbS = Integer.toString(nb);
                infosReservation.add("Nombre de personnes :"+nbS); // n+m+2
            }
            
            rs = co.query(nbH);
            while(rs.next()){
                int nb = rs.getInt("nbHeures");
                String nbS = Integer.toString(nb);
                infosReservation.add("Nombre d'heures :"+nbS); // n+m+3
            }
            
            rs = co.query(salle);
            ArrayList<String> lesSalles = new ArrayList<>();  
            while(rs.next()){
               String laSalle = rs.getString("libelle");
               lesSalles.add(laSalle); // n+m+4
            }         
            infosReservation.add("Salle(s) : "+lesSalles);
            
            rs = co.query(disposition);
            while(rs.next()){
                String laDisposition = rs.getString("libelle");
                infosReservation.add("Disposition : "+laDisposition); // n+m+5
            }
            
            rs = co.query(nom);
            while(rs.next()){
               String name = rs.getString("prenom")+" "+rs.getString("nom");
               infosReservation.add("Client : "+name); // n+m+6
            }
           
            
            String []tableau = new String[infosReservation.size()];
            tableau = infosReservation.toArray(tableau);
            
            return tableau;
        }
            
        
        public String getNomClient(int idReservation) throws SQLException{
            String nom = "SELECT nom FROM client, reservation WHERE client.idClient=reservation.fkidClient AND reservation.idReservation='"+idReservation+"'";
            rs = co.query(nom);
            String name = "";
            while(rs.next()){
               name = rs.getString("nom");
            }
            return name; 
        }
        
        public String getPrenomClient(int idReservation) throws SQLException{
            String prenom = "SELECT nom, prenom FROM client, reservation WHERE client.idClient=reservation.fkidClient AND reservation.idReservation='"+idReservation+"'";
            rs = co.query(prenom);
            String name = "";
            while(rs.next()){
               name = rs.getString("prenom");
            }
            return name;
        }
        
        public int getidFacture(int idresa, double montant) throws SQLException{
        	String quest = "SELECT idFacture FROM facture WHERE montant ="+montant+" AND fkidReservation="+idresa+"";
            rs = co.query(quest);
            int idFormule=5;
            while (rs.next()){
                idFormule = rs.getInt("idFacture");
            }
            return idFormule;
        }
        
        public String[] getDatesTaches(String nom) throws SQLException{
            String dates = "SELECT dateDebut FROM tache WHERE nomTache='"+nom+"'";
            ArrayList<String> datesR = new ArrayList<>();
            rs = co.query(dates);
            String date = "";
            while(rs.next()){
               date = rs.getString("dateDebut");
               datesR.add(date);
            }
            datesR.add("");
            String datesRe[] = new String[datesR.size()];
            datesRe = datesR.toArray(datesRe);
            return datesRe;
        }
        
        
        public String[] getDatesReservations(int idClient) throws SQLException{
            String dates = "SELECT dateDebut FROM reservation WHERE fkidClient="+idClient+"";
            ArrayList<String> datesR = new ArrayList<>();
            rs = co.query(dates);
            String date = "";
            while(rs.next()){
               date = rs.getString("dateDebut");
               datesR.add(date);
            }
            datesR.add("");
            String datesRe[] = new String[datesR.size()];
            datesRe = datesR.toArray(datesRe);
            return datesRe;
        }
        public String getDateDebut(int idReservation) throws SQLException{
            String dateR = "SELECT dateDebut FROM reservation WHERE idReservation="+idReservation+"";
            rs = co.query(dateR);
            String date = "";
            while(rs.next()){
               date = rs.getString("dateDebut");
            }
            return date;
        }
        
        public String getDateDebutTache(int idTache) throws SQLException{
            String dateR = "SELECT dateDebut FROM tache WHERE idTache="+idTache+"";
            rs = co.query(dateR);
            String date = "";
            while(rs.next()){
               date = rs.getString("dateDebut");
            }
            return date;
        }
        
        public String getDateFinTache(int idTache) throws SQLException{
            String dateR = "SELECT dateFin FROM tache WHERE idTache="+idTache+"";
            rs = co.query(dateR);
            String date = "";
            while(rs.next()){
               date = rs.getString("dateFin");
            }
            return date;
        }
        
        public String getDateFin(int idReservation) throws SQLException{
            String dateR = "SELECT dateFin FROM reservation WHERE idReservation="+idReservation+"";
            rs = co.query(dateR);
            String date = "";
            while(rs.next()){
               date = rs.getString("dateFin");
            }
            return date;
        }
        
        public String getHeureDebutFromResa(int idReservation) throws SQLException{
        	String query = "SELECT heureDebut FROM reservation WHERE idReservation="+idReservation+"";
        	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            rs = co.query(query);
            String heure = "";
            while(rs.next()){
               heure = formatter.format(rs.getTime("heureDebut"));
               
            }
            return heure;
        }
        
        public String getHeureFinFromResa(int idReservation) throws SQLException{
        	String query = "SELECT heureFin FROM reservation WHERE idReservation="+idReservation+"";
        	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            rs = co.query(query);
            String heure = "";
            while(rs.next()){
               heure = formatter.format(rs.getTime("heureFin"));
               
            }
            return heure;
        }
        
        public int[] getSalleResa(int idReservation) throws SQLException{
        	String query = "SELECT idSallesResa FROM salleresa WHERE fkidReservation="+idReservation+"";
        	
            ArrayList<Integer> datesR = new ArrayList<>();
            rs = co.query(query);
            int salle = 0;
            while(rs.next()){
               salle = rs.getInt("idSallesResa");
               datesR.add(salle);
            }
            datesR.add(0);
            int datesRe[] = new int[datesR.size()];
            for(int i=0;i<datesR.size();++i)datesRe[i]=datesR.get(i);
            return datesRe;
        }
        
        public String getHeureDebutFromTache(int idTache) throws SQLException{
        	String query = "SELECT heureDebut FROM tache WHERE idTache="+idTache+"";
        	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            rs = co.query(query);
            String heure = "";
            while(rs.next()){
               heure = formatter.format(rs.getTime("heureDebut"));
               
            }
            return heure;
        }
        
        public String getHeureFinFromTache(int idTache) throws SQLException{
        	String query = "SELECT heureFin FROM tache WHERE idTache="+idTache+"";
        	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            rs = co.query(query);
            String heure = "";
            while(rs.next()){
               heure = formatter.format(rs.getTime("heureFin"));
               
            }
            return heure;
        }
        
        public String[] getSalleFromResa(int idReservation) throws SQLException{
        	String query= "SELECT salle.libelle FROM salle, infoSalle, salleResa WHERE salle.idSalle=infoSalle.fkidSalle AND salleResa.fkidInfoSalle=infoSalle.idInfoSalle AND salleResa.fkidReservation="+idReservation+"";
        	rs = co.query(query);
            String []salle = new String [3];
            salle[0]="";
            salle[1]="";
            salle[2]="";
            int lasalle=0;
            while(rs.next()){
               salle[lasalle] = rs.getString("salle.libelle");
               lasalle+=1;
            }
            System.out.println(salle);
            return salle;
        }
        
        public String[] getDispositionFromResa(int idReservation) throws SQLException{
        	String query= "SELECT disposition.libelle FROM disposition, infoSalle, salleResa WHERE disposition.idDisposition=infoSalle.fkidDisposition AND salleResa.fkidInfoSalle=infoSalle.idInfoSalle AND salleResa.fkidReservation="+idReservation+"";
        	rs = co.query(query);
        	String []salle = new String [3];
            salle[0]="";
            salle[1]="";
            salle[2]="";
            int lasalle=0;
            while(rs.next()){
               salle[lasalle] = rs.getString("disposition.libelle");
               lasalle+=1;
            }
            System.out.println(salle);
            return salle;
        }
        
        public int[] getNbpersonnesFromResa(int idReservation) throws SQLException{
        	String query = "SELECT nbPersonnes FROM salleResa WHERE fkidReservation="+idReservation+"";
        	rs = co.query(query);
            int[] id = new int[3];
            id[0]=0;
            id[1]=0;
            id[2]=0;
            int leid=0;
            while(rs.next()){
               id[leid] = rs.getInt("nbPersonnes");
               leid+=1;
            }
            System.out.println(id);
            return id;
        }
        
        public String getFormuleFromResa(int idReservation) throws SQLException{
        	String query = "SELECT formule.libelle FROM formule, reservation WHERE reservation.fkidFormule=idFormule AND reservation.idReservation="+idReservation+"";
        	rs = co.query(query);
            String formule = "";
            while(rs.next()){
               formule = rs.getString("formule.libelle");
            }
            System.out.println(formule);
            return formule;
        }
        
        public String[][] getOptionsFromResa(int[] idSalleResa) throws SQLException{
        	String[][] Optn = new String[6][3];
        	for(int k=0;k<6;++k)for(int j=0;j<3;++j)Optn[k][j]="Aucune";
        	for(int i=0;i<idSalleResa.length;++i){
        	try{
        	String query = "SELECT optionservice.libelle FROM optionservice, salleresa, choix WHERE optionservice.idOptionsServices=choix.fkidOptionsServices AND salleresa.idSallesResa=choix.fkidSalleResa AND salleresa.idSallesResa="+idSalleResa[i]+" AND optionservice.nature='option'";
        	//List<String> Optn = new ArrayList<>();  
            rs = co.query(query);
            
            
            int inst=0;
            while(rs.next()){
            	Optn[inst][i]=(rs.getString("optionservice.libelle"));
            	inst+=1;
            }
        		}catch (NullPointerException e){System.out.println("YA UN BLEM");}
        	}
            return Optn;
        }
        
        public String[][] getServicesFromResa(int[] idSalleResa) throws SQLException{
        	String[][] Optn = new String[6][3];
        	for(int k=0;k<6;++k)for(int j=0;j<3;++j)Optn[k][j]="Aucune";
        	for(int i=0;i<idSalleResa.length;++i){
        		try{
        	String query = "SELECT optionservice.libelle FROM optionservice, salleresa, choix WHERE optionservice.idOptionsServices=choix.fkidOptionsServices AND salleresa.idSallesResa=choix.fkidSalleResa AND salleresa.idSallesResa="+idSalleResa[i]+" AND optionservice.nature='service'";
        	//List<String> Optn = new ArrayList<>();  
            rs = co.query(query);
            
            
            int inst=0;
            while(rs.next()){
            	Optn[inst][i]=(rs.getString("optionservice.libelle"));
            	
            	inst+=1;
            }
        		}catch (NullPointerException e){}
        	}
        	
            return Optn;
        }

        
        public String getValidite(int idReservation) throws SQLException{
            String validite = "SELECT estValide FROM reservation WHERE idReservation="+idReservation+"";
            rs = co.query(validite);
            String valide = "";
            while(rs.next()){
               valide = rs.getString("estValide");
            }
            if("1".equals(valide)){
                valide = "valide";
            }
            else{
                valide = "non valide";
            }
            return valide;
        }
        
        public int getchoix(int idReservation, int idOptionService) throws SQLException{
        	String query = "SELECT idChoix FROM choix WHERE fkidReservation="+idReservation+" AND fkidOptionsServices="+idOptionService+"";
            rs = co.query(query);
            int choix = 0;
            while(rs.next()){
               choix = rs.getInt("idChoix");
            }
            return choix;
        }
        
        public int getIdUser(String login) throws SQLException{
        	String query = "SELECT idUsager FROM usager WHERE login='"+login+"'";
            rs = co.query(query);
            int choix = 0;
            while(rs.next()){
               choix = rs.getInt("idUsager");
            }
            return choix;
        }
        
        
       
        /*public int reservationExiste(int idClient, String dateDebut) throws SQLException{
            String existe = "SELECT count(*) as nombre FROM reservations WHERE '"+idClient+"'=fkidClient AND '"+dateDebut+"'=dateDebut";
            rs = co.query(existe);
            int resa = 0;
            while(rs.next()){
            resa = rs.getInt("nombre");
            }           
            return resa;
        }
        */
        
        public String[] getServices() throws SQLException{
            String service = "SELECT libelle FROM optionService WHERE nature='service'";
            ArrayList<String> lesServices = new ArrayList<>();  
            rs = co.query(service);
            while(rs.next()){
                String services = rs.getString("libelle");
                lesServices.add(services);
            }
            String lesServicestab[] = new String[lesServices.size()];
            lesServicestab = lesServices.toArray(lesServicestab);
            return lesServicestab;
        }
        
        public String[] getOptions() throws SQLException{
            String option = "SELECT libelle FROM optionService WHERE nature='option'";
            ArrayList<String> lesOptions = new ArrayList<>();  
            rs = co.query(option);
            while(rs.next()){
                String services = rs.getString("libelle");
                lesOptions.add(services);
            }
            String lesOptionstab[] = new String[lesOptions.size()];
            lesOptionstab = lesOptions.toArray(lesOptionstab);
            return lesOptionstab;
        }
        
        /**
        *
        * @param nom Prend le nom de l'utilisateur
        * @param prenom Prend le prénom de l'utilisateur
        * @return Retourne les informations sur le mail, le login et le mot de passe d'une personne sous forme d'un tableau
        * @throws SQLException
        */
       public int getIdOption(String choix) throws SQLException{
           String quest = "SELECT idOptionsServices FROM optionService WHERE libelle='"+choix+"'";
           int option = 0;
           rs = co.query(quest);
           if(rs.next()){
              option = rs.getInt("idOptionsServices");
           }
           return option;
       }
        
         public String[] getSalles(int idReservation) throws SQLException{
             String salle = "SELECT libelle FROM salle, infoSalle, salleResa WHERE infoSalle.fkidSalle=salle.idSalle AND salleResa.fkidInfoSalle=infoSalle.idInfoSalle AND salleResa.fkidReservation='"+idReservation+"'";
             ArrayList<String> salles = new ArrayList<>();  
             rs = co.query(salle);
             while(rs.next()){
                String lasalle = rs.getString("libelle");
                salles.add(lasalle);
            }
             String tableau[] = new String[salles.size()];
             tableau = salles.toArray(tableau);
             return tableau;
         }
         
         public String getDisposition(int idReservation, String salle) throws SQLException{
             String disposition = "SELECT disposition.libelle FROM disposition, salle, infoSalle, salleResa WHERE disposition.idDisposition=infoSalle.fkidDisposition AND salle.idSalle=infoSalle.fkidSalle AND salleResa.fkidInfoSalle=infoSalle.idInfoSalle AND salle.libelle='"+salle+"' AND salleResa.fkidReservation='"+idReservation+"'";
             rs = co.query(disposition);
             String dispo=null;
             while(rs.next()){
                dispo = rs.getString("libelle");
            }
             return dispo;
         }
         
         public int getIdInfoSalle(int idSalle, int idDisposition){
        	String infosalle="SELECT idInfoSalle FROM infosalle WHERE fkidDisposition="+idDisposition+" AND fkidSalle="+idSalle+"";
         	rs=co.query(infosalle);
         	int Info = 0;
         	try {
 				while(rs.next()){
 				    
 						Info = rs.getInt("idInfoSalle");
 					
 				}
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
         	return Info;
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
		String request = "SELECT login, password FROM Usager WHERE login='"+log+"' AND password='"+pwd+"'";
		rs = co.query(request);
		return rs.next();
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
        	
        	int coul = getCouleur(couleur);
            String quest1 = "SELECT * FROM "+nomTable+" WHERE libelle = '"+nom+"'";
            String quest2 = "SELECT * FROM salle, taches WHERE salle.fkidCouleur = "+coul+" OR taches.codeCouleur = '"+couleur+"'";
            return !((rs = co.query(quest1)).next() || (rs = co.query(quest2)).next());
        }

        /**
         *
         * @param nomTable Prend le nom de la table concernée : Salle || Tache
         * @param nom Prend le nom de la salle ou de la tache concernée
         * @return TRUE si le nom n'existe pas sinon FALSE
         * @throws SQLException
         */
        public boolean checkErreurModif(String nomTable, String nom, String id) throws SQLException{
            String quest = "SELECT * FROM "+nomTable+" WHERE '"+id+"' = '"+nom+"'";
            return !co.query(quest).next();
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
            return !rs.next();
        }
        
        public boolean checkClient(String nom, String prenom, String email, String phone) throws SQLException{
            try{
            	String request = "SELECT * FROM client WHERE mail = '"+email+"' OR (nom='"+nom+"' AND prenom='"+prenom+"') OR telephone='"+phone+"'";
            
            	rs = co.query(request);
            	return !rs.next();
            }catch (NullPointerException e){
            	return true;
            }
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
            return !rs.next();
        }
        
        
        public int checkResa(int idsalle, String dateDebut, String dateFin, String horaireDebut, String horaireFin) throws SQLException{
        	try{
        		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            	//String dateD=dateDebut.split("-")[2]+"-"+dateDebut.split("-")[1]+"-"+dateDebut.split("-")[0];
            	//String dateF=dateFin.split("-")[2]+"-"+dateFin.split("-")[1]+"-"+dateFin.split("-")[0];
            	String request = "SELECT reservation.idReservation, reservation.dateDebut, reservation.dateFin, reservation.heureDebut, reservation.heureFin FROM reservation, salleresa, infosalle WHERE reservation.idReservation = salleresa.fkidReservation AND salleresa.fkidInfoSalle=infosalle.idInfoSalle AND infosalle.fkidSalle="+idsalle+" GROUP BY reservation.idReservation, reservation.dateDebut, reservation.dateFin,reservation.heureDebut, reservation.heureFin";
            
            	System.out.println(request);
            	System.out.println("caca".equals("caca"));
            	System.out.println("caca".equals("prout"));
            	rs = co.query(request);
            	int id=0;
            	while(rs.next()){
            		String dD = rs.getString("dateDebut");
            		String dF = rs.getString("dateFin");
            		String hD = formatter.format(rs.getTime("heureDebut"));
            		String hF = formatter.format(rs.getTime("heureFin"));
            		if((ITcreerReservation1.comparerdates(dateDebut, dD) && ITcreerReservation1.comparerdates(dF ,dateDebut)) || (ITcreerReservation1.comparerdates(dateFin, dD) && ITcreerReservation1.comparerdates(dF, dateFin))|| (ITcreerReservation1.comparerdates(dD, dateDebut) && ITcreerReservation1.comparerdates(dateFin, dF))|| ITcreerReservation1.comparerheures(hF, horaireDebut, dateDebut, dF) || ITcreerReservation1.comparerheures(horaireFin, hD, dateFin, dD)){
            			id=rs.getInt("idReservation");
            			return id;
            		}
            		
            	}
            	return id;
            }catch (NullPointerException e){
            	return 0; 
			}
        }
        /*SELECT idReservation FROM reservation, salleresa, infosalle "
            			+ "WHERE (((substr(reservation.dateDebut, 7, 4) || '-' || substr(reservation.dateDebut, 4, 2) || '-' || substr(reservation.dateDebut, 1, 2)) BETWEEN '"+dateD+"' AND '"+dateF+"') "
            					+ "OR ((substr(reservation.dateFin, 7, 4) || '-' || substr(reservation.dateFin, 4, 2) || '-' || substr(reservation.dateFin, 1, 2)) BETWEEN '"+dateD+"' AND '"+dateF+"') "
            							+ "OR (substr(reservation.dateDebut, 7, 4) || '-' || substr(reservation.dateDebut, 4, 2) || '-' || substr(reservation.dateDebut, 1, 2))='"+dateF+"' AND  reservation.heureDebut > '"+horaireFin+":00"+"') "
            									+ "OR (substr(reservation.dateFin, 7, 4) || '-' || substr(reservation.dateFin, 4, 2) || '-' || substr(reservation.dateFin, 1, 2))='"+dateD+"' AND  reservation.heureFin < '"+horaireDebut+":00"+"')) "
            											+ "AND reservation.idReservation = salleresa.fkidReservation "
            											+ "AND salleresa.fkidInfoSalle=infosalle.idInfoSalle "
            											+ "AND infosalle.fkidSalle="+idsalle+"";*/

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
        public void MAJnom(String nomTable, String ancienNomChamps, String nouveauNomChamps, String idchamp){
            String quest = "UPDATE "+nomTable+" SET "+idchamp+" = '"+nouveauNomChamps+"' WHERE "+idchamp+" = '"+ancienNomChamps+"'";
            System.out.println(quest);
            co.update(quest);
            System.out.println("UPDATE REUSSIE");
        }
        
        
        
        
        /**
        *
        * @param nomTable Prend le nom de la table concernée : Salle || Tache
        * @param ancienNomChamps Prend l'ancien nom de l'attribut concerné
        * @param nouveauNomChamps Prend le nouveau nom de l'attribut concerné
        */
       public void MAJStrfromId(String nomTable, String changelement, String nouvelelement, int idelement, String idchamp){
           String quest = "UPDATE "+nomTable+" SET "+changelement+" = '"+nouvelelement+"' WHERE "+idchamp+" = '"+idelement+"'";
           System.out.println(quest);
           co.update(quest);
           System.out.println("UPDATE REUSSIE");
       }
       
       public void MAJIntfromId(String nomTable, String changelement, int nouvelelement, int idelement, String idchamp){
           String quest = "UPDATE "+nomTable+" SET "+changelement+" = '"+nouvelelement+"' WHERE "+idchamp+" = '"+idelement+"'";
           System.out.println(quest);
           co.update(quest);
           System.out.println("UPDATE REUSSIE");
       }
       
       public void MAJTimefromId(String nomTable, String changelement, String nouvelelement, int idelement, String idchamp){
           String quest = "UPDATE "+nomTable+" SET "+changelement+" = '"+Time.valueOf(nouvelelement)+"' WHERE "+idchamp+" = '"+idelement+"'";
           System.out.println(quest);
           co.update(quest);
           System.out.println("UPDATE REUSSIE");
       }

        /**
        *
        * @param nomTable Prend le nom de la table concernée : Salle || Tache
        * @param ancienNomChamps Prend l'ancien nom de l'attribut concerné
        * @param nouveauNomChamps Prend le nouveau nom de l'attribut concerné
        */
       public void MAJSuperficie(String nomTable, String ancienNomChamps, int nouveauNomChamps, String idchamp){
           String quest = "UPDATE "+nomTable+" SET "+idchamp+" = "+nouveauNomChamps+" WHERE libelle = '"+ancienNomChamps+"'";
           System.out.println(quest);
           co.update(quest);
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
       
       public void MAJUtilisateur(int id, String nom, String prenom, String email, int admin, String login, String mdp){
    	   String quest = "UPDATE usager SET nom = '"+nom+"', prenom = '"+prenom+"', mail = '"+email+"', administrateur = "+admin+", login = '"+login+"', password = '"+mdp+"' WHERE idUsager="+id+"";
           co.update(quest);
       }
       
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
        
        public void MAJprixPlateauxRepas(double prix){
            String quest = "UPDATE optionService SET prixHT = "+prix+" WHERE libelle = 'Plateaux repas'";
            co.query(quest);
        }

        public void MAJvalide(int valide,int idReservation) throws SQLException{
             String quest = "UPDATE reservations SET estValide = '"+valide+"' WHERE idReservation = '"+idReservation+"'";
             co.update(quest);
             System.out.println("UPDATE REUSSIE");
        }
        
        public void MAJdateD(String dateD,int idReservation) throws SQLException{
             String quest = "UPDATE reservations SET dateDebut = '"+dateD+"' WHERE idReservation = '"+idReservation+"'";
             co.update(quest);
        } 
        
        public void MAJdateF(String dateF, int idReservation) throws SQLException{
             String quest = "UPDATE reservations SET dateFin='"+dateF+"' WHERE idReservation = '"+idReservation+"'";
             co.update(quest);
        } 
        
        public void MAJheureD(String heureD, int idReservation) throws SQLException{
             String quest = "UPDATE reservations SET heureDebut = '"+heureD+"' WHERE idReservation = '"+idReservation+"'";
             co.update(quest);
        }
         
        public void MAJheureF(String heureF, int idReservation) throws SQLException{
             String quest = "UPDATE reservations SET heureFin = '"+heureF+"' WHERE idReservation = '"+idReservation+"'";
             co.update(quest);
        }
        
        public void MAJnbPersonnes(int nbP, int idReservation) throws SQLException{
             String quest = "UPDATE reservations SET nbPersonnes = '"+nbP+"' WHERE idReservation = '"+idReservation+"'";
             co.update(quest);
        }
        
        public void MAJnbHeures(int nbH,int idReservation) throws SQLException{
             String quest = "UPDATE reservations SET nbHeures = '"+nbH+"' WHERE idReservation = '"+idReservation+"'";
             co.update(quest);
        }
        
        public void MAJformule(int idReservation, String formule) throws SQLException{
             String quest = "UPDATE reservations SET fkidFormule=(SELECT idFormule FROM formule WHERE libelle='"+formule+"') WHERE idReservation = '"+idReservation+"'";
             co.update(quest);
        }
        
        public void MAJclient(int idReservation, String prenom, String nom) throws SQLException{
             String quest = "UPDATE reservations SET fkidClient = (SELECT idClient FROM client WHERE nom = '"+nom+"' AND prenom = '"+prenom+"') WHERE idReservation = '"+idReservation+"'";
             co.update(quest);
        }
        
        public void MAJchoix(int idChoix, int choix) throws SQLException{
             String quest = "UPDATE choix SET fkidOptionsServices = "+choix+" WHERE idChoix="+idChoix+"";
             co.update(quest);
        }
        
        public void MAJsalle(int nbPersonnes, int infosalle, int idReservation) throws SQLException{
            String quest = "UPDATE salleResa SET nbPersonnes="+nbPersonnes+", fkidInfoSalle = "+infosalle+" WHERE fkidReservation = "+idReservation+"";
            co.update(quest);
        }
        
        public void MAJdisposition(String salle, String disposition, int idReservation) throws SQLException{
             String quest = "UPDATE salleResa SET fkidInfoSalle = (SELECT idInfoSalle FROM infoSalle WHERE fkidSalle = (SELECT idSalle FROM salle WHERE libelle ='"+salle+"') AND fkidDisposition=(SELECT idDisposition FROM disposition WHERE libelle = '"+disposition+"')) WHERE fkidReservation ='101' '"+idReservation+"'";
             co.update(quest);
        }
        
        public void MAJReservation(int idReservation, String Datedebut, String Datefin, String Heuredebut, String Heurefin, int nbPersonne, double nbHeure, int idClient, int idFormule){
        	
        	String nouvelleResa = "UPDATE reservation SET dateDebut='"+Datedebut+"', dateFin='"+Datefin+"', heureDebut='"+Time.valueOf(Heuredebut)+"', heureFin='"+Time.valueOf(Heurefin)+"', nbPersonnes="+nbPersonne+", nbHeures="+nbHeure+", fkidClient = "+idClient+", fkidFormule="+idFormule+" WHERE idReservation="+idReservation+"";
        	co.update(nouvelleResa);
        	System.out.println(Time.valueOf(Heuredebut));
        }
        
        public void MAJTache(int id, String nom, int type, String comment, String dateDebut, String dateFin, String horaireDebut, String horaireFin){
            String quest = "UPDATE tache SET nomTache='"+nom+"', fkidTypeTache="+type+", descriptif='"+comment+"', dateDebut='"+dateDebut+"', dateFin='"+dateFin+"', heureDebut='"+Time.valueOf(horaireDebut)+"', heureFin='"+Time.valueOf(horaireFin)+"' WHERE idTache="+id+"";
            co.update(quest);
            System.out.println("MAJ Reussie");
        }
        
        public void MAJTypeTache(int id, String nom, String hex){
            String quest = "UPDATE typeTache SET nom='"+nom+"', codeCouleur='"+hex+"' WHERE idTypeTache="+id+"";
            co.update(quest);
            System.out.println("MAJ Reussie");
        }
        
        public void MAJSalle(int id, String name, int superficie, String couleur, String comment) throws SQLException{
            String quest = "UPDATE salle SET libelle='"+name+"', superficie="+superficie+", codeCouleur='"+couleur+"', descriptif='"+comment+"' WHERE idSalle="+id+"";
            co.update(quest);
            System.out.println("MAJ réussie");
        }
        
        public void MAJClient(int id, String nom, String prenom, String email, String phone, String adresse, String entite, String comment) throws SQLException{
            String quest = "UPDATE client SET nom='"+nom+"', prenom='"+prenom+"', eMail='"+email+"', telephone='"+phone+"', adresseFacturation='"+adresse+"', entite='"+entite+"', commentaire='"+comment+"' WHERE idClient="+id+"";
            co.update(quest);
            System.out.println("MAJ réussie");
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
         * @throws SQLException 
         */
        public void ajoutSalle(String name, int superficie, String couleur, String comment) throws SQLException{
            String quest = "INSERT INTO salle(libelle, superficie, codeCouleur, descriptif) VALUES('"+name+"', "+superficie+", '"+couleur+"', '"+comment+"')";
            co.execut(quest);
            System.out.println("Insertion réussie");
        }
       

        /**
         *
         * @param name Prend le nom que la nouvelle tache aura
         * @param couleur Prend le code couleur HEXADECIMAL que la nouvelle tache aura
         * @param comment Prend le commentaire que la nouvelle tache aura
         */
        public void ajoutTache(String nom, int type, String comment, String dateDebut, String dateFin, String horaireDebut, String horaireFin){
            String quest = "INSERT INTO tache(nomTache, fkidTypeTache, descriptif, dateDebut, dateFin, heureDebut, heureFin) VALUES('"+nom+"', "+type+", '"+comment+"', '"+dateDebut+"', '"+dateFin+"', '"+Time.valueOf(horaireDebut)+"', '"+Time.valueOf(horaireFin)+"' )";
            co.execut(quest);
            System.out.println("Insertion réussie");
        }

        public void ajoutClient(String nom, String prenom, String email, String phone, String adresse, String entite, String comment){
        	String quest = "INSERT INTO client(nom, prenom, adresseFacturation, entite, telephone, eMail,commentaire) VALUES('"+nom+"', '"+prenom+"', '"+adresse+"', '"+entite+"', '"+phone+"', '"+email+"', '"+comment+"' )";
            co.execut(quest);
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
            String request = "INSERT INTO usager(nom, prenom, mail, administrateur, login, password) VALUES('"+nom+"','"+prenom+"','"+email+"',"+admin+",'"+login+"','"+mdp+"')";
            co.execut(request);
        }
        
        public void ajoutChoix(int idReservation, int choix, int salleresa) throws SQLException{
        	 
        	 
        		 String nouveauChoix = "INSERT INTO choix (fkidReservation, fkidOptionsServices, fkidSalleResa) VALUES ("+idReservation+", "+choix+", "+salleresa+")";
        	 	 System.out.print(nouveauChoix);
        		 co.execut(nouveauChoix);
        	 
        }
        
        public void ajoutSalleResa(int nbPersonnes, int idReservation, int Info){
        	
        	
            String nouvelleSalle = "INSERT INTO salleResa (nbPersonnes, fkidReservation, fkidInfoSalle) VALUES ("+nbPersonnes+", "+idReservation+", "+Info+")";
            co.execut(nouvelleSalle);
        }
        
        public void ajoutTypeTache(String nom, String codeCouleur){
            String nouveauType = "INSERT INTO typeTache (nom, codeCouleur) VALUES ('"+nom+"', '"+codeCouleur+"')";
            co.execut(nouveauType);
        }
        
        public void ajoutReservation(String Datedebut, String Datefin, String Heuredebut, String Heurefin, int nbPersonne, double nbHeure, int idClient, int idFormule){
        	
        	String nouvelleResa = "INSERT INTO reservation (estValide, dateDebut, dateFin, heureDebut, heureFin, nbPersonnes, nbHeures, fkidClient, fkidFormule) VALUES (0, '"+Datedebut+"', '"+Datefin+"', '"+Time.valueOf(Heuredebut)+"', '"+Time.valueOf(Heurefin)+"', "+nbPersonne+", "+nbHeure+", "+idClient+", "+idFormule+")";
        	co.execut(nouvelleResa);
        	System.out.println(Time.valueOf(Heuredebut));
        }
        
        public void ajoutFacture(double montant, int resa) throws SQLException{
            String nouvelleFacture = "INSERT INTO facture (montant, fkidReservation) VALUES ("+montant+", "+resa+")";
            co.execut(nouvelleFacture);
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
            co.execut(request);
            System.out.println("DELETE REUSSIE");
        }

        /**
         *
         * @param login Prend le login d'un utilisateur
         */
        public void deleteUser(String login){
            String quest = "DELETE FROM usager WHERE login = '"+login+"'";
            System.out.println(quest);
            co.execut(quest);
            System.out.println("SUPPRESSION REUSSIE");
        }
        
        /**
        *
        * @param table Prend le nom de la table concernée
        * @param attribut Prend le nom de l'attribut concerné
        * @param identifiant Prend l'identifiant concern� pour l'attribut concerné
        */
       public void delete(String table, String attribut, String identifiant){
           String request = "DELETE FROM "+table+" WHERE "+attribut+" = '"+identifiant+"'";
           co.execut(request);
           System.out.println("DELETE REUSSIE");
       }
       
       public void deleteSalleResa(int idReservation){
    	   String request = "DELETE FROM salleResa WHERE fkidReservation="+idReservation+"";
    	   co.execut(request);
           System.out.println("DELETE REUSSIE");
       }
       
       public void deleteChoix(int idReservation){
    	   String request = "DELETE FROM choix WHERE fkidReservation="+idReservation+"";
    	   co.execut(request);
           System.out.println("DELETE REUSSIE");
       }
       
       public void deleteResa(int idReservation){
    	   String request = "DELETE FROM reservation WHERE idReservation="+idReservation+"";
    	   co.execut(request);
    	   request = "DELETE FROM salleResa WHERE fkidReservation="+idReservation+"";
    	   co.execut(request);
    	   request = "DELETE FROM choix WHERE fkidReservation="+idReservation+"";
    	   co.execut(request);
           System.out.println("DELETE REUSSIE");
       }
       
       public void deleteClient(String prenom, String nom){
    	   String request = "DELETE FROM client WHERE prenom='"+prenom+"' AND nom='"+nom+"'";
    	   co.execut(request);
           System.out.println("DELETE REUSSIE");
       }

	

	
}
        /*
        ========================================================================
                                    FIN SUPPRESSION
        ========================================================================
        */

 