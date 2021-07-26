package com.eduardo.jogodavelha;

import java.util.Random;

public class GameBot {

	private boolean isLineLosing;
	private boolean isLineWinning;
	private boolean isBoardPositionEmpty;
	private boolean isLineStarted;
	private int botSelectedPosition;

	public int botChoosePosition(GameBoard board) {

		while (!isBoardPositionEmpty) {

			int botSelectedPosition;

			if (GameCore.getNumOfMoves() == 1 || GameCore.getNumOfMoves() == 2) {

				botSelectedPosition = chooseRandomIndex(board.getBoardCornersAndCenter(), board);
				if (board.getBoard()[botSelectedPosition] == 0) {
					isBoardPositionEmpty = true;
				}

			} else {

				botSelectedPosition = checkGameConditions(board);

			}
		}
		isBoardPositionEmpty = false;
		return botSelectedPosition;
	}

	public int checkGameConditions(GameBoard board) {

		for (int[] lineStarted : board.getAllLines()) { // check se a linha foi começada
			isLineStarted = false;
			checkIfLineStarted(lineStarted, board);
			if (isLineStarted == true) {
				botSelectedPosition = chooseRandomIndex(lineStarted, board);
				break;
			}
		}

		for (int[] lineLoosing : board.getAllLines()) { // check se a linha está perdendo
			isLineLosing = false;
			checkIfLineisLosing(lineLoosing, board);
			if (isLineLosing == true) {
				botSelectedPosition = chooseRandomIndex(lineLoosing, board);
				break;
			}
		}

		for (int[] lineWinning : board.getAllLines()) { // check se a linha esta ganhando
			isLineWinning = false;
			checkIfLineisWinning(lineWinning, board);
			if (isLineWinning == true) {
				botSelectedPosition = chooseRandomIndex(lineWinning, board);
				break;
			}
		}
		if (board.getBoard()[botSelectedPosition] == 0) {
			isBoardPositionEmpty = true;
			return botSelectedPosition;
		}
		return botSelectedPosition;
	}

	public void checkIfLineStarted(int[] line, GameBoard board) {
		int numOfMarksBot = 0;
		int numOfMarksPlayer = 0;
		for (int i = 0; i < line.length; i++) {
			int index = line[i];
			if (board.getBoard()[index] == -1) {
				numOfMarksBot++;
			}
			if (board.getBoard()[index] == 1) {
				numOfMarksPlayer++;
			}
		}
		if (numOfMarksBot == 1 && numOfMarksPlayer == 0) {
			isLineStarted = true;
		}
	}

	public void checkIfLineisLosing(int[] line, GameBoard board) {
		boolean isSafe = false;
		for (int i = 0; i < line.length; i++) {
			int index = line[i];
			if (board.getBoard()[index] == -1) {
				isSafe = true;
				break;
			}
		}

		if (!isSafe) {
			int numOfMarks = 0;
			for (int j = 0; j < line.length; j++) {
				int index = line[j];
				if (board.getBoard()[index] == 1) {
					numOfMarks++;
				}
			}
			if (numOfMarks == 2) {
				isLineLosing = true;
			}
		}
	}

	public void checkIfLineisWinning(int[] line, GameBoard board) {
		int numOfMarksBot = 0;
		int numOfMarksPlayer = 0;
		for (int j = 0; j < line.length; j++) {
			int index = line[j];
			if (board.getBoard()[index] == -1) {
				numOfMarksBot++;
			}
			if (board.getBoard()[index] == 1) {
				numOfMarksPlayer++;
			}
		}
		if (numOfMarksBot == 2 && numOfMarksPlayer == 0) {
			isLineWinning = true;
		}
	}

	public int chooseRandomIndex(int[] line, GameBoard board) {
		int rnd = 0;
		int index = 0;
		boolean isEmpty = false;
		while (!isEmpty) {
			rnd = new Random().nextInt(line.length);
			index = line[rnd];
			if (board.getBoard()[index] == 0) {
				isEmpty = true;
			}
		}
		return index;
	}

}
