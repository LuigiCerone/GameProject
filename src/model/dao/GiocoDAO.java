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
}
