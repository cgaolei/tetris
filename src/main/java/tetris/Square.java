package tetris;

/**
 * This class represents one single square displayed in game
 * All other square objects are simply built up by square.
 * @author Lei Gao
 */
public class Square {

	private int x;

	private int y;

	private int colorCode;

	/** Creates a new instance of Square */
	public Square(int x, int y, int colorCode) {
		this.x = x;
		this.y = y;
		this.colorCode = colorCode;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getColorCode() {
		return colorCode;
	}
}
