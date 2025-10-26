package com.sintie.amoba;

public class Board {
    private final int rows;
    private final int cols;
    private final char[][] board;
    private final char EMPTY = ' ';

    public Board(Configuration config) {
        this.rows = config.rows;
        this.cols = config.cols;
        board = new char[rows][cols];
        initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                board[i][j] = EMPTY;
    }

    public void printBoard() {
        System.out.print("   ");
        for (int col = 0; col < cols; col++) {
            System.out.print(" " + (char) ('a' + col) + "  ");
        }
        System.out.println();

        printHorizontalLine();

        for (int row = 0; row < rows; row++) {
            System.out.printf("%2d", row + 1);

            for (int col = 0; col < cols; col++) {
                System.out.print("| " + board[row][col] + " ");
            }
            System.out.println("|");

            printHorizontalLine();
        }
    }

    private void printHorizontalLine() {
        System.out.print("  ");
        for (int i = 0; i < cols; i++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < rows &&
                col >= 0 && col < cols &&
                board[row][col] == EMPTY;
    }

    public void placeMark(int row, int col, char symbol) {
        if (isValidMove(row, col)) {
            board[row][col] = symbol;
        }
    }

    public boolean checkWin(int row, int col, char symbol) {
        return (countInDirection(row, col, 1, 0, symbol) + countInDirection(row, col, -1, 0, symbol) >= 3) || // függőleges
                (countInDirection(row, col, 0, 1, symbol) + countInDirection(row, col, 0, -1, symbol) >= 3) || // vízszintes
                (countInDirection(row, col, 1, 1, symbol) + countInDirection(row, col, -1, -1, symbol) >= 3) || // átló /
                (countInDirection(row, col, 1, -1, symbol) + countInDirection(row, col, -1, 1, symbol) >= 3);   // átló \
    }

    private int countInDirection(int row, int col, int dRow, int dCol, char symbol) {
        int count = 0;
        for (int i = 1; i <= 3; i++) {
            int newRow = row + dRow * i;
            int newCol = col + dCol * i;
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && board[newRow][newCol] == symbol) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (board[i][j] == EMPTY)
                    return false;
        return true;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
