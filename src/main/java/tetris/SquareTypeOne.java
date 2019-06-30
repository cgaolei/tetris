package tetris;


/**
 * 
 * @author Lei Gao
 */
public class SquareTypeOne extends SquareObject {

	/** Creates a new instance of SquareTypeOne */
	public SquareTypeOne(int x, int y, int size) {
		super(x, y, size);
		S[0] = new Square(iniX, iniY, 11);
		S[1] = new Square(iniX - size, iniY, 11);
		S[2] = new Square(iniX + size, iniY, 11);
		S[3] = new Square(iniX + size * 2, iniY, 11);
	}

	public SquareTypeOne(int size) {
		super(size);
		S[0] = new Square(iniX, iniY, 11);
		S[1] = new Square(iniX - size, iniY, 11);
		S[2] = new Square(iniX + size, iniY, 11);
		S[3] = new Square(iniX + size * 2, iniY, 11);
	}

	public void shapOne() {
		iniX = S[0].getX();
		iniY = S[0].getY();
		S[1].setPosition(iniX - size, iniY);
		S[2].setPosition(iniX + size, iniY);
		S[3].setPosition(iniX + size * 2, iniY);
	}

	public void shapTwo() {
		iniX = S[0].getX();
		iniY = S[0].getY();
		S[1].setPosition(iniX, iniY - size);
		S[2].setPosition(iniX, iniY + size);
		S[3].setPosition(iniX, iniY + size * 2);
	}

	public void getShap(int shap) {
		switch (shap) {
		case 1:
			shapOne();
			nextShap = 2;
			break;
		case 2:
			shapTwo();
			nextShap = 1;
			break;
		}
	}

}
