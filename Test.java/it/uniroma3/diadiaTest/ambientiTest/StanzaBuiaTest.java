package it.uniroma3.diadiaTest.ambientiTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaBuiaTest {	
	
	private Partita partita;
	private Stanza stanzaIniziale = new StanzaBuia("Atrio","lanterna");
	private Stanza stanzaVincente = new Stanza("Biblioteca");
	private String Buio = "Qui c'è buio pesto";
	private String Luce = "Ti trovi in: Atrio\n"
						 +"Uscite:  nord\n"
						 +"Attrezzi nella stanza: lanterna (3kg) ";


	@BeforeEach
	void setUp() {
		Labirinto lab = new Labirinto();
		lab.setStanzaIniziale(stanzaIniziale);
		lab.setStanzaVincente(stanzaVincente);
		stanzaIniziale.impostaStanzaAdiacente("nord", stanzaVincente);
		stanzaVincente.impostaStanzaAdiacente("sud", stanzaIniziale);
		this.partita = new Partita( lab ,new IOConsole());
	}

	@Test
	void testDescrizioneStanzaBuiaSenzaLanterna() {
		assertEquals(Buio,partita.getStanzaCorrente().getDescrizione());
	}
	
	@Test
	void testDescrizioneStanzaBuiaConLanterna() {
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("lanterna", 3));
		assertEquals(Luce,partita.getStanzaCorrente().getDescrizione());
	}

}
