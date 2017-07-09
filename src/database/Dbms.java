package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import model.Gioco;
import model.Livello;
import model.Moderatore;
import model.Recensione;
import model.Utente;


public final class Dbms {

	protected static String DRIVER = "com.mysql.jdbc.Driver";
	protected static String URL = "jdbc:mysql://localhost/Gaming";
	protected static String USER = "gaming";
	protected static String PSW = "gaming";
	
	protected static String TIPO = "tipo";
		
		/**
		 * Return a connection object used to connect to the database
		 * 
		 * @return con Connection object.
		 * */
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
		
		/**
		 * Method used to perform query on the database that returns
		 * a boolean values.
		 * 
		 * @param query The query that will be performed.
		 * @return false if the result's length is 0 
		 * 		   true otherwise.
		 */
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
		
		/**
		 * Method used to close the connection with the database
		 * 
		 * @param con connection object.
		 * */
		private static void closeConnectionToDB(Connection con) throws SQLException{
			con.close();
		}
		
		/**
		 * Method used to determine if the user is a simple user or a moderator
		 * 
		 * @param user username
		 * @param password password.
		 * */
		public static Utente queryUserType(String user, String password) throws SQLException{
			Utente  mUser = null;
			Moderatore mMod = null;
			String query = "SELECT * "
					+ "FROM utente JOIN utente_gioco ON (utente.id = utente_gioco.id) "
					+ "WHERE username=? AND password=? ;";
			
			Connection con = connectToDB();
			PreparedStatement cmd = con.prepareStatement(query);
			cmd.setString(1, user);
			cmd.setString(2, password);
			
			ResultSet res = cmd.executeQuery();
			
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

		/**
		 * Method used to insert a new user in the database.
		 * When there is a new subscription to the system this method will
		 * be used to handle it.
		 * 
		
		 * @param n user's first name
		 * @param c user's last name
		 * @param u user's username
		 * @param e user's email
		 * @param p user's password
		 * */
		public static int insertUser(String n, String c, String u, String e, String p) throws SQLException{
			String query = "INSERT INTO utente (nome, cognome, email, username, password)"
					+ " VALUES (?, ?, ?, ?,?) ;";
			
			Connection con = connectToDB();
			PreparedStatement cmd = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			cmd.setString(1, n);
			cmd.setString(2, c);
			cmd.setString(3, e);
			cmd.setString(4, u);
			cmd.setString(5, p);
			
			System.out.println(cmd);

			cmd.executeUpdate();
			
			int idInserito = -1;
			// Get the id of the just inserted user.
			ResultSet res = cmd.getGeneratedKeys();
			
			while(res.next()){
				idInserito = res.getInt(1);
			}
			//System.out.println(idInserito);
			res.close();
			cmd.close();
			return idInserito;
		}

		/**
		 * Method used to retrieve all the information related to all the games in the
		 * system. These information are stored into a linked list.
		 * 
		 * @return LinkedList<Gioco> list with all the games' information.
		 * */
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

		/**
		 * Method used to perform an SQL query with and UPDATE statement.
		 * 
		 * @param query SQL query.
		 * */
		public static void updateQuery(String query) throws SQLException {
			// TODO Auto-generated method stub
			Connection con = connectToDB();
			Statement cmd = con.createStatement ();
			
			cmd.executeUpdate(query);
			
			closeConnectionToDB(con);
			
		}
		
		/**
		 * Method used to update the gaming stats of the currently logged in user.
		 * 
		 * @param query an SQL query that select xp points
		 * @param utente the user whose data needs to be updated.
		 * */
		public static void aggiornaDatiGioco(String query, Utente utente) throws SQLException{
			Connection con = connectToDB();
			Statement cmd = con.createStatement();
			
			ResultSet res = cmd.executeQuery(query);
			
			while(res.next()){
				utente.setPuntiXP(res.getInt("puntixp"));
				//System.out.println(res.getInt("puntixp"));
			}	
			
			closeConnectionToDB(con);
		}
		
		/**
		 * Method used to retrieve all the reviews about a specific game
		 * 
		 * @param id game's id
		 * @return LinkedList<recensione> list of reviews.
		 * */
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

		/**
		 * Method used to fetch from the database all the review that haven't been 
		 * approved yet. This method is used for the moderator.
		 * 
		 * @return LinkedList<Recensione> list of not-approved reviews.
		 * */
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

		/**
		 * Method used to fetch from the database all the information about the users.
		 * 
		 * @return LinkedList<Utente> list of users.
		 * */
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

		/**
		 * Method used to get from the database the average score of a particular game
		 * 
		 * @param query SQL query
		 * @return media average score of a game.
		 * */
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
		
		/**
		 * Method used to check if a user has already voted for a specific game
		 * 
		 * @param query SQL query
		 * @return true if user has already voted
		 * 		   false otherwise.
		 * */
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

		/**
		 * Method used to fetch all the information related to the user's timeline.
		 * 
		 * @param query SQl query string.
		 * @return List<Livello> list of levels.
		 * */
		public static List<Livello> getTimeLine(String query) throws SQLException {
			LinkedList<Livello> mList= new LinkedList<Livello>();
			Connection con = connectToDB();
			Statement cmd = con.createStatement();
			ResultSet res = cmd.executeQuery(query);
			while(res.next()){
				mList.add(new Livello (res.getString("data"), res.getInt("livello")));
			}
			
			closeConnectionToDB(con);
			return mList;
		}
		
		/**
		 * Method used to add a new (not approved) review from a user.
		 * PreparedStatement is used to avoid SQL-injection.
		 * 
		 * @param recensioneText review's text
		 * @param idGioco game's id.
		 * */
		public static void aggiungiRecensione(String recensioneText, int idGioco) throws SQLException {
			Connection con = connectToDB();
			String query = "INSERT INTO recensione"
					+ " (id, testo, approvata, id_gioco) "
					+ " VALUES (null, ?, false, ?) ;";
			
			PreparedStatement cmd = con.prepareStatement(query);
			cmd.setString(1, recensioneText);
			cmd.setInt(2, idGioco);
			//System.out.println(query);		
			cmd.executeUpdate();
		}
}
