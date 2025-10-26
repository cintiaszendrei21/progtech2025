package com.sintie.amoba;
import java.util.Scanner;

public class Configuration {
    int rows, cols = 0;
    String playerName;

    public Configuration() {

    }

    public void blankInit() {
        Scanner scanner = new Scanner(System.in);
        // input name
        System.out.println("Add meg a neved.");
        playerName = scanner.nextLine();
        // input rows
        while(rows < 5 || rows > 25) {
            System.out.println("Add meg a sorok számát.(5 <= N <= 25)");
            rows = scanner.nextInt();
        }
        // input cols
        while(cols < 5 || cols > 25) {
            System.out.println("Add meg az oszlopok számát.(5 <= M <= 25)");
            cols = scanner.nextInt();
        }
    }
}
