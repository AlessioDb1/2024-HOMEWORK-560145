package it.uniroma3.diadiaTest.comandiTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class ComandoVaiTest {

	private FabbricaDiComandi f = new FabbricaDiComandiFisarmonica();
	private Partita partita;
	private Stanza stanzaIniziale = new Stanza("Atrio");
	private Stanza stanzaVincente = new Stanza("Biblioteca");


	@BeforeEach
	void setup() {
		Labirinto lab = new Labirinto();
		lab.setStanzaIniziale(stanzaIniziale);
		lab.setStanzaVincente(stanzaVincente);
		stanzaIniziale.impostaStanzaAdiacente("nord", stanzaVincente);
		stanzaVincente.impostaStanzaAdiacente("sud", stanzaIniziale);
		this.partita = new Partita( lab ,new IOConsole());

	}

	@Test
	void testComandoVaiConDirezionePercorribile() {
		Comando c = f.costruisciComando("vai nord");
		c.esegui(this.partita);
		String stanzaAttesa = this.stanzaVincente.getDescrizione();
		assertEquals(stanzaAttesa, this.partita.getStanzaCorrente().getDescrizione());
	}
	
	//Verifica che il giocatore non cambi stanza se prova ad andare in una direzione non esistente
	@Test
	void testComandoVaiConDirezioneNonPercorribile() {
		Comando c = f.costruisciComando("vai sud");
		c.esegui(this.partita);
		String stanzaAttesa = this.stanzaIniziale.getDescrizione();
		assertEquals(stanzaAttesa, this.partita.getStanzaCorrente().getDescrizione());
	}
}
