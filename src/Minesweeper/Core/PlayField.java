package Minesweeper.Core;
import java.awt.Color;
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
	  fields[x][y] = new Field(x,y);
	  fields[x][y].addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)){
					controller.tagSelectedField(fields[x][y]);
				} else if (SwingUtilities.isLeftMouseButton(e)){
					controller.checkClick(fields[x][y]);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
	return fields[x][y]; 
  }
	
  
  public Field setMineInPlayField (int x, int y) {
	  fields[x][y].setMine(true);
	  return fields[x][y];
  }
  
  public Field tagAField(int x, int y) {
	  fields[x][y].setTagged(true);
	  return fields[x][y];
  }
  
  public boolean checkStateUncovered(int x, int y) {
	  if(fields[x][y].isCovered() == false)
	  {
		  return false;
	  }else {
		  return true;
	  }
  }
  
}
