package it.uniroma3.diadia;


import java.util.Scanner;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"CFU iniziali: 20\nPerdi "+
			"la partita se finisci i CFU, ricorda: ogni volta che cambi stanza ne perdi 1!\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.\n";

	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "borsa", "fine"};

	private Partita partita;
	private IOConsole IO;
	private Labirinto labirinto;

	public DiaDia() {
		this.labirinto = new Labirinto().init();
		this.partita = new Partita(this.labirinto);
		this.IO = new IOConsole();
	}

	public void gioca() {
		String istruzione; 


		this.IO.mostraMessaggio(MESSAGGIO_BENVENUTO);
		this.IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());



		do {
			istruzione = this.IO.leggiRiga();
			if(partita.isFinita() == true) {partita.setFinita();}
		}
		while (!processaIstruzione(istruzione) && !(partita.isFinita()));


		if( partita.getGiocatore().getCfu() == 0 && partita.vinta() == false) {
			this.IO.mostraMessaggio("Hai esaurito tutti i CFU a tua disposizione! La prossima volta sarai più fortunato.");
		}

	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if(istruzione.isEmpty()){
			this.IO.mostraMessaggio("Non hai inserito un comando,riprova!");
			return false;
		}
		else {
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(); 
				return true;
			} else if (comandoDaEseguire.getNome().equals("vai"))
				this.vai(comandoDaEseguire.getParametro());

			else if (comandoDaEseguire.getNome().equals("aiuto"))
				this.aiuto();

			else if (comandoDaEseguire.getNome().equals("prendi")) {
				if(comandoDaEseguire.getParametro() == null)
					this.IO.mostraMessaggio("Non hai specificato l'oggetto da prendere,riprova!");
				else
					this.prendi(comandoDaEseguire.getParametro());
			}
			else if(comandoDaEseguire.getNome().equals("posa")) {
				if(comandoDaEseguire.getParametro()== null)
					this.IO.mostraMessaggio("Non hai specificato l'oggetto da posare,riprova!");
				else
					this.posa(comandoDaEseguire.getParametro());
			}

			else if(comandoDaEseguire.getNome().equals("borsa")) {
				this.borsa();
			}
			else
				this.IO.mostraMessaggio("Comando sconosciuto");

			if (this.partita.vinta()) {
				this.IO.mostraMessaggio("Hai vinto!");
				return true;
			}
			else
				return false;
		}
	}   
	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.IO.mostraMessaggio(elencoComandi[i]+" ");
		this.IO.mostraMessaggio("");
	}

	private void borsa() {
		this.IO.mostraMessaggio(this.partita.getGiocatore().getBorsa().getDescrizione());
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.IO.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.IO.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
		}
		this.IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.IO.mostraMessaggio("CFU rimanenti: " +partita.getGiocatore().getCfu());

	}
	/**
	 * Cerca di prendere un oggetto presente nella stanza aggiungendolo alla borsa,
	 * se non ci sono oggetti nella stanza,l'oggetto non è presente o la borsa è piena
	 * stampa un messaggio di errore.
	 * @param nomeAttrezzo
	 */
	private void prendi(String nomeAttrezzo) {
		if(this.partita.getStanzaCorrente().getNumeroAttrezzi() == 0)
			this.IO.mostraMessaggio("Non ci sono oggetti da prendere in questa stanza!");
		else if(this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo) == false)
			this.IO.mostraMessaggio("Questo attrezzo non è presente nella stanza!");
		else {
			Attrezzo attrezzoDaPrendere = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)==false)
				this.IO.mostraMessaggio("Impossibile prendere l'ogetto, la capienza della borsa è al limite.");
			else {
				this.partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
				this.IO.mostraMessaggio("Oggetto aggiunto correttamente alla borsa!");
			}
		}
	}
	/**
	 * Cerca di posare un oggetto dalla borsa del giocatore nella stanza,
	 * stampa un messaggio d'errore se la borsa è vuota,non si possiede tale oggetto 
	 * o la stanza è già piena.
	 * @param nomeAttrezzo
	 */
	private void posa (String nomeAttrezzo) {
		if(this.partita.getGiocatore().getBorsa().isEmpty() == true)
			this.IO.mostraMessaggio("Non c'è nessun oggetto nella borsa.");
		else if(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)== false)
			this.IO.mostraMessaggio("Non hai questo oggetto nella tua borsa!");
		else {
			Attrezzo attrezzoDaPosare = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(this.partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)==false)
				this.IO.mostraMessaggio("Impossibile posare l'ogetto, nella stanza ce ne sono già troppi.");
			else {
				this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
				this.IO.mostraMessaggio("Oggetto posato correttamente!");
			}
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole IO = new IOConsole();
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}