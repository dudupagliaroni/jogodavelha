package com.eduardo.jogodavelha;

public class LineStatusChecker {

    private int[] line;
    private GameBoard board;

    public LineStatusChecker(int[] line, GameBoard board) {
        this.line = line;
        this.board = board;
    }

    public void checkLineStatus(int[] line, GameBoard board) {

    }

    public boolean isLineSafe(int[] line, GameBoard board) {
        return true;
    }

    public int[] getLine() {
        return line;
    }


}
