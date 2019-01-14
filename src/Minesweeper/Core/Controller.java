package Minesweeper.Core;
import java.util.Random;

import Minesweeper.UI.UserInterface;

public class Controller {
	

	public PlayField playField;
	public UserInterface userInterface;
	private final int PLAYFIELD_DIMENSION;
	private final int NUMBER_OF_MINES = 8;

	public Controller(int dimensions)
	{
		PLAYFIELD_DIMENSION = dimensions;
		userInterface = new UserInterface(this, PLAYFIELD_DIMENSION);
		playField = new PlayField(this, PLAYFIELD_DIMENSION);
	}

	public PlayField generatePlayfield() {
		for(int i = 0; i < PLAYFIELD_DIMENSION; i++) {
			for(int j = 0; j < PLAYFIELD_DIMENSION; j++) {
				playField.createField(i, j);
			}
		}
		return playField;
	}

	public PlayField startGameAndReturnPlayField() {
		generatePlayfield();
		placeMines();
		return playField;
	}
	
	public PlayField placeMines() {
		Random r = new Random();
		int randomX = r.nextInt(PLAYFIELD_DIMENSION);
		int randomY = r.nextInt(PLAYFIELD_DIMENSION);
		for(int i = 0; i < NUMBER_OF_MINES; i++) {
			while(playField.getFieldByCoordinates(randomX, randomY).isMine())
			{
				randomX = r.nextInt(PLAYFIELD_DIMENSION);
				randomY = r.nextInt(PLAYFIELD_DIMENSION);
			}
			playField.placeMine(playField.getFieldByCoordinates(randomX, randomY));
		}
		return playField;
	}
	
	
	public void handleClick(Field field) {
		if(field.isCovered()) {
			field.setCovered(false); //damit beim Klick auf das letzte feld schon gameOver ausgegeben wird
			if(!field.isMine()) {
				userInterface.uncoverField(field);
				countNearbyMinesAndSetCounter(field);
				userInterface.showNeighbourMinesCount(field);
				uncoverNeighboursIfNoNeighbourMines(field);
			}else {
				userInterface.showGameOverMessage();
			}
		}
	}
	
	private void countNearbyMinesAndSetCounter(Field field) {
		int minesCount = 0;
		for(Field neighbour: playField.getNeighbours(field)) {
			if (neighbour.isMine()) {
				minesCount++;
			}
		}
		field.setNeighbourMinesCount(minesCount);
	}

	private void uncoverNeighboursIfNoNeighbourMines(Field field) {
		if(field.getNeighbourMinesCount() > 0)
			return;
		for(Field neighbour: playField.getNeighbours(field)) {
			if (!neighbour.isMine() && neighbour.isCovered()) {
				countNearbyMinesAndSetCounter(neighbour);
				neighbour.setCovered(false);
				userInterface.uncoverField(neighbour);
				userInterface.showNeighbourMinesCount(neighbour);
				uncoverNeighboursIfNoNeighbourMines(neighbour);
			}
		}
	}
	
	private void checkIfPlayerWon()
	{
		if(checkIfAllMinesAreTagged())
			userInterface.showYouWonMessage();
	}
	
	private boolean checkIfAllMinesAreTagged() {
		boolean allTagged = true;
		for(Field mine: playField.getMines())
		{
			if(!mine.isTagged())
				allTagged = false;
		}
		return allTagged;
	}

	public void tagField(Field field) {
		if(field.isCovered()) {
			playField.tagField(field);
			userInterface.tagField(field);
			checkIfPlayerWon();
		}
	}
}
