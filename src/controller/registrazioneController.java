package controller;

import model.Utente;
import model.dao.UtenteDAO;

public class registrazioneController {

	/**
	 * Method used to verify that the data are available.
	 * 
	 * @param u user's username
	 * @param e user's email
	 * 
	 * @return true if they aren't available
	 * false otherwise.
	 * */
	public static boolean datiGi‡Occupati(String u, String e) {
		// TODO Auto-generated method stub.
		return new UtenteDAO().datiGi‡Occupati(u,e);
	}

	/**
	 * Method used to insert a new user into the system.
	 * 
	 * @param n user's first name
	 * @param c user's last name
	 * @param u user's username
	 * @param e user's email
	 * @param p user's password
	 * */
	public static Utente inserisciNuovoUtente(String n, String c, String u, String e, String p) {
		Utente user = (Utente) new UtenteDAO().insericiNuovoUtente(n,c,u,e,p);
		loginController.mObject = user;
		return user;
	}

}
