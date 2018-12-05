package GUI.Minesweeper_GUI;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.HashMap;
import java.util.Map;

public class BenutzerschnittstelleGUI {
	private final PrintStream out = System.out;
	private JFrame myFrame;
	private JPanel spielfeldPanel;
	private ZellenPeer[][] zellen;
	private Map<Character, ImageIcon> imageMap;
	private ActionListener al;
	private String commandInput = "";
	private BenutzerschnittstelleGUI me;
	private JPanel statusPanel;
	private JLabel statusLabel;
	private int gridsize=32;

	public BenutzerschnittstelleGUI(Spielfeld spielfeld) {
		me = this;
		myFrame = new JFrame("Minesweeper123");
		al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ev: " + e.getActionCommand());
				commandInput = e.getActionCommand();
				synchronized (me) {
					me.notify();
				}
			}
		};
		initImages();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLayout(new FlowLayout());
		JPanel headPanel = new JPanel();
		JLabel headLabel = new JLabel("Minesweeper M226-1 C.Schweingruber GIBB 2017");
		headLabel.setVisible(true);
		headPanel.add(headLabel);
		myFrame.add(headPanel);
		GridLayout layout = new GridLayout(spielfeld.getXsize(), spielfeld.getYsize());
		spielfeldPanel = new JPanel(layout);
		myFrame.add(spielfeldPanel);
		statusPanel = new JPanel();
		statusLabel = new JLabel();
		statusLabel.setVisible(true);
		statusPanel.add(statusLabel);
		statusPanel.setVisible(true);

		zellen = createZellenPeers(spielfeld);
		myFrame.add(statusPanel);
		myFrame.pack();
		Dimension d = myFrame.getSize();

		myFrame.setSize(d.width, d.height + 1);
		myFrame.setVisible(true);
	}

	private void initImages() {
		imageMap = new HashMap<>();
		imageMap.put(' ', getImage("blank.gif"));
		imageMap.put('e', getImage("blank.gif"));
		imageMap.put('!', getImage("bombflagged.gif"));
		imageMap.put('*', getImage("bombrevealed.gif"));
		for (int i = 0; i <= 8; i++) {
			imageMap.put(Character.forDigit(i, 10), getImage("open" + i + ".gif"));
		}

	}

	private ImageIcon getImage(String s) {
		ImageIcon i=new ImageIcon(getClass().getResource("icon/" + s));
		return new ImageIcon(i.getImage().getScaledInstance(gridsize, gridsize, Image.SCALE_SMOOTH));
	}

	private ZellenPeer[][] createZellenPeers(Spielfeld spielfeld) {
		ZellenPeer[][] zp = new ZellenPeer[spielfeld.getXsize()][spielfeld.getYsize()];
		for (int i = 0; i < zp.length; i++)
			for (int j = 0; j < zp[0].length; j++) {
				zp[i][j] = new ZellenPeer(i, j, imageMap, al, gridsize);
				zp[i][j].setImage(' ');
				spielfeldPanel.add(zp[i][j]);
			}
		return zp;
	}

	Kommando liesEingabe(Spielfeld spielfeld) {
		do {
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}
			}
			String eingabe = nextLine();
			/*Validator validator = new Validator(eingabe, spielfeld);
			if (validator.istGueltig())
				return validator.erzeugeKommando();
			else
				zeigeFehlermeldung(validator.getFehlerMeldung()); */
		} while (true);
	}

	private String nextLine() {
		return commandInput;
	}

	private void zeigeFehlermeldung(String s) {
		out.println("Falsche eingabe: " + s);
	}

	public void zeigeSpielfeld(Spielfeld spielfeld) {
		for (int i = 0; i < spielfeld.getXsize(); i++) {
			for (int j = 0; j < spielfeld.getYsize(); j++) {
				zellen[i][j].setImage(spielfeld.getZellenChar(i, j), true);
			}
			statusLabel.setText(spielfeld.getMessage());
			myFrame.repaint();
		}
	}

	public void zeigeEingabeaufforderung() {
		statusLabel.setText(statusLabel.getText() + " Gib deinen Zug ein!");
		myFrame.repaint();
	}

	public void zeigeSchlussmeldung() {
		statusLabel.setText("The End");
		myFrame.repaint();
	}

}
