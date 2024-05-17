package it.uniroma3.diadiaTest.giocatoreTest;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


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
		assertEquals(0,this.borsa.getAttrezzi().size());
	}
	@Test
	void testRemoveAttrezzoConAttrezzoPresente() {
		assertEquals("Lancia",creaBorsa(1,"Lancia").removeAttrezzo("Lancia").getNome());
		assertEquals(0,this.borsa.getAttrezzi().size());
	}
	@Test
	void testRemoveAttrezzoConAttrezzoNonPresente() {
		assertNull(creaBorsa(1,"Spada").removeAttrezzo("Lancia"));
		assertEquals(1,this.borsa.getAttrezzi().size());
	}
	@Test
	void testGetContenutoOrdinatoPerPesoConBorsaOrdinata() {
		this.borsa = new Borsa(20);
		borsa.addAttrezzo(osso1);
		borsa.addAttrezzo(new Attrezzo("Spada", 2));
		borsa.getContenutoOrdinatoPerPeso();
		assertEquals("osso", this.borsa.getAttrezzi().get(0).getNome());
		assertEquals("Spada", this.borsa.getAttrezzi().get(1).getNome());
	}
	@Test
	void testGetContenutoOrdinatoPerPesoConBorsaNonOrdinato() {
		this.borsa = new Borsa(20);
		borsa.addAttrezzo(new Attrezzo("Spada", 2));
		borsa.addAttrezzo(osso1);
		borsa.getContenutoOrdinatoPerPeso();
		assertEquals("osso", this.borsa.getAttrezzi().get(0).getNome());
		assertEquals("Spada", this.borsa.getAttrezzi().get(1).getNome());
	}
	@Test
	void testGetContenutoOrdinatoPerPesoConBorsaConAttrezziStessoPeso() {
		this.borsa = new Borsa(20);
		borsa.addAttrezzo(new Attrezzo("spada", 1));
		borsa.addAttrezzo(osso1);
		borsa.getContenutoOrdinatoPerPeso();
		assertEquals("osso", this.borsa.getAttrezzi().get(0).getNome());
		assertEquals("spada", this.borsa.getAttrezzi().get(1).getNome());
	}
	@Test
	void testGetContenutoOrdinatoPerPesoConBorsaNonOrdinataEMoltiAttrezzi() {
		this.borsa = new Borsa(20);
		borsa.addAttrezzo(new Attrezzo("spada", 2));
		borsa.addAttrezzo(osso1);
		borsa.addAttrezzo(new Attrezzo("lancia", 5));
		borsa.addAttrezzo(new Attrezzo("pass", 0));
		borsa.addAttrezzo(new Attrezzo("lanterna", 4));
		borsa.getContenutoOrdinatoPerPeso();
		assertEquals("pass", this.borsa.getAttrezzi().get(0).getNome());
		assertEquals("osso", this.borsa.getAttrezzi().get(1).getNome());
		assertEquals("spada", this.borsa.getAttrezzi().get(2).getNome());
		assertEquals("lanterna", this.borsa.getAttrezzi().get(3).getNome());
		assertEquals("lancia", this.borsa.getAttrezzi().get(4).getNome());
	}
	@Test
	void testGetContenutoOrdinatoPerNomeConBorsaOrdinata() {
		this.borsa = new Borsa(20);
		borsa.addAttrezzo(osso1);
		borsa.addAttrezzo(new Attrezzo("spada", 2));
		Set<Attrezzo> s= borsa.getContenutoOrdinatoPerNome();
		ArrayList<Attrezzo> a = new ArrayList<Attrezzo>(s);
		assertEquals("osso", a.get(0).getNome());
		assertEquals("spada", a.get(1).getNome());
	}
	@Test
	void testGetContenutoOrdinatoPerNomeConBorsaNonOrdinata() {
		this.borsa = new Borsa(20);
		borsa.addAttrezzo(new Attrezzo("spada", 2));
		borsa.addAttrezzo(osso1);
		Set<Attrezzo> s= borsa.getContenutoOrdinatoPerNome();
		ArrayList<Attrezzo> a = new ArrayList<Attrezzo>(s);
		assertEquals("osso", a.get(0).getNome());
		assertEquals("spada", a.get(1).getNome());
	}
	@Test
	void testGetContenutoOrdinatoPerNomeConAttrezziStessoNome() {
		this.borsa = new Borsa(20);
		borsa.addAttrezzo(new Attrezzo("spada", 2));
		borsa.addAttrezzo(new Attrezzo("spada", 1));
		Set<Attrezzo> s= borsa.getContenutoOrdinatoPerNome();
		ArrayList<Attrezzo> a = new ArrayList<Attrezzo>(s);
		assertEquals(1, a.get(0).getPeso());
		assertEquals(2, a.get(1).getPeso());
	}
	
	@Test
	void testGetContenutoOrdinatoPerNomeConBorsaNonOrdinataEMoltiattrezzi() {
		this.borsa = new Borsa(20);
		borsa.addAttrezzo(new Attrezzo("spada", 2));
		borsa.addAttrezzo(osso1);
		borsa.addAttrezzo(new Attrezzo("lancia", 5));
		borsa.addAttrezzo(new Attrezzo("pass", 0));
		borsa.addAttrezzo(new Attrezzo("lanterna", 4));
		Set<Attrezzo> s= borsa.getContenutoOrdinatoPerNome();
		ArrayList<Attrezzo> a = new ArrayList<Attrezzo>(s);
		assertEquals("lancia", a.get(0).getNome());
		assertEquals("lanterna", a.get(1).getNome());
		assertEquals("osso", a.get(2).getNome());
		assertEquals("pass", a.get(3).getNome());
		assertEquals("spada", a.get(4).getNome());
	}
	@Test
	void testGetContenutoRaggruppatoPerPesoConUnSoloGruppo() {
		this.borsa = new Borsa(20);
		borsa.addAttrezzo(osso1);
		Map<Integer, Set<Attrezzo>> m = borsa.getContenutoRaggruppatoPerPeso();
		assertEquals("osso",new ArrayList<Attrezzo>(m.get(1)).get(0).getNome());
	}
	@Test
	void testGetContenutoRaggruppatoPerPesoConPiùGruppi() {
		this.borsa = new Borsa(20);
		borsa.addAttrezzo(osso1);
		borsa.addAttrezzo(new Attrezzo("spada", 2));
		borsa.addAttrezzo(new Attrezzo("lancia", 3));
		Map<Integer, Set<Attrezzo>> m = borsa.getContenutoRaggruppatoPerPeso();
		assertEquals("osso",new ArrayList<Attrezzo>(m.get(1)).get(0).getNome());
		assertEquals("spada",new ArrayList<Attrezzo>(m.get(2)).get(0).getNome());
		assertEquals("lancia",new ArrayList<Attrezzo>(m.get(3)).get(0).getNome());
	}
	
}
