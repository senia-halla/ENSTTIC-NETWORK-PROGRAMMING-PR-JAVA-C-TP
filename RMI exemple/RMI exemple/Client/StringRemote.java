import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StringRemote extends Remote {
	public int NbOccurrence (String c, String t) throws RemoteException;
}