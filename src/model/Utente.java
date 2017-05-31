package model;

public class Utente {
	private int ID;
	private String nome;
	private String cognome;
	private String email;
	private String username;
	private String password;
	private int puntiXP;
	private int livello;
	
	
	
	public Utente() {
		super();
	}
	
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
	
	public Utente(int iD, String username, int puntiXP) {
		this.ID = iD;
		this.username = username;
		this.puntiXP = puntiXP;
		this.livello = (int) Math.ceil(puntiXP/100);
	}

	
	public int getPuntiXP() {
		return puntiXP;
	}

	public void setPuntiXP(int puntiXP) {
		this.puntiXP = puntiXP;
		this.setLivello();
	}

	public int getLivello() {
		return livello;
	}

	public void setLivello(int livello) {
		this.livello = livello;
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
	
	public void setLivello(){
		this.livello = (int) Math.ceil(this.puntiXP/100);
	}
}
