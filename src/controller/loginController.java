package controller;

import model.Moderatore;
import model.Utente;
import model.dao.UtenteDAO;

public class loginController {
	public static Utente mObject = null;
	
	/**
	 * Method used to determine if the user exists and if so of which type he/she is.
	 * 
	 * @param username user's username
	 * @param password user's password
	 * @return true if the user exists 
	 * false otherwise.
	 * */
	public static boolean controllaUtente(String username, String password){
		
		mObject = new UtenteDAO().tipoUtente(username, password);
		
		// The user doesn't exist.
		if(mObject == null){
			return false;
		}
		// The user is a moderator.
		if(mObject instanceof Moderatore){
			mObject = (Moderatore) mObject;
			System.out.println(((Moderatore) mObject).getNome());
			return true;
		}
		
		// The user is a simple user i.e. a gamer.
		return true;
		
	}
}
