import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;

public class Serveur {
	
	public static void main(String[] args) {
		
		try {
			LocateRegistry.createRegistry(2000);
			System.out.println("Serveur : Instanciation des Objets");
			StringImpl s = new StringImpl ();
			System.out.println("Inscription de l'objet s dans RMIregistry");
			Naming.rebind("rmi://192.168.43.49:2000/Chaine", s);			//"rmi://192.168.200.90:2000/Chaine"
			CalculImpl c = new CalculImpl ();
			System.out.println("Inscription de l'objet c dans RMIregistry");
			Naming.rebind("rmi://192.168.43.49:2000/Calcul", c);
			System.out.println("Attente des invocations des clients ");
		}
		catch (Exception e) {
			System.out.println("Erreur de liaison de l'objet CalculImpl ou StringImpl");
			System.out.println(e.toString());
		}
	}
}