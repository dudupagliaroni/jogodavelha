package com.eduardo.jogodavelha;

import java.util.Scanner;

public class GameCore {

	static int numOfPlays = 0;
	boolean isGameOver = false;
	boolean isValid = false;
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
			System.out.println("Jogada número " + (numOfPlays+1));
			currentPlayer();
			choosePosition();
			board.printBoard();
			checkWinner();

			if (numOfPlays == 9) {
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

	public void currentPlayer() {// usar ENUM
		if (currentPlayer == 1) {
			currentPlayer = 2;
		} else {
			currentPlayer = 1;
		}
		System.out.println("É a vez do jogador " + currentPlayer);
		updateChar();
	}

	public void checkWinner() {
		// linha 1
		switch (board.board[0] + board.board[1] + board.board[2]) {
		case 3:
			System.out.println("Jogador 1 ganhou");
			isGameOver = true;
			break;
		case -3:
			System.out.println("Jogador 2 ganhou");
			isGameOver = true;
			break;
		}

		// linha 2
		switch (board.board[3] + board.board[4] + board.board[5]) {
		case 3:
			System.out.println("Jogador 1 ganhou");
			isGameOver = true;
			break;
		case -3:
			System.out.println("Jogador 2 ganhou");
			isGameOver = true;
			break;
		}

		// linha 3
		switch (board.board[6] + board.board[7] + board.board[8]) {
		case 3:
			System.out.println("Jogador 1 ganhou");
			isGameOver = true;
			break;
		case -3:
			System.out.println("Jogador 2 ganhou");
			isGameOver = true;
			break;
		}

		// coluna 1
		switch (board.board[0] + board.board[3] + board.board[6]) {
		case 3:
			System.out.println("Jogador 1 ganhou");
			isGameOver = true;
			break;
		case -3:
			System.out.println("Jogador 2 ganhou");
			isGameOver = true;
			break;
		}

		// coluna 2
		switch (board.board[1] + board.board[4] + board.board[7]) {
		case 3:
			System.out.println("Jogador 1 ganhou");
			isGameOver = true;
			break;
		case -3:
			System.out.println("Jogador 2 ganhou");
			isGameOver = true;
			break;
		}

		// coluna 3
		switch (board.board[2] + board.board[5] + board.board[8]) {
		case 3:
			System.out.println("Jogador 1 ganhou");
			isGameOver = true;
			break;
		case -3:
			System.out.println("Jogador 2 ganhou");
			isGameOver = true;
			break;
		}

		// diagonal 1
		switch (board.board[0] + board.board[4] + board.board[8]) {
		case 3:
			System.out.println("Jogador 1 ganhou");
			isGameOver = true;
			break;
		case -3:
			System.out.println("Jogador 2 ganhou");
			isGameOver = true;
			break;
		}

		// diagonal 2
		switch (board.board[2] + board.board[4] + board.board[6]) {
		case 3:
			System.out.println("Jogador 1 ganhou");
			isGameOver = true;
			break;
		case -3:
			System.out.println("Jogador 2 ganhou");
			isGameOver = true;
			break;
		}

	}

}
