package Minesweeper.Core;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class Field extends JButton{

	private boolean covered = true;
	private boolean mine = false;
	private boolean tagged = false;
	private int countNeignbourMines = 0;
	private int x, y;
	
	public Field(int x, int y) {
		this.x = x;
		this.y = y;
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.BLACK);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isCovered() {
		return covered;
	}
	public void setCovered(boolean covered) {
		this.covered = covered;
	}
	public boolean isMine() {
		return mine;
	}
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	public boolean isTagged() {
		return tagged;
	}
	public void setTagged(boolean tagged) {
		this.tagged = tagged;
	}
	public int getCountNeignbourMines() {
		return countNeignbourMines;
	}
	public void setCountNeignbourMines(int countNeignbourMines) {
		this.countNeignbourMines = countNeignbourMines;
	}
}
