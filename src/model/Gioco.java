package model;

public class Gioco {
	private int Id;
	private String nome;
	private int valoreXP;
	private float media;
	private int numeroRecensioni;
	
	public float getMedia() {
		return media;
	}

	public void setMedia(float media) {
		this.media = media;
	}

	public int getNumeroRecensioni() {
		return numeroRecensioni;
	}

	public void setNumeroRecensioni(int numeroRecensioni) {
		this.numeroRecensioni = numeroRecensioni;
	}

	public Gioco(int id, String nome, int valoreXP, float media, int numero) {
		Id = id;
		this.nome = nome;
		this.valoreXP = valoreXP;
		this.media = media;
		this.numeroRecensioni = numero;
	}
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getValoreXP() {
		return valoreXP;
	}
	
	public void setValoreXP(int valoreXP) {
		this.valoreXP = valoreXP;
	}
	
	public String toString(){
		return this.getNome() + "  " + this.getValoreXP() + "  ";
	}
	
}
