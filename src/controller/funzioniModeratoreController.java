package controller;

import model.dao.GiocoDAO;
import model.dao.UtenteDAO;

import java.util.LinkedList;
import java.util.Vector;

import model.Recensione;
import model.Utente;

public class funzioniModeratoreController {
		
		static LinkedList<Recensione> mRecensioni = null;
		static LinkedList<Utente> mUtenti = null;
			
		public static Object[][] listaRecensioni() {
			
			mRecensioni= new GiocoDAO().listaRecensioniNonApprovate();
			Object[][] mMatrix = new Object[mRecensioni.size()][3];
			
			int i = 0;
			for(Recensione r : mRecensioni){
				mMatrix[i][0] = (Object)r.getId();
				mMatrix[i][1] = (Object)r.getTesto();
				mMatrix[i][2] = (Object)r.isApprovata();
				i++;
			}
			//	mModelList.addElement(u);
			return mMatrix;
			
		}

		public static void approvaRecensione(String idRecensione) {
			// TODO Auto-generated method stub
			new GiocoDAO().approvaRecensione(idRecensione);
			
		}
		
		public static void disapprovaRecensione(String idRecensione) {
			// TODO Auto-generated method stub
			new GiocoDAO().disapprovaRecensione(idRecensione);
			
		}

		public static Object[][] listaUtenti() {
			mUtenti= new UtenteDAO().listaUtenti();
			Object[][] mMatrix = new Object[mUtenti.size()][5];
			
			int i = 0;
			for(Utente u : mUtenti){
				mMatrix[i][0] = (Object)u.getID();
				mMatrix[i][1] = (Object)u.getUsername();
				mMatrix[i][2] = (Object)u.getPuntiXP();
				mMatrix[i][3] = (Object)u.getLivello();
				i++;
			}
			return mMatrix;
		}

		public static void modificaXPUtente(String op, String id) throws NumberFormatException {
			try {
				new UtenteDAO().modificaXPUtente(new Integer(op), new Integer(id));
			} catch (Exception e) {
				System.out.println("Errore formato numero!");
			}
		}

		public static void aggiornaDatiGioco() {
			new UtenteDAO().aggiornaDatiGioco(loginController.mObject);
		}	
}
