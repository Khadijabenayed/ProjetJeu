import java.io.Serializable;

public class Joueur extends Personne implements Serializable {
	public String mdp;

	public Joueur(String nom, String mdp) {
		this.nomPersonne = nom;
		this.mdp = mdp;
		this.nbreDePtDeVieCourant = 10;
		this.nbreDePtDeVieMax = 10;
		this.numPieceCourante=1;
        
	}

}
