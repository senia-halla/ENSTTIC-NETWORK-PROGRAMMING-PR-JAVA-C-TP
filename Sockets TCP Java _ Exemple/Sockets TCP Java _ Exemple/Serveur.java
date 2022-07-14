import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;

public class Serveur {
	public static void main(String[] args) {
		ServerSocket serveur  ;
		Socket socket ;
		BufferedReader in;
		PrintWriter out;
		try {	
                    serveur = new ServerSocket (2000); // instanciation du serveur avec un num de port
                    System.out.println ("Le serveur est a l'ecoute du port " + serveur.getLocalPort());
                    socket = serveur.accept (); 	// Attente de la connexion d'un client et acceptation	
                    System.out.println ("LE SEREVEUR EST ENTRAIN DE FAIRE AUTRE CHOSE");
                    System.out.println ("Connexion d'un client ...");
                    out = new PrintWriter (socket.getOutputStream());
		    out.println ("Vous etes connecte !");
		    out.flush ();
		    socket.close ();
		    serveur.close ();
		} catch (IOException e) {}
	}
}