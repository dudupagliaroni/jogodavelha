package com.eduardo.jogodavelha;

import java.util.Scanner;

public class GameCore {

	static int numOfPlays = 1;
	boolean isGameOver = false;
	boolean hasWinner = false;
	int selectedPosition;
	int currentPlayer = 1;
	int mark = 1;

	GameBoard board = new GameBoard();
	GameBot bot;
	Scanner scan = new Scanner(System.in);

	public GameCore() {
		bot = new GameBot(board);
		board.printBoard();
		mainLoop();
	}

	public void mainLoop() {
		while (!isGameOver) {
			System.out.println("Jogada número " + (numOfPlays));
			currentPlayer();
			choosePosition();
			board.printBoard();
			checkWinner();

			if (numOfPlays == 10 && hasWinner == false) {
				isGameOver = true;
				System.out.println("Fim de jogo! Empate!!!");
			}

		}
	}

	public void choosePosition() {
		if (currentPlayer == 1) {
			boolean isEmpty = false;

			while (!isEmpty) {
				System.out.println("Escolha a posição (1-9): ");
				selectedPosition = scan.nextInt();

				while (selectedPosition < 1 || selectedPosition > 9) {
					System.out.println("Posição inválida!");
					System.out.println("Escolha a posição (1-9): ");
					selectedPosition = scan.nextInt();
				}
				isEmpty = board.isEmpty(selectedPosition - 1);
			}
			selectedPosition = selectedPosition - 1;
			System.out.println("O jogador 1 escolheu a posição " + (selectedPosition + 1));
		}
		if (currentPlayer == 2) {
			selectedPosition = bot.getSelectedPosition();
			System.out.println("O bot escolheu a posição " + (selectedPosition + 1));
		}

		setPosition();
	}

	public void setPosition() {
		board.setPosition(selectedPosition, mark);
		numOfPlays++;
	}

	public void updateChar() {
		if (currentPlayer == 1) {
			mark = 1;
		} else {
			mark = -1;
		}
	}

	public void currentPlayer() {
		if (numOfPlays % 2 == 1) {
			currentPlayer = 1;
		} else {
			currentPlayer = 2;
		}
		System.out.println("É a vez do jogador " + currentPlayer);
		updateChar();
	}

	public void checkWinner() {

		int[] row1 = { 0, 1, 2 };
		int[] row2 = { 3, 4, 5 };
		int[] row3 = { 6, 7, 8 };
		int[] column1 = { 0, 3, 6 };
		int[] column2 = { 1, 4, 7 };
		int[] column3 = { 2, 5, 8 };
		int[] diagonal1 = { 0, 4, 8 };
		int[] diagonal2 = { 2, 4, 6 };
		int[] allLines[] = { row1, row2, row3, column1, column2, column3, diagonal1, diagonal2 };

		for (int[] line : allLines) {
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
			if (numOfMarksBot == 3) {
				System.out.println("Fim de Jogo! O Bot venceu!");
				isGameOver = true;
				hasWinner = true;
				break;
			}

			if (numOfMarksPlayer == 3) {
				System.out.println("Fim de Jogo! O Jogador 1 venceu");
				isGameOver = true;
				hasWinner = true;
				break;
			}
		}

	}
}
