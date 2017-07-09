package model.dao;

import java.util.LinkedList;

import model.Gioco;
import model.Recensione;

public interface GiocoDAO_Interface {

	public LinkedList<Gioco> listaGiochi();
	
	public LinkedList<Recensione> listaRecensioni(int idGioco);
	
	public LinkedList<Recensione> listaRecensioniNonApprovate();
	
	public void approvaRecensione(String idRecensione);
	
	public void disapprovaRecensione(String idRecensione);
	
	public void aggiungiVoto(int idGioco, int idUtente, Integer votoInserito);
	
	public float ottieniMedia(int id);
	
	public boolean giocoGiaVotato(int idGioco, int idUtente);
	
	public void modificaVoto(int idGioco, int idUtente, int votoInserito);
	
	public void aggiungiRecensione(String recensioneText, int idGioco);
}
