package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class LabirintoBuilder extends Labirinto{

	private Stanza stanzaCache;
	private ArrayList<Stanza> stanze;

	public LabirintoBuilder() {
		super();
		this.stanzaCache = null;
		this.stanze = new ArrayList<Stanza>();
	}

	public Labirinto getLabirinto() {
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String s) {
		this.stanzaCache = new Stanza(s);
		stanze.add(stanzaCache);
		this.setStanzaVincente(stanzaCache);
		return this;
	}

	public LabirintoBuilder addStanzaIniziale(String s) {
		this.stanzaCache = new Stanza(s);
		stanze.add(stanzaCache);
		this.setStanzaIniziale(stanzaCache);
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nome, int p) {
		this.stanzaCache.addAttrezzo(new Attrezzo(nome, p));
		return this;
	}
	
	public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo attrezzo) {
		Mago m = new Mago(nome, presentazione, attrezzo);
		this.stanzaCache.setPersonaggio(m);
		return this;
	}
	
	public LabirintoBuilder addStrega(String nome, String presentazione) {
		Strega s = new Strega(nome, presentazione);
		this.stanzaCache.setPersonaggio(s);
		return this;
	}
	
	public LabirintoBuilder addCane(String nome, String presentaz) {
		Cane c = new Cane(nome, presentaz);
		this.stanzaCache.setPersonaggio(c);
		return this;
		
	}

	public LabirintoBuilder addStanza(String s) {
		Stanza stanza = new Stanza(s);
		this.stanze.add(stanza);
		this.stanzaCache = stanza;
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nome,String direzione,String pass) {
		Stanza s = new StanzaBloccata(nome, direzione, pass);
		this.stanzaCache = s;
		this.stanze.add(s);
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nome,String luce) {
		Stanza s = new StanzaBuia(nome, luce);
		this.stanzaCache = s;
		this.stanze.add(s);
		return this;
	}

	public LabirintoBuilder addStanzaMagica(String nome,int soglia) {
		Stanza s = new StanzaMagica(nome, soglia);
		this.stanzaCache = s;
		this.stanze.add(s);
		return this;
	}


	public LabirintoBuilder addAdiacenza(String s1,String s2,String dir) {
		Stanza daSettare = null;
		Stanza adiacente = null;
		for (Stanza stanza : stanze) {
			if(stanza.getNome()==s1)
				daSettare = stanza;
			if(stanza.getNome()== s2)
				adiacente = stanza;
		}
		daSettare.impostaStanzaAdiacente(dir, adiacente);
		return this;
	}
}
