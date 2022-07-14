import java.io.*;
import java.rmi.*;

class Client {
	public static void main (String [] args) throws IOException {
		int nb = 0;
		String ligne, motRecherche;
		System.out.println("Saisissez une phrase : ");
		BufferedReader entree = new BufferedReader (new InputStreamReader (System.in));
		ligne= entree.readLine();
		System.out.println("Mot recherche : ");
		motRecherche = entree.readLine();
		try {
			StringRemote s = (StringRemote) Naming.lookup("rmi://192.168.43.49:2000/Chaine");
			nb = s.NbOccurrence(motRecherche, ligne);
			System.out.println(" Dans la phrase \"" + ligne + "\", il y a " + nb + " occurrences de \"" + motRecherche + "\"");		
			CalculRemote a = (CalculRemote) Naming.lookup("rmi://192.168.43.49:2000/Calcul");
			System.out.println (5 + " + " + 6 + " = " + a.add(5,6) );
		}catch (Exception e) {
			System.out.println("Erreur d'acces a un objet distant");
			System.out.println(e.toString());
		}
	}
}
