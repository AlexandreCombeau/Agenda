/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import reservations.Evenement;
import usager.Usager;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
               String name = rs.getString("libelle");
               nom[i] = name;
               i++;
            }
            return nom;
        }
        
        /**
        *
        * @param table Prend le nom de la table : Salle || Tache
        * @return Retourne toutes les Salles ou toutes les Taches
        * @throws SQLException
        */
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
       public String getEmail(String user){
           String quest = "SELECT mail FROM usager WHERE login = '"+user+"'";
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
       
        /**
         * 
         * @param choix Prend "Option" ou "Service"
         * @return Retourne toutes les options ou tout les services
         * @throws SQLException 
         */
        public String[] getOptionService(String choix) throws SQLException{
            String quest = "SELECT libelle FROM optionsServices WHERE nature = '"+choix+"'";
            String quest1 = "SELECT COUNT(idOptionsServices) FROM optionsServices WHERE nature = '"+choix+"'";

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
        public int getSuperficieSalle(String nomSalle) {
            String quest = "SELECT superficie FROM salles WHERE libelle = '"+nomSalle+"'";
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
        
        /**
         * 
         * @param nomSalle Prend le nom de la salle concernée
         * @return Retourne le descriptif de la salle en question
         * 
         */
        public String getCommentSalle(String nomSalle){
            String quest = "SELECT descriptif FROM salles WHERE libelle = '"+nomSalle+"'";
            try{
            rs = co.query(quest);
            if(rs.next()){
                String comment = rs.getString("descriptif");
                return comment;
            }
            } catch (SQLException ex){
            	
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
            String quest = "SELECT descriptif FROM optionsservices WHERE libelle = '"+service+"'";
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
            String quest = "SELECT montant FROM TarifSalleFormule, Salle, Formule WHERE Salle.idSalle = TarifSalleFormule.fk_idSalle AND Formule.idFormule = TarifSalleFormule.fk_idFormule AND Salle.nomSalle = '"+nomSalle+"' AND Formule.nomFormule = '"+nomFormule+"'";
            rs = co.query(quest);
            if(rs.next()){
                double tarif = rs.getDouble("montant");
                return tarif;
            }
            return 0;
        }
        
        public double getTarifService(String nomservice) throws SQLException{
            String quest = "SELECT prixHT FROM optionsservices WHERE libelle = '"+nomservice+"'";
            rs = co.query(quest);
            if(rs.next()){
                double tarif = rs.getDouble("prixHT");
                return tarif;
            }
            return 0;
        }
        
        public double getTarifServiceint(int idservice) throws SQLException{
            String quest = "SELECT prixHT FROM optionsservices WHERE idOptionsServices = "+idservice+"";
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
    	 rs = co.query("SELECT id FROM couleurs WHERE codeHexa = '"+couleurHex+"'");
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
            String request = "SELECT dateDebut, dateFin, nbPersonnes, valide FROM r�servations WHERE dateDebut > '" + dateDebut +"' AND dateFin < '" + dateFin + "'";
            rs = co.query(request);
            return rs;
        }

        
        // in work <tag>
        public ResultSet getReservationsJour(Calendar cal) throws SQLException {
            ArrayList<Evenement> ListeEvenements = new ArrayList<>();            
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String dateDebut = sdf.format(cal.getTime());
            //cal.add(Calendar.DATE, 1); // obtient le jour suivant
            String dateFin = sdf.format(cal.getTime());
            String request = "SELECT dateDebut, dateFin, nbPersonnes, estValide, heureDebut, heureFin FROM r�servations WHERE dateDebut <= '" + dateDebut +"' AND dateFin >= '" + dateFin + "'";
            //cal.add(Calendar.DATE, -1); // reviens à la date de base
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
            String quest = "SELECT nom, prenom FROM clients";
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
            String quest = "SELECT nom, prenom, adresseFacturation, entite, telephone, eMail FROM clients WHERE idClient='"+id+"'";
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
            String quest = "SELECT idClient FROM clients WHERE nom='"+nom+"' AND prenom='"+prenom+"'";
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
        
        public String[] getnomsClients() throws SQLException{
            String quest = "SELECT nom FROM clients";
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
            String quest = "SELECT prenom FROM clients";
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
            String quest = "SELECT idReservation FROM r�servations WHERE fkidClient='"+idClient+"' AND dateDebut='"+dateDe+"'";
            rs = co.query(quest);
            int IdReservation=0;
            while (rs.next()){
                IdReservation = rs.getInt("idReservation");
                System.out.println(IdReservation);
            }
            return IdReservation;
        }
    /**
     *
     * @param idReservation
     * @param laFormule
     * @return
     * @throws SQLException
     */
        public String[] getInfosReservation(int idReservation) throws SQLException{
            String heureF = "SELECT heureFin FROM r�servations WHERE idReservation="+idReservation+"";
            String nom = "SELECT nom, prenom FROM clients, r�servations WHERE clients.idClient=r�servations.fkidClient AND r�servations.idReservation="+idReservation+"";
            String heureD = "SELECT heureDebut  FROM r�servations WHERE idReservation="+idReservation+"";
            String dateF = "SELECT dateFin FROM r�servations WHERE idReservation="+idReservation+"";
            String nbP = "SELECT nbPersonnes  FROM r�servations WHERE idReservation="+idReservation+"";
            String nbH = "SELECT nbHeures  FROM r�servations WHERE idReservation="+idReservation+"";
            String dateD = "SELECT dateDebut FROM r�servations WHERE idReservation="+idReservation+"";
            String service = "SELECT libelle FROM optionsServices, choix WHERE optionsServices.idOptionsServices=choix.fkidOptionsServices AND choix.fkidReservation="+idReservation+" AND optionsServices.nature='service'";
            String option = "SELECT libelle FROM optionsServices, choix WHERE optionsServices.idOptionsServices=choix.fkidOptionsServices AND choix.fkidReservation="+idReservation+" AND optionsservices.nature='option'";
            String formule = "SELECT libelle FROM formule, r�servations WHERE formule.idFormule=r�servations.fkidFormule AND r�servations.idReservation="+idReservation+"";
            String salle = "SELECT libelle FROM salles, infoSalle, sallesResa WHERE infoSalle.fkidSalle=salles.idSalle AND sallesResa.fkidInfoSalle=infoSalle.idInfoSalle AND sallesResa.fkidReservation="+idReservation+"";
            String disposition = "SELECT libelle FROM sallesResa, disposition, infoSalle WHERE infoSalle.fkidDisposition=disposition.idDisposition AND sallesResa.fkidInfoSalle=infoSalle.idInfoSalle AND sallesResa.fkidReservation='"+idReservation+"'";

            ArrayList<String> infosReservation = new ArrayList<>();  
            
            rs = co.query(dateD);
            while(rs.next()){
                String DateDebut = rs.getString("dateDebut");
                infosReservation.add("Date de début: " +DateDebut);
            }
            
            rs = co.query(dateF);
            while(rs.next()){
                String dateFin = rs.getString("dateFin");
                infosReservation.add("Date de fin : " +dateFin);
            }
            
            rs = co.query(heureD);
            while(rs.next()){
                String heureDebut = rs.getString("heureDebut");
                infosReservation.add("Heure de début : "+heureDebut);
            }
            
            rs = co.query(heureF);
            while(rs.next()){
                String heureFin = rs.getString("heureFin");
                infosReservation.add("Heure de fin : "+ heureFin);
            }
            
            rs = co.query(service);
            ArrayList<String> lesServices = new ArrayList<>();  
            while(rs.next()){
                String leService = rs.getString("libelle");
                lesServices.add(leService);
            }
            if (!lesServices.isEmpty()){
                infosReservation.add("Service(s) : " +lesServices);
            }
            else{
                infosReservation.add("Service(s) : aucun service");
            }
            
            
            rs = co.query(option);
            ArrayList<String> lesOptions = new ArrayList<>();  
            while(rs.next()){
                String lOption = rs.getString("libelle");
                lesOptions.add(lOption);
            }
            if (!lesOptions.isEmpty()){
                infosReservation.add("Option(s) : " +lesOptions);
            }
            else{
                infosReservation.add("Option(s) : aucune option");
            }
            
            
            rs = co.query(formule);
            while(rs.next()){
                String laformule = rs.getString("libelle");
                infosReservation.add("Formule : "+laformule);
            }
            
            rs = co.query(nbP);
            while(rs.next()){
                int nb = rs.getInt("nbPersonnes");
                String nbS = Integer.toString(nb);
                infosReservation.add("Nombre de personnes :"+nbS);
            }
            
            rs = co.query(nbH);
            while(rs.next()){
                int nb = rs.getInt("nbHeures");
                String nbS = Integer.toString(nb);
                infosReservation.add("Nombre d'heures :"+nbS);
            }
            
            rs = co.query(salle);
            ArrayList<String> lesSalles = new ArrayList<>();  
            while(rs.next()){
               String laSalle = rs.getString("libelle");
               lesSalles.add(laSalle);
            }         
            infosReservation.add("Salle(s) : "+lesSalles);
            
            rs = co.query(disposition);
            while(rs.next()){
                String laDisposition = rs.getString("libelle");
                infosReservation.add("Disposition : "+laDisposition);
            }
            
            rs = co.query(nom);
            while(rs.next()){
               String name = rs.getString("prenom")+" "+rs.getString("nom");
               infosReservation.add("Client : "+name);
            }
           
            
            String tableau[] = new String[infosReservation.size()];
            tableau = infosReservation.toArray(tableau);
            
            System.out.println(Arrays.toString(tableau));
            return tableau;
        }
            
        
        public String getNomClient(int idReservation) throws SQLException{
            String nom = "SELECT nom FROM client, reservations WHERE client.idClient=reservations.fkidClient AND reservations.idReservation='"+idReservation+"'";
            rs = co.query(nom);
            String name = "";
            while(rs.next()){
               name = rs.getString("nom");
            }
            return name; 
        }
        
        public String getPrenomClient(int idReservation) throws SQLException{
            String prenom = "SELECT nom, prenom FROM client, reservations WHERE client.idClient=reservations.fkidClient AND reservations.idReservation='"+idReservation+"'";
            rs = co.query(prenom);
            String name = "";
            while(rs.next()){
               name = rs.getString("prenom");
            }
            return name;
        }
        
        public int getidFacture(int idresa, double montant) throws SQLException{
        	String quest = "SELECT idFacture FROM factures WHERE montant ="+montant+" AND fkidReservation="+idresa+"";
            rs = co.query(quest);
            int idFormule=5;
            while (rs.next()){
                idFormule = rs.getInt("idFacture");
            }
            return idFormule;
        }
        
        
        public String[] getDatesReservations(int idClient) throws SQLException{
            String dates = "SELECT dateDebut FROM r�servations WHERE fkidClient="+idClient+"";
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
            String dateR = "SELECT dateDebut FROM r�servations WHERE idReservation="+idReservation+"";
            rs = co.query(dateR);
            String date = "";
            while(rs.next()){
               date = rs.getString("dateDebut");
            }
            return date;
        }
        
        public String getValidite(int idReservation) throws SQLException{
            String validite = "SELECT estValide FROM r�servations WHERE idReservation="+idReservation+"";
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
            String service = "SELECT libelle FROM optionsServices WHERE nature='service'";
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
            String option = "SELECT libelle FROM optionsServices WHERE nature='option'";
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
           String quest = "SELECT idOptionsServices FROM optionsServices WHERE libelle='"+choix+"'";
           int option = 0;
           rs = co.query(quest);
           if(rs.next()){
              option = rs.getInt("idOptionsServices");
           }
           return option;
       }
        
         public String[] getSalles(int idReservation) throws SQLException{
             String salle = "SELECT libelle FROM salle, infoSalle, sallesResa WHERE infoSalle.fkidSalle=salle.idSalle AND sallesResa.fkidInfoSalle=infoSalle.idInfoSalle AND sallesResa.fkidReservation='"+idReservation+"'";
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
             String disposition = "SELECT disposition.libelle FROM disposition, salle, infoSalle, sallesResa WHERE disposition.idDisposition=infoSalle.fkidDisposition AND salle.idSalle=infoSalle.fkidSalle AND sallesResa.fkidInfoSalle=infoSalle.idInfoSalle AND salle.libelle='"+salle+"' AND sallesResa.fkidReservation='"+idReservation+"'";
             rs = co.query(disposition);
             String dispo=null;
             while(rs.next()){
                dispo = rs.getString("libelle");
            }
             return dispo;
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
            String quest2 = "SELECT * FROM salles, taches WHERE salles.fkidCouleur = "+coul+" OR taches.codeCouleur = '"+couleur+"'";
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
            String quest = "UPDATE optionsServices SET prixHT = "+prix+" WHERE libelle = 'Plateaux repas'";
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
        
        public void MAJchoix(int idReservation, String choix) throws SQLException{
             String quest = "UPDATE choix SET fkidOptionsServices = (SELECT idOptionsServices from optionsServices where libelle = '"+choix+"') WHERE fkidReservation = '"+idReservation+"'";
             co.update(quest);
        }
        
        public void MAJsalle(String salle, String disposition, int idReservation) throws SQLException{
            String quest = "UPDATE sallesResa SET fkidInfoSalle = (SELECT idInfoSalle FROM infoSalle WHERE fkidSalle = (SELECT idSalle FROM salle WHERE libelle ='"+salle+"') AND fkidDisposition=(SELECT idDisposition FROM disposition WHERE libelle = '"+disposition+"')) WHERE fkidReservation = '"+idReservation+"'";
            co.update(quest);
        }
        
        public void MAJdisposition(String salle, String disposition, int idReservation) throws SQLException{
             String quest = "UPDATE sallesResa SET fkidInfoSalle = (SELECT idInfoSalle FROM infoSalle WHERE fkidSalle = (SELECT idSalle FROM salle WHERE libelle ='"+salle+"') AND fkidDisposition=(SELECT idDisposition FROM disposition WHERE libelle = '"+disposition+"')) WHERE fkidReservation ='101' '"+idReservation+"'";
             co.update(quest);
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
        	int idcouleur=getCouleur(couleur);
            String quest = "INSERT INTO salles(libelle, superficie, fkidCouleur, descriptif) VALUES('"+name+"', "+superficie+", "+idcouleur+", '"+comment+"')";
            co.execut(quest);
            System.out.println("Insertion réussie");
        }
       

        /**
         *
         * @param name Prend le nom que la nouvelle tache aura
         * @param couleur Prend le code couleur HEXADECIMAL que la nouvelle tache aura
         * @param comment Prend le commentaire que la nouvelle tache aura
         */
        public void ajoutTache(String name, String couleur, String comment){
            String quest = "INSERT INTO Taches(nomTache, codeCouleur, descriptif) VALUES('"+name+"', '"+couleur+"', '"+comment+"')";
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
            String request = "INSERT INTO usager(nom, prenom, mail, administrateur, login, password) VALUES('"+nom+"','"+prenom+"','"+email+"',"+admin+",'"+login+"','"+mdp+"')";
            co.execut(request);
        }
        
        public void ajoutChoix(int idReservation, String choix) throws SQLException{
        	 int idOption=getIdOption(choix);
        	 if(idOption!=0){
        		 String nouveauChoix = "INSERT INTO choix (fkidReservation, fkidOptionsServices) VALUES ("+idReservation+", "+idOption+")";
        	 	 co.execut(nouveauChoix);
        	 }
        }
        
        public void ajoutSalleResa(int idReservation, String salle, String disposition) throws SQLException{
            String nouvelleSalle = "INSERT INTO sallesResa (fkidReservation, fkidInfoSalle) VALUES ('"+idReservation+"', (SELECT idInfoSalle FROM infoSalle WHERE fkidSalle=(SELECT idSalle FROM salle WHERE libelle = '"+salle+"') AND fkiddisposition=(SELECT idDisposition FROM disposition WHERE libelle='"+disposition+"')))";
            co.execut(nouvelleSalle);
        }
        
        public void ajoutReservation(String Datedebut, String Datefin, String Heuredebut, String Heurefin, int nbPersonne, int nbHeure, int idClient, int idFormule){
        	System.out.println(Time.valueOf(Heuredebut));
        	String nouvelleResa = "INSERT INTO r�servations (estValide, dateDebut, dateFin, heureDebut, heureFin, nbPersonnes, nbHeures, fkidClient, fkidFormule) VALUES (0, '"+Datedebut+"', '"+Datefin+"', '"+Time.valueOf(Heuredebut)+"', '"+Time.valueOf(Heurefin)+"', "+nbPersonne+", "+nbHeure+", "+idClient+", "+idFormule+")";
        	co.execut(nouvelleResa);
        }
        
        public void ajoutFacture(double montant, int resa) throws SQLException{
            String nouvelleFacture = "INSERT INTO factures (montant, fkidReservation) VALUES ("+montant+", "+resa+")";
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
            co.update(request);
            System.out.println("DELETE REUSSIE");
        }

        /**
         *
         * @param login Prend le login d'un utilisateur
         */
        public void deleteUser(String login){
            String quest = "DELETE FROM usager WHERE login = '"+login+"'";
            System.out.println(quest);
            co.update(quest);
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
           co.update(request);
           System.out.println("DELETE REUSSIE");
       }
}
        /*
        ========================================================================
                                    FIN SUPPRESSION
        ========================================================================
        */

 