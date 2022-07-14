import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String[] args) throws IOException, SocketException{
         
         
         DatagramSocket clientSocket = new DatagramSocket();
         
         InetAddress IPAddress = InetAddress.getByName("localhost");
         
         byte[] sendData = new byte[1024];
         byte[] receiveData = new byte[1024];
         
         BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // Equivalent Scanner 
         String sentence = inFromUser.readLine();
         sendData = sentence.getBytes();
         
         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
         clientSocket.send(sendPacket);
         
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
         clientSocket.receive(receivePacket);
         
         String modifiedSentence = new String(receivePacket.getData());
         System.out.println("FROM SERVER: port " + receivePacket.getPort());
         
         clientSocket.close();
    }
}