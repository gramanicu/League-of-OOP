package com.gramanicu.main;


public final class Main {

    private Main() {
        // just to trick checkstyle
    }

    /**
     * @param args Input filename, Output filename
     */
    public static void main(final String[] args) {
        Game.getInstance().load(args[0]);
        Game.getInstance().start();
        Game.getInstance().results(args[1]);
    }
}
