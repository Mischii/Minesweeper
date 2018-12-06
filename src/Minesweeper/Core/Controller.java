package Minesweeper.Core;
import java.util.Random;

import Minesweeper.UI.UserInterface;

public class Controller {
	

	public PlayField playField = new PlayField(this);
	public UserInterface ui = new UserInterface(this);
	public boolean gameOver = false;
	public boolean youWon = false;
	
	public PlayField generatePlayfield() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				playField.setField(i, j);
			}
		}
		return playField;
	}
	
	public PlayField generateMines() {
		Random r = new Random();
		
		for(int i = 0; i < 8; i++) {
			int randomX = r.nextInt(8);
			int randomY = r.nextInt(8);
			
			playField.setMineInPlayField(randomX, randomY);
			System.out.println("Mine: " + randomX + randomY);
		}
		return playField;
	}
	
	public void checkClick(Field field) {
		if(playField.checkStateUncovered(field.getXCoord(), field.getYCoord()) == true) {
			field.setCovered(false);//damit beim cklick auf das letste feld schon gameOver ausgegeben wird
			if(gameOver(field) == false) {
				//field.setCovered(false);
				ui.showField(field);
				uncoverNeighbout(field);
			}else if(youWon == true){
				ui.showYouWon();
			}else {
				ui.showGameOver();
			}
		}
		
	}
	
	private void uncoverNeighbout(Field field) {
		//Nachbaren Aufdecken
		//ui.showField(field);
		//ui.showNeighbour(field)
		
	}

	//hat einen Fehler
	public boolean gameOver(Field field) {
		if(allFieldsUncovered(playField) == 56) {
			gameOver = true;
			youWon = true;
		}else if(field.isMine() == true ) {
			gameOver = true;
		}
		else {
			gameOver = false;
		}
		return gameOver;
	}
	
	private int allFieldsUncovered(PlayField pf) {
		int counter = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(pf.checkStateUncovered(i, j) == false) {
					counter++;
				}
			}
		}
		return counter;
	}
	

	public void tagSelectedField(Field field) {
		ui.showATaggedField(playField.tagAField(field.getXCoord(), field.getYCoord()));
	}
}
