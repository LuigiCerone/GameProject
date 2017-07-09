package view;

import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.funzioniModeratoreController;
import controller.loginController;
import gui.funzioniModeratorePage;
import gui.profiloUtentePage;

public class funzioniModeratoreView {

	private static final String NUMERIC_PATTERN = "^(0|[1-9][0-9]*)$";
	private static JTable tableRecensioni;
	private JScrollPane scrollPane;
	private static JTable tableUtenti;
	private JScrollPane scrollPaneUtenti;
	private static DefaultTableModel tableRecensioniModel;
	private static DefaultTableModel tableUtentiModel;

	/**
	 * Method used to create the list of the reviews that haven't been approved yet.
	 * This list is inserted into a JTable object which is in turn inserted into a JScrollPane
	 * 
	 * @param recensioni a JPanel where the JScrollPane will appear
	 * @param approva a JButton that when clicked approves the currently selected review
	 * @param disapprova a JButton that when clicked disapproves the currently selected review. 
	 * */
	public void creaListaRecensioni(JPanel recensioni, JButton approva, JButton disapprova) {
		String[] names = { "id", "testo", "approvata" };
		Object[][] mMatrix = funzioniModeratoreController.listaRecensioni();

		tableRecensioniModel = new DefaultTableModel(mMatrix, names) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (getRowCount() == 0) {
					return super.getColumnClass(columnIndex);
				}
				Object value = getValueAt(0, columnIndex);
				if (value == null) {
					return super.getColumnClass(columnIndex);
				}

				return value.getClass();
			}
		};

		this.tableRecensioni = new JTable(tableRecensioniModel);
		scrollPane = new JScrollPane(tableRecensioni);
		scrollPane.setColumnHeaderView(tableRecensioni.getTableHeader());
		scrollPane.setPreferredSize(new Dimension(800, 200));
		recensioni.add(scrollPane);
		this.tableRecensioni.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// do some actions here, for example
				// print first column value from selected row

				if (!event.getValueIsAdjusting() && tableRecensioni.getSelectedRow() >= 0
						&& tableRecensioni.getSelectedRow() <= tableRecensioni.getRowCount()) {
					approva.putClientProperty("id",
							tableRecensioni.getValueAt(tableRecensioni.getSelectedRow(), 0).toString());
					approva.putClientProperty("riga", tableRecensioni.getSelectedRow());

					disapprova.putClientProperty("id",
							tableRecensioni.getValueAt(tableRecensioni.getSelectedRow(), 0).toString());
					disapprova.putClientProperty("riga", tableRecensioni.getSelectedRow());
				}
			}

		});

	}

	/**
	 * Method used to approve a specific review
	 * 
	 * @param btnApprova a JButton that when clicked approves the currently selected review
	 * @param lblErroreRecensioni a JLabel used to display error to the user.
	 * */
	public void approvaRecensione(JButton btnApprova, JLabel lblErroreRecensioni) {
		lblErroreRecensioni.setText("");
		String idRecensione = (String) btnApprova.getClientProperty("id");
		System.out.println(idRecensione);
		
		funzioniModeratoreController.approvaRecensione(idRecensione);
		
		int riga = 0;
		if(btnApprova.getClientProperty("riga") == null){
			lblErroreRecensioni.setText("Errore - Seleziona una riga!");
		}
		else {
			riga = (int)btnApprova.getClientProperty("riga");
			tableRecensioniModel.removeRow(riga);
		}
		
	}

	/**
	 * Method used to disapprove a specific review
	 * 
	 * @param btnDisapprova a JButton that when clicked disapproves the currently selected review
	 * @param lblErroreRecensioni a JLabel used to display error to the user.
	 * */
	public void disapprovaRecensione(JButton btnDisapprova, JLabel lblErroreRecensioni) {
		lblErroreRecensioni.setText("");
		String idRecensione = (String) btnDisapprova.getClientProperty("id");
		System.out.println(idRecensione);
		funzioniModeratoreController.disapprovaRecensione(idRecensione);
		int riga = 0;
		if(btnDisapprova.getClientProperty("riga") == null){
			lblErroreRecensioni.setText("Errore - Seleziona una riga!");
		}
		else{
			riga = (int)btnDisapprova.getClientProperty("riga");
			tableRecensioniModel.removeRow(riga);
		}
		
	}

	/**
	 * Method used to modify the user's xp points. Only a moderator can use this method.
	 * 
	 * @param comboBox a JComboBox used to select if moderator wants to increase or decrease xp
	 * @param xpField a JTextField used to insert how many xp points give/remove
	 * @param btnAfferma a JButton that when clicked change the xp
	 * @param lblErroreUtenti a JLabel used to display error to the user.
	 * */
	public void modificaXPUtente(JComboBox comboBox, JTextField xpField, JButton btnAfferma, JLabel lblErroreUtenti) throws NumberFormatException{
		int newXP = 0, riga = 0;
		lblErroreUtenti.setText("");
		if (!controllaTesto(xpField)){
			lblErroreUtenti.setText("Errore - Inserire un numero intero!");
			return;
		}
		String id = (String) btnAfferma.getClientProperty("id");
		System.out.println(id);
		if (btnAfferma.getClientProperty("riga") != null)
			riga = (int) btnAfferma.getClientProperty("riga");
		else
			lblErroreUtenti.setText("Errore - Selezionare un utente!");
		int exXP = (int) tableUtentiModel.getValueAt(riga, 2);
		int exLevel = (int) tableUtentiModel.getValueAt(riga, 3);

		String op = "";

		try {
			if (comboBox.getSelectedItem().equals("Aumenta")) {
				op = "+";
				newXP = exXP + new Integer(xpField.getText());
			} else {
				op = "-";
				newXP = exXP - new Integer(xpField.getText());
			}
			op += xpField.getText();
		} catch (Exception e) {
			System.out.println("Errore formato numero!");
		}
		tableUtentiModel.setValueAt(newXP, riga, 2);

		System.out.println(op);

		// Level check.
		int newLevel = (int) newXP / 100;
		if (newLevel != exLevel) {
			tableUtentiModel.setValueAt(newLevel, riga, 3);
		}
		
		funzioniModeratoreController.modificaXPUtente(op, id);
	}

	/**
	 * Method used to check if the text contains only numerics
	 * 
	 * @param xpField a JTextField with user's data.
	 *  */
	private boolean controllaTesto(JTextField xpField) {
		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile(NUMERIC_PATTERN);
		matcher = pattern.matcher(xpField.getText());

		if (!(matcher.matches())) {
			return false;
		}
		return true;
	}

	/**
	 * Method used to create the list of the system's users.
	 * This list is inserted into a JTable object which is in turn inserted into a JScrollPane 
	 * 
	 * @param cambioLivello a JPanel where the JScrollPane will appear
	 * @param btnAfferma a JButton used to fires the event.
	 * */
	public void creaListaUtenti(JPanel cambioLivello, JButton btnAfferma) {
		// TODO Auto-generated method stub

		String[] names = { "Id", "Username", "XP", "Livello" };
		Object[][] mMatrix = funzioniModeratoreController.listaUtenti(loginController.mObject.getID());

		tableUtentiModel = new DefaultTableModel(mMatrix, names) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableUtenti = new JTable(tableUtentiModel);

		scrollPaneUtenti = new JScrollPane(tableUtenti);
		scrollPaneUtenti.setColumnHeaderView(tableUtenti.getTableHeader());
		scrollPaneUtenti.setPreferredSize(new Dimension(800, 200));
		cambioLivello.add(scrollPaneUtenti);
		// tableUtenti.tableChanged(new
		// TableModelEvent(tableUtenti.getModel()));

		tableUtenti.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && tableUtenti.getSelectedRow() >= 0
						&& tableUtenti.getSelectedRow() <= tableUtenti.getRowCount()) {// This
																						// line
																						// prevents
																						// double
																						// events
					System.out.println(tableUtenti.getValueAt(tableUtenti.getSelectedRow(), 0).toString());
					// mostraRecensione(table.getValueAt(table.getSelectedRow(),
					// 0).toString());
					btnAfferma.putClientProperty("id",
							tableUtenti.getValueAt(tableUtenti.getSelectedRow(), 0).toString());
					btnAfferma.putClientProperty("riga", tableUtenti.getSelectedRow());
					// creaListaRecensioni(recensioni,approva,disapprova);
				}
			}
		});
	}

	/**
	 * Method used to close the frame.
	 * 
	 * @param fMP the frame that has to be closed.
	 * */
	public void tornaDietro(funzioniModeratorePage fMP) {
		// TODO Auto-generated method stub
		fMP.setVisible(false);
		fMP.dispose();
		funzioniModeratoreController.aggiornaDatiGioco();
		profiloUtentePage framePaginaUtente = new profiloUtentePage(loginController.mObject);
		framePaginaUtente.setVisible(true);
	}

}
