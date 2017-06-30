package controller;

import model.dao.GiocoDAO;
import model.dao.UtenteDAO;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.DefaultListModel;

import model.Gioco;
import model.Recensione;
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

	public static Object[][] listaRecensioni(int idGioco) {
		LinkedList<Recensione> mRecensioni = new GiocoDAO().listaRecensioni(idGioco);
		//System.out.println("LISTA : " + mRecensioni);
		Object[][] mMatrix = new Object[mRecensioni.size()][1];
		
		int i = 0;
		for(Recensione r : mRecensioni){
			mMatrix[i][0] = (Object) r.getTesto();
			i++;
		}
		//	mModelList.addElement(g);
		return mMatrix;
	}

	public static void aggiungiVoto(int idGioco, int idUtente, Integer votoInserito) {
		// TODO Auto-generated method stub
		new GiocoDAO().aggiungiVoto(idGioco,idUtente,votoInserito);
	}

	public static boolean giocoGiaVotato(int idGioco, int idUtente, int votoInserito) {
		return new GiocoDAO().giocoGiaVotato(idGioco, idUtente, votoInserito);
	}

	public static void modificaVoto(int idGioco, int idUtente, int votoInserito) {
		// TODO Auto-generated method stub
		new GiocoDAO().modificaVoto(idGioco,idUtente, votoInserito);
	}

}
