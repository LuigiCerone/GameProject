package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.profiloUtenteController;
import gui.funzioniModeratorePage;
import gui.listaGiochiPage;
import gui.profiloUtentePage;
import gui.registrazionePage;

public class profiloUtenteView {
	JTable tableTimeLine;
	JScrollPane scrollPaneTimeLine;

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

}
