/*
 * @author
 * @version 1.0
 * */

package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Moderatore;
import model.Utente;
import view.profiloUtenteView;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class profiloUtentePage extends JFrame {
	
	private Utente mUtente;
	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 * @param mObject 
	 */
	public profiloUtentePage(Utente mUtente) {
		profiloUtentePage pP = this;
		this.mUtente = mUtente;
		boolean isMod = false;
		
		if(mUtente instanceof Moderatore)
			isMod = true;
		// se mod mostra button extra.
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Profilo utente");
		this.setResizable(false);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBenvenuto = new JLabel("<html>Benvenuto,    "
				+ "<span style='font-size: 34px; font-weight:bold;'>"
				+mUtente.getUsername() +"</span></html>");
		
		lblBenvenuto.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblBenvenuto.setBounds(31, 32, 466, 106);
		contentPane.add(lblBenvenuto);
		
		JButton btnModeratore = new JButton("Moderatore");
		btnModeratore.setBounds(614, 39, 152, 70);
		btnModeratore.setVisible(isMod);
		contentPane.add(btnModeratore);
		
		JLabel lblAnagrafica = new JLabel(
				"<html>"
				+ "<fieldset>"
				+ "<h1>Sezione anagrafica</h1>"
				+ "<ul>"
				+ "<li>Nome:  <b>"+ mUtente.getNome() + "</b></li> "
				+ "<li>Cognome:  <b>" + mUtente.getCognome() + "</b></li>"
				+ "<li>E-mail:  <b>" + mUtente.getEmail() + "</b></li>"
				+ "<li>Username:  <b>" + mUtente.getUsername() + "</b></li>"
				+ "</ul></fieldset></html>");
		
		lblAnagrafica.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAnagrafica.setVerticalAlignment(SwingConstants.TOP);
		lblAnagrafica.setBounds(47, 149, 309, 179);
		contentPane.add(lblAnagrafica);
		
		JLabel lblGaming = new JLabel("<html>"
				+ "<fieldset>"
				+ "<h1>Sezione gaming</h1>"
				+ "<ul>"
				+ "<li>Livello:  <b>"+ mUtente.getLivello() + "</b></li> "
				+ "<li>Trofei:  <b>" + mUtente.getCognome() + "</b></li>"
				+ "<li>Punti esperienza:  <b>" + mUtente.getPuntiXP() + "</b></li>"
				+ "</ul></fieldset></html>");
		lblGaming.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblGaming.setVerticalAlignment(SwingConstants.TOP);
		lblGaming.setBounds(381, 149, 361, 156);
		contentPane.add(lblGaming);
		
		JButton btnListaGiochi = new JButton("Lista giochi");
		btnListaGiochi.setBounds(614, 365, 152, 68);
		contentPane.add(btnListaGiochi);
		
		JLabel lblTimeline = new JLabel("<html>"
				+ "<fieldset>"
				+ "<h1>Timeline</h1>"
				+ "<ul>"
				+ "</ul></fieldset></html>");
		lblTimeline.setVerticalAlignment(SwingConstants.TOP);
		lblTimeline.setBounds(47, 304, 260, 156);
		contentPane.add(lblTimeline);
		
		
		btnModeratore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnListaGiochi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new profiloUtenteView().visualizzaListaGiochi(pP,mUtente.getID());
			}
		});
	}
}
