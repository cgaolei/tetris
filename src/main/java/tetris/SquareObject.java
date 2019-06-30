package tetris;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Abstract SquareObject definition
 * @author Lei Gao
 */
public abstract class SquareObject {

	protected int size = 20;

	protected int iniX = 140;

	protected int iniY = 20;

	protected boolean isBottom = false;

	protected Square[] S = new Square[4];

	protected int currentShap = 1;

	protected int nextShap = 2;

	/** Creates a new instance of SquareObject */
	public SquareObject(int x, int y, int size) {
		this.iniX = x;
		this.iniY = y;
		this.size = size;
	}

	public SquareObject(int size) {
		this.size = size;
	}

	public void rotate() {
		int tempShap = currentShap;
		getShap(nextShap);
		if (!isMovable()) {
			getShap(tempShap);
		}
	}

	public abstract void getShap(int shap);

	public boolean isMovable() {
		for (int i = 0; i < 4; i++) {
			if (SquareMatrix.getValue(S[i].getX(), S[i].getY()) != 0)
				return false;
		}
		return true;
	}

	public void moveLeft() {
		for (int i = 0; i < 4; i++) {
			S[i].setX(S[i].getX() - size);
		}
		if (!isMovable()) {
			for (int i = 0; i < 4; i++) {
				S[i].setX(S[i].getX() + size);
			}
		}
	}

	public void moveRight() {
		for (int i = 0; i < 4; i++) {
			S[i].setX(S[i].getX() + size);
		}
		if (!isMovable()) {
			for (int i = 0; i < 4; i++) {
				S[i].setX(S[i].getX() - size);
			}
		}
	}

	public void moveDown() {
		for (int i = 0; i < 4; i++) {
			S[i].setY(S[i].getY() + size);
		}
		if (!isMovable()) {
			for (int i = 0; i < 4; i++) {
				S[i].setY(S[i].getY() - size);
			}
			isBottom = true;
		}
	}

	public void paintObject(Graphics g) {
		for (int i = 0; i < 4; i++) {
			if (S[i] != null) {
				g.setColor(getColor(S[i].getColorCode()));
				g.fill3DRect(S[i].getX(), S[i].getY(), size, size, true);
				g.setColor(Color.BLACK);
				g.drawRect(S[i].getX(), S[i].getY(), size, size);
			}
		}
	}

	/**
	 * Get the Color code according to the object code
	 * 
	 * @param v
	 *            the object code
	 * @return the AWT color
	 */
	public Color getColor(int v) {
		switch (v) {
		case 10:
			return Color.GREEN;
		case 11:
			return Color.BLUE;
		case 12:
			return Color.CYAN;
		case 13:
			return Color.MAGENTA;
		case 14:
			return Color.ORANGE;
		case 15:
			return Color.YELLOW;
		case 16:
			return Color.RED;
		case 6:
			return Color.WHITE;
		default:
			return Color.DARK_GRAY;
		}
	}
}
