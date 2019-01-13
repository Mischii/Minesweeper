package Minesweeper.Core;

import Minesweeper.UI.UserInterface;

public class Minesweeper {
	public static final int DIMENSIONS = 8;

	public static void main(String[] args) {
		Controller controller = new Controller(DIMENSIONS);
		UserInterface userInterface = new UserInterface(controller, DIMENSIONS);
		userInterface.createUI();
	}
}
