package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;

import controller.listaGiochiController;
import controller.loginController;
import gui.aggiungiRecensionePage;
import gui.listaGiochiPage;
import gui.profiloUtentePage;
import model.Gioco;

public class listaGiochiView {
	private static JTable tableGiochi = new JTable();
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
	private JButton aggiungiRecButton = null;
	
	/**
	 * Method used to add xp to the user when he/she decides to play with a game.
	 * 
	 * @param userID the user's ID
	 * @param button a JButton that when clicked means "played".
	 *  */
	public void aggiungiXPUtente(int userID, JButton button) {
		int valoreXPGioco = (int) button.getClientProperty("xp");
		listaGiochiController.aggiungiXPUtente(userID, valoreXPGioco);
	}

	/**
	 * Method used to close the frame
	 * 
	 * @param lGP the frame that has to be closed.
	 * */
	public void tornaDietro(listaGiochiPage lGP) {
		// TODO Auto-generated method stub
		lGP.setVisible(false);
		lGP.dispose();
		new listaGiochiController().aggiornaDatiGioco(loginController.mObject);
		profiloUtentePage framePaginaUtente = new profiloUtentePage(loginController.mObject);
		framePaginaUtente.setVisible(true);
	}


	/**
	 * Method used to create the list of all the games available on the platform.
	 * This information will be inserted into a JTable and a JScrollPane.
	 * 
	 * @param lGP the frame where the list needs to be placed
	 * @param mMap a TreeMap that is used to keep track of all the Components necessary.
	 * */
	public void creaLista(listaGiochiPage lGP, TreeMap<String,Component> mMap) {
		String[] names = {"Id", "Nome", "Valore", "Media voti"};
		Object[][] mMatrix = listaGiochiController.listaGiochi();
		
		tableGiochi = new JTable(mMatrix,names);
		tableGiochi.setDefaultEditor(Object.class, null);
		tableGiochi.setName("tableGiochi");
		mMap.put(tableGiochi.getName(), tableGiochi);

		scrollPane = new JScrollPane(tableGiochi);
		scrollPane.setColumnHeaderView(tableGiochi.getTableHeader());
		scrollPane.setPreferredSize(new Dimension(800,130));
		scrollPane.setName("scrollPane");
		mMap.put(scrollPane.getName(), scrollPane);
		
		contentPane = (JPanel) mMap.get("contentPane");
		contentPane.add(scrollPane);
		
		//System.out.println(mMap.toString());
		
		tableGiochi.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	        	if (!event.getValueIsAdjusting())//This line prevents double events
	            //System.out.println(tableGiochi.getValueAt(tableGiochi.getSelectedRow(), 0).toString());
	        	
	        	mostraGioco(tableGiochi.getValueAt(tableGiochi.getSelectedRow(), 0).toString(), lGP, mMap);
	        	btnNuovoVoto.putClientProperty("riga", tableGiochi.getSelectedRow());
	        }
	    });
	}

	/**
	 * Method used to display all the information about the requested game.
	 * 
	 * @param idGioco the requested game's ID
	 * @param mMap a HashMap that is used to keep track of all the Components necessary.
	 * */
	private void mostraGioco(String idGioco, listaGiochiPage lGP,  TreeMap<String,Component> mMap){
		
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
		lblInfoGioco.setText("<html> <ul> <li> Valore XP: " + gRichiesto.getValoreXP()+"</li></ul></html>");
		lblInfoGioco.setVerticalAlignment(JLabel.NORTH);
		lblInfoGioco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		infoGioco.add(lblInfoGioco);

		// Bottone valore.
		buttonGioca = (JButton) mMap.get("buttonGioca");
		buttonGioca.setVisible(true);
		buttonGioca.putClientProperty("xp", gRichiesto.getValoreXP());

		btnNuovoVoto = (JButton) mMap.get("btnNuovoVoto");	
		btnNuovoVoto.setVisible(true);
		btnNuovoVoto.putClientProperty("id", gRichiesto.getId());
		
		// New vote.
		nuovoVoto = (JPanel) mMap.get("nuovoVoto");
		nuovoVoto.setVisible(true);
		
		
		// List of the reviews.
		String[] name = {"Testo"};
		Object[][] mMatrix = listaGiochiController.listaRecensioni(gRichiesto.getId());
		
		tableRecensioni = new JTable(mMatrix,name);
		tableRecensioni.setDefaultEditor(Object.class, null);
		tableRecensioni.setName("tableRecensioni");
		mMap.put(tableRecensioni.getName(), tableRecensioni);

		scrollPaneRecensioni = new JScrollPane(tableRecensioni);
		scrollPaneRecensioni.setColumnHeaderView(tableRecensioni.getTableHeader());
		scrollPaneRecensioni.setName("scrollPaneRecensioni");
		scrollPaneRecensioni.setPreferredSize(new Dimension(400,130));
		mMap.put(scrollPane.getName(), scrollPaneRecensioni);
		
		recensioniPanel.add(scrollPaneRecensioni);
		
		// New review.
		aggiungiRecButton = (JButton) mMap.get("aggiungiRecButton");
		aggiungiRecButton.setVisible(true);
		aggiungiRecButton.putClientProperty("id", gRichiesto.getId());
	}

	/**
	 * Method used to add a new vote to the game
	 * 
	 * @param votoInserito the new vote
	 * @param btnNuovoVoto a JButton that when clicked insert the new vote.
	 * */
	public void aggiungiVotoGioco(int votoInserito, JButton btnNuovoVoto) {
		int idGioco = (int) btnNuovoVoto.getClientProperty("id");
		int idUtente = loginController.mObject.getID();
		
		if(listaGiochiController.giocoGiaVotato(idGioco, idUtente)){
			//System.out.println("Gia votato");
			listaGiochiController.modificaVoto(idGioco, idUtente, votoInserito);
		}
		else{
			//System.out.println("Voto "+ votoInserito + " per " + idGioco + " da " + idUtente);
			listaGiochiController.aggiungiVoto(idGioco,idUtente,votoInserito);
			//System.out.println("Nuovo voto");
		}
		
		tableGiochi.getModel().setValueAt(listaGiochiController.ottieniMedia(idGioco), (int) btnNuovoVoto.getClientProperty("riga"), 3);
	}


	/**
	 * Method used to add a new review.
	 * 
	 * @param aggiungiRecButton a JButton that when clicked insert the new review.
	 * */
	public void aggiungiRecensione(JButton aggiungiRecButton) {
		int idGioco = (int) aggiungiRecButton.getClientProperty("id");
		aggiungiRecensionePage frameAggiungiRec = new aggiungiRecensionePage(idGioco);
		frameAggiungiRec.setVisible(true);
	}

}
