import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServeurDiscussionEx extends UnicastRemoteObject implements ServeurDiscussion{

	private static final long serialVersionUID = 1L;

	public ServeurDiscussionEx()  throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
	}
	public void discuter(Piece piece, String texte,Joueur joueur)throws RemoteException{
		Date maDate = new Date();   
		System.out.println(maDate.toString()+ "***"+joueur.nomPersonne +"***");
		System.out.println(texte);
	}
	public String getDiscussion(int numPiece)throws RemoteException{
		
		File file = new File("C:/Users/ut/workspace/Jeu/src/discussion.json");
		JSONParser parser = new JSONParser();
		String texte, loginJoueur, horaire,chaine="";
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
						chaine+=loginJoueur + ": " + horaire + ": " + texte+"\n";
						// srAlert.envoyerString(texte);
					}

				}
			}
			}
			return(chaine);
		} catch (Exception e) {
		System.out.println("erreur fichier!!!");
		} 
		return("erreur");
	}
	
	public void setDiscussion(Joueur jo,String texte)throws RemoteException{
		File file = new File("C:/Users/ut/workspace/Jeu/src/discussion.json");
		JSONParser parser = new JSONParser();
		String  loginJoueur;
		boolean existe=false;
		int numPieceJ;
		try {
			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray pieces = (JSONArray) jsonObject.get("Pieces");
			for (int i = 0; i < pieces.size(); i++) {
				JSONObject piece = (JSONObject) pieces.get(i);
				numPieceJ = ((Number) piece.get("numPiece")).intValue();
				if(jo.numPieceCourante==numPieceJ){
				JSONArray joueurs = (JSONArray) piece.get("Personnes");
				for (int j = 0; j < joueurs.size(); j++) {
					JSONObject joueur = (JSONObject) joueurs.get(j);
					loginJoueur = (String) joueur.get("login");
					if(loginJoueur.equals(jo.nomPersonne)){
						existe=true;
					JSONArray textes = (JSONArray) joueur.get("textes");
					JSONObject texteJ = new JSONObject();
					texteJ.put("horaire", new Date().toString());	
					texteJ.put("texte", texte);
					textes.add(texteJ);
					
					}
					}
				if(!existe)
				{
					JSONObject nvJoueur=new JSONObject();
					nvJoueur.put("login", jo.nomPersonne);
					JSONArray nvTextes = new JSONArray();
					JSONObject nvTexte= new JSONObject();
					nvTexte.put("horaire", new Date().toString());
					nvTexte.put("texte", texte);
					nvTextes.add(nvTexte);
					nvJoueur.put("textes",nvTextes);
					joueurs.add(nvJoueur);
					System.out.println(nvJoueur.toString());
					
				}
				System.out.println(existe);
				System.out.println(pieces.toString());
				PrintWriter writer = new PrintWriter(file);
				writer.write(obj.toString());
				writer.flush();
				writer.close();
			}
			}
		} catch (Exception e) {
		System.out.println("erreur fichier!!!");
		} 
	}
}
