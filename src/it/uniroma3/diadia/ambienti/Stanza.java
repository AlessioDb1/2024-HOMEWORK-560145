package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Mago;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	private static final int NUMERO_MASSIMO_ATTREZZI = 10;
	private String nome;
	private ArrayList<Attrezzo> attrezzi;
	private Map<String, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio = null;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new TreeMap<String, Stanza>();
		this.attrezzi = new ArrayList<Attrezzo>();
		
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		boolean aggiornato = false;
		if (this.stanzeAdiacenti.containsKey(direzione)) {
			stanzeAdiacenti.replace(direzione, stanza);
			aggiornato = true;
		}
		if (!aggiornato)
			if (this.stanzeAdiacenti.size()<4) {
				this.stanzeAdiacenti.put(direzione, stanza);
			}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
		
	}

	/**
	 * Restituisce il nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public ArrayList<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	public void setPersonaggio(AbstractPersonaggio p) {
		this.personaggio = p;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi.add(attrezzo);
			return true;
		}
		else 
			return false;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("Ti trovi in: "+this.nome);
		risultato.append("\nUscite: ");
		Set<String> dir = this.stanzeAdiacenti.keySet();
		for (String direzione : dir)
			risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : attrezzi) {
			risultato.append(attrezzo.toString()+" ");
		}

		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		Iterator<Attrezzo> it = this.attrezzi.iterator();
		while (it.hasNext()) {
			Attrezzo a = it.next();
			if(a.getNome().equals(nomeAttrezzo))
				trovato = true;
		}
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 *         null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		if(this.hasAttrezzo(nomeAttrezzo)) {
			Iterator<Attrezzo> it = this.attrezzi.iterator();
			while (it.hasNext()) {
				Attrezzo a = it.next();
				if(a.getNome().equals(nomeAttrezzo))
					attrezzoCercato = a;
			}
		}
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {

		//verifica che l'attrezzo da rimuovere sia presente nella stanza
		if( this.hasAttrezzo(nomeAttrezzo) == false) {
			return false; //attrezzo non rimosso
		}
		else {
			Iterator<Attrezzo> it = this.attrezzi.iterator();
			while (it.hasNext()) {
				Attrezzo attrezzo = it.next();
				if(attrezzo.getNome().equals(nomeAttrezzo))
					it.remove();
			}
		}
		return true;
	}


	public Set<String> getDirezioni() {
		Set<String> dir = this.stanzeAdiacenti.keySet();
		return dir;
	}

	public Map<String, Stanza> getStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	//Metodi per testing

	/**
	 * Imposta manualmente una stanza adiacente in una data direzione
	 * @param stanza
	 * @param direzione
	 * @param indice
	 */
	public void setStanza(Stanza stanza,String direzione) {
		this.stanzeAdiacenti.put(direzione, stanza);

	}
	/**
	 * Aggiunge manualmente un attrezzo a this.attrezzi[] 
	 * @param attrezzo
	 * @param indice
	 */
	public void  setAttrezzo(Attrezzo attrezzo,int indice) {
		this.attrezzi.add(indice, attrezzo);
	}
}