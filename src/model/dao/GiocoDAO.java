package model.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import database.Dbms;
import model.Gioco;
import model.Recensione;

public class GiocoDAO implements GiocoDAO_Interface {
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

	public LinkedList<Recensione> listaRecensioni(int idGioco) {
		LinkedList<Recensione> mRecensioni = null;
		try {
			mRecensioni = Dbms.getRecensioni(idGioco);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mRecensioni;
	}
	
	public LinkedList<Recensione> listaRecensioniNonApprovate() {
		LinkedList<Recensione> mRecensioni = null;
		try {
			mRecensioni = Dbms.getRecensioniNonApprovate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mRecensioni;
	}

	public void approvaRecensione(String idRecensione) {
		// TODO Auto-generated method stub
		try {
			Dbms.approvaRecensione(idRecensione);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void disapprovaRecensione(String idRecensione) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM recensione"
				+ " WHERE id = '" + idRecensione +"';";
		try {
			Dbms.eseguiUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void aggiungiVoto(int idGioco, int idUtente, Integer votoInserito) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO voto (id_voto,id_gioco,id_utente,voto) "
				+ "VALUES (null, '"+ idGioco + "', '"+ idUtente + "', '"+ votoInserito + "');";
		//System.out.println(query);
		try {
			Dbms.aggiungiVoto(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

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

	public boolean giocoGiaVotato(int idGioco, int idUtente, int votoInserito) {
		boolean giaVotato = false;
		String query = "SELECT COUNT(voto.id_voto) as numero"
				+ " FROM voto"
				+ " WHERE voto.id_gioco = '"+ idGioco + "' AND voto.id_utente = '"+ idUtente + "';";
		try {
			giaVotato = Dbms.giaVotato(query);
			System.out.println(giaVotato);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return giaVotato;
	}

	public void modificaVoto(int idGioco, int idUtente, int votoInserito) {
		// TODO Auto-generated method stub
		String query = "UPDATE voto "
				+ "SET voto.voto = '"+ votoInserito + "'"
				+ " WHERE voto.id_gioco = '"+ idGioco + "' AND voto.id_utente = '"+ idUtente + "'; ";
		try {
			Dbms.eseguiUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
				
	}

	public void aggiungiRecensione(String recensioneText, int idGioco) {
		try {
			Dbms.aggiungiRecensione(recensioneText, idGioco);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
