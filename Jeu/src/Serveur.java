//import java.net.MalformedURLException;
//import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
//import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Serveur{
	public Serveur(){
		
	}
public static void main(String[] args) {
	try {
		LocateRegistry.createRegistry(1099);
	} catch (RemoteException e1) {
		
	}
	
		try {
			
		
			ServeurDeJeuEx serveur= new ServeurDeJeuEx();
			Naming.rebind("Jeu",serveur);
			//Alert srAlert = (Alert) Naming.lookup("rmi://localhost/Alert");
			System.out.println("Serveur Jeu");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
