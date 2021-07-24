package com.eduardo.jogodavelha;

import java.util.Random;
import java.util.Scanner;

public class GameCore {

	private int numOfPlays = 1;
	private boolean isGameOver = false;
	private boolean hasWinner = false;
	private boolean PVP = false;
	private int userSelectedPosition;
	private int currentPlayer = 1;
	private int playerBoardMark = 1; // são 3 estados - como consertar isso?
	private int pointsPlayer1;
	private int pointsPlayer2;

	GameBoard board = new GameBoard();
	GameBot bot;
	Scanner scan = new Scanner(System.in);

	public GameCore() {
		bot = new GameBot(board);
		board.printBoard();
		setPVP();
		mainLoop(); // criar game start fora do construtor
		continuePlaying();
	}

	public void mainLoop() {
		while (!isGameOver) {
			System.out.println("Jogada número " + (numOfPlays));
			if (numOfPlays == 1) {
				currentPlayer = randomPlayerStart(); // colocar numa função
				System.out.println("O jogador " + currentPlayer + " começa!");
				if (currentPlayer == 1) {
					playerBoardMark = 1;
				} else {
					playerBoardMark = -1;
				}
			} else {
				changePlayer();
			}
			choosePosition();
			board.printBoard();
			checkWinner();

			if (numOfPlays == 10 && !hasWinner) {
				isGameOver = true;
				System.out.println("Fim de jogo! Empate!!!");
			}

			if (isGameOver) {
				continuePlaying();
			}

		}

	}

	public void continuePlaying() {
		System.out.println("O Placar esta:");
		System.out.println("Jogador 1 " + pointsPlayer1 + " x " + pointsPlayer2 + " Jogador 2");
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

	public boolean setPVP() { // mudar nome da função
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
		userSelectedPosition = bot.getSelectedPosition();
		System.out.println("O bot escolheu a posição " + (userSelectedPosition + 1));
	}

	public void humanMove() {

		boolean isEmpty = false;

		while (!isEmpty) { //tentar fugir dos while

			System.out.println("Escolha a posição (1-9): ");
			userSelectedPosition = scan.nextInt();

			while (userSelectedPosition < 1 || userSelectedPosition > 9) {
				System.out.println("Posição inválida!");
				System.out.println("Escolha a posição entre 1 e 9: ");
				userSelectedPosition = scan.nextInt();
			}

			isEmpty = board.isPositionEmpty(userSelectedPosition - 1);

		}

		userSelectedPosition = userSelectedPosition - 1;
		System.out.println("O jogador " + currentPlayer + " escolheu a posição " + (userSelectedPosition + 1));

	}

	public void setPosition() {
		board.setPosition(userSelectedPosition, playerBoardMark);
		numOfPlays++;
	}

	public void changePlayer() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
			playerBoardMark = -1;
		} else {
			currentPlayer = 1;
			playerBoardMark = 1;
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
			int numOfMarksPlayer2 = 0;
			int numOfMarksPlayer1 = 0;
			for (int i = 0; i < line.length; i++) {
				int index = line[i];
				if (board.board[index] == -1) {
					numOfMarksPlayer2++;
				}
				if (board.board[index] == 1) {
					numOfMarksPlayer1++;
				}
			}
			if (numOfMarksPlayer2 == 3) {
				System.out.println("Fim de Jogo! O jogador " + currentPlayer + " venceu! \n");
				isGameOver = true;
				hasWinner = true;
				pointsPlayer2++;
				break;
			}

			if (numOfMarksPlayer1 == 3) {
				System.out.println("Fim de Jogo! O Jogador " + currentPlayer + " venceu! \n");
				isGameOver = true;
				hasWinner = true;
				pointsPlayer1++;
				break;
			}

		}

	}

}
