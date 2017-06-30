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

import java.util.HashMap;

public class listaGiochiPage extends JFrame {

	private JPanel contentPane;
	private JPanel infoGioco;
	private JPanel recensioniGioco;
	private JList list;
	private int userID;
	private JLabel lblVoto;
	private JLabel lblNewLabel;
	private JLabel lblVotoGioco;
	private JSpinner spinner;
	private JButton btnNuovoVoto;
	private JLabel label;
	private HashMap<String, Component> mMap = new HashMap<String, Component>();
	private JPanel recensioniPanel;
	private JLabel lblRecensioni;

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
		contentPane.setName("contentPane");
		mMap.put(contentPane.getName(), contentPane);
		//setContentPane(contentPane);
		
		
		recensioniGioco = new JPanel(new BorderLayout());
		recensioniGioco.setLayout(new GridLayout(1,2));
		recensioniGioco.setName("recensioniGioco");
		mMap.put(recensioniGioco.getName(), recensioniGioco);
		
		infoGioco =  new JPanel(new BorderLayout());
		infoGioco.setName("infoGioco");
		mMap.put(infoGioco.getName(), infoGioco);
		
		getContentPane().add(contentPane);
		getContentPane().add(infoGioco);
		getContentPane().add(recensioniGioco);
		
		JLabel lblNomeGioco = new JLabel();
		lblNomeGioco.setName("lblNomeGioco");
		mMap.put(lblNomeGioco.getName(), lblNomeGioco);
		contentPane.add(lblNomeGioco);
		
		JLabel lblInfoGioco = new JLabel();
		lblInfoGioco.setName("lblInfoGioco");
		//infoGioco.add(lblInfoGioco);
		mMap.put(lblInfoGioco.getName(), lblInfoGioco);
		
		JButton buttonGioca = new JButton("Gioca");
		buttonGioca.setName("buttonGioca");
		buttonGioca.setVisible(false);
		mMap.put(buttonGioca.getName(), buttonGioca);
		
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.setName("topPanel");
		mMap.put(topPanel.getName(), topPanel);
		
		JButton buttonBack = new JButton("Indietro");
		buttonBack.setName("buttonBack");
		mMap.put(buttonBack.getName(), buttonBack);
		
		topPanel.add(buttonBack);
		topPanel.add(buttonGioca);
		
		JPanel bottomPanel = new JPanel(new GridLayout(2,1));
		bottomPanel.setName("bottomPanel");
		recensioniGioco.add(bottomPanel);
		mMap.put(bottomPanel.getName(), bottomPanel);
		
		lblVoto = new JLabel("Voto medio:");
		lblVoto.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblVoto.setVerticalAlignment(SwingConstants.TOP);
		lblVoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoto.setName("lblVoto");
		bottomPanel.add(lblVoto);
		mMap.put(lblVoto.getName(), lblVoto);
		
		
		contentPane.add(BorderLayout.EAST, topPanel);
		
		//infoGioco.add(button);
		lblVotoGioco= new JLabel("");
		lblVotoGioco.setHorizontalAlignment(SwingConstants.LEFT);
		bottomPanel.add(lblVotoGioco);
		lblVotoGioco.setName("lblVotoGioco");
		mMap.put(lblVotoGioco.getName(), lblVotoGioco);
		
		
		lblNewLabel = new JLabel("Nuovo voto:");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setName("lblNewLabel");
		bottomPanel.add(lblNewLabel);
		mMap.put(lblNewLabel.getName(), lblNewLabel);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinner.setName("spinner");
		//spinner.setEditor(null);
		
		mMap.put(spinner.getName(), spinner);
		
		JPanel nuovoVoto = new JPanel(new FlowLayout());
		nuovoVoto.setName("nuovoVoto");
		mMap.put(nuovoVoto.getName(), nuovoVoto);
		
		btnNuovoVoto = new JButton("Vota");
		btnNuovoVoto.setHorizontalAlignment(SwingConstants.LEFT);
		nuovoVoto.add(btnNuovoVoto);
		nuovoVoto.setVisible(false);
		btnNuovoVoto.setName("btnNuovoVoto");
		mMap.put(btnNuovoVoto.getName(), btnNuovoVoto);
		//btnVota.setVisible(false);
		
		label = new JLabel("         ");
		label.setName("label");
		mMap.put(label.getName(), label);
		
		nuovoVoto.add(label);
		nuovoVoto.add(spinner);
		
		bottomPanel.add(BorderLayout.CENTER, nuovoVoto);
		
		recensioniPanel = new JPanel();
		recensioniGioco.add(recensioniPanel, BorderLayout.NORTH);
		recensioniPanel.setName("recensioniPanel");
		mMap.put("recensioniPanel",  recensioniPanel);
		
		lblRecensioni = new JLabel("Recensioni: ");
		lblRecensioni.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRecensioni.setName("lblRecensioni");
		mMap.put("lblRecensioni",  lblRecensioni);
		
		recensioniPanel.add(lblRecensioni);
		
		// lista giochi.
		new listaGiochiView().creaLista1(lGP, mMap);
		
		buttonGioca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// L'utente vuole giocare ad un particolare gioco.
				new listaGiochiView().aggiungiXPUtente(userID, buttonGioca);
			}
		});
		
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new listaGiochiView().tornaDietro(lGP);
			}
		});
		
		btnNuovoVoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new listaGiochiView().aggiungiVotoGioco((int)spinner.getValue(), btnNuovoVoto);
			}
		});
	}

}
