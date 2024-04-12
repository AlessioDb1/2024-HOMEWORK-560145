package it.uniroma3.diadiaTest.ambientiTest;
import it.uniroma3.diadia.ambienti.Labirinto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

 
class LabirintoTest {

	private Labirinto lab = new Labirinto().init();
	
	
	//Verifica che l'inizializzazione del labirinto sia andata a buon fine.
	@Test
	public void testInizializzaLabirinto() {
		assertNotNull(this.lab);		
}
	//Verifica (analizzando il nome della stanza restituita) che l'atrio sia impostato come stanza iniziale.
	@Test
	public void testGetStanzaIniziale() {
		assertEquals("Atrio",this.lab.getStanzaIniziale().getNome());
	}
	//Verifica (analizzando il nome della stanza restituita) che la biblioteca sia impostata come stanza vincente.
	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca",this.lab.getStanzaVincente().getNome());
	}

}

