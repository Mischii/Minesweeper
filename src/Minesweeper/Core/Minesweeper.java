package Minesweeper.Core;

import Minesweeper.UI.UserInterface;

public class Minesweeper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Controller controller = new Controller();
		controller.generatePlayfield();
		controller.generateMines();
		UserInterface ui = new UserInterface(controller);
		ui.createUI();
		/*while(controller.gameOver == false) {
			//checkClick();
		}*/
	}

}
