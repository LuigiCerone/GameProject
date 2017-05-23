package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class profiloUtentePage extends JFrame {
	
	private Object mObject;
	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 * @param mObject 
	 */
	public profiloUtentePage(Object mObject) {
		this.mObject = mObject;
		// se mod mostra button extra.
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
