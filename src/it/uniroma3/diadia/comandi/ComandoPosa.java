package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Cerca di posare un oggetto dalla borsa del giocatore nella stanza,
 * stampa un messaggio d'errore se la borsa è vuota,non si possiede tale oggetto 
 * o la stanza è già piena.
 * @param nomeAttrezzo
 */

public class ComandoPosa extends AbstractComando{
	
	
	

	@Override
	public void esegui(Partita partita) {
		if(partita.getGiocatore().getBorsa().isEmpty() == true)
			partita.getIO().mostraMessaggio("Non c'è nessun oggetto nella borsa.");
		else if(partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro())== false)
			partita.getIO().mostraMessaggio("Non hai questo oggetto nella tua borsa!");
		else {
			Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
			if(partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)==false)
				partita.getIO().mostraMessaggio("Impossibile posare l'ogetto, nella stanza ce ne sono già troppi.");
			else {
				partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
				partita.getIO().mostraMessaggio("Oggetto posato correttamente!");
			}
		}
	}
	
	

	@Override
	public String getNome() {
		
		return "posa";
	}

	
}
