package it.uniroma3.diadia.personaggi;

import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.costanti.Direzioni;

public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_SALUTO = "Data la tua premura nel salutarmi, ti teletrasporterò nella stanza con più oggetti qui vicino";
	private static final String MESSAGGIO_OFFESA = "Pff, che maleducazione, non mi degni di un saluto e poi prentendi che ti aiuti? SPARISCI!!\n\n"
			+ "Sei stato teletrasportato...";
	private final String REGALO = "Un regalo? Per me? HAHAHAHAHAHAHA";
	
	
	public Strega(String nome, String presentazione) {
		super(nome,presentazione);
		
	}
	
	@Override 
	public String agisci(Partita partita) {
		if(this.haSalutato()) {
			int max = -1;
			Stanza piuAttrezzi = null ;
			Map<Direzioni,Stanza> m = partita.getStanzaCorrente().getStanzeAdiacenti();
			for (Map.Entry<Direzioni, Stanza> entry : m.entrySet()) {
				Stanza val = entry.getValue();
				if(val.getAttrezzi().size()>max && val != partita.getLabirinto().getStanzaVincente()) {
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
			Map<Direzioni,Stanza> m = partita.getStanzaCorrente().getStanzeAdiacenti();
			for (Map.Entry<Direzioni, Stanza> entry : m.entrySet()) {
				Stanza val = entry.getValue();
				if(val.getAttrezzi().size()<min && val != partita.getLabirinto().getStanzaVincente()) {
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
		return REGALO;
	}

}
