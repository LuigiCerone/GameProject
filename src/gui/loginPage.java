package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import view.loginView;

public class loginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNonSeiAncora;
	private JLabel labelErrore;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPage frame = new loginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Frame constructor.
	 */
	public loginPage() {
		loginPage lP = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		this.setResizable(false);
		setTitle("Login");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setBounds(300, 181, 126, 40);
		btnAccedi.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(63, 32, 117, 30);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		textField = new JTextField();
		textField.setToolTipText("username");
		textField.setBounds(232, 32, 162, 29);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(63, 105, 78, 23);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("password");
		passwordField.setBounds(232, 102, 162, 29);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		lblNonSeiAncora = new JLabel("Non sei ancora registrato?");
		lblNonSeiAncora.setBounds(94, 273, 175, 14);
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setBounds(300, 256, 126, 40);
		btnRegistrati.setFont(new Font("Tahoma", Font.PLAIN, 19));
		contentPane.setLayout(null);
		contentPane.add(lblNonSeiAncora);
		contentPane.add(btnRegistrati);
		contentPane.add(lblUsername);
		contentPane.add(lblPassword);
		contentPane.add(passwordField);
		contentPane.add(textField);
		contentPane.add(btnAccedi);
		
		labelErrore = new JLabel("Errore! Username/Password errati");
		labelErrore.setVisible(false);
		labelErrore.setEnabled(false);
		labelErrore.setBounds(206, 142, 246, 14);
		contentPane.add(labelErrore);
		
		// Listen for Enter key press to log in.
		lP.getRootPane().setDefaultButton(btnAccedi);
		
		btnAccedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loginView().accedi(textField.getText(), new String(passwordField.getPassword()), labelErrore,lP);
			}
		});
		
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loginView().registrati(lP);
			}
		});
	}

}
