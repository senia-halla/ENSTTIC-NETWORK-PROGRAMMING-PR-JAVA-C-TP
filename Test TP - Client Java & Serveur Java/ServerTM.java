import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Random;
import java.io.PrintWriter;

public class ServerTM {
	public static int nbSecret ; 
	public static String gangant;
	public static boolean fin =false ; 
	
	public static void main(String[] args){
		ServerSocket serveur;
		try {
			serveur = new ServerSocket(2000);
			nbSecret = new Random().nextInt(100);
			System.out.println("le nombre a deviner est"+nbSecret);
			Thread t = new Thread(new Traitement_requetes(serveur));
			t.start();
			System.out.println(nbSecret);
			System.out.println ("LE SEREVEUR EST ENTRAIN DE FAIRE AUTRE CHOSE");
			
			System.out.println("Le serveur est pret a recevoir des requetes ...");
		} catch (IOException e) { e.printStackTrace(); }
	}	
}

class Traitement_requetes implements Runnable {
	
	private final ServerSocket serveur;
	private final Socket[] sockets;
	private int nbrclient = 0;
	private static final int nbrclientMax = 100;
	private PrintWriter out;
	
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
	    } catch (IOException e) { }
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
			//out = new PrintWriter(socket.getOutputStream());
			//out.println("Vous etes le client numero " + nClient);
			//out.println("devine un nombre entre 0 et 100");
			while (true) {
				String IP = socket.getRemoteSocketAddress().toString();
				
				BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
				String req =in.readLine();
				int nb = Integer.parseInt(req);
				
				
				if (ServerTM.fin==false) {
					if(nb<ServerTM.nbSecret) {
						out = new PrintWriter(socket.getOutputStream());
						out.println("votre nmbre est plus petit que le nombre secret");
						out.flush();
					}
					else if (nb>ServerTM.nbSecret) {
						out = new PrintWriter(socket.getOutputStream());
						out.println("votre nmbre est plus grand que le nombre secret");
						out.flush();
					}
					else {
                                            try (socket) {
                                                out = new PrintWriter(socket.getOutputStream());
                                                ServerTM.fin= true ;
                                                ServerTM.gangant =IP;
                                                out.println("vous avez ganger ");
                                                out.flush();
                                                socket.close();
                                                
                                            }
					}
				}
				else {
                                    try (socket) {
                                        out = new PrintWriter(socket.getOutputStream());
                                        out.println("le jeu est temine "+ServerTM.gangant+"est le gagnat ");
                                        out.flush();
                                    }
					
				}
			
			out.flush();
			// nbrclient++;
			}}
	catch (IOException e) { e.printStackTrace(); }}}
