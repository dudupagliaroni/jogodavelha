package com.eduardo.jogodavelha;

import java.util.Random;

public class GameBot {

	GameBoard board = new GameBoard();

	boolean isLosing;
	boolean isWinning;
	boolean isEmpty;
	boolean wasLineStarted;


	int selectedPosition;


	int[] allPositions = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	int[] row1 = { 0, 1, 2 };
	int[] row2 = { 3, 4, 5 };
	int[] row3 = { 6, 7, 8 };
	int[] column1 = { 0, 3, 6 };
	int[] column2 = { 1, 4, 7 };
	int[] column3 = { 2, 5, 8 };
	int[] diagonal1 = { 0, 4, 8 };
	int[] diagonal2 = { 2, 4, 6 };
	int[] corners = { 0, 2, 4, 6, 8 };

	int[] allLines[] = { row1, row2, row3, column1, column2, column3, diagonal1, diagonal2 };

	public GameBot(GameBoard board) {
		setBoard(board);
	}

	public void choosePosition() {

		while (!isEmpty) {

			selectedPosition = chooseRandomIndex(allPositions);

			while (GameCore.numOfPlays == 1 || GameCore.numOfPlays == 2) {

				selectedPosition = chooseRandomIndex(corners);
				if (board.board[selectedPosition] == 0) {
					isEmpty = true;
					break;
				}

			}

			for (int[] line1 : allLines) {
				wasLineStarted = false;
				wasLineStarted(line1);
				if (wasLineStarted == true) {
					selectedPosition = chooseRandomIndex(line1);
					break;
				}
			}

			for (int[] line3 : allLines) {
				isLosing = false;
				isLosing(line3);
				if (isLosing == true) {
					selectedPosition = chooseRandomIndex(line3);
					isLosing = false;

				}
			}

			for (int[] line2 : allLines) {
				isWinning = false;
				isWinning(line2);
				if (isWinning == true) {
					selectedPosition = chooseRandomIndex(line2);
					break;
				}
			}
			if (board.board[selectedPosition] == 0) {
				isEmpty = true;
			}
		}
		isEmpty = false;
	}

	public void wasLineStarted(int[] line) {
		int numOfMarksBot = 0;
		int numOfMarksPlayer = 0;
		for (int i = 0; i < line.length; i++) {
			int index = line[i];
			if (board.board[index] == -1) {
				numOfMarksBot++;
			}
			if (board.board[index] == 1) {
				numOfMarksPlayer++;
			}
		}
		if (numOfMarksBot == 1 && numOfMarksPlayer == 0) {
			wasLineStarted = true;
		}
	}

	public void isLosing(int[] line) {
		boolean isSafe = false;
		for (int i = 0; i < line.length; i++) {
			int index = line[i];
			if (board.board[index] == -1) {
				isSafe = true;
				break;
			}
		}

		if (!isSafe) {
			int numOfMarks = 0;
			for (int j = 0; j < line.length; j++) {
				int index = line[j];
				if (board.board[index] == 1) {
					numOfMarks++;
				}
			}
			if (numOfMarks == 2) {
				isLosing = true;
			}
		}
	}

	public void isWinning(int[] line) {
		int numOfMarksBot = 0;
		int numOfMarksPlayer = 0;
		for (int j = 0; j < line.length; j++) {
			int index = line[j];
			if (board.board[index] == -1) {
				numOfMarksBot++;
			}
			if (board.board[index] == 1) {
				numOfMarksPlayer++;
			}
		}
		if (numOfMarksBot == 2 && numOfMarksPlayer == 0) {
			isWinning = true;
		}
	}

	public int chooseRandomIndex(int[] line) {
		int rnd = 0;
		int index = 0;
		boolean isEmpty = false;
		while (!isEmpty) {
			rnd = new Random().nextInt(line.length);
			index = line[rnd];
			if (board.board[index] == 0) {
				isEmpty = true;
			}
		}
		return index;
	}

	public void setBoard(GameBoard board) {
		this.board = board;
	}

	public int getSelectedPosition() {
		choosePosition();
		return selectedPosition;
	}

	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

}
