package admin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface Igeneration {
	public void generer(String[] client, String[][][] salle, String[] equipements, String[] services, String[] commentaires, String[][][] OS) throws FileNotFoundException, IOException, SQLException;
		
	
	public static Igeneration getIg (int i){
		if (i==1) return new generationDevis();
		else if (i==2) return new generationFacture();
		else return null;
	}
}
