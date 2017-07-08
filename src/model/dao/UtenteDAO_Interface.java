package model.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Livello;
import model.Utente;

public interface UtenteDAO_Interface {
	public Utente tipoUtente(String user, String password);
	
	public boolean datiGi‡Occupati(String user, String email);
	
	public Utente insericiNuovoUtente(String n, String c, String u, String e, String p);
	
	public void aggiungiXPUtente(int userID, int valoreXPGioco);
	
	public void aggiornaDatiGioco(Utente mUtente);
	
	public LinkedList<Utente> listaUtenti();
	
	public void modificaXPUtente(Integer puntiXP, Integer id);
	
	public List<Livello> timeLineList(int id);
}
