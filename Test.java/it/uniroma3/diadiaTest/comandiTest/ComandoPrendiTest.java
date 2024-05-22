package it.uniroma3.diadiaTest.comandiTest;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoPrendiTest {
	private FabbricaDiComandi f = new FabbricaDiComandiRiflessiva();
	private Partita partita;
	private Stanza stanzaTest = new Stanza("Atrio");

	@BeforeEach
	void setup() {
		stanzaTest.addAttrezzo(new Attrezzo("osso", 1));
		Labirinto lab = new Labirinto();
		lab.setStanzaIniziale(stanzaTest);
		this.partita = new Partita( lab ,new IOConsole());
	}

	//I test vengono svolti nella stanza iniziale del labirinto : Atrio,
	//la quale contiene un solo attrezzo "osso"


	//Verifica che "osso" venga preso dalla stanza attuale
	@Test
	void testComandoPrendiConAttrezzoPresente() {
		AbstractComando c = f.costruisciComando("prendi osso");
		c.esegui(this.partita);
		assertEquals(0, this.partita.getStanzaCorrente().getAttrezzi().size());
		assertEquals(1,this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}
	//Verifica che "osso" non venga preso dalla stanza attuale
	@Test
	void testComandoPrendiConAttrezzoNonPresente() {
		AbstractComando c = f.costruisciComando("prendi lanterna");
		c.esegui(this.partita);
		assertEquals(1,this.partita.getStanzaCorrente().getAttrezzi().size());
		assertEquals(0,this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}
	//Verifica che usando comandoPrendi non venga preso un attrezzo se la borsa ha già peso massimo
	@Test
	void testComandoPrendiConBorsaPesoMax() {
		AbstractComando c = f.costruisciComando("prendi osso");

		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("spada", 10));

		c.esegui(this.partita);
		assertEquals(1,this.partita.getStanzaCorrente().getAttrezzi().size());
		assertEquals(1,this.partita.getGiocatore().getBorsa().getAttrezzi().size());
	}

}
