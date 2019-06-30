package tetris;

/**
 * 
 * @author Lei Gao
 */
public class SquareTypeTwo extends SquareObject {

	/** Creates a new instance of SquareTypeOne */
	public SquareTypeTwo(int x, int y, int size) {
		super(x, y, size);
		S[0] = new Square(iniX, iniY, 12);
		S[1] = new Square(iniX - size, iniY, 12);
		S[2] = new Square(iniX, iniY + size, 12);
		S[3] = new Square(iniX + size, iniY + size, 12);
	}

	public SquareTypeTwo(int size) {
		super(size);
		S[0] = new Square(iniX, iniY, 12);
		S[1] = new Square(iniX - size, iniY, 12);
		S[2] = new Square(iniX, iniY + size, 12);
		S[3] = new Square(iniX + size, iniY + size, 12);
	}

	public void shapOne() {
		iniX = S[0].getX();
		iniY = S[0].getY();
		S[1].setPosition(iniX - size, iniY);
		S[2].setPosition(iniX, iniY + size);
		S[3].setPosition(iniX + size, iniY + size);
	}

	public void shapTwo() {
		iniX = S[0].getX();
		iniY = S[0].getY();
		S[1].setPosition(iniX, iniY + size);
		S[2].setPosition(iniX + size, iniY);
		S[3].setPosition(iniX + size, iniY - size);
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
			nextShap = 1;
			break;
		}
	}
}
