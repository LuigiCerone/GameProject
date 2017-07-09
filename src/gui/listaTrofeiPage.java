package gui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class listaTrofeiPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Frame constructor.
	 * 
	 * @param userID the user's ID
	 * @param livello the user's level.
	 */
	public listaTrofeiPage(int userID, int livello) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 330);
		setTitle("Lista trofei");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane trofeiScrollPane = new JScrollPane();
		contentPane.add(trofeiScrollPane);
		JPanel image = new JPanel();
		if(livello > 5) livello = 5;
		for (int i = 1; i <=livello ; i++) {
			try {
				BufferedImage myPicture = ImageIO.read(new File("img/t"+i+".png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				image.add(picLabel);
				trofeiScrollPane.getViewport().add(image, null);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if(livello == 0){
			contentPane.add(new JLabel("Non hai ancora nessun trofeo!"));
		}
		
	}

}
