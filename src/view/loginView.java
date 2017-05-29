/*
 * @author Luigi Cerone
 * @version 1.0
 * */
package view;

import javax.swing.JLabel;

import controller.loginController;
import gui.loginPage;
import gui.profiloUtentePage;
import gui.registrazionePage;

public class loginView {
	public void accedi(String username, String password, JLabel labelErrore, loginPage lP){
		// Controllare presenza utente altrimenti labelErrore = "errore".
		// se utente normale aprire menu utente base altrimenti se moderatore aprire pag mod.
		
		if(loginController.controllaUtente(username,password)){
			System.out.println("Utente ok");
			passaAProfilo(lP);
		}
		else labelErrore.setVisible(true);
	}
	
	public void registrati(loginPage lP){
		// L'utente si vuole iscrivere.
		lP.setVisible(false); 
		lP.dispose(); // Chiudi loginPage.
		registrazionePage frameRegistrazione = new registrazionePage();
		frameRegistrazione.setVisible(true);
	}
	
	private void passaAProfilo(loginPage lP){
		lP.setVisible(false);
		lP.dispose();
		profiloUtentePage framePaginaUtente = new profiloUtentePage(loginController.mObject);
		framePaginaUtente.setVisible(true);
	}
}
