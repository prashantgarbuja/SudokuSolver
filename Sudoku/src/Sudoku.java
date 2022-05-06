
public class Sudoku {
	
	//Size of the board.
	private static final int GRID_SIZE = 9;
	public static void main(String[] args) {

		//Sudoku board
		int[][] board = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0}
		};

		if(solver(board))
			printBoard(board);
		else
			System.out.println("Sorry! Counld not be solved.");
	}
	
	private static void printBoard(int[][] board) {
		for (int i=0; i<GRID_SIZE; i++) {
			if(i % 3 == 0 && i != 0) System.out.println("---------------");
			for (int j=0; j<GRID_SIZE; j++) {
				if(j % 3 == 0 && j != 0) System.out.print(" | ");
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		
	}

	private static boolean checkRow(int[][] board, int number, int row) {
		for (int i=0; i<GRID_SIZE; i++) {
			if(board[row][i] == number)
				return true; //return true if number is in row
		}
		return false;
	}

	private static boolean checkColumn(int[][] board, int number, int column) {
		for (int i=0; i<GRID_SIZE; i++) {
			if(board[i][column] == number)
				return true; //return true if number is in column
		}
		return false;
	}
	
	private static boolean checkLocalBox(int[][] board, int number, int row, int column) {
		int localFirstRow = row - row % 3;
		int localFirstColumn = column - column % 3;
		
		for(int i=localFirstRow; i < localFirstRow + 3; i++) {
			for(int j=localFirstColumn; j < localFirstColumn + 3; j++) {
				if(board[i][j] == number) return true; //return true if number is in local 3x3 box
			}
		}
		return false;
	}
	
	private static boolean isValid(int[][] board, int number, int row, int column) {
		return !checkRow(board, number, row) && 
				!checkColumn(board, number, column) && 
				!checkLocalBox(board, number, row, column);
		//if number is in either of the row or column or local box return false else true.
	}
	
	private static boolean solver(int[][] board) {
		for(int i=0; i<GRID_SIZE; i++) {
			for(int j=0; j<GRID_SIZE; j++) {
				if(board[i][j] == 0) {
					for(int number=1; number<=GRID_SIZE; number++) {  //Placing 1 to 9 in board with 0 value, and validating.
						if(isValid(board, number, i, j)) {
							board[i][j] = number;
							if(solver(board))
								return true;
							else
								board[i][j] = 0;
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	
}
