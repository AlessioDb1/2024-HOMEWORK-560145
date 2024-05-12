package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ambienti.Labirinto;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private boolean finita;

	private Labirinto labirinto;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	private IO IO;

	public Partita(Labirinto labirinto, IO IO){
		this.finita = false;
		this.labirinto = labirinto;
		this.giocatore = new Giocatore();
		this.stanzaCorrente = labirinto.getStanzaIniziale();
		this.IO = IO;
	}
	
	public Partita(Labirinto labirinto) {
		this(labirinto,null);
	}
	
	public void setLabirinto(Labirinto l){
		labirinto = l;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente == labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || !(giocatoreIsVivo());
	}
	
	public boolean giocatoreIsVivo() {
		return !(this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	/**
	 * Restituisce un riferimento all'istanza Labirinto di Partita
	 * @return riferimento all'istanza Labirinto
	 */
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	/**
	 * Restituisce un riferimento all'istanza Giocatore di Partita
	 * @return riferimento all'istanza Giocatore
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	public IO getIO() {
		return this.IO;
	}
}
