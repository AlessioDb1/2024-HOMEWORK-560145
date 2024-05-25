package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{

	private Attrezzo attrezzo;
	private boolean hasOsso = false;
	private final static String ROSICCHIA_OSSO = "rosicchia felice il suo osso...";
	private final static String MORDE = "GRRRRR ARGH!!!\nSei stato morso e hai perso un cfu...";

	public Cane(String nome, String presentaz,Attrezzo attrezzo) {
		super(nome, presentaz);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		if (hasOsso) {
			return this.getNome()+" "+ ROSICCHIA_OSSO; 
		}
		else {
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(cfu-1);
			return MORDE;
		}
	}

	public Attrezzo getAttrezzo() {
		return attrezzo;
	}

	public boolean getHasOsso() {
		return this.hasOsso;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo) {
		if(attrezzo.getNome().equals("osso")) {
			this.hasOsso = true;
			return this.getNome()+" "+ROSICCHIA_OSSO;
		}
		else 
			return MORDE;
	}
}
