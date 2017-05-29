package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.listaGiochiController;
import controller.loginController;
import model.Gioco;
import gui.listaGiochiPage;
import gui.profiloUtentePage;

public class listaGiochiView {

	public void creaLista(JPanel contentPane, JPanel infoGioco, JPanel recensioniGioco, JList list, 
			JLabel lblNomeGioco, JLabel lblInfoGioco, JButton buttonGioca, JPanel nuovoVoto, JLabel lblVotoGioco, JButton btnVota) {
		// TODO Auto-generated method stub
		String[] names = {"Id", "Nome", "Valore", "Media voti"};
		Object[][] mMatrix = listaGiochiController.listaGiochi();
		
		JTable table = new JTable(mMatrix,names);
		table.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setColumnHeaderView(table.getTableHeader());
		scrollPane.setPreferredSize(new Dimension(800,300));
		
		contentPane.add(scrollPane);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	        	if (!event.getValueIsAdjusting())//This line prevents double events
	            System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	        	
	        	mostraGioco(table.getValueAt(table.getSelectedRow(), 0).toString(), lblNomeGioco, lblInfoGioco ,
	        			infoGioco, recensioniGioco , buttonGioca, nuovoVoto, lblVotoGioco, btnVota);
	        	
	        }
	    });
	}
	
	
	private void mostraGioco (String id, JLabel lblNomeGioco, JLabel lblInfoGioco, JPanel infoGioco, 
			JPanel recensioniGioco, JButton button, JPanel nuovoVoto, JLabel lblVotoGioco, JButton btnVota) {
		
		Gioco gRichiesto = listaGiochiController.informazioniGiocoDaId(id);
		
		lblNomeGioco.setText(gRichiesto.getNome().substring(0, 1).toUpperCase() + gRichiesto.getNome().substring(1));
		lblNomeGioco.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeGioco.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNomeGioco.setVerticalAlignment(JLabel.NORTH);
		infoGioco.add(lblNomeGioco, BorderLayout.NORTH);
		
		lblInfoGioco.setText("<html> <ul> <li> Valore XP: " + gRichiesto.getValoreXP()+"</li>"
				+ "<li>Voto medio: " + gRichiesto.getMedia()+ "</li></ul></html>");
		lblInfoGioco.setVerticalAlignment(JLabel.NORTH);
		lblInfoGioco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		infoGioco.add(lblInfoGioco);
		
		// Bottone valore.
		button.setVisible(true);
		button.putClientProperty("xp", gRichiesto.getValoreXP());
		
		lblVotoGioco.setVerticalAlignment(JLabel.NORTH);
		lblVotoGioco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVotoGioco.setText(gRichiesto.getMedia() + "");
		
		nuovoVoto.setVisible(true);
		//btnVota.putClientProperty("voto", );
	}
	
	public void aggiungiXPUtente(int userID, JButton button) {
		// TODO Auto-generated method stub
		int valoreXPGioco = (int) button.getClientProperty("xp");
		listaGiochiController.aggiungiXPUtente(userID, valoreXPGioco);
	}


	public void tornaDietro(listaGiochiPage lGP) {
		// TODO Auto-generated method stub
		lGP.setVisible(false);
		lGP.dispose();
		new listaGiochiController().aggiornaDatiGioco(loginController.mObject);
		profiloUtentePage framePaginaUtente = new profiloUtentePage(loginController.mObject);
		framePaginaUtente.setVisible(true);
	}

	
}
