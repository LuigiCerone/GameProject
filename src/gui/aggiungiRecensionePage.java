package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.aggiungiRecensioneView;

import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class aggiungiRecensionePage extends JFrame {
	private JPanel contentPane;
	private int idGioco;
	
	public aggiungiRecensionePage(int idGioco) {
		aggiungiRecensionePage aRP = this;
		this.idGioco = idGioco;
		setTitle("Aggiungi recensione");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(5, 5, 311, 251);
		contentPane.add(textPane);
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setBounds(326, 72, 98, 45);
		contentPane.add(btnAggiungi);
		
		JButton btnAnnulla = new JButton("Cancella");
		btnAnnulla.setBounds(326, 149, 98, 45);
		contentPane.add(btnAnnulla);
		
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new aggiungiRecensioneView().aggiungi(textPane.getText(), idGioco);
			}
		});
		
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new aggiungiRecensioneView().annulla(aRP);
			}
		});
	}
}
