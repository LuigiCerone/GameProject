package model.dao;

import java.sql.SQLException;

public interface UtenteDAO_Interface {
	public boolean ËRegistrato();
	
	public Object tipoUtente(String user, String password) throws SQLException;
}
