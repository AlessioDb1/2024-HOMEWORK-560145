package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;

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

	private Partita partita;
	private IO IO;
	private Labirinto labirinto;

	public DiaDia(IO IO ) {
		this.labirinto = new Labirinto().init();
		this.partita = new Partita(this.labirinto,IO);
		this.IO = IO;
	}

	public void gioca() {
		String istruzione; 


		this.IO.mostraMessaggio(MESSAGGIO_BENVENUTO);
		

		do {
			istruzione = this.IO.leggiRiga();
		}
		while (!processaIstruzione(istruzione));
	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();

		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			this.IO.mostraMessaggio("Hai vinto!!");
			
		if (!this.partita.giocatoreIsVivo() && !this.partita.vinta())

			this.IO.mostraMessaggio("Hai esaurito i CFU...\nRitenta,sarai più fortunato");

		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		IO IO = new IOConsole();
		DiaDia gioco = new DiaDia(IO);
		gioco.gioca();
	}
}