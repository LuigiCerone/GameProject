package view;

import controller.aggiungiRecensioneController;
import gui.aggiungiRecensionePage;

public class aggiungiRecensioneView {

	/**
	 * Method used to close current frame.
	 * 
	 * @param aRP the frame that has to be closed.
	 * */
	public void annulla(aggiungiRecensionePage aRP) {
		aRP.setVisible(false);
		aRP.dispose();
	}

	/**
	 * Method used to add a new game's review.
	 * 
	 * @param recensioneText new review's text
	 * @param idGioco game's ID.
	 * */
	public void aggiungi(String recensioneText, int idGioco) {
		new aggiungiRecensioneController().aggiungi(recensioneText, idGioco);
	}
	
	
}
