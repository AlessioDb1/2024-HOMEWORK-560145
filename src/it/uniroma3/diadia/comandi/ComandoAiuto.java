package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */

public class ComandoAiuto implements Comando{

	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "borsa", "guarda", "fine"};
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			partita.getIO().mostraMessaggio(elencoComandi[i]+" ");
		partita.getIO().mostraMessaggio("");
	}
	
	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome() {
		
		return "aiuto";
	}

	@Override
	public String getParametro() {
		
		return null;
	}
}
