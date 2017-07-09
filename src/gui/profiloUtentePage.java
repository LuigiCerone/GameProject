package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.loginController;
import model.Moderatore;
import model.Utente;
import view.profiloUtenteView;

public class profiloUtentePage extends JFrame {
	
	private Utente mUtente;
	private JPanel contentPane;
	

	/**
	 * Frame constructor.
	 * 
	 * @param mUtente the user's currently logged in.
	 */
	public profiloUtentePage(Utente mUtente) {
		profiloUtentePage pP = this;
		this.mUtente = mUtente;
		boolean isMod = false;
		
		if(mUtente instanceof Moderatore)
			isMod = true;
		// If the user is moderator the button needs to be displayed.
		
		GridLayout layout = new GridLayout(3,2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Profilo utente");
		this.setResizable(false);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(layout);
		
		JPanel topLeftPanel = new JPanel();
		JLabel lblBenvenuto = new JLabel("<html>Benvenuto,    "
				+ "<span style='font-size: 34px; font-weight:bold;'>"
				+mUtente.getUsername() +"</span></html>");
		
		lblBenvenuto.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblBenvenuto.setBounds(31, 32, 466, 106);
		topLeftPanel.add(lblBenvenuto);
		
		JPanel topRightPanel = new JPanel();
		JButton btnModeratore = new JButton("Moderatore");
		btnModeratore.setBounds(614, 39, 152, 70);
		btnModeratore.setVisible(isMod);
		topRightPanel.add(btnModeratore);
		contentPane.add(topLeftPanel);
		contentPane.add(topRightPanel);
		
		JButton btnLogOut = new JButton("Log out");
		
		topRightPanel.add(btnLogOut);
		
		JPanel middleLeftPanel = new JPanel();
		JPanel middleRightPanel = new JPanel();
		
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
		middleLeftPanel.add(lblAnagrafica);
		
		JLabel lblGaming = new JLabel("<html>" 
				+ "<h1>Sezione gaming</h1>"
				+ "<ul>"
				+ "<li>Livello:  <b>"+ mUtente.getLivello() + "</b></li> "
				+ "<li>Punti esperienza:  <b>" + mUtente.getPuntiXP() + "</b></li>"
				+ "</ul></html>");
		lblGaming.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblGaming.setVerticalAlignment(SwingConstants.TOP);
		lblGaming.setBounds(381, 149, 361, 156);
		middleRightPanel.add(lblGaming);
		
		contentPane.add(middleLeftPanel);
		contentPane.add(middleRightPanel);
		
		
		JPanel bottomLeftPanel = new JPanel();
		JPanel bottomRightPanel = new JPanel();
		
		JLabel lblTimeline = new JLabel("<html>"
				+ "<h1>Timeline</h1>"
				+ "<ul>"
				+ "</ul></html>");
		lblTimeline.setVerticalAlignment(SwingConstants.TOP);
		lblTimeline.setBounds(47, 304, 260, 156);
		
		bottomLeftPanel.add(lblTimeline);
		new profiloUtenteView().creaTimeLine(bottomLeftPanel);
		contentPane.add(bottomLeftPanel);
		
		
		JButton btnListaGiochi = new JButton("Lista giochi");
		btnListaGiochi.setBounds(614, 365, 152, 68);
		
		
		JButton btnListaTrofei = new JButton("Lista trofei");
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		buttonPanel.add(btnListaGiochi);
		buttonPanel.add(btnListaTrofei);
		
		middleRightPanel.add(buttonPanel);
		//bottomRightPanel.add(btnListaGiochi);
		contentPane.add(bottomRightPanel);
		
		btnModeratore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new profiloUtenteView().passaModeratore(pP);
			}
		});
		
		btnListaGiochi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new profiloUtenteView().visualizzaListaGiochi(pP,mUtente.getID());
			}
		});
		
		btnListaTrofei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new profiloUtenteView().visualizzaListaTrofei(mUtente.getID(), mUtente.getLivello());
			}
		});
		
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginController.mObject = null;
				new profiloUtenteView().logOut(pP);
			}
		});
	}
}
