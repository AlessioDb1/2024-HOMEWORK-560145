package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Cerca di prendere un oggetto presente nella stanza aggiungendolo alla borsa,
 * se non ci sono oggetti nella stanza,l'oggetto non è presente o la borsa è piena
 * stampa un messaggio di errore.
 * @param nomeAttrezzo
 */

public class ComandoPrendi implements Comando{
	
	private String nomeAttrezzoDaPrendere;

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getNumeroAttrezzi() == 0)
			partita.getIO().mostraMessaggio("Non ci sono oggetti da prendere in questa stanza!");
		else if(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzoDaPrendere) == false)
			partita.getIO().mostraMessaggio("Questo attrezzo non è presente nella stanza!");
		else {
			Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzoDaPrendere);
			if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)==false)
				partita.getIO().mostraMessaggio("Impossibile prendere l'ogetto, la capienza della borsa è al limite.");
			else {
				partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzoDaPrendere);
				partita.getIO().mostraMessaggio("Oggetto aggiunto correttamente alla borsa!");
			}
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaPrendere = parametro;
	}

	@Override
	public String getNome() {
		
		return "prendi";
	}

	@Override
	public String getParametro() {
		
		return this.nomeAttrezzoDaPrendere;
				
	}
}
