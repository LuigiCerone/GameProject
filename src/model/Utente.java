package model;

/**
 * Class used to represent a user.
 * */
public class Utente {
	private int ID;
	private String nome;
	private String cognome;
	private String email;
	private String username;
	private String password;
	private int puntiXP;
	private int livello;
	
	
	/**
	 * Empty constructor.
	 * */
	public Utente() {
		super();
	}
	
	/**
	 * User's constructor
	 * @param iD user's ID
	 * @param nome user's name
	 * @param cognome user's last name
	 * @param email user's email
	 * @param username moderator's username
	 * @param password user's password
	 * @param puntiXP user's xp points
	 * */
	public Utente(int iD, String nome, String cognome, String email, String username, String password, int puntiXP) {
		super();
		ID = iD;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.username = username;
		this.password = password;
		this.puntiXP = puntiXP;
		this.livello = (int) Math.ceil(puntiXP/100);
	}
	
	/**
	 * User's constructor
	 * @param iD user's ID
	 * @param username moderator's username
	 * @param puntiXP user's xp points
	 * */
	public Utente(int iD, String username, int puntiXP) {
		this.ID = iD;
		this.username = username;
		this.puntiXP = puntiXP;
		this.livello = (int) Math.ceil(puntiXP/100);
	}

	/**
	 * @return user's xp points.
	 * */
	public int getPuntiXP() {
		return puntiXP;
	}
	
	/**
	 * Set user's xp points
	 * @param puntiXP xp point's.
	 * */
	public void setPuntiXP(int puntiXP) {
		this.puntiXP = puntiXP;
		this.setLivello();
	}

	/**
	 * @return user's level.
	 * */
	public int getLivello() {
		return livello;
	}

	/**
	 * Set user level
	 * @param livello the user's level
	 * */
	public void setLivello(){
		this.livello = (int) Math.ceil(this.puntiXP/100);
	}

	/**
	 * @return user's ID
	 * */
	public int getID() {
		return ID;
	}
	
	/**
	 * Change user's ID
	 * @param iD the new ID.
	 * */
	public void setID(int iD) {
		ID = iD;
	}
	
	/**
	 * @return user's name.
	 * */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Set new user's first name.
	 * @param nome the new first name.
	 * */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return user's last name.
	 * */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * Change user's last name
	 * @param cognome the new user's last name.
	 * */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * @return user's email.
	 * */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Change user's email
	 * @param email the new email.
	 * */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return user's username.
	 * */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Change user's username
	 * @param username the new username.
	 * */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Change the user's password
	 * @param password the new password.
	 * */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
