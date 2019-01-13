package Minesweeper.UI;

import javax.swing.JOptionPane;

public class MessageWindow extends JOptionPane{

	public void showGameEndMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}

