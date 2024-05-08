package it.uniroma3.diadia.CriteriOrdinamento;

import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComparatorAttrezziPeso implements Comparator<Attrezzo> {


	@Override
	public int compare(Attrezzo o1, Attrezzo o2) {
		if(o1.getPeso()-o2.getPeso() == 0) {
			return o1.getNome().compareTo(o2.getNome());
		}
		else
			return o1.getPeso()-o2.getPeso();
	}

}

