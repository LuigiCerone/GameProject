package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.funzioniModeratoreController;
import controller.loginController;
import gui.funzioniModeratorePage;
import gui.profiloUtentePage;



public class funzioniModeratoreView {
	
	private JTable tableRecensioni = new JTable();
	private JScrollPane scrollPane = new JScrollPane();
	private JTable tableUtenti;
	private JScrollPane scrollPaneUtenti;
	
	public void creaListaRecensioni(JPanel recensioni,JButton approva, JButton disapprova){
		
		String[] names = { "id" , "testo" , "approvata" };
		
		Object[][] mMatrix = funzioniModeratoreController.listaRecensioni();
		
		
		tableRecensioni = new JTable(mMatrix,names);
		tableRecensioni.setDefaultEditor(Object.class, null);
		scrollPane = new JScrollPane(tableRecensioni);
		scrollPane.setColumnHeaderView(tableRecensioni.getTableHeader());
		scrollPane.setPreferredSize(new Dimension(800,200));
		recensioni.add(scrollPane);
		tableRecensioni.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	        	
	        	if (!event.getValueIsAdjusting())//This line prevents double events
	            System.out.println(tableRecensioni.getValueAt(tableRecensioni.getSelectedRow(), 0).toString());
	        	//mostraRecensione(table.getValueAt(table.getSelectedRow(), 0).toString());
	        	System.out.println(tableRecensioni.getSelectedRow());
	        	approva.putClientProperty("id", tableRecensioni.getValueAt(tableRecensioni.getSelectedRow(), 0).toString());
	        	approva.putClientProperty("riga", tableRecensioni.getSelectedRow() + 1);
	        	disapprova.putClientProperty("id", tableRecensioni.getValueAt(tableRecensioni.getSelectedRow(), 0).toString());
	        	disapprova.putClientProperty("riga", tableRecensioni.getSelectedRow() +1 );
	        	
	        	//creaListaRecensioni(recensioni,approva,disapprova);
	        }

	    });
		
	}

	public void approvaRecensione(JButton btnApprova) {
		String idRecensione= (String) btnApprova.getClientProperty("id");
		System.out.println(idRecensione);
		funzioniModeratoreController.approvaRecensione(idRecensione);
		
		DefaultTableModel model = (DefaultTableModel)tableRecensioni.getModel();
		model.removeRow((int) btnApprova.getClientProperty("riga"));
	}


	public void disapprovaRecensione(JButton btnDisapprova) {
		// TODO Auto-generated method stub
		String idRecensione= (String) btnDisapprova.getClientProperty("id");
		System.out.println(idRecensione);
		funzioniModeratoreController.disapprovaRecensione(idRecensione);
		((DefaultTableModel)tableRecensioni.getModel()).removeRow((int) btnDisapprova.getClientProperty("riga"));
	}

	public void modificaXPUtente(JComboBox comboBox, JTextField xpField, JButton btnAfferma) {
		String op = "";
		if(comboBox.getSelectedItem().equals("Aumenta"))
			op = "+";
		else 
			op = "-";
		op += xpField.getText();
		System.out.println(op);
		funzioniModeratoreController.modificaXPUtente(op);
	}

	public void creaListaUtenti(JPanel cambioLivello) {
		// TODO Auto-generated method stub

		String[] names = { "Id" , "Username" , "XP", "Livello" };
		Object[][] mMatrix = funzioniModeratoreController.listaUtenti();
		
		tableUtenti = new JTable(mMatrix,names);
		tableUtenti.setDefaultEditor(Object.class, null);
		
		
		scrollPaneUtenti = new JScrollPane(tableUtenti);
		scrollPaneUtenti.setColumnHeaderView(tableUtenti.getTableHeader());
		scrollPaneUtenti.setPreferredSize(new Dimension(800,200));
		cambioLivello.add(scrollPaneUtenti);
		
		tableUtenti.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	           	if (!event.getValueIsAdjusting())//This line prevents double events
	            System.out.println(tableUtenti.getValueAt(tableUtenti.getSelectedRow(), 0).toString());
	           	tableUtenti.revalidate();
	        	//mostraRecensione(table.getValueAt(table.getSelectedRow(), 0).toString());
	        	
	        	//creaListaRecensioni(recensioni,approva,disapprova);
	        }

	    });
	}

	public void tornaDietro(funzioniModeratorePage fMP) {
		// TODO Auto-generated method stub
		fMP.setVisible(false);
		fMP.dispose();
		funzioniModeratoreController.aggiornaDatiGioco();
		profiloUtentePage framePaginaUtente = new profiloUtentePage(loginController.mObject);
		framePaginaUtente.setVisible(true);
	}

}
