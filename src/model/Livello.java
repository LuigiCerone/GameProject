package model;

public class Livello {
	private String data;
	private int livello;
	
	public Livello(String data, int livello) {
		super();
		this.data = data;
		this.livello = livello;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getLivello() {
		return livello;
	}
	public void setLivello(int livello) {
		this.livello = livello;
	}	
}
