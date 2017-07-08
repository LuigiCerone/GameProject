package model;

/**
 * Class used to represent a review.
 * */
public class Recensione {
	private int id;
	private String testo;
	private boolean approvata;
	
	/**
	 * Review's constructor
	 * @param id review's id
	 * @param testo review's text
	 * @param approvata boolean flag that marks if a moderator
	 * has approved the review. 
	 * */
	public Recensione(int id, String testo, boolean approvata) {
		super();
		this.id = id;
		this.testo = testo;
		this.approvata = approvata;
	}
	
	/**
	 * @return review's id.
	 * */
	public int getId() {
		return id;
	}
	
	/**
	 * Change a review ID.
	 * @param id new review ID.
	 * */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return review's text.
	 * */
	public String getTesto() {
		return testo;
	}
	
	/**
	 * Change review's text.
	 * @param testo new text.
	 * */
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	/**
	 * @return true if the review is approved
	 * 		false otherwise.
	 * */
	public boolean isApprovata() {
		return approvata;
	}
}
