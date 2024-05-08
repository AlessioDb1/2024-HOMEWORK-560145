package it.uniroma3.diadiaTest.ambientiTest;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StanzaTest {

	private Stanza stanza;
	private Attrezzo osso = new Attrezzo("osso",1);

	static final private String[] direzioni = {"nord","sud","ovest","est"};

	//Crea una Stanza inizializzando le stanze adiacenti all'array "stanze"
	//passato come parametro,segue l'ordine nord,sud,ovest,est
	public Stanza stanza(String...stanze) {
		Stanza stanza = new Stanza("Atrio");
		for(int i = 0;i<stanze.length;i++) {
			stanza.setStanza(new Stanza(stanze[i]),direzioni[i]);
		}
		return stanza;
	}
	/**
	 * Crea una stanza inizializzando gli attrezzi contenuti all'array "attrezzi"
	 * passato come parametro
	 * @param attrezzi
	 * @return
	 */
	public Stanza stanzaAttrezzo(String...attrezzi) {
		this.stanza = new Stanza("Atrio");
		for(int i = 0;i<attrezzi.length;i++) {
			stanza.setAttrezzo(new Attrezzo(attrezzi[i],1), i);
		}
		return stanza;
	}

	//Crea una Stanza con n "osso"
	public Stanza stanzaOsso(int n) {
		String[] attrezzi = new String[n];
		for(int i=0;i<attrezzi.length;i++)
			attrezzi[i]="osso";
		Stanza stanza = stanzaAttrezzo(attrezzi);
		return stanza;
	}

	//AddAttrezzo
	@Test
	void testAddAttrezzoAStanzaVuota() {
		assertTrue(stanzaAttrezzo().addAttrezzo(osso));
	}
	@Test
	void testAddAttrezzoAStanzaCon1SoloPostoLibero() {
		assertTrue(stanzaOsso(9).addAttrezzo(osso));
	}
	@Test
	void testAddAttrezzoAStanzaPiena(){
		assertFalse(stanzaOsso(10).addAttrezzo(osso));
	}
	//RemoveAttrezzo
	@Test
	void testRemoveAttrezzoDaStanzaVuota() {
		assertFalse(stanzaAttrezzo().removeAttrezzo("osso"));
		assertEquals(0,this.stanza.getAttrezzi().size());
	}
	@Test
	void testRemoveAttrezzoConAttrezzoNonPresente() {
		assertFalse(stanzaAttrezzo("Spada").removeAttrezzo("osso"));
		assertEquals(1,this.stanza.getAttrezzi().size());
	}
	@Test
	void testRemoveAttrezzoConAttrezzoPresente() {
		assertTrue(stanzaAttrezzo("Lancia").removeAttrezzo("Lancia"));
		assertEquals(0,this.stanza.getAttrezzi().size());
	}

	//ImpostaStanzaAdiacente
	//Creo uno stato noto col metodo stanza 
	//e lo confronto con una stanza(stanza2) a cui applico ImpostaStanzaAdiacente
	@Test
	void testImpostaStanzaAdiacenteAStanzaVuota() {
		Stanza stanza2 = stanza();
		stanza2.impostaStanzaAdiacente("nord", new Stanza("biblioteca"));
		assertEquals(stanza("biblioteca").getDescrizione(),stanza2.getDescrizione());
	}
	@Test
	void testImpostaStanzaAdiacenteConUnaSolaDirezioneLibera() {
		Stanza stanza2 = stanza("N10","N11","N3");
		stanza2.impostaStanzaAdiacente("est", new Stanza("biblioteca"));
		assertEquals(stanza("N10","N11","N3","biblioteca").getDescrizione(),stanza2.getDescrizione());
	}
	@Test
	void testImpostaStanzaAdiacenteSostituendoDirezioneGiàPresente() {
		Stanza stanza2 = stanza("laboratorio","N11");
		stanza2.impostaStanzaAdiacente("sud", new Stanza("biblioteca"));
		assertEquals(stanza("laboratorio","biblioteca").getDescrizione(),stanza2.getDescrizione());
	}
}
