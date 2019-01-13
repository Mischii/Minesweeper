package Minesweeper.Core;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class Field extends JButton{

	private boolean covered = true;
	private boolean mine = false;
	private boolean tagged = false;
	private int neighbourMinesCount = 0;
	private int xCoordinate, yCoordinate;
	
	public Field(int x, int y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.BLACK);
	}
	
	public int getXCoordinate() {
		return xCoordinate;
	}

	public int getYCoordinate() {
		return yCoordinate;
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
	public int getNeighbourMinesCount() {
		return neighbourMinesCount;
	}
	public void setNeighbourMinesCount(int neighbourMinesCount) {
		this.neighbourMinesCount = neighbourMinesCount;
	}
}

