package GUI.Minesweeper_GUI;


/**
 * GIBB Christian Schweingruber, Bern 2017
 * Modul 226
 */


public class Spielfeld {

	private boolean spielLaeuft = true;
	private String message="";
	private int xSize;
	private int ySize;
	
	private char[] guiCodes={' ','!','*','0','1','2','3','4','5','6','7','8'};
	/**
	 * @param groesse
	 */
	public Spielfeld(int groesse) {
		xSize=groesse;
		ySize=groesse;
	}

	public void aufdecken(int i, int j) {
		message="ziehe: "+i + " "+j;
	}

	public void markieren(int i, int j) {
		message="markiere: "+i + " "+j;
	}

	public boolean spielLaeuft() {
		return spielLaeuft ;
	}

	public boolean gueltigerZug(int x, int y) {
		return true;
	}
	
	public char getZellenChar(int x, int y){
		//return guiCodes[(x+y) % guiCodes.length];
		return (x%2==0?'2':' ');
	}
	
	public int getXsize(){
		return xSize;
	}

	public int getYsize(){
		return ySize;
	}
	
	public String getMessage(){
		return message;
	}
	
}