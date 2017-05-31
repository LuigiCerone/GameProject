package model;

public class Recensione {
	private int id;
	private String testo;
	private boolean approvata;
	
	public Recensione(int id, String testo, boolean approvata) {
		super();
		this.id = id;
		this.testo = testo;
		this.approvata = approvata;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTesto() {
		return testo;
	}
	
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	public boolean isApprovata() {
		return approvata;
	}
	
	public void setApprovata(boolean approvata) {
		this.approvata = approvata;
	}
	
	public String toString(){
		return this.testo + " ";
	}
}
