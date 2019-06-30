package tetris;

import java.awt.*;
import javax.swing.JPanel;

/**
 * This is the middle left panel for displaying the next generated object
 * @author Lei Gao
 *
 */
public class TestPanel extends JPanel {

	private static final long serialVersionUID = -9132982378357189445L;

	private SquareObject nextObject;

	private int posX;

	private int posY;

	private int size;

	public TestPanel() {
		setLayout(null);
		setBackground(Color.PINK);
		setBorder(new javax.swing.border.LineBorder(Color.GREEN, 10, true));
		setMinimumSize(new java.awt.Dimension(160, 120));
		setPreferredSize(new java.awt.Dimension(160, 120));
		posX = 66;
		posY = 60;
		size = 20;
		nextObject = setObject();
	}

	private SquareObject setObject() {
		int type = TetrixApp.gamePanel.getNextObject();
		switch (type) {
		case 0:
			return new SquareTypeZero(posX, posY, size);
		case 1:
			return new SquareTypeOne(posX, posY, size);
		case 2:
			return new SquareTypeTwo(posX, posY, size);
		case 3:
			return new SquareTypeThree(posX, posY, size);
		case 4:
			return new SquareTypeFour(posX, posY, size);
		case 5:
			return new SquareTypeFive(posX, posY, size);
		case 6:
			return new SquareTypeSix(posX, posY, size);
		}
		return null;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("Courier New", Font.BOLD, 18));
		g.drawString("Next Object", 20, 30);
		nextObject = setObject();
		if (nextObject != null) {
			nextObject.paintObject(g);
		}
	}
}
