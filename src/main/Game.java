package main;

import angels.Angel;
import angels.AngelType;
import angels.AngelsFactory;
import heroes.Hero;
import heroes.HeroesFactory;
import maps.Map;
import maps.Movement;
import maps.Point;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

final class Game {
    private ArrayList<Hero> players;
    private ArrayList<ArrayList<Movement>> playerMovements;
    private ArrayList<ArrayList<Angel>> angelsToAdd;
    private int rounds;
    private int round;
    private static Game instance = null;

    private Game() {
        players = new ArrayList<>();
        playerMovements = new ArrayList<>();
        angelsToAdd = new ArrayList<>();
    }

    static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Load the game data.
     * @param inputPath The input file
     */
    void load(final String inputPath) {
        try {
            File file = new File(inputPath);
            Scanner input = new Scanner(file);

            int height = input.nextInt();
            int width = input.nextInt();

            Point.setBounds(width, height);

            char[][] terrain = new char[height][width];

            for (int i = 0; i < height; i++) {
                String row = input.next();
                for (int j = 0; j < width; j++) {
                    terrain[i][j] = row.charAt(j);
                }
            }

            Map.getInstance().loadMap(terrain);

            int playerCount = input.nextInt();

            for (int i = 0; i < playerCount; i++) {
                char type = input.next().charAt(0);
                int row = input.nextInt();
                int col = input.nextInt();
                Point point = new Point(col, row);

                players.add(HeroesFactory.getInstance().getHero(type, point));
                players.get(i).setId(i);
            }

            int roundCount = input.nextInt();
            rounds = roundCount;

            for (int i = 0; i < roundCount; i++) {
                String row = input.next();

                ArrayList<Movement> roundMovements = new ArrayList<>();

                for (int j = 0; j < playerCount; j++) {
                    char movement = row.charAt(j);
                    Movement actualMove;

                    switch (Character.toUpperCase(movement)) {
                        case 'U':
                            actualMove = Movement.UP;
                            break;
                        case 'D':
                            actualMove = Movement.DOWN;
                            break;
                        case 'L':
                            actualMove = Movement.LEFT;
                            break;
                        case 'R':
                            actualMove = Movement.RIGHT;
                            break;
                        default: // '_' or anything else
                            actualMove = Movement.NONE;
                            break;
                    }
                    roundMovements.add(actualMove);
                }
                playerMovements.add(roundMovements);
            }

            for (int i = 0; i < roundCount; i++) {
                ArrayList<Angel> roundAngels = new ArrayList<>();
                int angelCount = input.nextInt();
                if (angelCount != 0) {
                    for (int j = 0; j < angelCount; j++) {
                        String row = input.next();
                        String[] parts = row.split(",");
                        String angel = parts[0];
                        char x = parts[1].charAt(0);
                        char y = parts[2].charAt(0);
                        Point point = new Point(Character.getNumericValue(x),
                                Character.getNumericValue(y));

                        Angel newAngel = AngelsFactory.getInstance()
                                .getAngel(angel, point);
                        roundAngels.add(newAngel);
                    }
                }
                angelsToAdd.add(roundAngels);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void movePlayers() {
        for (int playerID = 0; playerID < players.size(); playerID++) {
            players.get(playerID).applyStrategy();
            players.get(playerID).move(playerMovements.get(round).get(playerID));
        }
    }

    // This algorithm works because we know only two players can reach the same spot
    private void fight() {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = i + 1; j < players.size(); j++) {
                if (players.get(i).getPosition().equals(players.get(j).getPosition())) {
                    fight(players.get(i), players.get(j));
                }
            }
        }
    }

    private void fight(final Hero first, final Hero second) {
        boolean firstDead;
        firstDead = first.isDead();
        boolean secondDead;
        secondDead = second.isDead();

        // If no one is dead
        if (!(firstDead || secondDead)) {
            first.attack(second);
            second.attack(first);

            boolean firstAfter;
            firstAfter = first.isDead();
            boolean secondAfter;
            secondAfter = second.isDead();

            // If someone died
            if (firstAfter || secondAfter) {
                 int firstLvl = first.getLevel();
                 int secondLvl = second.getLevel();

                if (firstAfter) {
                    GreatWizard.getInstance().playerKilled(second, first);
                }

                if (secondAfter) {
                    GreatWizard.getInstance().playerKilled(first, second);
                }

                if (secondAfter) {
                    first.won(second, secondLvl);
                }

                if (firstAfter) {
                    second.won(first, firstLvl);
                }
            }
        }
    }

    /**
     * Returns the current round.
     * @return The round number
     */
    int getRound() {
        return round;
    }

    /**
     * Start the game.
     */
    void start() {
        while (round != rounds) {
            GreatWizard.getInstance().printRound();
            movePlayers();
            fight();
            addAngels();
            round++;
        }
        GreatWizard.getInstance().printStats();
    }

    /**
     * Spawn the angels.
     */
    private void addAngels() {
        if (round < angelsToAdd.size()) {
            for (Angel angel : angelsToAdd.get(round)) {
                GreatWizard.getInstance().angelSpawned(angel);
                for (Hero hero : players) {
                    if (hero.getPosition().equals(angel.getPosition())) {
                        if (!hero.isDead() || angel.getType() == AngelType.Spawner) {
                            angel.apply(hero);
                        }
                    }
                }
            }
        }
    }

    ArrayList<Hero> getPlayers() {
        return players;
    }
}
