package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Comando sconosciuto. Riprova!");
	}
	
	

	@Override
	public String getNome() {
		
		return "ComandoNonValido";
	}

	
}
