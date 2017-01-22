package fr.villalem.labd;
import java.sql.*;

public class Connexion
{
	// je ne sais pas si ça sert à qqchose de faire un Main ici, à voir
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      //créé une connexion "c" à la base de donnée:
      c = DriverManager.getConnection("jdbc:sqlite:db/Agenda.db");
      System.out.println("Connecté à la base de donnée");
      
      stmt = c.createStatement();
      String sql = "SELECT * FROM Facture";
      ResultSet rs = stmt.executeQuery(sql);
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
  }
}