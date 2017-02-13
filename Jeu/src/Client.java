import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.util.Scanner;
import java.rmi.Naming;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//import java.util.Scanner;
import java.util.Date;

public class Client {
//clientttttt
	public static void main(String[] args) {// throws MalformedURLException,
											// RemoteException,
											// NotBoundException,
											// ExceptionMdpInconrrecte,
											// ExceptionJoueurNonExistant {
		String login, mdp,texte;
		char c, p;
		Joueur joueur;

		try {
			ServeurDeJeu srJeu = (ServeurDeJeu) Naming.lookup("rmi://localhost/Jeu");
			ServeurDiscussion srDiscussion = (ServeurDiscussion) Naming.lookup("rmi://localhost/Discussion");
			// AlertEx alert= new AlertEx();
			// Naming.rebind("Alert",alert);

			BufferedReader entreec = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader entreeporte = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(srDiscussion.getDiscussion(1));
			System.out.println("Vous êtes un nouveau joueur?(o/n)");
			c = (char) entreec.read();
			if (c == 'n') {
				System.out.println("Veuillez saisir ton login");
				login = entree.readLine();
				System.out.println("Veuillez saisir ton mot de passe");
				mdp = entree.readLine();
				
				if(srJeu.authentification(login, mdp)==1){
				joueur = srJeu.getJoueur(login, mdp);
				}
				else if(srJeu.authentification(login, mdp)==0){
					throw new ExceptionMdpInconrrecte();
				}
				else{
					throw new ExceptionJoueurNonExistant();
				}

			} else if (c == 'o') {
				System.out.println("Veuillez saisir un login");
				login = entree.readLine();
				if (srJeu.existeJoueur(login)) {
					System.out.println("Ce login est déja existant");
					joueur = null;
				} else {
					System.out.println("Veuillez saisir un mot de passe");
					mdp = entree.readLine();
					joueur = srJeu.creerJoueur(login, mdp);
				}
				
			}
			else {
				throw new ExceptionSaisie();
				
			}
				System.out.println("Bienvenue dans le jeu " + joueur.nomPersonne);
				System.out.println(srDiscussion.getDiscussion(joueur.numPieceCourante));
				System.out.println("Veuillez ecrire un texte pour communiquer");
				texte=entree.readLine();
				srDiscussion.setDiscussion(joueur, texte);
				System.out.println("Vers quel porte vous voulez se deplacer(E/N/O/S)");
				p = (char) entreeporte.read();
				if (p == 'E' || p == 'N' || p == 'O' || p == 'S') {
					if (srJeu.seDeplacer(joueur, p) == 0) {
						System.out.println("Cette porte n'existe pas");
					} else {
						joueur.numPieceCourante = srJeu.seDeplacer(joueur, p);
						System.out.println(joueur.numPieceCourante);
						srJeu.setJoueur(joueur);
						System.out.println(joueur.numPieceCourante);
					}
				} else {
					System.out.println("Veuillez verifier votre saisie!!");
				}
			

			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
