/*
 * @author Cerone Luigi
 * @version 1.0
 * */

package database;

import java.sql.*;
import java.util.LinkedList;

import model.Gioco;
import model.Moderatore;
import model.Recensione;
import model.Utente;


public final class Dbms {

	protected static String DRIVER = "com.mysql.jdbc.Driver";
	protected static String URL = "jdbc:mysql://localhost/Gaming";
	protected static String USER = "gaming";
	protected static String PSW = "gaming";
	
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
		
		public static Utente queryUserType(String query) throws SQLException{
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
					mUser.setPuntiXP(res.getInt("puntixp"));
					mUser.setLivello();
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
					mMod.setPuntiXP(res.getInt("puntixp"));
					mMod.setLivello();
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

		public static LinkedList<Gioco> getGames() throws SQLException {
			String query = "SELECT * FROM gioco;";
			Connection con = connectToDB();
			// Creiamo un oggetto Statement per poter interrogare il db.
			Statement cmd = con.createStatement ();
			// Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet.
			// String qry = "SELECT * FROM utente";
			ResultSet res = cmd.executeQuery(query);

			LinkedList<Gioco> mList = new LinkedList<Gioco>();
			
			// Stampiamone i risultati riga per riga
			while (res.next()) {
				// (id, nome, xp).
				Gioco g = new Gioco(res.getInt("id"), res.getString("nome"), res.getInt("punti"));
				mList.add(g);
			}
			closeConnectionToDB(con);
			return mList;
		}

		public static void aggiungiXPUtente(String query) throws SQLException {
			// TODO Auto-generated method stub
			Connection con = connectToDB();
			Statement cmd = con.createStatement ();
			
			cmd.executeUpdate(query);
			
			closeConnectionToDB(con);
			
		}
		
		public static void aggiornaDatiGioco(String query, Utente utente) throws SQLException{
			Connection con = connectToDB();
			Statement cmd = con.createStatement();
			
			ResultSet res = cmd.executeQuery(query);
			
			while(res.next()){
				utente.setPuntiXP(res.getInt("puntixp"));
				System.out.println(res.getInt("puntixp"));
			}	
			
			closeConnectionToDB(con);
		}
		
		public static void inserisciProfiloGioco(String query) throws SQLException{
			Connection con = connectToDB();
			Statement cmd = con.createStatement();
			
			cmd.executeUpdate(query);
						
			closeConnectionToDB(con);
		}

		public static LinkedList<Recensione> getRecensioni(int id) throws SQLException {
			String query = "SELECT * "
					+ "FROM recensione "
					+ "WHERE approvata = 1 AND id_gioco='"+id+"';";
			
			Connection con = connectToDB();
			// Creiamo un oggetto Statement per poter interrogare il db.
			Statement cmd = con.createStatement ();
			// Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet.
			// String qry = "SELECT * FROM utente";
			ResultSet res = cmd.executeQuery(query);
			LinkedList<Recensione> mList = new LinkedList<Recensione>();
			// Stampiamone i risultati riga per riga
			while (res.next()) {
				// (id, testo, approvata).
				Recensione r = new Recensione(res.getInt("id"), res.getString("testo"), res.getBoolean("approvata"));
				mList.add(r);
			}
			closeConnectionToDB(con);
			return mList;
		}

		public static LinkedList<Recensione> getRecensioniNonApprovate() throws SQLException {
			String query = "SELECT * "
					+ "FROM recensione "
					+ "WHERE approvata = 0 ;";
			
			Connection con = connectToDB();
			// Creiamo un oggetto Statement per poter interrogare il db.
			Statement cmd = con.createStatement ();
			// Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet.
			// String qry = "SELECT * FROM utente";
			ResultSet res = cmd.executeQuery(query);
			LinkedList<Recensione> mList = new LinkedList<Recensione>();
			// Stampiamone i risultati riga per riga
			while (res.next()) {
				// (id, testo, approvata).
				Recensione r = new Recensione(res.getInt("id"), res.getString("testo"), res.getBoolean("approvata"));
				mList.add(r);
			}
			closeConnectionToDB(con);
			return mList;
		}

		public static void approvaRecensione(String idRecensione) throws SQLException {
			
			// TODO Auto-generated method stub
			String query = "UPDATE recensione"
					+ " SET recensione.approvata = '1' "
					+ " WHERE id = '" + idRecensione +"';";
			
			Connection con = connectToDB();
			// Creiamo un oggetto Statement per poter interrogare il db.
			Statement cmd = con.createStatement ();
			// Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet.
			// String qry = "SELECT * FROM utente";
			cmd.executeUpdate(query);
		}

		public static void eseguiUpdate(String query) throws SQLException {
			// TODO Auto-generated method stub
			Connection con = connectToDB();
			// Creiamo un oggetto Statement per poter interrogare il db.
			Statement cmd = con.createStatement ();
			// Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet.
			// String qry = "SELECT * FROM utente";
			cmd.executeUpdate(query);
			closeConnectionToDB(con);
		}

		public static LinkedList<Utente> getListaUtenti() throws SQLException{
			String query = "SELECT utente.id, utente.username, utente_gioco.puntixp "
					+ "FROM utente JOIN utente_gioco ON (utente.id = utente_gioco.id) ; ";
			Connection con = connectToDB();
			// Creiamo un oggetto Statement per poter interrogare il db.
			Statement cmd = con.createStatement ();
			// Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet.
			// String qry = "SELECT * FROM utente";
			ResultSet res = cmd.executeQuery(query);
			
			LinkedList<Utente> mList = new LinkedList<Utente>();
			// Stampiamone i risultati riga per riga
			while (res.next()) {
				// (id, username, xp).
				Utente u = new Utente(res.getInt("id"), res.getString("username"), res.getInt("puntixp"));
				System.out.println(u);
				mList.add(u);
				
			}
			closeConnectionToDB(con);
			return mList;
		}

		public static void aggiungiVoto(String query) throws SQLException{
			// TODO Auto-generated method stub
			Connection con = connectToDB();
			// Creiamo un oggetto Statement per poter interrogare il db.
			Statement cmd = con.createStatement ();
			// Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet.
			// String qry = "SELECT * FROM utente";
			cmd.executeUpdate(query);
			closeConnectionToDB(con);
		}

		public static float getMedia(String query) throws SQLException {
			// TODO Auto-generated method stub
			float media = 0;
			Connection con = connectToDB();
			// Creiamo un oggetto Statement per poter interrogare il db.
			Statement cmd = con.createStatement ();
			// Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet.
			// String qry = "SELECT * FROM utente";
			ResultSet res = cmd.executeQuery(query);
			while (res.next()) {
				media = res.getFloat("media");
			}
			closeConnectionToDB(con);
			return media;
		}
		
		public static boolean giaVotato(String query) throws SQLException {
			// TODO Auto-generated method stub
			int num = 0;
			Connection con = connectToDB();
			// Creiamo un oggetto Statement per poter interrogare il db.
			Statement cmd = con.createStatement ();
			// Eseguiamo una query e immagazziniamone i risultati in un oggetto ResultSet.
			// String qry = "SELECT * FROM utente";
			ResultSet res = cmd.executeQuery(query);
			while (res.next()) {
				num = res.getInt("numero");
			}
			closeConnectionToDB(con);
			if(num == 0)
				return false;
			else return true;
		}
}
