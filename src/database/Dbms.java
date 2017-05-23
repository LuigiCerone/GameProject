/*
 * @author Cerone Luigi
 * @version 1.0
 * */

package database;

import java.sql.*;

import model.Moderatore;
import model.Utente;


public final class Dbms {

	protected static String DRIVER = "com.mysql.jdbc.Driver";
	protected static String URL = "jdbc:mysql://localhost/Gaming";
	protected static String USER = "luigi";
	protected static String PSW = "progettooop";
	
	protected static String TIPO = "tipo";
	
		private static Connection connectToDB() {
			Connection con  = null;
			try {
				// Carichiamo un driver di tipo 1 (bridge jdbc-odbc).
				//String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
				Class.forName(DRIVER);
				// Creiamo la stringa di connessione.
				// Otteniamo una connessione con username e password.
				con = DriverManager.getConnection (URL, USER, PSW);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return con;
		}
		
		public static boolean booleanQueryDB(String query) throws SQLException{
			Connection con = connectToDB();
			// Creiamo un oggetto Statement per poter interrogare il db.
			Statement cmd = con.createStatement ();
			// Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet.
			
			// String qry = "SELECT * FROM utente";
			ResultSet res = cmd.executeQuery(query);
			
			int size = 0;
			if (res != null)   
			{  
			  res.beforeFirst();  
			  res.last();  
			  size = res.getRow();  
			}  
			
			res.close();
			cmd.close();
			
			if (size == 0) return false;
			else return true;
		}
		
		private static void closeConnectionToDB(Connection con) throws SQLException{
			con.close();
		}
		
		public static Object queryUserType(String query) throws SQLException{
			//Object mObject = null;
			Utente  mUser = null;
			Moderatore mMod = null;
			
			Connection con = connectToDB();
			Statement cmd = con.createStatement ();
			// Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet.
			
			// String qry = "SELECT * FROM utente";
			ResultSet res = cmd.executeQuery(query);
			
			// Stampiamone i risultati riga per riga
			while (res.next()) {
				System.out.println(res.getString(TIPO));
				if(res.getString(TIPO).equals("giocatore")){
					mUser =  new Utente();
					// Popola utente.
					mUser.setID(res.getInt("id"));
					mUser.setNome(res.getString("nome"));
					mUser.setCognome(res.getString("cognome"));
					mUser.setEmail(res.getString("email"));
					mUser.setUsername(res.getString("username"));
					mUser.setPassword(res.getString("password"));
				}
				else if (res.getString(TIPO).equals("moderatore")){
					mMod = new Moderatore();
					// Popola utente.
					mMod.setID(res.getInt("id"));
					mMod.setNome(res.getString("nome"));
					mMod.setCognome(res.getString("cognome"));
					mMod.setEmail(res.getString("email"));
					mMod.setUsername(res.getString("username"));
					mMod.setPassword(res.getString("password"));
				}
			}
			res.close();
			cmd.close();
			if(mMod == null  && mUser == null ) return null;
			if(mMod == null  && mUser != null) return mUser;
			if(mMod != null  && mUser == null) return mMod;
			return null;
		}

		public static int insertUser(String query) throws SQLException{
			// TODO Auto-generated method stub.
			Connection con = connectToDB();
			Statement cmd = con.createStatement ();
			cmd.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			int idInserito = -1;
			// Prendo l'id appena inserito.
			ResultSet res = cmd.getGeneratedKeys();
			
			while(res.next()){
				idInserito = res.getInt(1);
			}
			System.out.println(idInserito);
			res.close();
			cmd.close();
			return idInserito;
		}
}
