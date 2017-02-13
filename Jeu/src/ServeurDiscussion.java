import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServeurDiscussion extends Remote {
public void discuter(String texte,Joueur joueur)throws RemoteException;
public String getDiscussion(int numPiece)throws RemoteException;
public void setDiscussion(Joueur j,String texte)throws RemoteException;
public void inscriptionAlerte(String login,Alert alert)throws RemoteException;
}
