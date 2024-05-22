package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa è la classe principale crea e istanzia tutte le altre
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


	public DiaDia(Labirinto l, IO IO) {
		this.partita = new Partita(l,IO);
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
		AbstractComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();

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
		Labirinto l = new LabirintoBuilder()
	    .addStanzaIniziale("Atrio")
	    .addAttrezzo("osso", 1)
		.addStanzaVincente("Biblioteca")
		.addStanza("Bagno")
		.addAttrezzo("cartaIgienica", 2)
		.addStanzaBuia("N10", "lanterna")
		.addAttrezzo("passpartout", 0)
		.addStanzaMagica("N12", 2)
		.addCane("Snoopy", "Volevo dire... Bau.\n(Sembra volere qualcosa, controlla nella tua borsa)")
		.addStanzaBloccata("LabCampus", "sud", "passpartout")
		.addStrega("Cal", "Sono la strega responsabile della biblioteca, dovrai ingegnarti per riuscire ad entrare...")
		.addAttrezzo("anretnal", 2)
		.addAdiacenza("LabCampus", "Biblioteca", "sud")
		.addAdiacenza("Biblioteca","LaboratorioCampus", "nord")
		.addAdiacenza("LabCampus", "N12", "ovest")
		.addAdiacenza("N12", "LabCampus", "est")
		.addAdiacenza("N12", "Atrio", "nord")
		.addAdiacenza("Atrio", "N12", "sud")
		.addAdiacenza("N10", "Atrio", "sud")
		.addAdiacenza("Atrio", "N10", "nord")
		.addAdiacenza("Atrio","Bagno","ovest")
		.addAdiacenza("Bagno","Atrio","est")
		.getLabirinto();
		DiaDia gioco = new DiaDia(l,IO);
		gioco.gioca();
	}
}
