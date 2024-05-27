package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.*;

public class ComandoRegala extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		if(this.getParametro() == null)
			partita.getIO().mostraMessaggio("Non hai specificato cosa vuoi regalare!");
		else if(!partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro()))
			partita.getIO().mostraMessaggio("Non puoi regalare qualcosa che non hai...");
		else {
			AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
			if(personaggio.getClass() == Cane.class) {
				Cane cane = (Cane)personaggio;
				String msg = cane.riceviRegalo(partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro()));
				if(this.getParametro().equals("osso")) {
					partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
					partita.getStanzaCorrente().addAttrezzo(cane.getAttrezzo());
				}
				else
					cane.agisci(partita);
				partita.getIO().mostraMessaggio(msg);
			}
			else if(personaggio.getClass() == Mago.class) {
				Mago mago = (Mago)personaggio;
				String msg = personaggio.riceviRegalo(partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro()));
				partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
				partita.getStanzaCorrente().addAttrezzo(mago.getRegalo());
				partita.getIO().mostraMessaggio(msg);
			}
			else if(personaggio.getClass() == Strega.class) {
				String msg = personaggio.riceviRegalo(partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro()));
				partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
				partita.getIO().mostraMessaggio(msg);
			}
		}

	}

	@Override
	public String getNome() {

		return "regala";
	}

}
