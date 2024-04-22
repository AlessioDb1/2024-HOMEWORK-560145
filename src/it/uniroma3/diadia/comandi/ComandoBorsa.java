package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;

public class ComandoBorsa implements Comando{

	@Override
	public void esegui (Partita partita) {
		partita.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
	}
	
	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome() {
		
		return "borsa";
	}

	@Override
	public String getParametro() {
		
		return null;
	}
}
