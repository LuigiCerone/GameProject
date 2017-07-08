package model;

/**
 * Class used to represent a moderator.
 * */
public class Moderatore extends Utente{
	private int ID;
	private String nome;
	private String cognome;
	private String email;
	private String username;
	private String password;
	
	/**
	 * Empty constructor.
	 * */
	public Moderatore() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Moderator's constructor
	 * @param iD moderator's ID
	 * @param nome moderator's name
	 * @param cognome moderator's last name
	 * @param email moderator's email
	 * @param username moderator's username
	 * @param password moderator's password
	 * @param puntiXP moderator's xp points
	 * */
	public Moderatore(int iD, String nome, String cognome, String email, String username, String password, int puntiXP) {
		super(iD, nome, cognome, email, username, password, puntiXP);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return moderator's ID
	 * */
	public int getID() {
		return ID;
	}
	
	/**
	 * Change a moderator ID
	 * @param iD new ID
	 * */
	public void setID(int iD) {
		ID = iD;
	}
	
	/**
	 * @return moderator's first name.
	 * */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Change moderator's name
	 * @param nome moderator's new name.
	 * */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return moderator's last name.
	 * */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * Set the moderator's last name.
	 * @param cognome new last name.
	 * */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * @return moderator's email.
	 * */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Change moderator's email.
	 * @param email new email.
	 * */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return moderator's last name.
	 * */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Change modetator username.
	 * */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Change moderator's password
	 * @param password new password
	 * */
	public void setPassword(String password) {
		this.password = password;
	}
}
