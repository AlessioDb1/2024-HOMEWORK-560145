package it.uniroma3.diadiaTest;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {

	private Partita partita;

	@BeforeEach
	private void setUp() {
		this.partita = new Partita(new Labirinto().init());
	}

	public Partita partita(boolean vinta,int cfu, boolean finita) {
		partita.getGiocatore().setCfu(cfu);
		if(vinta == true)
			partita.setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		else
			partita.setStanzaCorrente(partita.getLabirinto().getStanzaIniziale());
		if(finita == true)
			partita.setFinita();
		return partita;
	}
	@Test
	void testIsFinitaSePartitaVinta() {
		assertTrue(partita(true,1,false).isFinita());
	}
	@Test 
	void testIsFinitaSeZeroCfu() {
		assertTrue(partita(false,0,false).isFinita());
	}
	@Test
	void testIsFinitaSePartitaFinita() {
		assertTrue(partita(false,1,true).isFinita());
	}
	@Test
	void testIsFinitaSePartitaAncoraInCorso() {
		assertFalse(partita(false,1,false).isFinita());
	}


}
