/*
 * @author Cerone Luigi
 * @version 1.0
 * */

package view;

import java.awt.Color;
import java.awt.Component;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import gui.registrazionePage;
import controller.registrazioneController;;

public class registrazioneView {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + 
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public boolean confermaRegistrazione(String n, String c, String u, String e, String p, String p1, registrazionePage rP, JLabel lbl){
		
		//Controllo pattern email
		TreeMap<String,Boolean> mMap = new TreeMap<String,Boolean>();
		mMap.put("Nome",true);
		mMap.put("Cognome", true);
		mMap.put("Username", true);
		mMap.put("Password", true);
		mMap.put("Email", true);
		
		if(n.length() <= 0 || n.length() > 30)
			mMap.put("Nome", false);
		if(c.length() <= 0 || c.length() > 30)
			mMap.put("Cognome", false);
		if(e.length() <= 0 || e.length() > 30)
			mMap.put("Email", false);
		if(u.length() <= 0 || u.length() > 15)
			mMap.put("Username", false);
		if(p.length() <= 0 || p.length() > 15)
			mMap.put("Password", false);
		if(p1.length() <= 0 || p1.length() > 15)
			mMap.put("Password", false);
		
		// Controllare presenza di caratteri speciali nei campi.
		
		Pattern pattern;
		Matcher matcher;
		
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(e); 
		
		if(!(matcher.matches())){
			mMap.put("Email", false);
		}
		
		if(!p.equals(p1))
			mMap.put("Password", false);
		
		aggiornaErrori(mMap,rP);
		if(!aggiornaLabel(mMap, rP, lbl)) return false;
		
		if(registrazioneController.datiGi‡Occupati(u,e)){
			mMap.put("Email", false);
			mMap.put("Username", false);
			aggiornaErrori(mMap,rP);
			if(!aggiornaLabel(mMap, rP, lbl)) return false;
			lbl.setText("<html>Errore! Username ed email gi‡ <br> occupati!</html>");
		}
		
		// Se sono arrivato qui tutti i dati sono ok. Quindi posso inserire nel DB.
		registrazioneController.inserisciNuovoUtente(n,c,u,e,p);
		
		return true;
	}

	private void aggiornaErrori(TreeMap<String, Boolean> mMap, registrazionePage rP) {
		for(Map.Entry<String, Boolean> entry : mMap.entrySet()){
			String toUpdate = "";
			if(!entry.getValue()){
				// Colora il jText in rosso.
				// System.out.println("text"+entry.getKey());
				Color c = new Color(255,50,50);
				colora("text"+entry.getKey(), rP, c);
			} // end-if.
			else if(entry.getValue()){
				// Altrimenti ripristina bianco.
				Color c = new Color(255,255,255);
				colora("text"+entry.getKey(), rP, c);
			}
		} // end-for.
	}

	private void colora(String string, registrazionePage rP, Color c) {
		// TODO Auto-generated method stub.
		Component[] components = rP.getContentPane().getComponents();
		// System.out.println(components[1].getName());
		for( int i = 1; i < rP.getContentPane().getComponentCount(); i++){
			//System.out.println(components[i].getName());
			//System.out.println("entro"); // <-----------
			if(components[i].getName() == null) continue;
			if(components[i].getName().equals(string))
				components[i].setBackground(c);
		}
	}
	
	private boolean aggiornaLabel(TreeMap<String, Boolean> mMap, registrazionePage rP, JLabel lbl){
		for(boolean b : mMap.values())
			if(b == false) {
				lbl.setText("<html>Errore! Modifica i campi<br> contrassegnati in rosso</html>");
				lbl.setVisible(true);
				return false;
				}
		// Altrimenti non ci sono errori.
		lbl.setText("");
		return true;
	}
}
