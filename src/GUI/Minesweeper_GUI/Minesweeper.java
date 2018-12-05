package GUI.Minesweeper_GUI;


public class Minesweeper {
	private final Spielfeld spielfeld = new Spielfeld(8);
	private final BenutzerschnittstelleGUI benutzerschnittstelle = new BenutzerschnittstelleGUI(spielfeld);

	public static void main(String[] args) {
		Minesweeper minesweeper = new Minesweeper();
		minesweeper.spielen();
	}
	
	private void spielen() {
		do {
			benutzerschnittstelle.zeigeSpielfeld(spielfeld);
			benutzerschnittstelle.zeigeEingabeaufforderung();
			Kommando kommando = benutzerschnittstelle.liesEingabe(spielfeld);
			kommando.ausfuehren(spielfeld);			
		} while (spielLaeuft());
		benutzerschnittstelle.zeigeSpielfeld(spielfeld);
		benutzerschnittstelle.zeigeSchlussmeldung();
	}

	private boolean spielLaeuft() {
		return spielfeld.spielLaeuft();
	}
}

