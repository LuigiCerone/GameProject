package model.dao;

import java.sql.SQLException;
import java.util.LinkedList;

import database.Dbms;
import model.Gioco;

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
}
