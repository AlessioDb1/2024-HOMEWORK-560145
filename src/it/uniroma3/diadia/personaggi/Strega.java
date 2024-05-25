package it.uniroma3.diadia.personaggi;

import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_SALUTO = "Data la tua premura nel salutarmi, ti teletrasporterò nella stanza con più oggetti qui vicino";
	private static final String MESSAGGIO_OFFESA = "Pff, che maleducazione, non mi degni di un saluto e poi prentendi che ti aiuti? SPARISCI!!\n\n"
			+ "Sei stato teletrasportato...";
	
	
	public Strega(String nome, String presentazione) {
		super(nome,presentazione);
		
	}
	
	@Override 
	public String agisci(Partita partita) {
		if(this.haSalutato()) {
			int max = -1;
			Stanza piuAttrezzi = null ;
			Map<String,Stanza> m = partita.getStanzaCorrente().getStanzeAdiacenti();
			for (Map.Entry<String, Stanza> entry : m.entrySet()) {
				Stanza val = entry.getValue();
				if(val.getAttrezzi().size()>max) {
					piuAttrezzi = val;
					max = val.getAttrezzi().size();
				}
			}
			partita.setStanzaCorrente(piuAttrezzi);
			return MESSAGGIO_SALUTO;
		}
		else {
			int min = 11;
			Stanza menoAttrezzi = null ;
			Map<String,Stanza> m = partita.getStanzaCorrente().getStanzeAdiacenti();
			for (Map.Entry<String, Stanza> entry : m.entrySet()) {
				Stanza val = entry.getValue();
				if(val.getAttrezzi().size()<min) {
					menoAttrezzi = val;
					min = val.getAttrezzi().size();
				}
			}
			partita.setStanzaCorrente(menoAttrezzi);
			return MESSAGGIO_OFFESA;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo) {
		// TODO Auto-generated method stub
		return null;
	}

}
