package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
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
	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane();
	private JLabel lblNomeGioco = new JLabel();
	private JLabel lblInfoGioco = new JLabel();
	private JButton buttonGioca = new JButton();
	private JLabel lblVotoGioco = new JLabel();
	private JButton btnNuovoVoto = new JButton();
	private JPanel infoGioco = new JPanel();
	private JPanel contentPane = new JPanel();
	private JPanel nuovoVoto = new JPanel();
	private JTable tableRecensioni = new JTable();
	private JScrollPane scrollPaneRecensioni = new JScrollPane();
	private JPanel recensioniPanel = new JPanel();
	
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


	public void creaLista1(listaGiochiPage lGP, HashMap<String, Component> mMap) {
		// TODO Auto-generated method stub
		String[] names = {"Id", "Nome", "Valore", "Media voti"};
		Object[][] mMatrix = listaGiochiController.listaGiochi();
		
		table = new JTable(mMatrix,names);
		table.setDefaultEditor(Object.class, null);
		table.setName("table");
		mMap.put(table.getName(), table);

		scrollPane = new JScrollPane(table);
		scrollPane.setColumnHeaderView(table.getTableHeader());
		scrollPane.setPreferredSize(new Dimension(800,300));
		scrollPane.setName("scrollPane");
		mMap.put(scrollPane.getName(), scrollPane);
		
		contentPane = (JPanel) mMap.get("contentPane");
		contentPane.add(scrollPane);
		
		//System.out.println(mMap.toString());
		//Finder listaGiochiFinder = new Finder(lGP);
		
		//System.out.println("QUIIII");
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	        	if (!event.getValueIsAdjusting())//This line prevents double events
	            System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	        	
	        	mostraGioco(table.getValueAt(table.getSelectedRow(), 0).toString(), lGP, mMap);
	        	
	        }
	    });
	}

	private void mostraGioco(String idGioco, listaGiochiPage lGP,  HashMap<String, Component> mMap){
		// TODO Auto-generated method stub
		
		recensioniPanel = (JPanel) mMap.get("recensioniPanel");
		recensioniPanel.removeAll();
		
		Gioco gRichiesto = listaGiochiController.informazioniGiocoDaId(idGioco);
		//listaGiochiFinder.stampa();
		//Component[] components = lGP.getComponents();
		
		lblNomeGioco = (JLabel) mMap.get("lblNomeGioco");
		lblNomeGioco.setText(gRichiesto.getNome().substring(0, 1).toUpperCase() + gRichiesto.getNome().substring(1));
		lblNomeGioco.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeGioco.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNomeGioco.setVerticalAlignment(JLabel.NORTH);
		
		infoGioco = (JPanel) mMap.get("infoGioco");
		infoGioco.add(lblNomeGioco, BorderLayout.NORTH);

		lblInfoGioco = (JLabel) mMap.get("lblInfoGioco");
		lblInfoGioco.setText("<html> <ul> <li> Valore XP: " + gRichiesto.getValoreXP()+"</li>"
				+ "<li>Voto medio: " + gRichiesto.getMedia() + "</li></ul></html>");
		lblInfoGioco.setVerticalAlignment(JLabel.NORTH);
		lblInfoGioco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		infoGioco.add(lblInfoGioco);


		// Bottone valore.
		buttonGioca = (JButton) mMap.get("buttonGioca");
		buttonGioca.setVisible(true);
		buttonGioca.putClientProperty("xp", gRichiesto.getValoreXP());

		lblVotoGioco = (JLabel) mMap.get("lblVotoGioco");
		lblVotoGioco.setVerticalAlignment(JLabel.NORTH);
		lblVotoGioco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVotoGioco.setText(gRichiesto.getMedia() + "");

		btnNuovoVoto = (JButton) mMap.get("btnNuovoVoto");	
		btnNuovoVoto.setVisible(true);
		btnNuovoVoto.putClientProperty("id", gRichiesto.getId());
		
		// Info inserimento nuovo gioco.
		nuovoVoto = (JPanel) mMap.get("nuovoVoto");
		nuovoVoto.setVisible(true);
		
		
		// Recensioni del gioco clickato.
		String[] name = {"Testo"};
		Object[][] mMatrix = listaGiochiController.listaRecensioni(gRichiesto.getId());
		
		tableRecensioni = new JTable(mMatrix,name);
		tableRecensioni.setDefaultEditor(Object.class, null);
		tableRecensioni.setName("tableRecensioni");
		mMap.put(table.getName(), tableRecensioni);

		scrollPaneRecensioni = new JScrollPane(tableRecensioni);
		scrollPaneRecensioni.setColumnHeaderView(tableRecensioni.getTableHeader());
		scrollPaneRecensioni.setName("scrollPaneRecensioni");
		scrollPaneRecensioni.setPreferredSize(new Dimension(400,130));
		mMap.put(scrollPane.getName(), scrollPaneRecensioni);
		
		
		recensioniPanel.add(scrollPaneRecensioni);
	}


	public void aggiungiVotoGioco(int votoInserito, JButton btnNuovoVoto) {
		// TODO Auto-generated method stub
		int idGioco = (int) btnNuovoVoto.getClientProperty("id");
		int idUtente = loginController.mObject.getID();
		
		if(listaGiochiController.giocoGiaVotato(idGioco, idUtente, votoInserito)){
			System.out.println("Gia votato");
			listaGiochiController.modificaVoto(idGioco, idUtente, votoInserito);
		}
		else{
			//System.out.println("Voto "+ votoInserito + " per " + idGioco + " da " + idUtente);
			listaGiochiController.aggiungiVoto(idGioco,idUtente,votoInserito);
			System.out.println("Nuovo voto");
		}
	}

}
