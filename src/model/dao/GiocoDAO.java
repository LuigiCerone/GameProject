package model.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import database.Dbms;
import model.Gioco;
import model.Recensione;

public class GiocoDAO implements GiocoDAO_Interface {
	
	/**
	 * Method used to get the list of games.
	 * 
	 * @return LinkedList<Gioco> list of games.
	 * */
	public LinkedList<Gioco> listaGiochi(){
		LinkedList<Gioco> mList = null;;
		try {
			mList = Dbms.getGames();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}

	/**
	 * Method used to get the list of game reviews.
	 * 
	 * @return LinkedList<Recensione> list of reviews.
	 * */
	public LinkedList<Recensione> listaRecensioni(int idGioco) {
		LinkedList<Recensione> mRecensioni = null;
		try {
			mRecensioni = Dbms.getRecensioni(idGioco);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mRecensioni;
	}
	
	/**
	 * Method used to get the list of not approved reviews.
	 * 
	 * @return LinkedList<Recensione> list of not approved reviews.
	 * */
	public LinkedList<Recensione> listaRecensioniNonApprovate() {
		LinkedList<Recensione> mRecensioni = null;
		try {
			mRecensioni = Dbms.getRecensioniNonApprovate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mRecensioni;
	}

	/**
	 * Method used to approve a specific review
	 * 
	 * @param idRecensione the review's id that has to be approved.
	 * */
	public void approvaRecensione(String idRecensione) {
		
		String query = "UPDATE recensione"
				+ " SET recensione.approvata = '1' "
				+ " WHERE id = '" + idRecensione +"';";
		try {
			Dbms.updateQuery(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	/**
	 * Method used to disapprove a specific review
	 * 
	 * @param idRecensione the review's id that has to be disapproved.
	 * */
	public void disapprovaRecensione(String idRecensione) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM recensione"
				+ " WHERE id = '" + idRecensione +"';";
		try {
			Dbms.updateQuery(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Method used to add a new vote to a specific game.
	 * 
	 *  @param idGioco the game'id
	 *  @param idUtente the user's id.
	 *  @param votoInserito the new user's vote.
	 * */
	public void aggiungiVoto(int idGioco, int idUtente, Integer votoInserito) {
		String query = "INSERT INTO voto (id_voto,id_gioco,id_utente,voto) "
				+ "VALUES (null, '"+ idGioco + "', '"+ idUtente + "', '"+ votoInserito + "');";
		//System.out.println(query);
		try {
			Dbms.updateQuery(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Method used to get the average score of a specific game
	 * 
	 * @param id the game's id
	 * @return media the average score of the game.
	 * */
	public float ottieniMedia(int id) {
		float media = 0;
		String query = "SELECT AVG(voto) as media "
				+ " FROM voto"
				+ " WHERE id_gioco = '" + id +  "';";
		try {
			media = Dbms.getMedia(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return media;
	}

	/**
	 * Method used to check if the currently logged in user has already voted this game.
	 * 
	 * @param idGioco the game'id
	 * @param idUtente the user's id.
	 * */
	
	public boolean giocoGiaVotato(int idGioco, int idUtente) {
		boolean giaVotato = false;
		String query = "SELECT COUNT(voto.id_voto) as numero"
				+ " FROM voto"
				+ " WHERE voto.id_gioco = '"+ idGioco + "' AND voto.id_utente = '"+ idUtente + "';";
		try {
			giaVotato = Dbms.giaVotato(query);
			//System.out.println(giaVotato);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return giaVotato;
	}

	/**
	 * Method used to change the old vote from a user with a new one
	 * 
	 * @param idGioco the game'id
	 * @param idUtente the user's id.
	 * @param votoInserito the new user's vote.
	 * */
	public void modificaVoto(int idGioco, int idUtente, int votoInserito) {
		String query = "UPDATE voto "
				+ "SET voto.voto = '"+ votoInserito + "'"
				+ " WHERE voto.id_gioco = '"+ idGioco + "' AND voto.id_utente = '"+ idUtente + "'; ";
		try {
			Dbms.updateQuery(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
				
	}

	
	/**
	 * Method used to add a new review to a specific game
	 * 
	 * @param recensioneText the new review's text
	 * @param idGioco the id of the game reviewed.
	 * */
	public void aggiungiRecensione(String recensioneText, int idGioco) {
		try {
			Dbms.aggiungiRecensione(recensioneText, idGioco);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
