package it.uniroma3.diadiaTest.comandiTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class ComandoPosaTest {

	private FabbricaDiComandi f = new FabbricaDiComandiFisarmonica();
	private Partita partita;
	private Stanza stanzaTest = new Stanza("Atrio");

	@BeforeEach
	void setup() {
		stanzaTest.addAttrezzo(new Attrezzo("osso", 1));
		Labirinto lab = new Labirinto();
		lab.setStanzaIniziale(stanzaTest);
		this.partita = new Partita( lab ,new IOConsole());

	}

	//I test vengono svolti nella stanza Atrio,
	//la quale contiene un solo attrezzo "osso"

	@Test
	void testComandoPosaConAttrezzoPresente() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("spada",1));
		Comando c = f.costruisciComando("posa spada");
		c.esegui(this.partita);
		assertEquals(2, this.partita.getStanzaCorrente().getAttrezzi().size());
		assertEquals(0,this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}

	@Test
	void testComandoPosaConAttrezzoNonPresente() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("spada",1));
		Comando c = f.costruisciComando("posa osso");
		c.esegui(this.partita);
		assertEquals(1, this.partita.getStanzaCorrente().getAttrezzi().size());
		assertEquals(1,this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}

	@Test
	void testComandoPosaConStanzaPiena() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("spada",1));
		Comando c = f.costruisciComando("posa spada");
		for(int i = 1; i<10;i++) {
			this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("spada",1));
		}
		c.esegui(this.partita);
		assertEquals(10, this.partita.getStanzaCorrente().getAttrezzi().size());
		assertEquals(1,this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}
}
