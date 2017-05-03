/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalem.labd;
import villalem.reservations.Evenement;
import villalem.usager.Usager;

import java.awt.Color;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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
    	PreparedStatement request = null;
    	request = co.getConnection().prepareStatement("SELECT * FROM Usager WHERE login = ? AND password = ? ");
    	request.setString(1, login);
    	request.setString(2, pwd);
    	rs = request.executeQuery();
        while (rs.next() ) {
            int identifiant = rs.getInt("id");
            int admin = rs.getInt("administrateur");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
        
            unUsager = new Usager(identifiant, admin, nom, prenom);
        }
        return unUsager;
    }


    /**
     *
     * @param table Prend le nom de la table : Salle || evenement
     * @return Retourne toutes les Salles ou toutes les type d'evenement
     * @throws SQLException
     */
    @Deprecated
    public String[] getSalleTacheEntiteFormule(String table) throws SQLException{
    	//TODO
    	
    	String quest = "SELECT * FROM "+table;
        String quest1 = "SELECT COUNT(id"+table+") FROM "+table;
        String[] nom = {};
        rs = co.query(quest1);
        while(rs.next()){
            int longueurTableau = rs.getInt("COUNT(id"+table+")");
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
     * @param choix Prend "Option" ou "Service"
     * @return Retourne toutes les options ou tout les services
     * @throws SQLException 
     */
    @Deprecated
    public String[] getOptionService(String choix) throws SQLException{
        String quest = "SELECT libelle FROM OptionsServices WHERE nature = '"+choix+"'";
        String quest1 = "SELECT COUNT(idOptionsServices) FROM OptionsServices WHERE nature = '"+choix+"'";
        String[] nom = {};
        rs = co.query(quest1);
        while(rs.next()){
        int longueurTableau = rs.getInt("COUNT(idOptionsServices)");         
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
     * @param nomSalle Prend le nom de la salle concernée
     * @return Retourne le descriptif de la salle en question
     * @throws SQLException 
     */
    public String getCommentSalle(String nomSalle) throws SQLException{
    	PreparedStatement request = co.getConnection().prepareStatement("SELECT descriptif FROM Salle WHERE nom = ?");
        request.setString(1, nomSalle);
        rs = request.executeQuery();
        if(rs.next()){
            return rs.getString("descriptif");
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
    	PreparedStatement request = co.getConnection().prepareStatement("SELECT descriptif FROM services WHERE intitule = ?");	
    	request.setString(1, service);
    	rs = request.executeQuery();
        if(rs.next()){ 
            return rs.getString("descriptif");
        }
        return "";
    }
    
    /**
     * 
     * @param nomSalle Prend le nom de la salle concernée
     * @param nomFormule Prend le nom de la formule concernée
     * @return retourne le tarif
     * @throws SQLException 
     */
    //TODO
    public double getTarifSalle(String nomSalle, String nomFormule) throws SQLException{
        String quest = "SELECT tarif FROM TarifsSalles, Salle, Formule WHERE Salle.idSalle = TarifsSalles.fkidSalle AND Formule.idFormule = TarifsSalles.fkidFormule AND Salle.libelle = '"+nomSalle+"' AND Formule.libelle = '"+nomFormule+"'";
        rs = co.query(quest);
        if(rs.next()){ 
            return  rs.getDouble("tarif");
        }
        return 0;
    }
    
    
    /**
     * Cette methode renvoi le prix HT d'un service, si le service n'a pas de prix il sera indiqué comme coutant 0
     * @param nomService
     * @return prixHT du service
     * @throws SQLException
     */
    public double getTarifService(String nomService) throws SQLException{
    	PreparedStatement request = co.getConnection().prepareStatement("SELECT prixHT FROM services WHERE libelle = ?");;
    	request.setString(1, nomService);
        rs = request.executeQuery();
        if(rs.next()){
            return rs.getDouble("prixHT");
        }
        return 0;
    }

    /**
     *
     * @param nom Prend le nom de la tache concernée, on peut retrouver les differentes taches dans le google agenda
     * @return Retourne un "Color" en RGB, si le nom de la table est invalide on retourne du noir
     * @throws SQLException
     */
    public Color hex2Rgb(String nom) throws SQLException {
    	PreparedStatement request = co.getConnection().prepareStatement("SELECT codeHexa FROM couleurs, typeevenement where typeevenement.id = couleurs.id and typeevenement.nom = ?");
    	request.setString(1, nom);
    	rs = request.executeQuery();
        if(rs.next()){
        	 return Color.decode(rs.getString(1));
        }
        else
        	return new Color(0,0,0);
       
    }

    //in work <tag>
    public String getInformationReservation(int idReservation) throws SQLException{
    	PreparedStatement request = null;
    	String str ="";
    	String infoClient = "";
        request = co.getConnection().prepareStatement("");
        request.setInt(1, idReservation);
        rs = request.executeQuery();			
        return ""	;
    }
    
    /**
     * Cette methode retourne la liste des options selectionnées pour une reservation
     * @param idReservation
     * @return listOptions
     * @throws SQLException
     */
    public List<String> getOptionsReservation(int idReservation) throws SQLException {
    	List<String> listOptions = new ArrayList<>();
    	PreparedStatement request = co.getConnection().prepareStatement("select case when ( options & 1 ) THEN (select type from options  where options.valeur = 1) ELSE 'false' END,case when ( options & 2 ) THEN (select type from options  where options.valeur = 2) ELSE 'false' END,case when ( options & 4 ) THEN (select type from options  where options.valeur = 4) ELSE 'false' END,case when ( options & 8 ) THEN (select type from options  where options.valeur = 8) ELSE 'false' END,case when ( options & 16 ) THEN (select type from options  where options.valeur = 16) ELSE 'false' END,case when ( options & 32 ) THEN (select type from options  where options.valeur = 32) ELSE 'false' END from reservation where id = ?");
        request.setInt(1, idReservation);
        request.executeQuery();
        if(rs.next()) {
        	for(int i=0;i<rs.getMetaData().getColumnCount();i++) {
        		String temp = rs.getString(i);
        		if(!("false".equals(temp))) {
        			listOptions.add(temp);
        		}
        	}
        }
        rs = request.executeQuery();
    	return listOptions;
    }
    
    /**
     * Cette methode retourne la liste des services selectionnées pour une reservation
     * @param idReservation
     * @return listServices
     * @throws SQLException
     */
    public List<String> getServicesReservation(int idReservation) throws SQLException{
    	List<String> listServices = new ArrayList<>();
    	PreparedStatement request = co.getConnection().prepareStatement("select services, case when (services & 1) then (select intitule from services where services.valeur = 1) else 'false' end, case when (services & 2) then (select intitule from services where services.valeur = 2) else 'false' end, case when (services & 4) then (select intitule from services where services.valeur = 4) else 'false' end, case when (services & 8) then (select intitule from services where services.valeur = 8) else 'false' end, case when (services & 16) then (select intitule from services where services.valeur = 16) else 'false' end,case when (services & 32) then (select intitule from services where services.valeur = 32) else 'false' end,case when (services & 64) then (select intitule from services where services.valeur = 64) else 'false' end, case when (services & 128) then (select intitule from services where services.valeur = 128) else 'false' end, case when (services & 256) then (select intitule from services where services.valeur = 256) else 'false' end from reservation where id = ?");	
        request.setInt(1, idReservation);
        request.executeQuery();
        if(rs.next()) {
        	for(int i=0;i<rs.getMetaData().getColumnCount();i++) {
        		String temp = rs.getString(i);
        		if(!("false".equals(temp))) {
        			listServices.add(temp);
        		}
        	}
        }
        rs = request.executeQuery();
    	return listServices;
    }

    /**
     * @param cal
     * @return Retourne les reservations d'une semaine donnée
     */
    //TODO
    public ResultSet getReservationsSemaine(Calendar cal) {
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateDebut = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 7); // obtient le dernier jour de la semaine (un dimanche)
        String dateFin = sdf.format(cal.getTime());
        String request = "SELECT * WHERE dateDebut > '" + dateDebut +"' AND dateFin < '" + dateFin + "'";
        rs = co.query(request);
        return rs;
    }

    
    /**
     * Cette method renvoie les evenement d'un jour
     * @param Calendar cal, on utilise cal pour determiner de quelles evenements ont renvoient
     * @return resultset de la requete
     * @throws SQLException
     */
    public ResultSet getEvenementsJour(Calendar cal) throws SQLException {  
        PreparedStatement request = null;
        request = co.getConnection().prepareStatement("SELECT * FROM evenement WHERE dateDebut <= ? AND dateFin >= ?");
        request.setDate(1, Date.valueOf(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH))));
        request.setDate(2, Date.valueOf(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH))));
        rs = request.executeQuery();
        return rs;
    }	
    
    /**
     * Cette methode renvoie les informations essentielles d'un evenement
     * @param idEvenement
     * @return les horraires de cet evenement 
     * @throws SQLException
     */
    public String getInfoEvenement(int idEvenement) throws SQLException {
    	PreparedStatement request = null;
    	request = co.getConnection().prepareStatement("SELECT * FROM evenement WHERE id = ?");
    	request.setInt(1, idEvenement);
    	rs = request.executeQuery();
    	if(rs.next()) {
    		return "Commence le "+rs.getString("dateDebut")+" à "+rs.getString("heureDebut")+" et finis le "+rs.getString("dateFin")+" à "+rs.getString("heureFin")+"\n";
    	}
    	else {
    		return "";
    	}
    }
    
    /**
     * Retourne les informations concernants une reservation
     * @param idEvenement
     * @return message, il contient toutes les informations utilse concernant une reservation et mis proprement dans un message
     * @throws SQLException
     */ 
    //TODO
    public String getInfoReservation(int idEvenement) throws SQLException {
    	PreparedStatement request = co.getConnection().prepareStatement("select * from reservation, evenement where reservation.id = evenement.reservation and evenement.id = ?");
    	request.setInt(1, idEvenement);
    	rs = request.executeQuery();
    	if(rs.next()) {
    		int idReservation = rs.getInt("reservation");
        	List<String> options = this.getOptionsReservation(idReservation);
        	List<String> services = this.getServicesReservation(idReservation);
        	
        	String message = "Cette reservation dure "+rs.getInt("id")+"\n"
        			+"Il y aura "+rs.getInt("nbPersonnes")+" à cette reservation \n"
        			+"Options : \n\t"
        			+options.stream().reduce(", ", String::concat)+"\n"
        			+"Services : \n\t"
        			+services.stream().reduce(", ", String::concat);
        	
        	return message;
    	}
    	else {
    		return "";
    	}
    }
     
    
    /**
     * Cette methode est une requete sql prepare qui renvoi la couleur correspondante a un typ d'evenement 
     * @param idEvenement
     * @return
     * @throws SQLException
     */
    public String getCouleurEvenement(int idEvenement) throws SQLException {
    	 PreparedStatement request = co.getConnection().prepareStatement("select codeHexa from couleurs, evenement, typeevenement  where evenement.id = ? and evenement.id = typeevenement.id and typeevenement.couleur = couleurs.id");
         request.setInt(1, idEvenement);
         rs = request.executeQuery();
         if(rs.next()) {
        	 return rs.getString("codeHexa");
         }
         return "";
    }
    

    /**
     * 
     * @param idEvenement
     * @return true si l'evenement est une reservation et false dans le cas contraire
     * @throws SQLException
     */
    public Boolean getEstReservation(int idEvenement) throws SQLException {
    	PreparedStatement request = co.getConnection().prepareStatement("select * from evenement where id = ?");
    	request.setInt(1, idEvenement);
    	rs = request.executeQuery();
    	if(rs.next())     		
    		return rs.getInt("reservation") > 0;
    	else {
    		throw new SQLException();
    	}
    }

    /**
     *
     * @param nom Prend le nom de l'utilisateur
     * @param prenom Prend le prénom de l'utilisateur
     * @return Retourne les informations sur le mail, le login et le mot de passe d'une personne sous forme d'un tableau
     * @throws SQLException
     */
    public List<String> getUtilisateur(String nom, String prenom) throws SQLException{
    	PreparedStatement request = co.getConnection().prepareStatement("select * from usager where nom = ? and prenom = ?");
    	request.setString(1, nom);
    	request.setString(2, prenom);
    	List<String> listeUtilisateur = new ArrayList<>();
        if(rs.next()){
        	listeUtilisateur.add(rs.getString("mail"));
        	listeUtilisateur.add(rs.getString("login"));
        	listeUtilisateur.add(rs.getString("password"));
        }
        return listeUtilisateur;
    }

    /**
     * @return Retourne tout les utilisateur (nom + prenom dans une varaible)
     * @throws SQLException
     */
    public List<String> getNomUtilisateur() throws SQLException{
        PreparedStatement request = co.getConnection().prepareStatement("select * from usager");
        rs = request.executeQuery();
        List<String> listeNomUtilisateur = new ArrayList<>();
        while(rs.next()) {
        	listeNomUtilisateur.add(rs.getString("nom")+" - "+rs.getString("prenom"));
        }
        return listeNomUtilisateur;
    }
    
	/**
	 * @return le mail d'un client
	 * @throws SQLException
	 */
	public List<String> getClients() throws SQLException{
    	PreparedStatement request = co.getConnection().prepareStatement("select * from clients");
    	List<String> infoClient = new ArrayList<>();
    	rs = request.executeQuery();
        while(rs.next()){
        	infoClient.add(rs.getString("email"));
        }
        return infoClient;
    }

        /**
         *
         * @param id
         * @return
         * @throws SQLException
         */
    public List<String> getInfosClient(int id) throws SQLException{
    	PreparedStatement request = co.getConnection().prepareStatement("SELECT * FROM clients WHERE id = ?");
    	List<String> infoClient = new ArrayList<>();
    	request.setInt(1, id);
    	rs = request.executeQuery();
    	int i=0;
    	while(rs.next()) {
    		infoClient.add(rs.getString(i));
    		i++;
    	}
        return infoClient;
    }
    
    /**
     * Cette method renvoie l'id d'un client à parit de son nom et prenom
     * @param nom
     * @param prenom
     * @return
     * @throws SQLException
     */
    public int getIdClient(String nom, String prenom) throws SQLException{
    	PreparedStatement request = co.getConnection().prepareStatement("SELECT id FROM clients WHERE nom= ? AND prenom= ?");
    	request.setString(1, nom);
    	request.setString(1, prenom);
    	rs = request.executeQuery();
        if(rs.next())
        	return rs.getInt("id");
        else
        	return 0;
    }
    
    /**
     * Cette method renvoie tous les nom - prenom des clients
     * @return
     * @throws SQLException
     */
    public List<String> getnomsClients() throws SQLException{
    	PreparedStatement request = co.getConnection().prepareStatement("SELECT nom FROM clients");
        List<String> nom = new ArrayList<>();
        rs = request.executeQuery();
        while(rs.next()){
           nom.add(rs.getString("nom"));
        }
        return nom;
    }
    
    /**
     * Cette method renvoie les prenoms de tous les clients
     * @return
     * @throws SQLException
     */
    public List<String> getprenomsClients() throws SQLException{
        PreparedStatement request = co.getConnection().prepareStatement("select prenom from clients");
        List<String> prenom = new ArrayList<>();
        rs = request.executeQuery();
        while(rs.next()){
           prenom.add(rs.getString("prenom"));
        }
        return prenom;
    }
    
    /**
     * Cette methode renvoie le client d'une reservation
     * @param idClient
     * @param dateDe
     * @return
     * @throws SQLException
     */
    public int getIdReservation(int idClient) throws SQLException{
    	PreparedStatement request = co.getConnection().prepareStatement("select id from reservation where client = ?");
    	request.setInt(1, idClient);
    	rs = request.executeQuery();
    	if(rs.next()) {
    		return rs.getInt("client");
    	}
    	return 0;
      
    }
    
	/**
	 *
	 * @param idReservation
	 * @param laFormule
	 * @return
	 * @throws SQLException
	 */
    //TODO
    public String[] getInfosReservation(int idReservation) throws SQLException{
        String heureF = "SELECT heureFin FROM reservations WHERE idReservation='"+idReservation+"'";
        String nom = "SELECT nom, prenom FROM clients, reservations WHERE clients.idClient=reservations.fkidClient AND reservations.idReservation='"+idReservation+"'";
        String heureD = "SELECT heureDebut  FROM reservations WHERE idReservation='"+idReservation+"'";
        String dateF = "SELECT dateFin FROM reservations WHERE idReservation='"+idReservation+"'";
        String nbP = "SELECT nbPersonnes  FROM reservations WHERE idReservation='"+idReservation+"'";
        String nbH = "SELECT nbHeures  FROM reservations WHERE idReservation='"+idReservation+"'";
        String dateD = "SELECT dateDebut FROM reservations WHERE idReservation='"+idReservation+"'";
        String service = "SELECT libelle FROM optionsServices, choix WHERE optionsServices.idOptionsServices=choix.fkidOptionsServices AND choix.fkidReservation='"+idReservation+"' AND optionsServices.nature='service'";
        String option = "SELECT libelle FROM optionsServices, choix WHERE optionsServices.idOptionsServices=choix.fkidOptionsServices AND choix.fkidReservation='"+idReservation+"' AND optionsservices.nature='option'";
        String formule = "SELECT libelle FROM formule, reservations WHERE formule.idFormule=reservations.fkidFormule AND reservations.idReservation='"+idReservation+"'";
        String salle = "SELECT libelle FROM salle, infoSalle, sallesResa WHERE infoSalle.fkidSalle=salle.idSalle AND sallesResa.fkidInfoSalle=infoSalle.idInfoSalle AND sallesResa.fkidReservation='"+idReservation+"'";
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
        infosReservation.add("Service : " +lesServices);
        rs = co.query(option);
        ArrayList<String> lesOptions = new ArrayList<>();  
        while(rs.next()){
            String lOption = rs.getString("libelle");
            lesOptions.add(lOption);
        }
        infosReservation.add("Option : " +lesOptions);
        
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
        ArrayList<String> lesSalles = new ArrayList<>();  
        rs = co.query(salle);
        while(rs.next()){
           String laSalle = rs.getString("libelle");
           lesSalles.add(laSalle);
        }
        infosReservation.add("salles : "+lesSalles);
        rs = co.query(disposition);
        while(rs.next()){
            String laDisposition = rs.getString("libelle");
            infosReservation.add("Disposition : "+laDisposition);
        }
        rs = co.query(nom);
        while(rs.next()){
           String name = rs.getString("prenom")+" "+rs.getString("nom");
           infosReservation.add("client : "+name);
        }
       
        
        String[] tableau = new String[infosReservation.size()];
        tableau = infosReservation.toArray(tableau);
        
        return tableau;
    }
    
    /**
     * Cette methode renvoie le nom d'un client d'une reservation à parir de son id
     * @param idReservation
     * @return nom du client
     * @throws SQLException
     */
    public String getNomClient(int idReservation) throws SQLException{
    	PreparedStatement request = co.getConnection().prepareStatement("select nom from clients, reservation where clients.id = reservation.client and reservation.id = ?");
    	request.setInt(1, idReservation);
    	rs = request.executeQuery();
    	if(rs.next()) {
    		return rs.getString("nom");
    	}
    	return "";
    }
    
    /**
     * Cette methode renvoie le prenom d'un client d'une reservation à partir de son id
     * @param idReservation
     * @return prenom du client
     * @throws SQLException
     */
    public String getPrenomClient(int idReservation) throws SQLException{
        PreparedStatement request = co.getConnection().prepareStatement("select prenom from clients, reservation where clients.id = reservation.client and reservation.id = ?");
        request.setInt(1, idReservation);
        rs = request.executeQuery();
        if(rs.next()) {
        	return rs.getString("prenom");
        }
        return "";
    }
    
    /**
     * Cette methode renvoie les dates de reservation d'un client à partir de son id
     * @param idClient
     * @return liste de date
     * @throws SQLException
     */
    public List<String> getDatesReservations(int idClient) throws SQLException{
        PreparedStatement request = co.getConnection().prepareStatement("select dateDebut from evenement, clients, reservation where evenement.id = reservation.id and reservation.client = clients.id and clients.id = ?"); 
        request.setInt(1, idClient);
        rs = request.executeQuery();
        List<String> dates = new ArrayList<>();
        while(rs.next()) {
        	dates.add(rs.getString(1));
        }
        return dates;
        
    }
    
    /**
     * Cette methode renvoie la date de debut d'une reservation 
     * @param idReservation
     * @return
     * @throws SQLException
     */
    public String getDateDebut(int idReservation) throws SQLException{
    	PreparedStatement request = co.getConnection().prepareStatement("select dateDebut from reservation where id = ?");
        request.setInt(1, idReservation);
        rs = request.executeQuery();
        if(rs.next()) {
        	return rs.getString(1);
        }
        return "";
    }
    
    /**
     * Cette methode permet de determiner si une reservation à été validé
     * @param idReservation
     * @return une string indiquant la validité de la reservation
     * @throws SQLException
     */
    public String getValidite(int idReservation) throws SQLException {
    	PreparedStatement request = co.getConnection().prepareStatement("select estValide from reservation where id = ?");
        request.setInt(1, idReservation);
        rs = request.executeQuery();
        if(rs.next()) {
        	return (rs.getInt(1)==1)?" valide":" nom valide";
        }
        return " pas de reservation avec cet id";
    }
    
    /**
     * Cette methode renvoie tous les services
     * @return la liste des nom des services 
     * @throws SQLException
     */
    public List<String> getServices() throws SQLException {
        PreparedStatement request = co.getConnection().prepareStatement("select intitule from services");
        rs = request.executeQuery();
        List<String> services = new ArrayList<>();
        while(rs.next()) {
        	services.add(rs.getString("1"));
        }
        return services;
    }
    
    
    /**
     * Cette methode renvoie toutes les options
     * @return la liste des nom des options
     * @throws SQLException
     */
    public List<String> getOptions() throws SQLException {
    	PreparedStatement request = co.getConnection().prepareStatement("select type from options");
    	rs = request.executeQuery();
    	List<String> options = new ArrayList<>();
    	while(rs.next()) {
    		options.add(rs.getString(1));    		
    	}
    	return options;
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
        String quest1 = "SELECT * FROM "+nomTable+" WHERE nom"+nomTable+" = '"+nom+"'";
        String quest2 = "SELECT * FROM Salle, Taches WHERE Salle.codeCouleur = '"+couleur+"' OR Tache.codeCouleur = '"+couleur+"'";
        return !((rs = co.query(quest1)).next() || (rs = co.query(quest2)).next());
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
    
    public void MAJprixPlateauxRepas(double prix){
        String quest = "UPDATE OptionsServices SET prixHT = "+prix+" WHERE libelle = 'Plateaux repas'";
        co.query(quest);
    }

    public void MAJvalide(int valide,int idReservation) throws SQLException{
         String quest = "UPDATE reservation SET estValide = '"+valide+"' WHERE idReservation = '"+idReservation+"'";
         co.update(quest);
         System.out.println("UPDATE REUSSIE");
    }
    
    public void MAJdateD(String dateD,int idReservation) throws SQLException{
         String quest = "UPDATE reservations SET dateDebut = '"+dateD+"' WHERE idReservation = '"+idReservation+"'";
         co.query(quest);
    } 
    
    public void MAJdateF(String dateF, int idReservation) throws SQLException{
         String quest = "UPDATE reservations SET dateFin='"+dateF+"' WHERE idReservation = '"+idReservation+"'";
         co.query(quest);
    } 
    
    public void MAJheureD(String heureD, int idReservation) throws SQLException{
         String quest = "UPDATE reservations SET heureDebut = '"+heureD+"' WHERE idReservation = '"+idReservation+"'";
         co.query(quest);
    }
     
    public void MAJheureF(String heureF, int idReservation) throws SQLException{
         String quest = "UPDATE reservations SET heureFin = '"+heureF+"' WHERE idReservation = '"+idReservation+"'";
         co.query(quest);
    }
    
    public void MAJnbPersonnes(int nbP, int idReservation) throws SQLException{
         String quest = "UPDATE reservations SET nbPersonnes = '"+nbP+"' WHERE idReservation = '"+idReservation+"'";
         co.query(quest);
    }
    
    public void MAJnbHeures(int nbH,int idReservation) throws SQLException{
         String quest = "UPDATE reservations SET nbHeures = '"+nbH+"' WHERE idReservation = '"+idReservation+"'";
         co.query(quest);
    }
    
    public void MAJformule(int idReservation, String formule) throws SQLException{
         String quest = "UPDATE reservations SET fkidFormule=(SELECT idFormule FROM formule WHERE libelle='"+formule+"') WHERE idReservation = '"+idReservation+"'";
         co.query(quest);
    }
    
    public void MAJclient(int idReservation, String prenom, String nom) throws SQLException{
         String quest = "UPDATE reservations SET fkidClient = (SELECT idClient from clients where nom = '"+nom+"' AND prenom = '"+prenom+"') WHERE idReservation = '"+idReservation+"'";
         co.query(quest);
    }
    
    public void MAJchoix(int idReservation, String choix) throws SQLException{
         String quest = "UPDATE choix SET fkidOptionsServices = (SELECT idOptionsServices from optionsServices where libelle = '"+choix+"') WHERE idReservation = '"+idReservation+"'";
         co.query(quest);
    }
    
    public void MAJsalle(int idClient, String dateDebut) throws SQLException{
         
    }
    
    public void MAJdisposition(int idClient, String dateDebut) throws SQLException{
         
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
        String request = "INSERT INTO Usager(nom, prenom, mail, administrateur, login, password) VALUES('"+nom+"','"+prenom+"','"+email+"',"+admin+",'"+login+"','"+mdp+"')";
        rs = co.query(request);
    }
    
    public void ajoutChoix(int idReservation, String choix) throws SQLException{
         String nouveauChoix = "INSERT INTO choix (fkidReservation, fkidOptionsServices) VALUES ('"+idReservation+"', (SELECT idOptionsServices FROM optionsServices WHERE libelle='"+choix+"'))";
         rs = co.query(nouveauChoix);
    }
    
    public void ajoutSalle(int idReservation, String salle, String disposition) throws SQLException{
         String nouvelleSalle = "INSERT INTO sallesResa (fkidReservation, fkidInfoSalle) VALUES ('"+idReservation+"', (SELECT idInfoSalle FROM infoSalle WHERE fkidSalle=(SELECT idSalle FROM salles WHERE libelle = '"+salle+"') AND fkdisposition=(SELECT idDisposition FROM disposition WHERE libelle='"+disposition+"')))";
         rs = co.query(nouvelleSalle);
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
}
        /*
        ========================================================================
                                    FIN SUPPRESSION
        ========================================================================
        */

  