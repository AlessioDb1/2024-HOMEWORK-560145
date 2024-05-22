package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{

	private Attrezzo attrezzo = null;
	private boolean hasOsso = false;
	private final static String ROSICCHIA_OSSO = "rosicchia felice il suo osso...";
	private final static String MORDE = "GRRRRR ARGH!!!\nSei stato morso e hai perso un cfu...";
	
	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
	}
	
	@Override
	public String agisci(Partita partita) {
		if (hasOsso) {
			return ROSICCHIA_OSSO; 
		}
		else {
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(cfu-1);
			return MORDE;
		}
	}
	
	public void ricevi(Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}

	public Attrezzo getAttrezzo() {
		return attrezzo;
	}
	
	public boolean getHasOsso() {
		return this.hasOsso;
	}
}
