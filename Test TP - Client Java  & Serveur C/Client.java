import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		final Socket clientSocket;
		final BufferedReader in ;
		final PrintWriter out;
		final Scanner sc = new Scanner(System.in);
		
		try {
			clientSocket = new Socket("127.0.0.1" ,23);
			out= new PrintWriter(clientSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println("connexion etablie vous pouver discuter mtn : TAPEZ TEST POUR VOIR SI SA MARCHE\n");
			
		
		
		
		// sender thread 
		 Thread sender = new Thread(new Runnable() {
		 	String message ;
			// @Override
			public void run() {
				
				while(true) {
					
					message= sc.nextLine();
					out.println(message);
					out.flush();
			}
		}
		});
		sender.start();
		//reciever Thread 
		Thread reciever = new Thread(new Runnable() {
			String message;
				public void run() {
		 	     try {
					  
						message=in.readLine();
						while(message!=null) {
							System.out.println("SERVEUR C :"+message.trim());
							message=in.readLine();
							}
						
		 			out.close();
		 			clientSocket.close();
					  
		 			} catch (IOException e) {
		 		 	e.printStackTrace();
		 		 }
				
			 }
		});
		
		 reciever.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}