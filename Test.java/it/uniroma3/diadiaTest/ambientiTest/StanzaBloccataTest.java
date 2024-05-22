package it.uniroma3.diadiaTest.ambientiTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.*;

class StanzaBloccataTest {

	private Partita partita;
	private Stanza stanzaIniziale = new StanzaBloccata("Atrio","nord", "passpartout");
	private Stanza stanzaVincente = new Stanza("Biblioteca");
	private FabbricaDiComandi f = new FabbricaDiComandiRiflessiva();

	@BeforeEach
	void setUp()  {
		Labirinto lab = new Labirinto();
		lab.setStanzaIniziale(stanzaIniziale);
		lab.setStanzaVincente(stanzaVincente);
		stanzaIniziale.impostaStanzaAdiacente("nord", stanzaVincente);
		stanzaVincente.impostaStanzaAdiacente("sud", stanzaIniziale);
		this.partita = new Partita( lab ,new IOConsole());
	}

	//Verifica che il giocatore non cambi stanza se prova ad andare in una direzione bloccata
	@Test
	void testStanzaBloccataSeVaiInDirezioneBloccata() {
		AbstractComando c = f.costruisciComando("vai nord");
		c.esegui(this.partita);
		String stanzaAttesa = this.stanzaIniziale.getDescrizione();
		assertEquals(stanzaAttesa, this.partita.getStanzaCorrente().getDescrizione());
	}
	
	@Test
	void testStanzaBloccataConDirezionePercorribileGrazieAlPass() {
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("passpartout", 0));
		AbstractComando c = f.costruisciComando("vai nord");
		c.esegui(this.partita);
		String stanzaAttesa = this.stanzaVincente.getDescrizione();
		assertEquals(stanzaAttesa, this.partita.getStanzaCorrente().getDescrizione());
	}

}
