/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.*;

/**
 *
 * @author Villalemons
 */
public class Connexion {
    
    	
    private String DBPath = "Chemin aux base de donnée SQLite";
    private Connection connection = null;
    private Statement statement = null;
 
    public Connexion(String dBPath) {
        DBPath = dBPath;
    }
 
    /**
     * Cette fonction sert à se connecter à la base de données
     */
    public void connect() {
        String url = "jdbc:mysql://127.0.0.1/villalemagenda";
        String login = "java";
        String mdp = "Villa2017";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, login, mdp);
            statement = connection.createStatement();
            System.out.println("Connexion a " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connexion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connexion");
        }
    }
 
    /**
     * Cette fonction sert à se déconnecter de la base de données
     */
    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
        }
    }
    
    /**
     * Cette fonction sert à exécuter une requête.
     * @param request
     * @return Retourne le résultat d'une requête
     */
    public ResultSet query(String request) {
        ResultSet result = null;
        try {
            result = statement.executeQuery(request);
        } catch (SQLException e) {
            System.out.println("Erreur dans la requête : " + request);
        }
        return result;
    }
    
    /**
     *
     * @param request
     * @return 
     */
    public int update (String request) {
        int result = 0;
        try {
            statement.executeUpdate(request);
        } catch (SQLException e) {
            System.out.println("Erreur dans la requête : " + request);
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean execut (String request) {
        boolean result = true;
        try {
            statement.execute(request);
        } catch (SQLException e) {
            System.out.println("Erreur dans la requête : " + request);
            e.printStackTrace();
        }
        return result;
    }

    Statement createStatement(int TYPE_SCROLL_SENSITIVE, int CONCUR_UPDATABLE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	
    
}
