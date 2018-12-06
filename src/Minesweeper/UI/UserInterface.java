package Minesweeper.UI;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import Minesweeper.Core.Controller;
import Minesweeper.Core.Field;
import Minesweeper.Core.PlayField;

public class UserInterface {

	public JFrame window = new JFrame();
	public JPanel mainComponent = new JPanel();
	private PlayField playField;
	private Controller controller;
	
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
			for(Field f: playField.fields[i]) {
				mainComponent.add(f);
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
	
	//zeigt das Label nicht an???
	public void showGameOver() {
		JLabel label = new JLabel();
		label.setSize(200, 200);
		label.setText("GameOver!!!");
		label.setBackground(Color.WHITE);
		label.setVisible(true);
		mainComponent.add(label);
		System.out.println("GAMEOVER");
	}
	
	public void showYouWon() {
		JLabel label = new JLabel();
		label.setSize(200, 200);
		label.setText("You Won!!!");
		label.setBackground(Color.WHITE);
		label.setVisible(true);
		mainComponent.add(label);
		System.out.println("You Won");
	}
}
