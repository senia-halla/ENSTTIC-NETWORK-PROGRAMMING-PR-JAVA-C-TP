import java.io.IOException;
import java.net.*;
import java.io.PrintWriter;

public class Serveur3 {
	public static void main(String[] args){
		ServerSocket serveur;
		try {
			serveur = new ServerSocket(2000);
			Thread t = new Thread(new Traitement_requetes(serveur));
			t.start();
			
			System.out.println ("LE SEREVEUR EST ENTRAIN DE FAIRE AUTRE CHOSE");
			
			System.out.println("Le serveur est pret a recevoir des requetes ...");
		} catch (IOException e) { e.printStackTrace(); }
	}	
}

class Traitement_requetes implements Runnable {
	private  ServerSocket serveur;
	private  Socket[] sockets;
	private int nbrclient = 0;
	private static final int nbrclientMax = 100;
	
	public Traitement_requetes(ServerSocket s){
		serveur = s;
		sockets = new Socket[nbrclientMax];
	}
			
        @Override
	public void run() {
		try {
			while(true){
				sockets[nbrclient] = serveur.accept();
				Thread t = new Thread (new Connexion_client (sockets[nbrclient], nbrclient+1));
				nbrclient++;
				t.start();
                        }
        
                } catch (IOException e) { e.printStackTrace(); }
	}

}

class Connexion_client implements Runnable {

	private final Socket socket;
	private final int nClient;
	private PrintWriter out;
	
	public Connexion_client(Socket s, int nClient){
		socket = s;
		this.nClient = nClient;
	}
			
        @Override
	public void run() {
		try {
			System.out.println("Le client numero "+nClient+ " est connecte !");
			out = new PrintWriter(socket.getOutputStream());
			out.println("Vous etes le client numero " + nClient);
			out.flush();
			socket.close();
	    } catch (IOException e) { e.printStackTrace(); }
	}

}