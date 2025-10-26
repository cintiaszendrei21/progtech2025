package com.sintie.amoba;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public int[] getMove(Board board, Scanner scanner) {
        int row = -1, col = -1;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print("Add meg a lépést (pl. 5 d): ");
                row = scanner.nextInt() - 1;
                String colStr = scanner.next().toLowerCase();
                col = colStr.charAt(0) - 'a';

                if (board.isValidMove(row, col)) {
                    valid = true;
                } else {
                    System.out.println("Érvénytelen mező. Próbáld újra!");
                }
            } catch (Exception e) {
                System.out.println("Hibás bemenet. Próbáld újra!");
                scanner.nextLine();
            }
        }

        return new int[]{row, col};
    }
}