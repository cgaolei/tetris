package tetris;

/**
 * The representation of the main game area. 
 * This is a virtual representation, so it is invisiable
 * @author Lei Gao
 */
public class SquareMatrix {

	/** Creates a new instance of SquareMatrx */
	protected static int[][] matrix = new int[29][19];

	public static void initMatrix() {
		for (int row = 1; row <= 27; row++) {
			for (int col = 1; col <= 17; col++) {
				matrix[row][col] = 0;
			}
		}
		for (int row = 0; row < 29; row++) {
			matrix[row][0] = 1;
			matrix[row][18] = 1;
		}

		for (int col = 1; col <= 17; col++) {
			matrix[0][col] = 1;
			matrix[28][col] = 1;
		}
	}

	public static int getValue(int x, int y) {
		int row = (y / 20) + 1;
		int col = (x / 20) + 1;
		return matrix[row][col];
	}

	public static void setValue(int x, int y, int value) {
		int row = (y / 20) + 1;
		int col = (x / 20) + 1;
		matrix[row][col] = value;
	}

	public static boolean isFullRow(int row) {
		for (int col = 1; col < 18; col++) {
			if (matrix[row][col] == 0) {
				return false;
			}
		}
		return true;
	}

	public static void removeRow(int row) {
		while (!isEmptyRow(row - 1)) {
			rowReplace(row);
			row--;
		}
		emptyRow(row, 0);
	}

	public static void emptyRow(int row, int V) {
		for (int col = 1; col < 18; col++) {
			matrix[row][col] = V;
		}
	}

	private static void rowReplace(int row) {
		for (int col = 1; col < 18; col++) {
			matrix[row][col] = matrix[row - 1][col];
		}
	}

	public static boolean isEmptyRow(int row) {
		for (int col = 1; col < 18; col++) {
			if (matrix[row][col] != 0) {
				return false;
			}
		}
		return true;
	}
}
