package it.uniroma3.diadia.costanti;
import static it.uniroma3.diadia.costanti.Direzioni.*;

public class DirezioniUtils {

	public static Direzioni opposta(Direzioni direzione) {
		switch (direzione) {
		case nord: return sud;
		case est: return ovest;
		case sud: return nord;
		case ovest: return est;
		default: return null;
		}

	}
}
