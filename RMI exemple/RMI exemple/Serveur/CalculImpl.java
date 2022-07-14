import java.rmi.*;
import java.rmi.server.*;

public class CalculImpl extends UnicastRemoteObject implements CalculRemote {

	public CalculImpl() throws RemoteException {
		super();
	}

	public int add (int a, int b) throws RemoteException {
		return (a+b);
	}
	
}
