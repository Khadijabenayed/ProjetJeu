import java.rmi.Remote;

public interface Alert extends Remote{
	void envoyerString(String texte);

}
