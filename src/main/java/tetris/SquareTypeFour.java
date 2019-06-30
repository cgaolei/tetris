package tetris;

/**
 * 
 * @author Lei Gao
 */
public class SquareTypeFour extends SquareObject {

	/** Creates a new instance of SquareTypeOne */
	public SquareTypeFour(int x, int y, int size) {
		super(x, y, size);
		S[0] = new Square(iniX, iniY, 14);
		S[1] = new Square(iniX, iniY - size, 14);
		S[2] = new Square(iniX + size, iniY, 14);
		S[3] = new Square(iniX + size * 2, iniY, 14);
	}

	public SquareTypeFour(int size) {
		super(size);
		S[0] = new Square(iniX, iniY, 14);
		S[1] = new Square(iniX, iniY - size, 14);
		S[2] = new Square(iniX + size, iniY, 14);
		S[3] = new Square(iniX + size * 2, iniY, 14);
	}

	public void shapOne() {
		iniX = S[0].getX();
		iniY = S[0].getY();
		S[1].setPosition(iniX, iniY - size);
		S[2].setPosition(iniX + size, iniY);
		S[3].setPosition(iniX + size * 2, iniY);
	}

	public void shapFour() {
		iniX = S[0].getX();
		iniY = S[0].getY();
		S[1].setPosition(iniX + size, iniY);
		S[2].setPosition(iniX, iniY + size);
		S[3].setPosition(iniX, iniY + size * 2);
	}

	public void shapThree() {
		iniX = S[0].getX();
		iniY = S[0].getY();
		S[1].setPosition(iniX, iniY + size);
		S[2].setPosition(iniX - size, iniY);
		S[3].setPosition(iniX - size * 2, iniY);
	}

	public void shapTwo() {
		iniX = S[0].getX();
		iniY = S[0].getY();
		S[1].setPosition(iniX - size, iniY);
		S[2].setPosition(iniX, iniY - size);
		S[3].setPosition(iniX, iniY - size * 2);
	}

	public void getShap(int shap) {
		switch (shap) {
		case 1:
			shapOne();
			currentShap = 1;
			nextShap = 2;
			break;
		case 2:
			shapTwo();
			currentShap = 2;
			nextShap = 3;
			break;
		case 3:
			shapThree();
			currentShap = 3;
			nextShap = 4;
			break;
		case 4:
			shapFour();
			currentShap = 4;
			nextShap = 1;
			break;
		}
	}
}
