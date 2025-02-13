package main;

public final class Main {

    private Main() {
        // just to trick checkstyle
    }

    /**
     * @param args Input filename, Output filename
     */
    public static void main(final String[] args) {
        GreatWizard.getInstance().setOutput(args[1]);
        Game.getInstance().load(args[0]);
        Game.getInstance().start();
        GreatWizard.getInstance().closeOutput();
    }
}
