package com.eduardo.jogodavelha;

public class LineChecker {

    public boolean checkIfLineisLosing(Player player, int[] line, GameBoard board) {
        
        boolean isLineLosing = false;
        boolean isLineSafe = false;

        for (int i = 0; i < line.length; i++) {
            int index = line[i];
            if (board.getBoard()[index] == -1) {
                isLineSafe = true;
                break;
            }
        }

        if (!isLineSafe) {
            int numOfMarks = 0;
            for (int i = 0; i < line.length; i++) {
                int index = line[i];
                if (board.getBoard()[index] == 1) {
                    numOfMarks++;
                }
            }
            if (numOfMarks == 2) {
                isLineLosing = true;
            }
        }
        return isLineLosing;
    }

}
