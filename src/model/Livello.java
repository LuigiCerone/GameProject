package model;

/**
 * Class used to represent a level.
 * */
public class Livello {
	private String data;
	private int livello;
	
	/**
	 * Level constructor.
	 * @param data date on which the level has been achieved
	 * @param livello level achieved
	 * */
	public Livello(String data, int livello) {
		super();
		this.data = data;
		this.livello = livello;
	}
	/**
	 * @return date
	 * */
	public String getData() {
		return data;
	}
	/**
	 * @param data date
	 * */
	public void setData(String data) {
		this.data = data;
	}
	
	/**
	 * @return level.
	 * */
	public int getLivello() {
		return livello;
	}
	
	/**
	 * @param livello set the level
	 * */
	public void setLivello(int livello) {
		this.livello = livello;
	}	
}
