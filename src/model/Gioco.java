package model;

import model.dao.GiocoDAO;

/**
 * Class used to represent a game.
 * */
public class Gioco {
	private int Id;
	private String nome;
	private int valoreXP;
	private float media;

	/**
	 * @return average score of the game.
	 * */
	 public float getMedia() {
		return media;
	}
	
	 /**
	  * Game constructor.
	  * 
	  * @param id game's id
	  * @param nome game's name
	  * @param game's XP value
	  * */
	 
	public Gioco(int id, String nome, int valoreXP) {
		Id = id;
		this.nome = nome;
		this.valoreXP = valoreXP;
		this.media = new GiocoDAO().ottieniMedia(this.Id);
	}
	
	/**
	 * @return game's id
	 * */
	public int getId() {
		return Id;
	}
	
	/**
	 * @return game's name.
	 * */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @return game's XP value
	 * */
	public int getValoreXP() {
		return valoreXP;
	}
	
	/**
	 * @return game representation.
	 * */
	public String toString(){
		return this.getNome() + "  " + this.getValoreXP() + "  ";
	}
	
}
