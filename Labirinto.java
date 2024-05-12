package it.uniroma3.diadia.ambienti;
/**
 * Classe Labirinto - labirinto di stanze in cui si muove il giocatore.
 * Questa classe modella il labirinto in cui dovrà muoversi il giocatore,
 * memorizza la stanza iniziale e quella vincente.
 * @see Stanza
 * @version base
 */
public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	
	public void setStanzaIniziale(Stanza stanza) {
		this.stanzaIniziale = stanza;
	}
	
	public void setStanzaVincente(Stanza stanza) {
		this.stanzaVincente = stanza;
	}
}
