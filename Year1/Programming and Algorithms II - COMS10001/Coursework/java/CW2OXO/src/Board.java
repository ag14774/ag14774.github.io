import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {

	private Player[][] board;
	private Player curr;

	public static final int rows = 10, cols = 10, inARow = 10;

	@SuppressWarnings("unused")
	Board() {
		try {
			if (inARow > rows && inARow > cols)
				throw new NumberFormatException("Invalid inARow value!");
			if ((rows >= 3 && cols > 0) | (rows > 0 && cols >= 3)) {
				board = new Player[rows][cols];
				for (int r = 0; r < rows; r++) {
					for (int c = 0; c < cols; c++) {
						board[r][c] = Player.None;
					}
				}
			} else
				throw new NumberFormatException("Invalid Grid Size!!!");
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		curr = Player.X;
	}

	private boolean checkIfLetter(char c) {
		if ((c <= 'z' && c >= 'a') || (c >= 'A' && c <= 'Z'))
			return true;
		else
			return false;
	}

	// Only works for 3x3. ***Temporary***
	Position position(String pos) {
		char ch;
		int col = 0;
		int row = 0;
		int index = 0;
		if (pos == null || pos.length() == 0)
			return null;
		pos = pos.toLowerCase();
		try {
			for (index = 0; checkIfLetter(ch = pos.charAt(index)); index++) {
				row = row * 26 + (ch - 'a' + 1);
			}
			row--;
			col = Integer.parseInt(pos.substring(index)) - 1;
			if (board[row][col] != Player.None)
				return null;
		} catch (Exception e) {
			return null;
		}
		return new Position(row, col);
	}

	@Override
	public String toString() {
		String s = "";
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				switch (board[r][c]) {
				case None:
					s += ". ";
					break;
				case O:
					s += "O ";
					break;
				case X:
					s += "X ";
					break;
				default:
					break;
				}
			}
			s += "\n";
		}
		return s;
	}

	void move(Position position) {
		int r = position.row();
		int c = position.col();
		if (board[r][c] != Player.None)
			throw new Error("Position not empty!");
		else {
			board[r][c] = curr;
			curr = curr.other();
		}
	}

	Position[] blanks() {
		ArrayList<Position> nones = new ArrayList<Position>();
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (board[r][c] == Player.None) {
					nones.add(new Position(r, c));
				}
			}
		}
		Position[] result = new Position[nones.size()];
		result = nones.toArray(result);
		return result;
	}

	private Player checkHorizontal(int r, int c) {
		Player tempPlayer = board[r][c];
		for (int i = 1; i < inARow; i++) {
			if (tempPlayer != board[r][c + i]) {
				return Player.None;
			}
		}
		return tempPlayer;
	}

	private Player checkVertical(int r, int c) {
		Player tempPlayer = board[r][c];
		for (int i = 1; i < inARow; i++) {
			if (tempPlayer != board[r + i][c]) {
				return Player.None;
			}
		}
		return tempPlayer;
	}

	private Player checkDiagonal(int r, int c) {
		Player tempPlayer = board[r][c];
		for (int i = 1; i < inARow; i++) {
			if (tempPlayer != board[r + i][c + i]) {
				return Player.None;
			}
		}
		return tempPlayer;
	}

	private Player checkDiagonalLeft(int r, int c) {
		Player tempPlayer = board[r][c];
		for (int i = 1; i < inARow; i++) {
			if (tempPlayer != board[r + i][c - i]) {
				return Player.None;
			}
		}
		return tempPlayer;
	}

	Player winner() {
		Player result = Player.None;
		boolean blankFlag = false;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (board[r][c] == Player.None)
					blankFlag = true;
				// SPACE ON THE RIGHT --- SPACE BELOW --- SPACE LEFT
				if (((cols - c) >= inARow) && ((rows - r) >= inARow) && ((c + 1) >= inARow)) {
					if (result == Player.None)
						result = checkHorizontal(r, c);
					if (result == Player.None)
						result = checkVertical(r, c);
					if (result == Player.None)
						result = checkDiagonal(r, c);
					if (result == Player.None)
						result = checkDiagonalLeft(r, c);
					if (result != Player.None)
						return result;
				} else if (((cols - c) >= inARow) && ((rows - r) >= inARow) && ((c + 1) < inARow)) {
					if (result == Player.None)
						result = checkHorizontal(r, c);
					if (result == Player.None)
						result = checkVertical(r, c);
					if (result == Player.None)
						result = checkDiagonal(r, c);
					if (result != Player.None)
						return result;
				} else if (((cols - c) >= inARow) && ((rows - r) < inARow) && ((c + 1) >= inARow)) {
					if (result == Player.None)
						result = checkHorizontal(r, c);
					if (result != Player.None)
						return result;
				} else if (((cols - c) < inARow) && ((rows - r) >= inARow) && ((c + 1) >= inARow)) {
					if (result == Player.None)
						result = checkVertical(r, c);
					if (result == Player.None)
						result = checkDiagonalLeft(r, c);
					if (result != Player.None)
						return result;
				} else if (((cols - c) >= inARow) && ((rows - r) < inARow) && ((c + 1) < inARow)) {
					if (result == Player.None)
						result = checkHorizontal(r, c);
					if (result != Player.None)
						return result;
				}else if (((cols - c) < inARow) && ((rows - r) >= inARow) && ((c + 1) < inARow)) {
					if (result == Player.None)
						result = checkVertical(r, c);
					if (result != Player.None)
						return result;
				}

			}
		}
		if (blankFlag)
			return Player.None;
		else
			return Player.Both;
	}

	void undo(Position pos) {
		int r = pos.row();
		int c = pos.col();
		board[r][c] = Player.None;
		curr = curr.other();
	}

	int add(int a, int b) {
		return a + b;
	}

	Position suggest() {
		Player tempWinner;
		Player computer = curr;
		ArrayList<Position> currBlanks = new ArrayList<Position>(Arrays.asList(blanks()));
		ArrayList<Position> possibleSuggestions = new ArrayList<Position>();
		for (Position temp : currBlanks)
			possibleSuggestions.add(temp);
		for (Position compMove : currBlanks) {
			move(compMove);
			tempWinner = winner();
			Position[] newBlanks = blanks();
			if (tempWinner == computer || tempWinner == Player.Both) {
				undo(compMove);
				return compMove;
			}
			for (Position humanMove : newBlanks) {
				move(humanMove);
				tempWinner = winner();
				undo(humanMove);
				if (tempWinner == computer.other()) {
					possibleSuggestions.remove(compMove);
				}
			}
			undo(compMove);
		}
		if (possibleSuggestions.size() != 0) {
			Random generator = new Random();
			int index = generator.nextInt(possibleSuggestions.size());
			return possibleSuggestions.get(index);
		} else {
			Random generator = new Random();
			int index = generator.nextInt(currBlanks.size());
			return currBlanks.get(index);
		}
	}

	public static void main(String[] args) {
		Board testBoard = new Board();
		testBoard.testPosition("b2");
		testBoard.testToString();
		testBoard.testBlanks();
	}

	void testPosition(String pos) {
		Position testPos = position(pos);
		System.out.println("Row: " + testPos.row() + " Col: " + testPos.col());
	}

	void testToString() {
		System.out.println(toString());
	}

	void testBlanks() {
		Position[] tempArray = blanks();
		for (Position temp : tempArray)
			System.out.println("Row: " + temp.row() + " Col: " + temp.col());
	}

}
