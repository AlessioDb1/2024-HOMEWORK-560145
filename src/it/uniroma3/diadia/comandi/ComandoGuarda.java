package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getIO().mostraMessaggio("CFU rimanenti: "+ partita.getGiocatore().getCfu());}


	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "guarda";
	}

}
