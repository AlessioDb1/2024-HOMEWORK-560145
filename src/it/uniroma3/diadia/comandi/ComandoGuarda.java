package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	@Override
	public void esegui(Partita partita) {
		
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getIO().mostraMessaggio("CFU rimanenti: "+ partita.getGiocatore().getCfu());}

	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "guarda";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

}
