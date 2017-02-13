import java.io.File;
import java.io.FileReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ServeurD {

	public ServeurD() {

	}

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e1) {

		}
		try {

			ServeurDiscussionEx serveurD = new ServeurDiscussionEx();
			Naming.rebind("Discussion", serveurD);
		
			// Alert srAlert = (Alert) Naming.lookup("rmi://localhost/Alert");

			System.out.println("Serveur Discussion");
		/*	File file = new File("C:/Users/ut/workspace/Jeu/src/discussion.json");
			JSONParser parser = new JSONParser();
			String texte, loginJoueur, horaire;
			int numPieceJ;
			try {
				Object obj = parser.parse(new FileReader(file));
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray pieces = (JSONArray) jsonObject.get("Pieces");
				for (int i = 0; i < pieces.size(); i++) {
					JSONObject piece = (JSONObject) pieces.get(i);
					numPieceJ = ((Number) piece.get("numPiece")).intValue();
					if(numPiece==numPieceJ){
					JSONArray joueurs = (JSONArray) piece.get("Personnes");
					for (int j = 0; j < joueurs.size(); j++) {
						JSONObject joueur = (JSONObject) joueurs.get(j);
						loginJoueur = (String) joueur.get("login");
						JSONArray textes = (JSONArray) joueur.get("textes");
						for (int k = 0; k < textes.size(); k++) {
							JSONObject texteJ = (JSONObject) textes.get(k);
							horaire = (String) texteJ.get("horaire");
							texte = (String) texteJ.get("texte");
						System.out.println(loginJoueur + ": " + horaire + ": " + texte);
							// srAlert.envoyerString(texte);
						}

					}
				}
				}
			} catch (Exception e) {
			System.out.println("erreur fichier!!!");
			}*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
