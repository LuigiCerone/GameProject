package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import view.funzioniModeratoreView;

public class funzioniModeratorePage extends JFrame {

	private int userID;
	private JPanel recensioni;
	private JPanel cambioLivello;
	private JLabel lblNewLabel;
	private JLabel lblVotoGioco;
	
	/**
	 * Frame constructor.
	 */
	public funzioniModeratorePage() {
		
		funzioniModeratorePage fMP = this;
		
		setTitle("Funzioni Moderatore");
		//setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		
		GridLayout layout = new GridLayout(2, 1);
		fMP.getContentPane().setLayout(layout);
		
		JPanel recensioni = new JPanel();
		getContentPane().add(recensioni);
		
		JButton btnDisapprova = new JButton("Disapprova");
		recensioni.add(btnDisapprova);
		JButton btnApprova = new JButton("Approva");
		recensioni.add(btnApprova);
		
		JButton btnTornaDietro = new JButton("Torna al menu");
		recensioni.add(btnTornaDietro);
		
		JPanel cambioLivello = new JPanel();
		getContentPane().add(cambioLivello);
		
		JLabel lblModificaEsperienza = new JLabel("Modifica Esperienza:");
		cambioLivello.add(lblModificaEsperienza);
		lblModificaEsperienza.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblModificaEsperienza.setVerticalAlignment(SwingConstants.TOP);
		lblModificaEsperienza.setHorizontalAlignment(SwingConstants.CENTER);
		
		String[] names = {"Aumenta", "Diminuisci"};
		
		JComboBox comboBox = new JComboBox(names);
		cambioLivello.add(comboBox);
		
		JTextField xpField = new JTextField();
		
		xpField.setPreferredSize(new Dimension(60, 22));
		cambioLivello.add(xpField);
		
		JButton btnAfferma = new JButton("Afferma");
		
		cambioLivello.add(btnAfferma);
		
		new funzioniModeratoreView().creaListaUtenti(cambioLivello, btnAfferma);
		
		JLabel lblErroreUtenti = new JLabel("");
		cambioLivello.add(lblErroreUtenti);
		JLabel lblErroreRecensioni = new JLabel("");
		recensioni.add(lblErroreRecensioni);
	
		new funzioniModeratoreView().creaListaRecensioni(recensioni, btnApprova, btnDisapprova);
		
		btnDisapprova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new funzioniModeratoreView().disapprovaRecensione(btnDisapprova, lblErroreRecensioni);
			}
		});
		
		btnApprova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new funzioniModeratoreView().approvaRecensione(btnApprova, lblErroreRecensioni);
			}
		});
		
		btnAfferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new funzioniModeratoreView().modificaXPUtente(comboBox, xpField, btnAfferma, lblErroreUtenti);
			}
		});
		
		btnTornaDietro.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				new funzioniModeratoreView().tornaDietro(fMP);
			}
		});
	}


}
