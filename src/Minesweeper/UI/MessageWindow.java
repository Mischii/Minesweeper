package Minesweeper.UI;

import javax.swing.JOptionPane;

public class MessageWindow extends JOptionPane{

	public void showGameEnd (String winner) {
		
		JOptionPane.showMessageDialog(null, winner);
	}
}

