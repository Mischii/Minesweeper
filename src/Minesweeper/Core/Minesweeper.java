package Minesweeper.Core;

import Minesweeper.UI.UserInterface;

public class Minesweeper {

	public static void main(String[] args) {

		Controller controller = new Controller();
		UserInterface ui = new UserInterface(controller);
		ui.createUI();
	}
}
