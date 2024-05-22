package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		String msg;
		AbstractPersonaggio p = partita.getStanzaCorrente().getPersonaggio();
		msg = p.saluta();
		partita.getIO().mostraMessaggio(msg);
	}

	@Override
	public String getNome() {
		
		return "saluta";
	}
	

}
