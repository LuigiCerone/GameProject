package controller;

import model.dao.GiocoDAO;
import model.dao.UtenteDAO;

import java.util.LinkedList;

import javax.swing.DefaultListModel;

import model.Gioco;
import model.Utente;

public class listaGiochiController {
	static LinkedList<Gioco> mList = null;
	
	public static Object[][] listaGiochi() {
		mList = new GiocoDAO().listaGiochi();
		Object[][] mMatrix = new Object[mList.size()][4]; 
		int i = 0;
		for(Gioco g : mList){
			mMatrix[i][0] = (Object)g.getId();
			mMatrix[i][1] = (Object)g.getNome();
			mMatrix[i][2] = (Object)new Integer(g.getValoreXP());
			mMatrix[i][3] = (Object)new Float(g.getMedia());
			i++;
		}
		//	mModelList.addElement(g);
		return mMatrix;
	}

	public static Gioco informazioniGiocoDaId(String id) {
		Gioco giocoRichiesto = null;
		for(Gioco g : mList)
			if(g.getId() == new Integer(id)) giocoRichiesto = g;
		return giocoRichiesto;
	}

	public static void aggiungiXPUtente(int userID, int valoreXPGioco) {
		new UtenteDAO().aggiungiXPUtente(userID,valoreXPGioco);
	}

	public void aggiornaDatiGioco(Utente mUtente) {
		// TODO Auto-generated method stub
		new UtenteDAO().aggiornaDatiGioco(mUtente);
	}

}
