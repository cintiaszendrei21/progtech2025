package com.sintie.amoba;

import java.util.Scanner;

public abstract class Player {
    protected final String name;
    protected final char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract int[] getMove(Board board, Scanner scanner);
}
