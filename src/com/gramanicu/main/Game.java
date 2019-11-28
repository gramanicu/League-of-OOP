package com.gramanicu.main;

import com.gramanicu.heroes.Hero;
import com.gramanicu.heroes.HeroesFactory;
import com.gramanicu.maps.Map;
import com.gramanicu.maps.Movement;
import com.gramanicu.maps.Point;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public final class Game {
    private ArrayList<Hero> players;
    private ArrayList<ArrayList<Movement>> playerMovements;
    private int rounds;
    private int round;
    private static Game instance = null;

    private Game() {
        players = new ArrayList<>();
        playerMovements = new ArrayList<>();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Load the game data.
     * @param inputPath The input file
     */
    public void load(final String inputPath) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void movePlayers() {
        for (int playerID = 0; playerID < players.size(); playerID++) {
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
        first.attack(second);
        second.attack(first);
    }

    /**
     * Start the game.
     */
    public void start() {
        while (round != rounds) {
            movePlayers();
            fight();

            round++;
        }
    }

    /**
     * Print the player stats to output.
     * @param output The output file
     */
    public void results(final String output) {
        for (Hero player : players) {
            System.out.println(player.getStats());
        }
    }
}
