package model.dao;

import java.sql.SQLException;

import database.Dbms;
import model.Utente;

public class UtenteDAO implements UtenteDAO_Interface {
	@Override
	public boolean ËRegistrato() {
		return false;
	}

	@Override
	public Object tipoUtente(String user, String password) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM utente "
				+ "WHERE username='"+user+"' AND password='"+password+"';";
		Object mObject = null;
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
		String query = "SELECT * FROM utente "
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

	public Object insericiNuovoUtente(String n, String c, String u, String e, String p) {
		String query = "INSERT INTO utente (nome, cognome, email, username, password)"
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
			user = new Utente(id,n,c,e,u,p);
		}
		return user;
	}
}
