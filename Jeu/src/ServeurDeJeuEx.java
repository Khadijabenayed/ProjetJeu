import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class ServeurDeJeuEx extends UnicastRemoteObject implements ServeurDeJeu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String login, mdp;
	Joueur monJoueur;
	File file = new File("C:/Users/ut/workspace/Jeu/src/baseDonnee.json");

	JSONParser parser = new JSONParser();

	public ServeurDeJeuEx() throws RemoteException {
	}
public boolean existeJoueur(String login)throws RemoteException{
	boolean existe=false;
	String loginJoueur;
	
	try {
		Object obj = parser.parse(new FileReader(file));
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray joueursJ = (JSONArray) jsonObject.get("Joueurs");

		for (int i = 0; i < joueursJ.size(); i++) {

			JSONObject joueur = (JSONObject) joueursJ.get(i);
			loginJoueur = (String) joueur.get("login");
			if (login.equals(loginJoueur))

			{  
				existe=true;
			}
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return  existe;
}
	public Joueur creerJoueur(String login, String mdp)throws RemoteException {
		try {
			Joueur jo = new Joueur(login, mdp);
			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray joueursJ = (JSONArray) jsonObject.get("Joueurs");
			JSONObject joueur = new JSONObject();
			joueur.put("login", login);
			joueur.put("mdp", mdp);
			joueur.put("numPieceCourante", 1);
			joueur.put("nbrePtDeVieCourant", 10);
			joueur.put("nbrePtDeVieMax", 10);
			System.out.println(joueur.toString());
			joueursJ.add(joueur);
			PrintWriter writer = new PrintWriter(file);
			writer.write(obj.toString());
			writer.flush();
			writer.close();
			//System.out.println("Bienvenue dans le jeu " + login);
			//System.out.println(obj.toString());
			return jo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int authentification(String login, String mdp) throws RemoteException{
		boolean exist = false;
		int auth=-2;
		String loginJoueur, mdpJoueur;
		try {
			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray joueursJ = (JSONArray) jsonObject.get("Joueurs");

			for (int i = 0; i < joueursJ.size(); i++) {

				JSONObject joueur = (JSONObject) joueursJ.get(i);
				loginJoueur = (String) joueur.get("login");
				mdpJoueur = (String) joueur.get("mdp");
				if (login.equals(loginJoueur))

				{
					if (mdp.equals(mdpJoueur)) {
						exist = true;
						auth=1;
						//return("Bienvenue dans le jeu " + loginJoueur);
					} else {
						//return("Mot de passe incorrect");
						auth=0;
					}
				}
			}

			if (exist == false) {
				//return("Le joueur est non existant");
				auth=-1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return(auth);
	}
    public Joueur getJoueur(String login,String mdp)throws RemoteException{
    	Joueur jo = new Joueur(login, mdp);
    
    	String loginJoueur, mdpJoueur;
		try {
			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray joueursJ = (JSONArray) jsonObject.get("Joueurs");

			for (int i = 0; i < joueursJ.size(); i++) {

				JSONObject joueur = (JSONObject) joueursJ.get(i);
				loginJoueur = (String) joueur.get("login");
				mdpJoueur = (String) joueur.get("mdp");
				if (login.equals(loginJoueur)&& (mdp.equals(mdpJoueur)))

				{  
						jo.numPieceCourante= ((Number)joueur.get("numPieceCourante")).intValue();
						jo.nbreDePtDeVieCourant= ((Number)joueur.get("nbrePtDeVieCourant")).intValue();
						jo.nbreDePtDeVieMax=((Number)joueur.get("nbrePtDeVieMax")).intValue();     
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	return(jo);
	
}
    public void setJoueur(Joueur j)throws RemoteException{
    
		try {
			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray joueursJ = (JSONArray) jsonObject.get("Joueurs");
			//int i=0;
			for (int i = 0; i < joueursJ.size(); i++) {

				JSONObject joueur = (JSONObject) joueursJ.get(i);
				//while(!(j.nomPersonne.equals((String) joueur.get("login")))&&(i<joueursJ.size())){
					System.out.println(joueur.toString());
					if((j.nomPersonne.equals((String) joueur.get("login")))){
						joueur.put("numPieceCourante",(Number)j.numPieceCourante);
						joueur.put("nbrePtDeVieCourant",(Number)j.nbreDePtDeVieCourant);
						joueur.put("nbrePtDeVieMax",(Number)j.nbreDePtDeVieMax);
						System.out.println(joueur.toString()); 
					}
			
			
				}
			System.out.println(obj.toString());
			PrintWriter writer = new PrintWriter(file);
			writer.write(obj.toString());
			writer.flush();
			writer.close();
				/*if (login.equals((String) joueur.get("login")))
					
				{joueur.put("numPieceCourante",(int)j.numPieceCourante);
				joueur.put("nbrePtDeVieCourant",(int)j.nbreDePtDeVieCourant);
				joueur.put("nbrePtDeVieMax",(int)j.nbreDePtDeVieMax);
				System.out.println(joueur.toString()); 
				}}
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	
}
	
	
	
	
	
	public int seDeplacer(Joueur joueur,char porte)throws RemoteException{ 
	
	
		int numPiece = joueur.numPieceCourante;
		int numPorte;
	 // 'O': get(0) //'N': get(1) //'E': get(2) //'S':get(3) 
	  try { 
		  switch (porte)
		  {
		    case 'O':
		     numPorte=0;
		      break;
		    case 'N':
		    numPorte=1;
		      break;
		    case 'E':
		    numPorte=2;
		      break;
		    case 'S':
			    numPorte=3;
			      break;
		    default:
		    	  numPorte=-1;
		  }
		  System.out.println(numPorte);
		  Object obj = parser.parse(new FileReader(file)); 
		  JSONObject jsonObject = (JSONObject) obj; 
		  JSONArray pieces = (JSONArray) jsonObject.get("Pieces");
	  
	  for (int i = 0; i < pieces.size(); i++) {
	  
	  JSONObject piece = (JSONObject) pieces.get(i);
	  if(((Number)piece.get("numPiece")).intValue()==numPiece){ 
		  JSONArray portes =(JSONArray)piece.get("portes"); 
		  //verifier si la porte existe
		  if(((Number)portes.get(numPorte)).intValue()!=0){
			  switch (numPorte)
			  {
			    case 0:
			     joueur.numPieceCourante-=1;
			      break;
			    case 1:
			    	  joueur.numPieceCourante-=5;
			      break;
			    case 2:
			    	  joueur.numPieceCourante+=1;
			      break;
			    case 3:
			    	  joueur.numPieceCourante+=5;
				      break;
			    default:
			    	  joueur.numPieceCourante=joueur.numPieceCourante;
			  }
			  
		  }
		  else
		  {
			  return 0;
		  }
		
	  }
	    } 
	  
	  } catch (Exception e) { // TODO Auto-generated catch block
	  e.printStackTrace(); } 
	  return(joueur.numPieceCourante);
	}

}
