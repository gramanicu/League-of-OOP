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
}
