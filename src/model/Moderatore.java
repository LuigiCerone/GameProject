package model;

public class Moderatore extends Utente{
	private int ID;
	private String nome;
	private String cognome;
	private String email;
	private String username;
	private String password;
	
	
	public Moderatore() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Moderatore(int iD, String nome, String cognome, String email, String username, String password, int puntiXP) {
		super(iD, nome, cognome, email, username, password, puntiXP);
		// TODO Auto-generated constructor stub
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
