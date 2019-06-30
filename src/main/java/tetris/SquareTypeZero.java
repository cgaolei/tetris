package tetris;

/**
 * 
 * @author Lei Gao
 */
public class SquareTypeZero extends SquareObject {

	/** Creates a new instance of SquareTypeZero */

	public SquareTypeZero(int x, int y, int size) {
		super(x, y, size);
		S[0] = new Square(iniX, iniY, 10);
		S[1] = new Square(iniX + size, iniY, 10);
		S[2] = new Square(iniX, iniY + size, 10);
		S[3] = new Square(iniX + size, iniY + size, 10);
	}

	public SquareTypeZero(int size) {
		super(size);
		S[0] = new Square(iniX, iniY, 10);
		S[1] = new Square(iniX + size, iniY, 10);
		S[2] = new Square(iniX, iniY + size, 10);
		S[3] = new Square(iniX + size, iniY + size, 10);
	}

	public void getShap(int shap) {
	}
}
