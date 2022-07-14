import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	
	public static void main(String[] args) {
			
		Socket socket;
		BufferedReader in;
		PrintWriter out;

		if (args.length != 2) {
			System.out.println ("\nUSAGE : java Client adresse port\n");
			return;
		}
		try {
                    socket = new Socket(args[0],Integer.parseInt(args[1]));
		    System.out.println("Demande de connexion");
                    in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
		    String message_distant = in.readLine();
		    System.out.println(message_distant);
		    socket.close();
		}
		catch (UnknownHostException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
	
	 
	}

}
