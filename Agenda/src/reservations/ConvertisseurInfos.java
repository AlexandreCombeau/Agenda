package reservations;

import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Cette classe est une classe de service, 
 * Elle converti la requete une requete de BD selectionnant toutes les informations utile sur un evenement donné 
 * en une chaine de caractere que l'on va afficher
 * @author Alexandre
 *
 */
public class ConvertisseurInfos {
	private ConvertisseurInfos() {	
	}

	public static String convertReservation(String[] chaine) {
	    StringBuilder sb = new StringBuilder();
		String[] infos = chaine;
		
		Function<Integer,String> ajout = index -> {
			StringBuilder sbTemp = new StringBuilder();
			int temp = 1;
			while(index+temp<infos.length) {
				 sbTemp.append(infos[temp+index]+"\n");
				temp++;
			}
			return sbTemp.toString();
		};

		sb.append(infos[0]+"-"+infos[1]+"\n"); // date debut et fin
		sb.append(infos[2]+" "+infos[3]+"\n"); // heure debut et fin
		if(infos[4].contains("aucun ")) {
			sb.append(infos[4]+"\n"); // aucun services
			if(infos[5].contains("aucune ")) {
				sb.append(infos[5]+"\n")
					.append(infos[6]+"\n")
					.append(infos[7]+"\n")
					.append(infos[8]+"\n")
					.append(infos[9]+" "+infos[10]+"\n")
					.append(infos[11]+"\n"); 
				// aucunes options - formule - nombre personnes - nombres heure - salle - disposition - client
			}
		}
		else {
			int indexService = 4;
			while(!infos[indexService+1].contains("Option(s) ")) { // services
				sb.append(infos[indexService]+" - ");
				indexService++;
			}
			int indexOptions = indexService;				
			if(infos[indexService+1].contains("aucune ")) { // pas d'options
				sb.append(infos[indexService]+"\n");
				sb.append(ajout.apply(indexService));
			}
			else {
				while(!infos[indexOptions+1].contains("Formule ")) { // options
					sb.append(infos[indexOptions]+" - ");
					indexOptions++;
				}
				sb.append(ajout.apply(indexService)); 
			}			
		}	
		return sb.toString();
	}
	
	public static String convertTache(String[] chaine) {
		StringBuilder sb = new StringBuilder();
		IntStream.range(0, chaine.length).forEach(i->sb.append(chaine[i]).append("\n"));
		System.out.println(sb.toString());
		return sb.toString();
	}
}
