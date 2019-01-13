package Minesweeper.UI;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Minesweeper.Core.Controller;
import Minesweeper.Core.Field;
import Minesweeper.Core.PlayField;

public class UserInterface {

	public JFrame window = new JFrame();
	public JPanel mainComponent = new JPanel();
	private PlayField playField;
	private Controller controller;
	public MessageWindow messageWindow = new MessageWindow();
	private final int PLAYFIELD_DIMENSION;


	public UserInterface(Controller ctrl, int dimensions) {
		controller = ctrl;
		PLAYFIELD_DIMENSION = dimensions;
	}
	
	public void createUI() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new FlowLayout());
		GridLayout layout = new GridLayout(PLAYFIELD_DIMENSION,PLAYFIELD_DIMENSION);
		mainComponent = new JPanel(layout);
		playField = controller.startGameAndReturnPlayField();
		addFieldsToMainComponent();
		window.add(mainComponent);
		window.pack();
		
		window.setVisible(true);
	}
	
	private void addFieldsToMainComponent() {
		for(int i = 0; i < PLAYFIELD_DIMENSION; i++)
		{
			for(int j = 0; j < PLAYFIELD_DIMENSION; j++)
			{
				mainComponent.add(playField.fields.get(j).get(i));
			}
		}
	}
	

	
	public void uncoverField(Field field) {
		field.setBackground(Color.blue);
		field.setForeground(Color.WHITE);
	}
	
	public void showNeighbourMinesCount(Field field) {
		field.setText(Integer.toString(field.getNeighbourMinesCount()));
	}
	
	public void tagField(Field field) {
		if(field.isTagged() ) {
			field.setBackground(Color.YELLOW);
		} else {
			field.setBackground(Color.BLACK);
		}
	}
	
	
	public void showGameOverMessage() {
		messageWindow.showGameEndMessage("Game over");
	}
	
	public void showYouWonMessage() {
		messageWindow.showGameEndMessage("You won!");
	}
}

