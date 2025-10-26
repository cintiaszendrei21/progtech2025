package com.sintie.amoba;

import java.awt.geom.Point2D;
import  java.util.ArrayList;
import java.util.List;
public class Board {
    private final int rows;
    private final int cols;
    private final char[][] board;
    private final char EMPTY = ' ';
    private List<Point2D> validMoves = new ArrayList<>();
    private boolean isFirstMove = true;

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
        if (isFirstMove) {
            return isValidFirstMove(row, col);
        } else {
            return row >= 0 && row < rows &&
                    col >= 0 && col < cols &&
                    board[row][col] == EMPTY;
        }
    }


    public void placeMark(int row, int col, char symbol) {
        if (isValidMove(row, col)) {
            board[row][col] = symbol;
            refreshValidMoves();
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
    private boolean isValidFirstMove(int row, int col) {
        List<Integer> validRows = new ArrayList<>();
        if (rows % 2 == 0) {
            validRows.add(rows / 2);
        }
        validRows.add(rows / 2 + 1);

        List<Integer> validCols = new ArrayList<>();
        if (cols % 2 == 0) {
            validCols.add(cols / 2);
        }
        validCols.add(cols / 2 + 1);

        if (validRows.contains(row + 1) && validCols.contains(col + 1) && board[row][col] == EMPTY) {
            isFirstMove = false;
            return true;
        }
        return false;
    }
    private void refreshValidMoves() {
        validMoves.clear();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] != EMPTY) {
                    refreshValidMovesNearTo(row, col);
                }
            }
        }
    }

    private void refreshValidMovesNearTo(int row, int col) {
        int[] checks = {-1, 0, 1};

        for (int dRow : checks) {
            int rowIndex = row + dRow;
            for (int dCol : checks) {
                int colIndex = col + dCol;

                if (rowIndex >= 0 && rowIndex < rows &&
                        colIndex >= 0 && colIndex < cols &&
                        board[rowIndex][colIndex] == EMPTY) {

                    Point2D validField = new Point2D.Double(rowIndex, colIndex);

                    if (!validMoves.contains(validField)) {
                        validMoves.add(validField);
                    }
                }
            }
        }
    }
}
