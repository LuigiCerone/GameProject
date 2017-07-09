package controller;

import java.util.LinkedList;

import model.Gioco;
import model.Recensione;
import model.Utente;
import model.dao.GiocoDAO;
import model.dao.UtenteDAO;

public class listaGiochiController {
	static LinkedList<Gioco> mList = null;

	/**
	 * Method used to create a matrix of all the information related to the
	 *  games available on the platform.
	 * 
	 * @return mMatrix a matrix of objects.
	 * */
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

	/**
	 * Method used to fetch all the information of a specific game
	 * 
	 * @param id the game's id.
	 * */
	public static Gioco informazioniGiocoDaId(String id) {
		Gioco giocoRichiesto = null;
		for(Gioco g : mList)
			if(g.getId() == new Integer(id)) giocoRichiesto = g;
		return giocoRichiesto;
	}

	/**
	 * Method used to add xp to the user when he/she decides to play with a game.
	 * 
	 * @param userID the user's ID
	 * @param valoreXPGioco the value of the game in XP points.
	 *  */
	public static void aggiungiXPUtente(int userID, int valoreXPGioco) {
		new UtenteDAO().aggiungiXPUtente(userID,valoreXPGioco);
	}

	/**
	 * Method used to refresh the gaming stats of the currently logged in user.
	 * 
	 * @param mUtente the user.
	 * */
	public void aggiornaDatiGioco(Utente mUtente) {
		new UtenteDAO().aggiornaDatiGioco(mUtente);
	}

	/**
	 * Method used to create a matrix of all the information related to the
	 * reviews available on the platform about a specific game. These reviews
	 * have been approved by a moderator.
	 * 
	 * @return mMatrix a matrix of objects.
	 * */
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

	/**
	 * Method used to add a new vote to a specific game.
	 * 
	 *  @param idGioco the game'id
	 *  @param idUtente the user's id.
	 *  @param votoInserito the user's vote.
	 * */
	public static void aggiungiVoto(int idGioco, int idUtente, int votoInserito) {
		new GiocoDAO().aggiungiVoto(idGioco,idUtente,votoInserito);
	}

	/**
	 * Method used to check if the currently logged in user has already voted this game.
	 * 
	 * @param idGioco the game'id
	 * @param idUtente the user's id.
	 * */
	public static boolean giocoGiaVotato(int idGioco, int idUtente) {
		return new GiocoDAO().giocoGiaVotato(idGioco, idUtente);
	}

	/**
	 * Method used to change the old vote from a user with a new one
	 * 
	 * @param idGioco the game'id
	 * @param idUtente the user's id.
	 * @param votoInserito the new user's vote.
	 * */
	public static void modificaVoto(int idGioco, int idUtente, int votoInserito) {
		// TODO Auto-generated method stub
		new GiocoDAO().modificaVoto(idGioco,idUtente, votoInserito);
	}

	/**
	 * Method used to get the average score of a specific game
	 * 
	 * @param idGioco the game's id
	 * @return f the average score.
	 * */
	public static Object ottieniMedia(int idGioco) {
		float f = new GiocoDAO().ottieniMedia(idGioco);
		return f;
	}

}
