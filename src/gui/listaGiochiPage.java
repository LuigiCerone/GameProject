package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.listaGiochiView;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Component;
import javax.swing.Box;

public class listaGiochiPage extends JFrame {

	private JPanel contentPane;
	private JPanel infoGioco;
	private JPanel recensioniGioco;
	private JList list;
	private JLabel lblRecensioni;
	private int userID;
	private JLabel lblVoto;
	private JLabel lblNewLabel;
	private JLabel lblVotoGioco;
	private JSpinner spinner;
	private JButton btnVota;
	private JLabel label;

	/**
	 * Create the frame.
	 */
	public listaGiochiPage(int userID) {
		listaGiochiPage lGP = this;
		this.userID = userID;
		setTitle("Lista giochi");
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		GridLayout layout = new GridLayout(3, 1);
		lGP.getContentPane().setLayout(layout);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		
		
		recensioniGioco = new JPanel(new BorderLayout());
		recensioniGioco.setLayout(new GridLayout(1,2));
		
		infoGioco =  new JPanel(new BorderLayout());
		getContentPane().add(contentPane);
		getContentPane().add(infoGioco);
		getContentPane().add(recensioniGioco);
		
		JLabel lblNomeGioco = new JLabel();
		JLabel lblInfoGioco = new JLabel();
		
		JButton button = new JButton("Gioca");
		button.setVisible(false);
		
		
		JPanel topPanel = new JPanel(new FlowLayout());
		JButton buttonBack = new JButton("Indietro");
		
		
		topPanel.add(buttonBack);
		topPanel.add(button);
		
		JPanel bottomPanel = new JPanel(new GridLayout(2,1));
		recensioniGioco.add(bottomPanel);
		
		lblVoto = new JLabel("Voto medio:");
		lblVoto.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblVoto.setVerticalAlignment(SwingConstants.TOP);
		lblVoto.setHorizontalAlignment(SwingConstants.CENTER);
		bottomPanel.add(lblVoto);
		
		
		contentPane.add(BorderLayout.EAST, topPanel);
		
		//infoGioco.add(button);
		lblVotoGioco= new JLabel("");
		lblVotoGioco.setHorizontalAlignment(SwingConstants.LEFT);
		bottomPanel.add(lblVotoGioco);
		
		
		
		
		lblNewLabel = new JLabel("Nuovo voto:");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		bottomPanel.add(lblNewLabel);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		
		JPanel nuovoVoto = new JPanel(new FlowLayout());
		
		btnVota = new JButton("Vota");
		btnVota.setHorizontalAlignment(SwingConstants.LEFT);
		nuovoVoto.add(btnVota);
		nuovoVoto.setVisible(false);
		//btnVota.setVisible(false);
		
		label = new JLabel("         ");
		nuovoVoto.add(label);
		nuovoVoto.add(spinner);
		
		bottomPanel.add(BorderLayout.CENTER, nuovoVoto);
		
		
		
		
		lblRecensioni = new JLabel("Recensioni:");
		lblRecensioni.setVerticalAlignment(SwingConstants.TOP);
		lblRecensioni.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecensioni.setFont(new Font("Tahoma", Font.BOLD, 17));
		recensioniGioco.add(lblRecensioni, BorderLayout.SOUTH);
		
		// lista giochi.
		new listaGiochiView().creaLista(contentPane, infoGioco , recensioniGioco, list, lblNomeGioco, lblInfoGioco, button, 
						nuovoVoto, lblVotoGioco, btnVota);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// L'utente vuole giocare ad un particolare gioco.
				new listaGiochiView().aggiungiXPUtente(userID, button);
			}
		});
		
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new listaGiochiView().tornaDietro(lGP);
			}
		});
	}

}
