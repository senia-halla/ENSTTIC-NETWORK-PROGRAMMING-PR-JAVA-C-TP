import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	public static void main(String[] args) throws IOException{
		DatagramSocket ds = new DatagramSocket();
		InetAddress add = InetAddress.getByName("localhost");
		String message = "Hello2";
		byte[] buff = message.getBytes();
		DatagramPacket dp = new DatagramPacket(buff, buff.length, add, 9095);
		ds.send(dp);
		ds.close();
	}

}
