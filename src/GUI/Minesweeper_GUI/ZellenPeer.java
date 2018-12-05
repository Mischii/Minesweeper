package GUI.Minesweeper_GUI;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ZellenPeer extends JPanel {

	private int x;
	private int y;
	private char currentChar;
	Map<Character, ImageIcon> imageMap;
	ImageIcon image=null;
	ActionListener al=null;
	

	public ZellenPeer(int i, int j, Map<Character, ImageIcon> imageMap, ActionListener al, int gridsize) {
		super();
		setPreferredSize(new Dimension(gridsize, gridsize));
		this.x=i;
		this.y=j;
		currentChar=' ';
		this.imageMap=imageMap;
		this.al=al;
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setImage(currentChar);
				repaint();
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setImage('0');
				repaint();
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (currentChar==' ' || currentChar=='!'){
					String command="";
					if (SwingUtilities.isRightMouseButton(e)){
						command="M";
					} else if (SwingUtilities.isLeftMouseButton(e)){
						command="T";
					}
					if (!command.equals("")){
						al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command+" "+y+" "+x));
					}
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        image.paintIcon(this, g, 0, 0);
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	} 	

	public void setImage(char c) {
		this.image = imageMap.get(c);
	} 	

	public void setImage(char c, boolean updateCChar) {
		this.currentChar=c;
		setImage(c);
	} 	
	
}
