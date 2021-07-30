package com.eduardo.jogodavelha;

import java.util.Random;

public class GameBot {

	private int botSelectedPosition = 0;

	public int botChoosePosition(GameBoard board) {
		
		botSelectedPosition = chooseRandomPosition(board.getAllPositions(),board);
		botSelectedPosition = positionIfLineWasStarted(board);
		botSelectedPosition = positionIfLineIsLoosing(board);
		botSelectedPosition = positionIfLineIsWinning(board);

		return botSelectedPosition;
	}

	public int positionIfLineWasStarted(GameBoard board) {
		for (int[] lineStarted : board.getAllLines()) { // check se a linha foi começada
			if (checkIfLineStarted(lineStarted, board)) {
				botSelectedPosition = chooseRandomPosition(lineStarted, board);
				break;
			}
		}
		return botSelectedPosition;
	}

	public boolean checkIfLineStarted(int[] line, GameBoard board) {
		boolean isLineStarted = false;
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
		return isLineStarted;
	}

	public int positionIfLineIsLoosing(GameBoard board) {
		for (int[] lineLoosing : board.getAllLines()) { // check se a linha está perdendo
			if (checkIfLineisLosing(lineLoosing, board)) {
				botSelectedPosition = chooseRandomPosition(lineLoosing, board);
				break;
			}
		}
		return botSelectedPosition;
	}

	public boolean checkIfLineisLosing(int[] line, GameBoard board) {
		boolean isLineLosing = false;
		boolean isLineSafe = false;
		for (int i = 0; i < line.length; i++) {
			int index = line[i];
			if (board.getBoard()[index] == -1) {
				isLineSafe = true;
				break;
			}
		}

		if (!isLineSafe) {
			int numOfMarks = 0;
			for (int i = 0; i < line.length; i++) {
				int index = line[i];
				if (board.getBoard()[index] == 1) {
					numOfMarks++;
				}
			}
			if (numOfMarks == 2) {
				isLineLosing = true;
			}
		}
		return isLineLosing;
	}

	public int positionIfLineIsWinning(GameBoard board) {
		for (int[] lineWinning : board.getAllLines()) { // check se a linha esta ganhando
			if (checkIfLineisWinning(lineWinning, board)) {
				botSelectedPosition = chooseRandomPosition(lineWinning, board);
				break;
			}
		}
		return botSelectedPosition;
	}

	public boolean checkIfLineisWinning(int[] line, GameBoard board) {
		boolean isLineWinning = false;
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
		if (numOfMarksBot == 2 && numOfMarksPlayer == 0) {
			isLineWinning = true;
		}
		return isLineWinning;
	}

	public int chooseRandomPosition(int[] line, GameBoard board) {
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
