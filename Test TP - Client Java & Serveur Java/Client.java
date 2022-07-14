import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socketc = new Socket("127.0.0.1",2000);
            BufferedReader in ;
            PrintWriter out;
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("guess your number : ");
                int nb1 = sc.nextInt();
                out = new PrintWriter(socketc.getOutputStream());
                out.println(nb1);
                out.flush();
                in = new BufferedReader(new InputStreamReader(socketc.getInputStream()));
                String message = in.readLine();
                System.out.println(message);
            }

        }catch (IOException e){

        }

    }

}