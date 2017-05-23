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

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class profiloUtentePage extends JFrame {
	
	private Object mObject;
	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 * @param mObject 
	 */
	public profiloUtentePage(Object mObject) {
		this.mObject = mObject;
		boolean isMod = false;
		
		if(mObject instanceof Moderatore)
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
		
		JLabel lblBenvenuto = new JLabel("Benvenuto,");
		lblBenvenuto.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblBenvenuto.setBounds(31, 32, 237, 70);
		contentPane.add(lblBenvenuto);
		
		JButton btnModeratore = new JButton("Moderatore");
		btnModeratore.setBounds(614, 39, 152, 70);
		btnModeratore.setVisible(isMod);
		contentPane.add(btnModeratore);
	}
}
