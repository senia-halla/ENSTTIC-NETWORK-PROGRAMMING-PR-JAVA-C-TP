import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP {
	
	public static void main(String[] args) throws IOException {
		
		while(true) {
			DatagramSocket ds = new DatagramSocket(9095);
			System.out.println("My IP is : "+InetAddress.getLocalHost().getHostAddress());
			System.out.println("Server started on port 9095");
			byte[] buff = new byte[256];
			DatagramPacket dp = new DatagramPacket(buff, buff.length);
			ds.receive(dp);
			String resp = new String(dp.getData());
			System.out.println("Message Recu : "+resp);
			ds.close();
		}
		
	}
	
}
