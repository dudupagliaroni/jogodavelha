package com.eduardo.jogodavelha;

import java.util.Random;
import java.util.Scanner;

public class GameCore {

	private static int numOfMoves = 1;
	private boolean isGameOver = false;
	private boolean gameHasWinner = false;
	private boolean PVP = false;
	private int currentPlayer = 1;
	private int currentPlayerBoardMark = 1; // são 3 estados -1, 0 e 1 - como consertar isso?
	private int pointsPlayer1;
	private int pointsPlayer2;

	private GameBoard board = new GameBoard();
	private GameBot bot = new GameBot();
	private Scanner scan = new Scanner(System.in);

	public GameCore() {
		startGame();
	}

	public void startGame() {
		System.out.println("Iniciando o jogo!");
		board.printBoard();
		setGameMode();
		mainLoop();
	}

	public void mainLoop() {
		while (!isGameOver) {
			System.out.println("Jogada número " + (numOfMoves));
			
			if (numOfMoves == 1) {
				currentPlayer = setRandomPlayerStart(); // colocar numa função
			} else {
				changeCurrentPlayer();
			}
			
			chooseBoardPosition();
			board.printBoard();
			checkGameWinner();

			if (numOfMoves == 10 && !gameHasWinner) {
				isGameOver = true;
				System.out.println("Fim de jogo! Empate!!!");
			}

			if (isGameOver) {
				chooseNextMach();
			}

		}

	}

	public void chooseNextMach() {
		System.out.println("O Placar esta:");
		System.out.println("Jogador 1 " + pointsPlayer1 + " x " + pointsPlayer2 + " Jogador 2");
		System.out.println("Deseja jogar novamente? 1-Sim/2-Não");
		int option = scan.nextInt();

		if (option == 1) {
			isGameOver = false;
			numOfMoves = 1;
			board.resetBoard();
			System.out.println("Novo jogo iniciado!");
		} else {
			System.out.println("Fim de Jogo!");
			System.exit(0);
		}
	}

	public int setRandomPlayerStart() {
		int[] players = { 1, 2 };
		int rnd = new Random().nextInt(players.length);

		if (players[rnd] == 1) {
			currentPlayerBoardMark = 1;
		} else {
			currentPlayerBoardMark = -1;
		}
		System.out.println("O jogador " + players[rnd] + " começa!");
		return players[rnd];

	}

	public boolean setGameMode() {
		System.out.println("Jogar 1x1 ou contra um bot? 1 - PVP / 2 - Bot");
		int option = scan.nextInt();
		if (option == 1) {
			return PVP = true;
		} else {
			return PVP = false;
		}
	}

	public void chooseBoardPosition() {
		if (PVP == true) {
			board.setBoardPosition(humanMove(), currentPlayerBoardMark);
			numOfMoves++;

		} else if (currentPlayer == 1 && PVP == false) {
			board.setBoardPosition(humanMove(), currentPlayerBoardMark);
			numOfMoves++;

		} else if (currentPlayer == 2 && PVP == false) {
			board.setBoardPosition(botMove(), currentPlayerBoardMark);
			numOfMoves++;
		}

	}

	public int botMove() {
		int botSelectedPosition = bot.botChoosePosition(board);
		System.out.println("O bot escolheu a posição " + (botSelectedPosition + 1));
		return botSelectedPosition;
	}

	public int humanMove() {

		boolean isPositionEmpty = false;
		int boardSelectedPosition = 0;

		while (!isPositionEmpty) { // tentar fugir dos while

			System.out.println("Escolha a posição (1-9): ");
			boardSelectedPosition = scan.nextInt() - 1;

			while (boardSelectedPosition < 0 || boardSelectedPosition > 8) {
				System.out.println("Posição inválida!");
				System.out.println("Escolha a posição entre 1 e 9: ");
				boardSelectedPosition = scan.nextInt() - 1;
			}

			isPositionEmpty = board.isBoardPositionEmpty(boardSelectedPosition);

		}

		System.out.println("O jogador " + currentPlayer + " escolheu a posição " + (boardSelectedPosition + 1));
		return boardSelectedPosition;

	}

	public void changeCurrentPlayer() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
			currentPlayerBoardMark = -1;
		} else {
			currentPlayer = 1;
			currentPlayerBoardMark = 1;
		}
		System.out.println("É a vez do jogador " + currentPlayer);

	}

	public void checkGameWinner() {

		
		for (int[] line : board.getAllLines()) {
			int numOfMarksPlayer2 = 0;
			int numOfMarksPlayer1 = 0;
			for (int i = 0; i < line.length; i++) {
				int index = line[i];
				if (board.getBoard()[index] == -1) {
					numOfMarksPlayer2++;
				}
				if (board.getBoard()[index] == 1) {
					numOfMarksPlayer1++;
				}
			}
			if (numOfMarksPlayer2 == 3) {
				System.out.println("Fim de Jogo! O jogador " + currentPlayer + " venceu! \n");
				isGameOver = true;
				gameHasWinner = true;
				pointsPlayer2++;
				break;
			}

			if (numOfMarksPlayer1 == 3) {
				System.out.println("Fim de Jogo! O Jogador " + currentPlayer + " venceu! \n");
				isGameOver = true;
				gameHasWinner = true;
				pointsPlayer1++;
				break;
			}

		}

	}

	public static int getNumOfPlays() {
		return numOfMoves;
	}

}
