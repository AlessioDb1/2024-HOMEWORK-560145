package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Cerca di prendere un oggetto presente nella stanza aggiungendolo alla borsa,
 * se non ci sono oggetti nella stanza,l'oggetto non è presente o la borsa è piena
 * stampa un messaggio di errore.
 * @param nomeAttrezzo
 */

public class ComandoPrendi extends AbstractComando{
	
	

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getAttrezzi().size() == 0)
			partita.getIO().mostraMessaggio("Non ci sono oggetti da prendere in questa stanza!");
		else if(partita.getStanzaCorrente().hasAttrezzo(this.getParametro()) == false)
			partita.getIO().mostraMessaggio("Questo attrezzo non è presente nella stanza!");
		else {
			Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(this.getParametro());
			if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)==false)
				partita.getIO().mostraMessaggio("Impossibile prendere l'ogetto, la capienza della borsa è al limite.");
			else {
				partita.getStanzaCorrente().removeAttrezzo(this.getParametro());
				partita.getIO().mostraMessaggio("Oggetto aggiunto correttamente alla borsa!");
			}
		}
	}

	@Override
	public String getNome() {
		
		return "prendi";
	}

	
}
