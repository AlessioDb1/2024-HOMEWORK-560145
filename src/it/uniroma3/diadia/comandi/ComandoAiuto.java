package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */

public class ComandoAiuto implements Comando{

	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "borsa:\n-peso (contenuto ordinato per peso)\n-nome (contenuto ordinato per nome)\n-gruppi (contenuto suddiviso in gruppi con stesso peso)", "guarda", "fine"};
	
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
