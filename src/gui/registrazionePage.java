package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.loginController;
import view.registrazioneView;

public class registrazionePage extends JFrame {
	private JTextField textNome;
	private JTextField textCognome;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRepeat;

	/**
	 * Frame constructor.
	 */
	public registrazionePage() {
		registrazionePage rP = this;
		
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		setTitle("Registrazione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(67, 38, 48, 20);
		getContentPane().add(lblNome);
		
		textNome = new JTextField();
		lblNome.setLabelFor(textNome);
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNome.setBounds(212, 38, 146, 26);
		getContentPane().add(textNome);
		textNome.setColumns(10);
		textNome.setName("textNome");
		
		JLabel lblCognome = new JLabel("Cognome:");
		lblCognome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCognome.setBounds(67, 88, 74, 20);
		getContentPane().add(lblCognome);
		
		textCognome = new JTextField();
		textCognome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCognome.setBounds(211, 90, 146, 26);
		getContentPane().add(textCognome);
		textCognome.setColumns(10);
		textCognome.setName("textCognome");
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(67, 136, 51, 20);
		getContentPane().add(lblEmail);
		
		JFormattedTextField textEmail = new JFormattedTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setBounds(212, 135, 146, 26);
		getContentPane().add(textEmail);
		textEmail.setName("textEmail");
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(67, 181, 97, 20);
		getContentPane().add(lblUsername);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textUsername.setBounds(212, 183, 146, 26);
		getContentPane().add(textUsername);
		textUsername.setColumns(10);
		textUsername.setName("textUsername");
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(212, 227, 146, 26);
		getContentPane().add(passwordField);
		passwordField.setName("textPassword");
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(67, 228, 80, 19);
		getContentPane().add(lblPassword);
		
		JLabel lblRipetiPassword = new JLabel("Ripeti Password");
		lblRipetiPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRipetiPassword.setBounds(67, 278, 113, 20);
		getContentPane().add(lblRipetiPassword);
		
		
		JLabel lblErrore = new JLabel("");
		lblErrore.setEnabled(false);
		lblErrore.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrore.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblErrore.setBounds(391, 181, 133, 48);
		lblErrore.setVisible(false);
		getContentPane().add(lblErrore);
		
		passwordFieldRepeat = new JPasswordField();
		passwordFieldRepeat.setBounds(212, 277, 146, 26);
		getContentPane().add(passwordFieldRepeat);
		passwordFieldRepeat.setName("textPassword");
		JButton btnRegistrati = new JButton("Registrati!");
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(new registrazioneView().confermaRegistrazione(textNome.getText(),textCognome.getText()
							,textUsername.getText(),textEmail.getText(),new String(passwordField.getPassword())
							,new String(passwordFieldRepeat.getPassword()),rP,lblErrore)){
						// Registrazione effettuata.
						System.out.println("qui");
						passaAProfilo(rP);
					} else {
						//errori.
					}
			}
		});
		btnRegistrati.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegistrati.setBounds(391, 113, 143, 48);
		getContentPane().add(btnRegistrati);
	}

	private void passaAProfilo(registrazionePage rP){
		rP.setVisible(false);
		rP.dispose();
		profiloUtentePage framePaginaUtente = new profiloUtentePage(loginController.mObject);
		framePaginaUtente.setVisible(true);
	}
}
