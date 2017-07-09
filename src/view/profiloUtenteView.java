package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.profiloUtenteController;
import gui.funzioniModeratorePage;
import gui.listaGiochiPage;
import gui.listaTrofeiPage;
import gui.loginPage;
import gui.profiloUtentePage;

public class profiloUtenteView {
	JTable tableTimeLine;
	JScrollPane scrollPaneTimeLine;

	/**
	 * Method used to launch the games' list view.
	 * 
	 * @param pP user page that will be closed
	 * @param userID the user's ID currently logged in.
	 * */
	public void visualizzaListaGiochi(profiloUtentePage pP, int userID) {
		pP.setVisible(false); 
		pP.dispose(); // Chiudi profiloUtentePage.
		listaGiochiPage frameListaGiochi = new listaGiochiPage(userID);
		frameListaGiochi.setVisible(true);
	}

	/**
	 * Method used to launch the moderator's frame functionalities
	 * 
	 * @param pP user page that will be closed
	 * */
	public void passaModeratore(profiloUtentePage pP) {
		pP.setVisible(false); 
		pP.dispose();
		funzioniModeratorePage frameModeratore = new funzioniModeratorePage();
		frameModeratore.setVisible(true);
	}

	/**
	 * Method used to create the user timeline
	 * 
	 * @param bottomPanel a JPanel where the timeline will be placed.
	 * */
	public void creaTimeLine(JPanel bottomPanel) {
		String[] names = { "Data" , "Livello"};
		Object[][] mMatrix = profiloUtenteController.timeLine();
		
		tableTimeLine = new JTable(mMatrix,names);
		tableTimeLine.setDefaultEditor(Object.class, null);
		
		
		scrollPaneTimeLine = new JScrollPane(tableTimeLine);
		scrollPaneTimeLine.setColumnHeaderView(tableTimeLine.getTableHeader());
		scrollPaneTimeLine.setPreferredSize(new Dimension(190,100));
		bottomPanel.add(scrollPaneTimeLine);
	}

	/**
	 * Method used to fetch all the user's trophies.
	 * 
	 *  @param userID the user's ID currently logged in
	 *  @param livello user's currently level
	 * */
	public void visualizzaListaTrofei(int userID, int livello) {
		listaTrofeiPage frameListaTrofei = new listaTrofeiPage(userID, livello);
		frameListaTrofei.setVisible(true);
	}

	/**
	 * Method used to log out
	 * 
	 * @param pP the user profile frame that has to be closed.
	 * */
	public void logOut(profiloUtentePage pP) {
		pP.setVisible(false); 
		pP.dispose();
		loginPage frameLogin = new loginPage();
		frameLogin.setVisible(true);
	}

}
