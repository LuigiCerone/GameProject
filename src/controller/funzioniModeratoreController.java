package controller;

import java.util.LinkedList;

import model.Recensione;
import model.Utente;
import model.dao.GiocoDAO;
import model.dao.UtenteDAO;

public class funzioniModeratoreController {
		
		static LinkedList<Recensione> mRecensioni = null;
		static LinkedList<Utente> mUtenti = null;
			
		/**
		 * Method used to create a matrix of the reviews that haven't been approved yet.
		 * This matrix is used to create the DefaultTableModel.
		 * 
		 * @return a matrix of objects.
		 * */
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
		
		/**
		 * Method used to approve a specific review
		 * 
		 * @param idRecensione the review's ID that has to be approved.
		 * */
		public static void approvaRecensione(String idRecensione) {
			// TODO Auto-generated method stub
			new GiocoDAO().approvaRecensione(idRecensione);
			
		}
		
		/**
		 * Method used to disapprove a specific review
		 * 
		 * @param idRecensione the review's ID that has to be disapproved.
		 * */
		public static void disapprovaRecensione(String idRecensione) {
			// TODO Auto-generated method stub
			new GiocoDAO().disapprovaRecensione(idRecensione);
			
		}

		/**
		 * Method used to create a matrix of the users.
		 * This matrix is used to create the DefaultTableModel.
		 * @param iD the currently logged in moderator
		 * 
		 * @return a matrix of objects.
		 * */
		public static Object[][] listaUtenti(int iD) {
			mUtenti= new UtenteDAO().listaUtenti();
			Object[][] mMatrix = new Object[mUtenti.size() - 1][5];
			
			int i = 0;
			for(Utente u : mUtenti){
				if(u.getID() == iD) continue;
				mMatrix[i][0] = (Object)u.getID();
				mMatrix[i][1] = (Object)u.getUsername();
				mMatrix[i][2] = (Object)u.getPuntiXP();
				mMatrix[i][3] = (Object)u.getLivello();
				i++;
			}
			return mMatrix;
		}

		/**
		 * Method used to modify the user's xp points. Only a moderator can use this method.
		 * 
		 * @param op a String that contains the xp points to add/remove to the user
		 * @param id user's id.
		 * */
		public static void modificaXPUtente(String op, String id) throws NumberFormatException {
			try {
				new UtenteDAO().modificaXPUtente(new Integer(op), new Integer(id));
			} catch (Exception e) {
				System.out.println("Errore formato numero!");
			}
		}

		/**
		 * Method used to update user's data related to gaming.
		 * */
		public static void aggiornaDatiGioco() {
			new UtenteDAO().aggiornaDatiGioco(loginController.mObject);
		}	
}
