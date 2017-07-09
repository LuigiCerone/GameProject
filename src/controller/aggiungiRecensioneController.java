package controller;

import model.dao.GiocoDAO;

public class aggiungiRecensioneController {

	/**
	 * Method used to add a new game's review.
	 * 
	 * @param recensioneText new review's text
	 * @param idGioco game's ID.
	 * */
	public void aggiungi(String recensioneText, int idGioco) {
		new GiocoDAO().aggiungiRecensione(recensioneText, idGioco);
	}
}
