package view;

import gui.funzioniModeratorePage;
import gui.listaGiochiPage;
import gui.profiloUtentePage;
import gui.registrazionePage;

public class profiloUtenteView {

	public void visualizzaListaGiochi(profiloUtentePage pP, int userID) {
			pP.setVisible(false); 
			pP.dispose(); // Chiudi profiloUtentePage.
			listaGiochiPage frameListaGiochi = new listaGiochiPage(userID);
			frameListaGiochi.setVisible(true);
	}

	public void passaModeratore(profiloUtentePage pP) {
		// TODO Auto-generated method stub
		pP.setVisible(false); 
		pP.dispose(); // Chiudi profiloUtentePage.
		funzioniModeratorePage frameModeratore = new funzioniModeratorePage();
		frameModeratore.setVisible(true);
	}

}
