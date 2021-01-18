package com.eduardo.jogodavelha;

public class GameBot {

	GameBoard board;
	int selectedPosition;

	/*
	 * int line1[] = { board.board[0], board.board[1], board.board[2] }; int line2[]
	 * = { board.board[3], board.board[4], board.board[5] }; int line3[] = {
	 * board.board[6], board.board[7], board.board[8] }; int column1[] = {
	 * board.board[0], board.board[3], board.board[6] }; int column2[] = {
	 * board.board[1], board.board[4], board.board[7] }; int column3[] = {
	 * board.board[2], board.board[5], board.board[8] }; int diagonal1[] = {
	 * board.board[0], board.board[4], board.board[8] }; int diagonal2[] = {
	 * board.board[2], board.board[4], board.board[6] };
	 */

	int[] corners = { 0, 2, 6, 8 };

	public GameBot(GameBoard board) {
		setBoard(board);
	}

	public void choosePosition() {

		if (GameCore.numOfPlays == 1) {
			if (board.board[4] == 0) {
				selectedPosition = 4;
			} else {
				for (int i : corners) {
					if (board.board[i] == 0) {

					}
				}

			}

		}

	}


	public void setBoard(GameBoard board) {
		this.board = board;
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}
	
	

}
