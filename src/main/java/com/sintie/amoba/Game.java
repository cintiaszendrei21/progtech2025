package com.sintie.amoba;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player humanPlayer;
    private final Player computerPlayer;
    private Player currentPlayer;

    public Game(Configuration config) {
        board = new Board(config);
        humanPlayer = new HumanPlayer(config.playerName, 'x');
        computerPlayer = new ComputerPlayer("Gép", 'o');
        currentPlayer = humanPlayer;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        board.initBoard();

        boolean gameOver = false;

        while (!gameOver) {
            board.printBoard();
            System.out.println("Játékos " + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") következik.");

            int[] move = currentPlayer.getMove(board, scanner);
            board.placeMark(move[0], move[1], currentPlayer.getSymbol());

            if (board.checkWin(move[0], move[1], currentPlayer.getSymbol())) {
                board.printBoard();
                System.out.println("A játékos " + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") nyert!");
                gameOver = true;
            } else if (board.isBoardFull()) {
                board.printBoard();
                System.out.println("Döntetlen!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == humanPlayer) ? computerPlayer : humanPlayer;
            }
        }

        scanner.close();
    }
}

