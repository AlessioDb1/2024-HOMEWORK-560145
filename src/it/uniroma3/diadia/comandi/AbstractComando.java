package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	private String parametro;
	abstract public void esegui(Partita partita);
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	abstract public String getNome();
	public String getParametro() {
		return this.parametro;
	}

}
