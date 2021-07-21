package com.eduardo.jogodavelha;

import java.util.Random;
import java.util.Scanner;

public class GameCore {

	static int numOfPlays = 1;
	boolean isGameOver = false;
	boolean hasWinner = false;
	boolean PVP = false;
	int selectedPosition;
	int currentPlayer = 1;
	int mark = 1;

	GameBoard board = new GameBoard();
	GameBot bot;
	Scanner scan = new Scanner(System.in);

	public GameCore() {
		bot = new GameBot(board);
		board.printBoard();
		setPVP();
		mainLoop();
		continuePlaying();
	}

	public void mainLoop() {
		while (!isGameOver) {
			System.out.println("Jogada número " + (numOfPlays));
			if (numOfPlays == 1) {
				currentPlayer = randomPlayerStart();
				System.out.println("O jogador " + currentPlayer + " começa!");
				if (currentPlayer == 1) {
					mark = 1;
				} else {
					mark = -1;
				}
			} else {
				changePlayer();
			}
			choosePosition();
			board.printBoard();
			checkWinner();


			if (numOfPlays == 10 && hasWinner == false) {
				isGameOver = true;
				System.out.println("Fim de jogo! Empate!!!");
			}

			if (isGameOver == true) {
				continuePlaying();
			}

		}

	}

	public void continuePlaying() {
		System.out.println("Deseja jogar novamente? 1-Sim/2-Não");
		int option = scan.nextInt();

		if (option == 1) {
			isGameOver = false;
			numOfPlays = 1;
			board.resetBoard();
			System.out.println("Novo jogo iniciado!");
		} else {
			System.out.println("Fim de Jogo!");
			System.exit(0);
		}
	}

	public void numberOfPlayers() {

	}

	public int randomPlayerStart() {
		int[] players = { 1, 2 };
		int rnd = new Random().nextInt(players.length);
		return players[rnd];

	}

	public boolean setPVP() {
		System.out.println("Jogar 1x1 ou contra um bot? 1 - PVP / 2 - Bot");
		int option = scan.nextInt();
		if (option == 1) {
			return PVP = true;
		} else {
			return PVP = false;
		}
	}

	public void choosePosition() {
		if (PVP == true) {
			humanMove();

		} else if (currentPlayer == 1 && PVP == false) {
			humanMove();
		} else if (currentPlayer == 2 && PVP == false) {
			botMove();
		}

		setPosition();

	}

	public void botMove() {
		selectedPosition = bot.getSelectedPosition();
		System.out.println("O bot escolheu a posição " + (selectedPosition + 1));
	}

	public void humanMove() {

		boolean isEmpty = false;

		while (!isEmpty) {

			System.out.println("Escolha a posição (1-9): ");
			selectedPosition = scan.nextInt();

			while (selectedPosition < 1 || selectedPosition > 9) {
				System.out.println("Posição inválida!");
				System.out.println("Escolha a posição entre 1 e 9: ");
				selectedPosition = scan.nextInt();
			}

			isEmpty = board.isPositionEmpty(selectedPosition - 1);

		}

		selectedPosition = selectedPosition - 1;
		System.out.println("O jogador " + currentPlayer + " escolheu a posição " + (selectedPosition + 1));

	}

	public void setPosition() {
		board.setPosition(selectedPosition, mark);
		numOfPlays++;
	}

	public void changePlayer() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
			mark = -1;
		} else {
			currentPlayer = 1;
			mark = 1;
		}
		System.out.println("É a vez do jogador " + currentPlayer);

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
				System.out.println("Fim de Jogo! O Bot venceu fazendo a linha " + line + "\n");
				isGameOver = true;
				hasWinner = true;
				break;
			}

			if (numOfMarksPlayer == 3) {
				System.out.println("Fim de Jogo! O Jogador 1 venceu fazendo a linha " + line + "\n");
				isGameOver = true;
				hasWinner = true;
				break;
			}

		}

	}
}
