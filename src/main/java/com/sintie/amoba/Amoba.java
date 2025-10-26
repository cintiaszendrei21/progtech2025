package com.sintie.amoba;


public class Amoba {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.blankInit();
        Game game = new Game(config);
        game.start();
    }
}
