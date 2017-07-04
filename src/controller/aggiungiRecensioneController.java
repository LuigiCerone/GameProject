package controller;

import model.dao.GiocoDAO;

public class aggiungiRecensioneController {

	public void aggiungi(String recensioneText, int idGioco) {
		// sanitize string.
		new GiocoDAO().aggiungiRecensione(recensioneText, idGioco);
	}
}
