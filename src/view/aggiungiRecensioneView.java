package view;

import controller.aggiungiRecensioneController;
import controller.listaGiochiController;
import controller.loginController;
import gui.aggiungiRecensionePage;
import gui.profiloUtentePage;

public class aggiungiRecensioneView {

	public void annulla(aggiungiRecensionePage aRP) {
		aRP.setVisible(false);
		aRP.dispose();
	}

	public void aggiungi(String recensioneText, int idGioco) {
		new aggiungiRecensioneController().aggiungi(recensioneText, idGioco);
	}
	
	
}
