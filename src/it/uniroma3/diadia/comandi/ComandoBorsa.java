package it.uniroma3.diadia.comandi;

import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoBorsa extends AbstractComando{


	@Override
	public void esegui (Partita partita) {
		if(this.getParametro() != null) {
			if(this.getParametro().equals("nome")) {
				Set<Attrezzo> s = partita.getGiocatore().getBorsa().getContenutoOrdinatoPerNome();
				if(s.size()==0)
					partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
				else {
					for (Attrezzo attrezzo : s) {
						partita.getIO().mostraMessaggio("-"+attrezzo.getNome());
					}
					partita.getIO().mostraMessaggio("("+partita.getGiocatore().getBorsa().getPeso()+"kg/"+partita.getGiocatore().getBorsa().getPesoMax()+"kg)");
				}
			}
			else if (this.getParametro().equals("gruppi")) {

				Map<Integer, Set<Attrezzo>> map = partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerPeso();
				if(map.size()==0)
					partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
				else {
					for (Map.Entry<Integer, Set<Attrezzo>> entry : map.entrySet()) {
						Integer key = entry.getKey();
						Set<Attrezzo> val = entry.getValue();
						partita.getIO().mostraMessaggio("("+key.toString()+"kg) :");
						for (Attrezzo attrezzo : val) {
							partita.getIO().mostraMessaggio("-"+attrezzo.getNome());
						}
					}
					partita.getIO().mostraMessaggio("("+partita.getGiocatore().getBorsa().getPeso()+"kg/"+partita.getGiocatore().getBorsa().getPesoMax()+"kg)");
				}
			}
			else if (this.getParametro().equals("peso")) {
				partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso();
				partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
			}
		}
		else
			partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
	}

	@Override
	public String getNome() {

		return "borsa";
	}


}
