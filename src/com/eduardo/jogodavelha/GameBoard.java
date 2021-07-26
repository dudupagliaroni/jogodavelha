package com.eduardo.jogodavelha;

public class GameBoard {

	private int board[] = new int[9];

	private int[] row1 = { 0, 1, 2 };
	private int[] row2 = { 3, 4, 5 };
	private int[] row3 = { 6, 7, 8 };
	private int[] column1 = { 0, 3, 6 };
	private int[] column2 = { 1, 4, 7 };
	private int[] column3 = { 2, 5, 8 };
	private int[] diagonal1 = { 0, 4, 8 };
	private int[] diagonal2 = { 2, 4, 6 };
	private int[] boardCornersAndCenter = { 0, 2, 4, 6, 8 };
	private int[] allPositions = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	private int[] allLines[] = { row1, row2, row3, column1, column2, column3, diagonal1, diagonal2 };

	public void printBoard() {
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				switch (board[k]) {
				case 0:
					System.out.print(" _ ");
					break;
				case 1:
					System.out.print(" X ");
					break;
				case -1:
					System.out.print(" O ");
					break;
				}
				k++;
			}
			System.out.println();
			System.out.println("---------");
		}
		System.out.println();
	}

	public void setBoardPosition(int position, int playerChosenMark) {
		this.board[position] = playerChosenMark;
	}

	public boolean isBoardPositionEmpty(int position) {
		if (this.board[position] == 0) {
			return true;
		} else {
			System.out.println("Lugar já ocupado, escolha outra posição!");
			return false;
		}
	}

	public void resetBoard() {
		for (int i = 0; i < board.length; i++) {
			board[i] = 0;
		}
	}

	public int[] getBoard() {
		return board;
	}

	public int[] getBoardCornersAndCenter() {
		return boardCornersAndCenter;
	}

	public int[][] getAllLines() {
		return this.allLines;
	}

	public int[] getAllPositions() {
		return allPositions;
	}

	public void setBoard(int[] board) {
		this.board = board;
	}

}
