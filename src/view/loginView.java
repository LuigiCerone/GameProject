package view;

import javax.swing.JLabel;

import controller.loginController;
import gui.loginPage;
import gui.profiloUtentePage;
import gui.registrazionePage;

public class loginView {
	
	/**
	 * Method used to log in.
	 * 
	 * @param username username
	 * @param password password
	 * @param labelErrore a JLabel used to show errors to the users if any
	 * @param lP login page that will be closed.
	 * */
	public void accedi(String username, String password, JLabel labelErrore, loginPage lP){
		if(loginController.controllaUtente(username,password)){
			//System.out.println("Utente ok");
			passaAProfilo(lP);
		}
		else labelErrore.setVisible(true);
	}
	
	/**
	 * Method used to switch to the sign up page.
	 * 
	 * @param lP login page that will be closed.
	 * */
	public void registrati(loginPage lP){
		// The user wants to sign up.
		lP.setVisible(false); 
		lP.dispose();
		registrazionePage frameRegistrazione = new registrazionePage();
		frameRegistrazione.setVisible(true);
	}
	
	/**
	 * Method that will close the login form.
	 * 
	 * @param lP login page that will be closed.*/
	private void passaAProfilo(loginPage lP){
		lP.setVisible(false);
		lP.dispose();
		profiloUtentePage framePaginaUtente = new profiloUtentePage(loginController.mObject);
		framePaginaUtente.setVisible(true);
	}
}
