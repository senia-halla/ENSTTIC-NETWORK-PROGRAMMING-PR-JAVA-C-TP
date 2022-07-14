import java.rmi.*;
import java.rmi.server.*;

public class StringImpl extends UnicastRemoteObject implements StringRemote {

	public StringImpl() throws RemoteException {
		super();
	}
	
	public int NbOccurrence (String c, String t) throws RemoteException {
		int Nb = 0;
		for (int i=0; i<t.length()-c.length()+1; i++)
			if ((t.substring(i, i+c.length())).equals(c)) Nb++;
		return Nb;
	} 
	
}
