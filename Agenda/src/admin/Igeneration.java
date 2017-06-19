package admin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface Igeneration {
	public void generer(String[] client, String[] infos, String[][] salle, String[] equipements, String[] services, String[] commentaires, int nbPersonnes) throws FileNotFoundException, IOException, SQLException;
		
	public void generer (int id) throws SQLException;
	public static Igeneration getIg (int i){
		if (i==1) return new generationDevis();
		else return null;
	}
}
