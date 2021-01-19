package com.eduardo.jogodavelha;

import java.util.Random;

public class GameBot {

	GameBoard board;
	int selectedPosition;

//	int row1[] = { board.board[0], board.board[1], board.board[2] };
//	int row2[] = { board.board[3], board.board[4], board.board[5] };
//	int row3[] = { board.board[6], board.board[7], board.board[8] };
//	int column1[] = { board.board[0], board.board[3], board.board[6] };
//	int column2[] = { board.board[1], board.board[4], board.board[7] };
//	int column3[] = { board.board[2], board.board[5], board.board[8] };
//	int diagonal1[] = { board.board[0], board.board[4], board.board[8] };
//	int diagonal2[] = { board.board[2], board.board[4], board.board[6] };

	int[] corners = { 0, 2, 6, 8 };

	public GameBot(GameBoard board) {
		setBoard(board);
	}

	public void choosePosition() {

		if (board.board[4] == 0) {
			selectedPosition = 4;
		} else {
			if (board.board[0] != 0 && board.board[2] != 0 && board.board[6] != 0 && board.board[8] != 0) {

			} else {
				selectedPosition = getRandomCorner(corners);
			}
		}
	}

	public int getRandomCorner(int[] array) {
		int rnd = 0;
		int index = 0;
		boolean isEmpty = false;
		while (!isEmpty) {
			rnd = new Random().nextInt(array.length);
			index = corners[rnd];
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
