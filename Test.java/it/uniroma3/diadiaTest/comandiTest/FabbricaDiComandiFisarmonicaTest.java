package it.uniroma3.diadiaTest.comandiTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandi f = new FabbricaDiComandiFisarmonica();
	
	@Test
	void testCostruisciComandoVai() {
		Comando c = f.costruisciComando("vai nord");
		assertEquals("vai",c.getNome());
		assertEquals("nord",c.getParametro());
		
	}
	
	@Test
	void testCostruisciComandoPrendi() {
		Comando c = f.costruisciComando("prendi osso");
		assertEquals("prendi",c.getNome());
		assertEquals("osso",c.getParametro());
		
	}
	
	@Test
	void testCostruisciComandoPosa() {
		Comando c = f.costruisciComando("posa osso");
		assertEquals("posa",c.getNome());
		assertEquals("osso",c.getParametro());
		
	}
	
	@Test
	void testCostruisciComandoGuarda() {
		Comando c = f.costruisciComando("guarda");
		assertEquals("guarda",c.getNome());
		assertNull(c.getParametro());
		
	}
	
	@Test
	void testCostruisciComandoFine() {
		Comando c = f.costruisciComando("fine");
		assertEquals("fine",c.getNome());
		assertNull(c.getParametro());
		
	}
	
	@Test
	void testCostruisciComandoNonValido() {
		Comando c = f.costruisciComando("");
		assertEquals("ComandoNonValido",c.getNome());
		assertNull(c.getParametro());
		
	}
	
	@Test
	void testCostruisciComandoBorsa() {
		Comando c = f.costruisciComando("borsa");
		assertEquals("borsa",c.getNome());
		assertNull(c.getParametro());
		
	}

}
