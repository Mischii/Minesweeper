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
				checkNearbyMines(field.getX(), field.getY());
				if(playField.getNeighbourMinesCounter(field.getX(), field.getY()) == 0) {
					uncoverNeighbour(field.getX(), field.getY(), field);
				}
			}else if(youWon == true){
				ui.showYouWon();
			}else {
				ui.showGameOver();
			}
		}
		
	}
	
	private void checkNearbyMines(int x, int y) {
		int mine = 0;
		for (int i = -1 + x; i <= 1 + x; i++)
        {
            for (int j = -1 + y; j <= 1 + y; j++)
            {
                if (!(x == i && y == j))
                {
                    if (playField.checkStateIsMine(x, y) == true)
                    {
                        mine++;
                    }
                }
            }
        }
		playField.setNeighbourMinesCounter(x, y, mine);
		System.out.println("X: " + x + " Y: " + " mine " + mine);
	}

	private void uncoverNeighbour(int x, int y, Field field) {
		for (int i = -1 + x; i <= 1 + x; i++)
        {
            for (int j = -1 + y; j <= 1 + y; j++)
            {
                if (!(x == i && y == j))
                {
                    if (playField.checkStateIsMine(x, y) == true)
                    {
                        checkNearbyMines(i,j);
                        playField.setStateUncovered(x, y);
                        ui.showField(field);
                		ui.showNeighbour(field);
                        if (playField.getNeighbourMinesCounter(x, y) == 0)
                        {
                        	uncoverNeighbour(i,j,field);
                        }
                    }
                }
            }
        }
		
	}

	//hat einen Fehler
	public boolean gameOver(Field field) {
		if(allFieldsUncovered(playField) == 56 && allMinesTaged(playField) == 8) {
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
	
	private int allMinesTaged(PlayField pf) {
		int counter = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(pf.checkStateTaged(i, j) == false) {
					counter++;
				}
			}
		}
		return counter;
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
