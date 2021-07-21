package com.eduardo.jogodavelha;

public class GameBoard {

	int board[] = new int[9];

	public GameBoard() {
	}

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

	public void setPosition(int position, int mark) {
		this.board[position] = mark;
	}

	public boolean isPositionEmpty(int position) {
		if (this.board[position] == 0) {
			return true;
		} else {
			System.out.println("Lugar já ocupado, escolha outra posição!");
			return false;
		}
	}

	public void resetBoard() {
		for (int i = 0; i < board.length; i ++) {
			board[i] =0;
		}
	}
	
}
