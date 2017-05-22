/*
 * @author Cerone Luigi
 * @version 1.0
 * */

package controller;

import gui.registrazionePage;
import model.Utente;
import model.dao.UtenteDAO;

public class registrazioneController {

	public static boolean datiGi‡Occupati(String u, String e) {
		// TODO Auto-generated method stub.
		return new UtenteDAO().datiGi‡Occupati(u,e);
	}

	public static Object inserisciNuovoUtente(String n, String c, String u, String e, String p) {
		Utente user = (Utente) new UtenteDAO().insericiNuovoUtente(n,c,u,e,p);
		loginController.mObject = user;
		return user;
	}

}
