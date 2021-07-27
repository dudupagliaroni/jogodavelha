package com.eduardo.jogodavelha;

import java.util.Random;
import java.util.Scanner;

public class GameCore {

	private static int numOfMoves = 1;
	private boolean isRoundOver = false;
	private boolean isGameOver = false;
	private boolean PVP = false;
	private int currentPlayer;
	private int currentPlayerBoardMark; // são 3 estados -1, 0 e 1 - como consertar isso?
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
		gameLoop();
		chooseNextMach();
	}

	public void gameLoop() {
		while (!checkGameWinner()) {
			System.out.println("Jogada número " + (numOfMoves));

			board.setBoardPosition(playersMove(setPlayer()), currentPlayerBoardMark);
			board.printBoard();

			if (checkRoundWinner() && !checkGameWinner()) {
				nextRound();
			}

		}

	}

	public void nextRound() {

		numOfMoves = 1;
		board.resetBoard();
		System.out.println("Novo round iniciado!");
		isRoundOver = false;

	}

	public boolean chooseNextMach() {

		System.out.println("Deseja jogar novamente? 1-Sim/2-Não");
		int option = scan.nextInt();

		if (option == 1) {
			isGameOver = false;
			isRoundOver = false;
			numOfMoves = 1;
			pointsPlayer1 = 0;
			pointsPlayer2 = 0;
			board.resetBoard();
			startGame();

		} else {
			System.out.println("Fim de Jogo!");
			System.exit(0);
		}
		return isGameOver;
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

	public int playersMove(int currentPlayer) {
		int position = 0;

		if (PVP == true) {
			position = humanMove();

		} else if (currentPlayer == 1 && PVP == false) {
			position = humanMove();

		} else if (currentPlayer == 2 && PVP == false) {
			position = botMove();
		}
		numOfMoves++;
		return position;
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

	public int changeCurrentPlayer() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
			currentPlayerBoardMark = -1;
		} else {
			currentPlayer = 1;
			currentPlayerBoardMark = 1;
		}
		System.out.println("É a vez do jogador " + currentPlayer);
		return currentPlayer;

	}

	public int setPlayer() {
		if (numOfMoves == 1) {
			currentPlayer = randomPlayerStart();
		} else {
			currentPlayer = changeCurrentPlayer();
		}
		return currentPlayer;
	}

	public int randomPlayerStart() {
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

	public boolean checkRoundWinner() {
		boolean hasWinner = false;
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
				System.out.println("Fim de Round! O jogador " + currentPlayer + " venceu! \n");
				isRoundOver = true;
				hasWinner = true;
				pointsPlayer2++;
				break;
			}

			if (numOfMarksPlayer1 == 3) {
				System.out.println("Fim de Round! O Jogador " + currentPlayer + " venceu! \n");
				isRoundOver = true;
				hasWinner = true;
				pointsPlayer1++;
				break;
			}

		}

		if (numOfMoves == 10 && !hasWinner) {
			isRoundOver = true;
			System.out.println("Fim de round! Empate!!!");
		}

		if (isRoundOver) {
			System.out.println("O Placar esta:");
			System.out.println("Jogador 1 | " + pointsPlayer1 + " x " + pointsPlayer2 + " | Jogador 2");
		}

		return isRoundOver;

	}

	public boolean checkGameWinner() {
		boolean isGameOver = false;
		if (pointsPlayer1 >= 2) {
			isGameOver = true;
			System.out.println("Jogador 1 ganhou!");
		}

		if (pointsPlayer2 >= 2) {
			isGameOver = true;
			System.out.println("Jogador 2 ganhou!");
		}

		return isGameOver;
	}

	public static int getNumOfMoves() {
		return numOfMoves;
	}

}
