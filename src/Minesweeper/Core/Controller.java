package Minesweeper.Core;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		int randomX = r.nextInt(8);
		int randomY = r.nextInt(8);
		for(int i = 0; i < 8; i++) {
			while(playField.checkStateIsMine(randomX, randomY))
			{
				randomX = r.nextInt(8);
				randomY = r.nextInt(8);
			}
			playField.setMineInPlayField(randomX, randomY);
		}
		return playField;
	}
	
	public void checkClick(Field field) {
		if(playField.checkStateCovered(field.getXCoord(), field.getYCoord())) {
			field.setCovered(false);//damit beim cklick auf das letste feld schon gameOver ausgegeben wird
			if(!field.isMine()) {
				//field.setCovered(false);
				ui.showField(field);
				if(checkGameOverWin())
					return;
				checkNearbyMines(field.getXCoord(), field.getYCoord());
				ui.showNeighbour(field);
				uncoverNeighbour(field.getXCoord(), field.getYCoord());
			}else {
				ui.showGameOver();
			}
		}
	}
	
	private void checkNearbyMines(int x, int y) {
		int mine = 0;
		for (int i = x-1; i <= x+1; i++)
        {
            for (int j = y-1; j <= y+1; j++)
            {
                if (!(x == i && y == j) &&
            		(i >= 0 && i < playField.fields.length) && 
            		(j >= 0 && j < playField.fields.length))
                {
                    if (playField.checkStateIsMine(i, j))
                    {
                        mine++;
                    }
                }
            }
        }
		playField.setNeighbourMinesCounter(x, y, mine);
	}

	private void uncoverNeighbour(int x, int y) {
		if(playField.getNeighbourMinesCounter(x, y) > 0)
			return;
		for (int i = x-1; i <= x+1; i++)
        {
            for (int j = y-1; j <= y+1; j++)
            {
            	if (!(x == i && y == j) &&
                		(i >= 0 && i < playField.fields.length) && 
                		(j >= 0 && j < playField.fields.length))
                {
                    if (!playField.checkStateIsMine(i, j) && playField.checkStateCovered(i, j))
                    {
                        checkNearbyMines(i,j);
                        playField.setStateUncovered(i, j);
                        ui.showField(playField.getFieldByCoordinates(i, j));
                		ui.showNeighbour(playField.getFieldByCoordinates(i, j));
                        uncoverNeighbour(i,j);
                    }
                }
            }
        }
		checkGameOverWin();
	}
	
	public boolean checkGameOverWin()
	{
		System.out.println("Uncovered fields: " + allFieldsUncovered(playField));
		System.out.println("Tagged mines: " + allMinesTaged(playField));

		if(allFieldsUncovered(playField) == 56 && allMinesTaged(playField) == 8)
		{
			ui.showYouWon();
			return true;
		}
		return false;
	}
	
	private int allMinesTaged(PlayField pf) {
		int counter = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(pf.checkStateTaged(i, j)) {
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
				if(pf.checkStateCovered(i, j) == false) {
					counter++;
				}
			}
		}
		return counter;
	}
	

	public void tagSelectedField(Field field) {
		ui.showATaggedField(playField.tagAField(field.getXCoord(), field.getYCoord()));
		checkGameOverWin();
	}
}
