import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculRemote extends Remote {
	public int add (int a, int b) throws RemoteException;
}