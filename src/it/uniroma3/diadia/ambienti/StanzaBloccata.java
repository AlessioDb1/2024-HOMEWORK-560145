package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.costanti.Direzioni;

public class StanzaBloccata extends Stanza{

	private String pass;
	private String dirBloccata;

	public StanzaBloccata(String nome, String dirBloccata, String attrezzoPass) {
		super(nome);
		this.pass = attrezzoPass;
		this.dirBloccata = dirBloccata;
	}
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		Direzioni dir = Direzioni.valueOf(direzione);
		Stanza stanza = null;
		if(this.getStanzeAdiacenti().containsKey(dir)) {
			if(direzione.equals(dirBloccata)&& !this.hasAttrezzo(pass))
				return this;
			else
				stanza = this.getStanzeAdiacenti().get(dir);
		}
		return stanza;
	}

	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(pass))
			return this.toString() + "\nL'uscita "+this.dirBloccata+" è sbloccata grazie a "+this.pass;
		else
			return this.toString() + "\nA quanto pare l'uscita "+this.dirBloccata+" è bloccata...";

	}
}
