package it.uniroma3.diadiaTest.giocatoreTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {
	
	private Giocatore giocatore;
	@BeforeEach
	void setUp() {
		this.giocatore = new Giocatore();
	}
	
	@Test
	void testInizializzaBorsa() {
		assertNotNull(this.giocatore.getBorsa());
	}

}
