import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Alert extends Remote {
	void envoyerString(String texte) throws RemoteException;

}
