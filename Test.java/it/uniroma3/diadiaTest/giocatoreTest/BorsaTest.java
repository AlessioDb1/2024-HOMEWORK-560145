package it.uniroma3.diadiaTest.giocatoreTest;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class BorsaTest {

	private Borsa borsa;
	private Attrezzo osso1 = new Attrezzo("osso",1);
	private Attrezzo osso2 = new Attrezzo("osso",2);
	
//Crea un array di tutti "osso" da usare come parametro per creaBorsa
	public String[] arrayOsso(int numeroOsso) {
		String[] a = new String[numeroOsso];
		String osso = "osso";
		for(int i = 0;i<a.length;i++) {
			a[i] = osso;
		}
		return a;
	}

	private Borsa creaBorsa(int pesoMax,String...a) {
		this.borsa = new Borsa(pesoMax);
		for(int i=0;i<a.length;i++) {
			borsa.setAttrezzo(i, new Attrezzo(a[i],1));
		}
		return borsa;
	}


	
	//AddAttrezzo
	@Test 
	void testAddAttrezzoABorsaVuota() {
		assertTrue(creaBorsa(1).addAttrezzo(osso1));
	}
	
	@Test
	void testAddAttrezzoABorsaOltrePesoMax() {
		assertFalse(creaBorsa(1).addAttrezzo(osso2));
	}
	@Test
	void testAddAttrezzoABorsaPiena() {
		assertFalse(creaBorsa(11,arrayOsso(10)).addAttrezzo(osso1));
	}
	//GetAttrezzo
	@Test
	void testGetAttrezzoConBorsaVuota() {
		assertNull(creaBorsa(1).getAttrezzo("osso"));
	}
	@Test
	void testGetAttrezzoConAttrezzoPresente() {
		assertEquals("Spada",creaBorsa(1,"Spada").getAttrezzo("Spada").getNome());
	}
	@Test
	void testGetAttrezzoConAttrezzoNonPresente() {
		assertNull(creaBorsa(1,"Spada").getAttrezzo("Lanterna"));
	}
	@Test
	//RemoveAttrezzo
	void testRemoveAttrezzoConBorsaVuota(){
		assertNull(creaBorsa(1).removeAttrezzo("osso"));
		assertEquals(0,this.borsa.getNumeroAttrezzi());
	}
	@Test
	void testRemoveAttrezzoConAttrezzoPresente() {
		assertEquals("Lancia",creaBorsa(1,"Lancia").removeAttrezzo("Lancia").getNome());
		assertEquals(0,this.borsa.getNumeroAttrezzi());
	}
	@Test
	void testRemoveAttrezzoConAttrezzoNonPresente() {
		assertNull(creaBorsa(1,"Spada").removeAttrezzo("Lancia"));
		assertEquals(1,this.borsa.getNumeroAttrezzi());
	}
}
