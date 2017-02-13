import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServeurDeJeu extends Remote{
	public boolean existeJoueur(String login)throws RemoteException;
	public Joueur creerJoueur(String login, String mdp) throws RemoteException;
	public int authentification(String login,String mdp) throws RemoteException;
	public Joueur getJoueur(String login,String mdp)throws RemoteException;
	public void setJoueur(Joueur j)throws RemoteException;
	public int seDeplacer(Joueur joueur,char porte)throws RemoteException;

	
	
}
