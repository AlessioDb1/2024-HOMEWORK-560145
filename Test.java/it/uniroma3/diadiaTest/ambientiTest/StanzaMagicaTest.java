package it.uniroma3.diadiaTest.ambientiTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	private Partita partita;
	private Stanza stanzaIniziale;
	
	
	@BeforeEach
	void setUp() {
		Labirinto lab = new Labirinto();
		this.stanzaIniziale = new StanzaMagica("Atrio",1);
		lab.setStanzaIniziale(stanzaIniziale);
		this.partita = new Partita( lab ,new IOConsole());
	}

	@Test
	void testAddAttrezzoOltreSogliaMagica() {
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("osso", 1));
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("spada", 1));
		assertEquals("adaps",this.partita.getStanzaCorrente().getAttrezzi().get(1).getNome());
		assertEquals(2, this.partita.getStanzaCorrente().getAttrezzi().get(1).getPeso());
	}
	
	@Test
	void testAddAttrezzoEntroSogliaMagica() {
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("spada", 1));
		assertEquals("spada",this.partita.getStanzaCorrente().getAttrezzi().get(0).getNome());
		assertEquals(1, this.partita.getStanzaCorrente().getAttrezzi().get(0).getPeso());
	}

}
