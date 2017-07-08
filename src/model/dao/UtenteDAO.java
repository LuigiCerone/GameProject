package model.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import database.Dbms;
import model.Livello;
import model.Recensione;
import model.Utente;

public class UtenteDAO implements UtenteDAO_Interface {
	@Override
	public Utente tipoUtente(String user, String password) {
		// TODO Auto-generated method stub
		String query = "SELECT * "
				+ "FROM utente JOIN utente_gioco ON (utente.id = utente_gioco.id) "
				+ "WHERE username='"+user+"' AND password='"+password+"' ;";
		Utente mObject = null;
		try {
			mObject = Dbms.queryUserType(query);
			//if(mObject instanceof Utente) System.out.println("turned");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mObject;
	}

	public boolean datiGi‡Occupati(String user, String email) {
		// TODO Auto-generated method stub.
		String query = "SELECT * "
				+ "FROM utente JOIN utente_gioco ON (utente.id = utente_gioco.id) "
				+ "WHERE username='"+user+"' AND email='"+email+"';";
		boolean occupato = false;
		try {
			// ritorna falso se user ed email non sono occupati.
			occupato = Dbms.booleanQueryDB(query);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return occupato;
	}

	public Utente insericiNuovoUtente(String n, String c, String u, String e, String p) {
		String query = 
				"INSERT INTO utente (nome, cognome, email, username, password)"
				+ "VALUES ('"+n+"', '"+c+"','"+e+"','"+u+"','"+p+"');";
		int id = -1;
		Utente user = null;
		try {
			id = Dbms.insertUser(query);
			//if(mObject instanceof Utente) System.out.println("turned");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			user = new Utente(id,n,c,e,u,p,0);
		}
		
		query = "INSERT INTO utente_gioco(id,puntixp)"
				+ "VALUES ('"+ id + "', 0);";
		try {
			Dbms.inserisciProfiloGioco(query);
		} catch (Exception e2) {
			// TODO: handle exception
		}
		return user;
	}
	
	public void aggiungiXPUtente(int userID, int valoreXPGioco){
	
		
		String query = "UPDATE utente_gioco "
					+ "SET utente_gioco.puntixp = utente_gioco.puntixp + '" 
				+ valoreXPGioco + "'  WHERE utente_gioco.id = '"+ userID + "';";
		System.out.println(query);
		try {
			Dbms.updateQuery(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void aggiornaDatiGioco(Utente mUtente) {
		// TODO Auto-generated method stub
		String query = "SELECT utente_gioco.puntixp "
				+ "FROM utente_gioco "
				+ "WHERE id= '" + mUtente.getID() + "';";
		//System.out.println("SELECT utente_gioco.puntixp FROM utente_gioco WHERE id= '" + mUtente.getID() + "';");
		try {
			Dbms.aggiornaDatiGioco(query, mUtente);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public LinkedList<Utente> listaUtenti() {
		LinkedList<Utente> mUtenti = null;
		
		try {
			mUtenti = Dbms.getListaUtenti();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mUtenti;
	}

	public void modificaXPUtente(Integer puntiXP, Integer id) {
		try {
			String query = "UPDATE utente_gioco "
					+ "SET utente_gioco.puntixp = puntixp +" + puntiXP +
					" WHERE utente_gioco.id = " + id;
			System.out.println(query);
			Dbms.updateQuery(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<Livello> timeLineList(int id) {
		List<Livello> timeLineList = null;
		try {
			String query = "SELECT data, livello"
					+ " FROM timeline "
					+ " WHERE id_giocatore = " + id + ";";
			timeLineList = Dbms.getTimeLine(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return timeLineList;
	}
}
