/*
 * @author Luigi Cerone
 * @version 1.0
 * */
package controller;

import model.Moderatore;
import model.Utente;
import model.dao.UtenteDAO;

public class loginController {
	public static Object mObject = null;
	public static boolean controllaUtente(String username, String password){
		
		mObject = new UtenteDAO().tipoUtente(username, password);
		
		// l'utente non esiste.
		if(mObject == null){
			return false;
		}
		// l'utente è un giocatore.
		if(mObject instanceof Utente){
			mObject = (Utente) mObject;
			System.out.println(((Utente) mObject).getNome());
			return true;
		}
		// l'utente è un moderatore.
		if(mObject instanceof Moderatore){}
		return false;
		
	}
}
