package Minesweeper.UI;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Minesweeper.Core.Controller;
import Minesweeper.Core.Field;
import Minesweeper.Core.PlayField;

public class UserInterface {

	public JFrame window = new JFrame();
	public JPanel mainComponent = new JPanel();
	private PlayField playField;
	private Controller controller;
	public MessageWindow mw = new MessageWindow();
	
	public UserInterface(Controller ctrl) {
		controller = ctrl;
	}
	
	public void createUI() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new FlowLayout());
		GridLayout layout = new GridLayout(8,8);
		mainComponent = new JPanel(layout);
		playField = controller.generatePlayfield();
		playField = controller.generateMines();
		addFieldsToMainComponent();
		window.add(mainComponent);
		window.pack();
		
		window.setVisible(true);
	}
	
	private void addFieldsToMainComponent() {
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				mainComponent.add(playField.fields[j][i]);
			}
		}
	}
	

	
	public void showField(Field field) {
		field.setBackground(Color.blue);
	}
	
	public void showNeighbour(Field field) {
		field.setText(Integer.toString(field.getCountNeignbourMines()));
	}
	
	public void showATaggedField(Field field) {
		field.setBackground(Color.YELLOW);
	}
	
	
	public void showGameOver() {
		mw.showGameEnd("Game over");
	}
	
	public void showYouWon() {
		mw.showGameEnd("You won!");
	}
}
