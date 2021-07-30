package com.eduardo.jogodavelha.tests;


import com.eduardo.jogodavelha.*;

import java.util.Random;

public class TesteLines {

    static int[] row1 = {0, 1, 2};
    static int[] row2 = {3, 4, 5};
    static int[] row3 = {6, 7, 8};
    static int[] column1 = {0, 3, 6};
    static int[] column2 = {1, 4, 7};
    static int[] column3 = {2, 5, 8};
    static int[] diagonal1 = {0, 4, 8};
    static int[] diagonal2 = {2, 4, 6};
    static int[] allPositions = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    static int[][] allLines = {row1, row2, row3, column1, column2, column3, diagonal1, diagonal2};
    static GameBoard board = new GameBoard();
    static GameBot bot = new GameBot();
    static Player player1 = new Player("Dudu", 1, 0);
    static int[] lineTest = {0, 1, 2};
    static int[] boardCornersAndCenter = {0, 2, 4, 6, 8};
    static int[] boardTest = {-1, 0, 0, -1, -1, 0, 1, 1, 0};
    static int[] emptyBoard = {0, 0, 0, 0, -1, 0, 0, 0, 0};
    static Player player2 = new Player("Bot", -1, 0);
    static LineBoard line = new LineBoard(row1);

    public static void main(String[] args) {

        board.setBoard(boardTest);

        LineChecker lineChecker = new LineChecker();

        for (int[] line : board.getAllLines()
             ) {
            System.out.println(lineChecker.checkIfLineisLosing(player1, line, board));
        }


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

        System.out.println("line was started " + bot.positionIfLineWasStarted(board));
        System.out.println("line is loosing " + bot.positionIfLineIsLoosing(board));
        System.out.println("line is winning " + bot.positionIfLineIsWinning(board));

        System.out.println("método do bot escolher " + bot.botChoosePosition(board));

        board.printBoard();

    }

    public static GameBoard randomBoard() {
        int rnd = 0;
        int mark = -1;
        int[] allPosition = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int pos = 0;
        boolean isEmpty = false;

        for (int i = 0; i < 7; i++) {
            isEmpty = false;

            if (mark == 1) {
                mark = -1;
            } else {
                mark = 1;
            }
            while (!isEmpty) {


                rnd = new Random().nextInt(allPosition.length);
                pos = allPosition[rnd];
                if (board.getBoard()[pos] == 0) {
                    board.setBoardPosition(pos, mark);
                    isEmpty = true;
                }

            }
        }
        return board;
    }
}