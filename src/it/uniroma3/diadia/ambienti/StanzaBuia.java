package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{

	private String luce;

	public StanzaBuia(String nome,String attrezzoLuce) {
		super(nome);
		this.luce = attrezzoLuce;

	}

	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(luce))
			return this.toString();
		else
			return "Qui c'è buio pesto";
	}


}
