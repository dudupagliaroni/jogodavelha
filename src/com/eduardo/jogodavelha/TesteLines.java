package com.eduardo.jogodavelha;

public class TesteLines {

	static int index = 0;

	public static void main(String[] args) {

		int[] row1 = { 0, 1, 2 };
		int[] row2 = { 3, 4, 5 };
		int[] row3 = { 6, 7, 8 };
		int[] column1 = { 0, 3, 6 };
		int[] column2 = { 1, 4, 7 };
		int[] column3 = { 2, 5, 8 };
		int[] diagonal1 = { 0, 4, 8 };
		int[] diagonal2 = { 2, 4, 6 };
		int[] boardCornersAndCenter = { 0, 2, 4, 6, 8 };
		int[] allPositions = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] allLines[] = { row1, row2, row3, column1, column2, column3, diagonal1, diagonal2 };

		GameBoard board = new GameBoard();
		GameBot bot = new GameBot();
		int[] lineTest = { 0, 1, 2 };
		int[] boardTest = { -1, -1, -1, 0, 1, -1, -1, 1, -1 };
		int[] emptyBoard = { 0, 0, 0, -1, 0, 0, 0, 0, 0 };
		board.setBoard(emptyBoard);
		board.printBoard();

		System.out.println("is started?");
		for (int[] line1 : allLines) {
			System.out.println(bot.checkIfLineStarted(line1, board));
		}

		System.out.println();
		System.out.println("is loosing?");
		for (int[] line2 : allLines) {
			System.out.println(bot.checkIfLineisLosing(line2, board));
		}

		System.out.println();
		System.out.println("is winning?");
		for (int[] line3 : allLines) {
			System.out.println(bot.checkIfLineisWinning(line3, board));
		}

		System.out.println();

		System.out.println("Random " + bot.chooseRandomPosition(allPositions, board));

		System.out.println("Se a linha comecou " + bot.positionIfLineWasStarted(board));
		System.out.println("line is loosing " + bot.positionIfLineIsLoosing(board));
		System.out.println("line is winning " + bot.positionIfLineIsWinning(board));

		System.out.println("método do bot escolher " + bot.botChoosePosition(board));
		
		board.printBoard();
		

	}

}
