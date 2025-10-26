package com.sintie.amoba;

import java.util.Random;
import java.util.Scanner;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public int[] getMove(Board board, Scanner scanner) {
        Random rand = new Random();
        int row, col;


        do {
            row = rand.nextInt(board.getRows());
            col = rand.nextInt(board.getCols());
        } while (!board.isValidMove(row, col));

        System.out.println("Gép lép: " + (row + 1) + " " + (char) ('a' + col));
        return new int[]{row, col};
    }
}