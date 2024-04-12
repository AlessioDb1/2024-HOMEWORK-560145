package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Borsa - borsa del giocatore in un gioco di ruolo.
 * Può contenere un numero limitato di attrezzi 
 * con peso complessivo massimo uguale a un valore dato.
 * 
 * @author docente di POO 
 * @see Attrezzo,Giocatore
 * @version base
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}

	/**
	 * Mette un attrezzo nella borsa
	 * @param attrezzo
	 * @return true se è possibile aggiungere l'attrezzo alla borsa,false altrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Cerca (per nome) un attrezzo nella borsa, se presente restituisce 
	 * l'attrezzo cercato.
	 * @param nomeAttrezzo
	 * @return attrezzo cercato se presente nella borsa, null altrimenti.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}
	/**
	 * Restituisce il peso totale di tutti gli attrezzi nella borsa
	 * @return peso totale borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	/**
	 * Verifica che nella borsa ci sia l'attrezzo nomeAttrezzo.
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo è nella borsa, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	/**
	 * Cerca (per nome) un attrezzo nella borsa,se presente lo rimuove
	 * dall'array attrezzi[] restituendo l'attrezzo cancellato.
	 * @param nomeAttrezzo
	 * @return attrezzo rimosso se presente nella borsa,null altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if (this.isEmpty() || this.hasAttrezzo(nomeAttrezzo)== false) {
			return a;
		}
		else {

			int posizioneCancellata = -1;

			for( int i = 0 ; i<this.attrezzi.length; i++) {
				posizioneCancellata++;

				if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
					a = this.attrezzi[i];
					this.attrezzi[i] = null;  //rende nullo il riferimento all'attrezzo da cancellare
					break;

				}
			}
			//Se l'attrezzo cancellato è l'ultimo non devo spostare i rimanenti
			if(posizioneCancellata != this.numeroAttrezzi-1) {
				//Sposta gli elementi a sinistra per non lasciare "vuoti" nell'array
				this.attrezzi[this.numeroAttrezzi] = null;
				for(int i = posizioneCancellata ; i<(this.numeroAttrezzi)-1;i++) {
					this.attrezzi[i] = this.attrezzi[i+1];
				}
			}
			//diminuisce il numero degli elementi nell'array
			this.numeroAttrezzi--;

			return a;
		}
	}

	
	public int getNumeroAttrezzi() {	
		return this.numeroAttrezzi;     
	}										

	public String getDescrizione() {
		return this.toString();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota\nCapienza massima :"+this.getPesoMax()+"kg");
		return s.toString();
	}
	
	//Metodi per testing
	
	/**
	 * Aggiunge manualmente un attrezzo a this.attrezzi[] 
	 * @param indice
	 * @param attrezzo
	 */
	public void setAttrezzo(int indice,Attrezzo attrezzo) {
		this.attrezzi[indice] = attrezzo;
		this.numeroAttrezzi++;
	}
}
