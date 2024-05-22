package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_SALUTO = "Data la tua premura nel salutarmi, ti teletrasporterò nella stanza con più oggetti qui vicino";
	private static final String MESSAGGIO_OFFESA = "Pff, che maleducazione, non mi degni di un saluto e poi prentendi che ti aiuti? SPARISCI!!\n\n"
			+ "Sei stato teletrasportato...";
	
	
	public Strega(String nome, String presentazione) {
		super(nome,presentazione);
		
	}
	
	@Override 
	public String agisci(Partita partita) {
		String msg;
		
	}

}
