package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.CriteriOrdinamento.ComparatorAttrezziNome;
import it.uniroma3.diadia.CriteriOrdinamento.ComparatorAttrezziPeso;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
/**
 * Classe Borsa - borsa del giocatore in un gioco di ruolo.
 * Può contenere un numero limitato di attrezzi 
 * con peso complessivo massimo uguale a un valore dato.
 * 
 * @author docente di POO 
 * @see Attrezzo,Giocatore
 * @version base
 */

public class Borsa{
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private ArrayList<Attrezzo> attrezzi;
	private int pesoMax;
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	/**
	 * Mette un attrezzo nella borsa
	 * @param attrezzo
	 * @return true se è possibile aggiungere l'attrezzo alla borsa,false altrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return this.attrezzi.add(attrezzo);

	}
	public int getPesoMax() {
		return pesoMax;
	}
	
	public ArrayList<Attrezzo> getContenutoOrdinatoPerPeso() {
		ComparatorAttrezziPeso c = new ComparatorAttrezziPeso();
		Collections.sort(this.attrezzi, c);
		return this.attrezzi;
	}
	
	public Set<Attrezzo> getContenutoOrdinatoPerNome(){
		Comparator<Attrezzo> c = new ComparatorAttrezziNome();
		Set<Attrezzo> s = new TreeSet<Attrezzo>(c);
		s.addAll(attrezzi);
		return s;
		
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> map = new TreeMap<Integer, Set<Attrezzo>>();
		for (Attrezzo attrezzo : attrezzi) {
			if(!map.containsKey(attrezzo.getPeso())) {
				map.put(attrezzo.getPeso(), new TreeSet<Attrezzo>());
				map.get(attrezzo.getPeso()).add(attrezzo);
			}
			else {
				map.get(attrezzo.getPeso()).add(attrezzo);
			}
		}
		return map; 
	}

	/**
	 * Cerca (per nome) un attrezzo nella borsa, se presente restituisce 
	 * l'attrezzo cercato.
	 * @param nomeAttrezzo
	 * @return attrezzo cercato se presente nella borsa, null altrimenti.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> it = this.attrezzi.iterator();
		while (it.hasNext()) {
			a = it.next();
			if (a.getNome().equals(nomeAttrezzo)) {
				return a;
			}
		}
		return null;
	}
	/**
	 * Restituisce il peso totale di tutti gli attrezzi nella borsa
	 * @return peso totale borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo attrezzo : attrezzi) {
			peso += attrezzo.getPeso();
		}
		return peso;
	}
	public ArrayList<Attrezzo> getAttrezzi() {
		return attrezzi;
	}
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	/**
	 * Verifica che nella borsa ci sia l'attrezzo nomeAttrezzo.
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo è nella borsa, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		boolean ans = false;
		Iterator<Attrezzo> it = this.attrezzi.iterator();
		while (it.hasNext()) {
			a = it.next();
			if (a.getNome().equals(nomeAttrezzo)) {
				ans = true;
			}
		}
		return ans;
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
			Iterator<Attrezzo> it = this.attrezzi.iterator();
			while (it.hasNext()) {
				a = it.next();
				if (a.getNome().equals(nomeAttrezzo)) {
					it.remove();
					return a;
				}
			}
		}
		return null;
	}


	public String getDescrizione() {
		return this.toString();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo attrezzo : attrezzi) {
				s.append(attrezzo.toString()+" ");
			}
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
		this.attrezzi.add(indice, attrezzo); 
	}
}
