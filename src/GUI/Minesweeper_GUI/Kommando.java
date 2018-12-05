package GUI.Minesweeper_GUI;

public class Kommando {
	  private String kommandoZeichen;
	  private int zeile;
	  private int spalte;

	  public Kommando(String kommandoZeichen, int zeile, int spalte) {
	    this.kommandoZeichen = kommandoZeichen;
	    this.zeile = zeile;
	    this.spalte = spalte;
	  }

	  public void ausfuehren(Spielfeld spielfeld) {
	    switch (kommandoZeichen) {
	    case "M":
	      spielfeld.markieren(spalte, zeile);
	    case "T":
	      spielfeld.aufdecken(spalte, zeile);
	    }
	  }
	}
