package it.uniroma3.diadiaTest.comandiTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.*;

class ComandoVaiTest {

	private FabbricaDiComandi f = new FabbricaDiComandiRiflessiva();
	private Partita partita;
	private Labirinto labirinto;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	@BeforeEach
	void setup() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Biblioteca", "Atrio","sud");
		this.stanzaIniziale = new Stanza("Atrio");
		this.stanzaVincente = new Stanza("Biblioteca");
		this.stanzaIniziale.impostaStanzaAdiacente("nord", stanzaVincente);
		this.stanzaVincente.impostaStanzaAdiacente("sud", stanzaIniziale);
		this.partita = new Partita( labirinto ,new IOConsole());

	}

	@Test
	void testComandoVaiConDirezionePercorribile() {
		AbstractComando c = f.costruisciComando("vai nord");
		c.esegui(this.partita);
		String stanzaAttesa = this.stanzaVincente.getDescrizione();
		assertEquals(stanzaAttesa, this.partita.getStanzaCorrente().getDescrizione());
	}

	//Verifica che il giocatore non cambi stanza se prova ad andare in una direzione non esistente
	@Test
	void testComandoVaiConDirezioneNonPercorribile() {
		AbstractComando c = f.costruisciComando("vai sud");
		c.esegui(this.partita);
		String stanzaAttesa = this.stanzaIniziale.getDescrizione();
		assertEquals(stanzaAttesa, this.partita.getStanzaCorrente().getDescrizione());
	}
}
