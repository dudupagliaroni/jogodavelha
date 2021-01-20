package com.eduardo.jogodavelha;

import java.util.ArrayList;
import java.util.Random;

public class GameBot {

	GameBoard board = new GameBoard();

	boolean isLosing;
	boolean isWinning;

	int selectedPosition;

	ArrayList<int[]> winningLines = new ArrayList<int[]>();
	ArrayList<int[]> losingLines = new ArrayList<int[]>();
	int[] allPositions = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	int[] row1 = { 0, 1, 2 };
	int[] row2 = { 3, 4, 5 };
	int[] row3 = { 6, 7, 8 };
	int[] column1 = { 0, 3, 6 };
	int[] column2 = { 1, 4, 7 };
	int[] column3 = { 2, 5, 8 };
	int[] diagonal1 = { 0, 4, 8 };
	int[] diagonal2 = { 2, 4, 6 };
	int[] corners = { 0, 2, 6, 8 };

	int[] allLines[] = { row1, row2, row3, column1, column2, column3, diagonal1, diagonal2 };

	public GameBot(GameBoard board) {
		setBoard(board);
	}

	public void choosePosition() {

		for (int[] line : allLines) {
			isLosing(line);
			if (isLosing == true) {
				selectedPosition = chooseRandomIndex(line);
				isLosing = false;
				break;
			}
		}

		for (int[] line : allLines) {
			isWinning(line);
			if (isLosing == true) {
				selectedPosition = chooseRandomIndex(line);
				break;
			}
		}

		if (board.board[4] == 0) {
			selectedPosition = 4;
		}
		if (board.board[0] != 0 || board.board[2] != 0 || board.board[6] != 0 || board.board[8] != 0) {
			selectedPosition = chooseRandomIndex(corners);
		} else {
			selectedPosition = chooseRandomIndex(allPositions);
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
		boolean isSafe = false;
		for (int i = 0; i < line.length; i++) {
			int index = line[i];
			if (board.board[index] == 1) {
				isSafe = true;
				break;
			}
		}

		if (!isSafe) {
			int numOfMarks = 0;
			for (int j = 0; j < line.length; j++) {
				int index = line[j];
				if (board.board[index] == -1) {
					numOfMarks++;
				}
			}
			if (numOfMarks == 2) {
				isWinning = true;
			}
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
