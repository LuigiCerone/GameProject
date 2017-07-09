package model.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.Dbms;
import model.Livello;
import model.Utente;

public class UtenteDAO implements UtenteDAO_Interface {
	
	@Override
	/**
	 * Method used to determine if the user is a simple user or a moderator
	 * 
	 * @param user the user's username
	 * @param password the user's password
	 * @return mObject a superclass object.
	 * */
	public Utente tipoUtente(String user, String password) {
		
		Utente mObject = null;
		try {
			mObject = Dbms.queryUserType(user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mObject;
	}

	/**
	 * Method used to verify that the data are available.
	 * 
	 * @param u user's username
	 * @param e user's email
	 * 
	 * @return true if they aren't available
	 * false otherwise.
	 * */
	public boolean datiGi‡Occupati(String user, String email) {
		String query = "SELECT * "
				+ "FROM utente JOIN utente_gioco ON (utente.id = utente_gioco.id) "
				+ "WHERE username='"+user+"' AND email='"+email+"';";
		boolean occupato = false;
		try {
			// return false if data aren't available.
			occupato = Dbms.booleanQueryDB(query);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return occupato;
	}

	/**
	 * Method used to insert a new user into the system.
	 * 
	 * @param n user's first name
	 * @param c user's last name
	 * @param u user's username
	 * @param e user's email
	 * @param p user's password
	 * */
	public Utente insericiNuovoUtente(String n, String c, String u, String e, String p) {
		/*String query = 
				"INSERT INTO utente (nome, cognome, email, username, password)"
				+ "VALUES ('"+n+"', '"+c+"','"+e+"','"+u+"','"+p+"');";*/
		int id = -1;
		Utente user = null;
		try {
			id = Dbms.insertUser(n,c,u,e,p);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			user = new Utente(id,n,c,e,u,p,0);
		}
		
		String query = "INSERT INTO utente_gioco(id,puntixp)"
				+ "VALUES ('"+ id + "', 0);";
		try {
			Dbms.updateQuery(query);
		} catch (Exception e2) {
			// TODO: handle exception
		}
		return user;
	}
	
	/**
	 * Method used to add xp to the user when he/she decides to play with a game.
	 * 
	 * @param userID the user's ID
	 * @param valoreXPGioco the value of the game in XP points.
	 *  */
	public void aggiungiXPUtente(int userID, int valoreXPGioco){
		String query = "UPDATE utente_gioco "
					+ "SET utente_gioco.puntixp = utente_gioco.puntixp + '" 
				+ valoreXPGioco + "'  WHERE utente_gioco.id = '"+ userID + "';";
		System.out.println(query);
		try {
			Dbms.updateQuery(query);
		} catch (Exception e) {
		}
	}

	/**
	 * Method used to refresh the gaming stats of the currently logged in user.
	 * 
	 * @param mUtente the user.
	 * */
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

	/**
	 * Method used to create a list of the users.
	 * 
	 * @return LinkedList<Utente> a list of users.
	 * */
	public LinkedList<Utente> listaUtenti() {
		LinkedList<Utente> mUtenti = null;
		
		try {
			mUtenti = Dbms.getListaUtenti();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mUtenti;
	}

	/**
	 * Method used to modify the user's xp points. Only a moderator can use this method.
	 * 
	 * @param op a String that contains the xp points to add/remove to the user
	 * @param id user's id.
	 * */
	public void modificaXPUtente(int puntiXP, int id) {
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

	/**
	 * Method used to fetch from the database all the infromation related to the user's levels.
	 * 
	 * @return List<Livello> a list of levels.
	 * */
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
	}}
