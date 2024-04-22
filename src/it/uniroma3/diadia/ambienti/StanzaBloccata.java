package it.uniroma3.diadia.ambienti;

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
		Stanza stanza = null;
		for(int i=0; i<this.getNumeroStanzeAdiacenti(); i++) {
			if (this.getDirezioni()[i].equals(direzione)) {
				if(this.getDirezioni()[i].equals(dirBloccata)&& !this.hasAttrezzo(pass))
					return this;
				else
					stanza = this.getStanzeAdiacenti()[i];
			}
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
