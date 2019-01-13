package Minesweeper.Core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class PlayField {

	public Controller controller;
	public Field[][] fields = new Field[8][8];

	public PlayField(Controller ctrl) {
		controller = ctrl;
	}

	public Field setField(int x, int y) {
		fields[x][y] = new Field(x, y);
		fields[x][y].addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					controller.tagSelectedField(fields[x][y]);
				} else if (SwingUtilities.isLeftMouseButton(e)) {
					controller.checkClick(fields[x][y]);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		return fields[x][y];
	}

	public Field setMineInPlayField(int x, int y) {
		fields[x][y].setMine(true);
		fields[x][y].setText("mine");
		return fields[x][y];
	}
	
	public Field setStateUncovered(int x, int y) {
		fields[x][y].setCovered(false);
		return fields[x][y];
	}
	
	public void setNeighbourMinesCounter(int x, int y, int mines) {
		fields[x][y].setCountNeignbourMines(mines);
	}
	
	public int getNeighbourMinesCounter(int x, int y) {
		return fields[x][y].getCountNeignbourMines();
	}

	public Field tagAField(int x, int y) {
		fields[x][y].setTagged(!fields[x][y].isTagged());
		return fields[x][y];
	}

	public boolean checkStateCovered(int x, int y) {
		return fields[x][y].isCovered();
	}

	public boolean checkStateTaged(int x, int y) {
		if (fields[x][y].isTagged() == false) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkStateIsMine(int x, int y) {
		return fields[x][y].isMine();
	}
	
	public Field getFieldByCoordinates(int x, int y) {
		for(int i = 0; i <= 8; i++)
		{
			for(int j = 0; j <= 8; j++)
			{
				if(i == x && j == y)
				{
					return fields[x][y];
				}
			}
		}
		return null;
	}
}

