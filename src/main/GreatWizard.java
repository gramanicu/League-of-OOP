package main;

import angels.Angel;
import angels.AngelType;
import heroes.Hero;
import heroes.HeroType;

import java.io.FileWriter;
import java.io.IOException;

public final class GreatWizard {
    private static GreatWizard instance = null;
    private static FileWriter fileWriter;

    private GreatWizard() { }

    void setOutput(final String output) {
        try {
            fileWriter = new FileWriter(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void closeOutput() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GreatWizard getInstance() {
        if (instance == null) {
            instance = new GreatWizard();
        }
        return instance;
    }

    /**
     * Message to print the current round.
     */
    void printRound() {
        try {
            if (Game.getInstance().getRound() != 0) {
                fileWriter.write("\n");
            }
            fileWriter.write(String.format("~~ Round %d ~~\n", Game.getInstance().getRound() + 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getAngel(final AngelType angelType) {
        String angel = "";
        switch (angelType) {
            case DamageAngel:
                angel = "DamageAngel";
                break;
            case DarkAngel:
                angel = "DarkAngel";
                break;
            case Dracula:
                angel = "Dracula";
                break;
            case GoodBoy:
                angel = "GoodBoy";
                break;
            case LevelUpAngel:
                angel = "LevelUpAngel";
                break;
            case LifeGiver:
                angel = "LifeGiver";
                break;
            case SmallAngel:
                angel = "SmallAngel";
                break;
            case Spawner:
                angel = "Spawner";
                break;
            case TheDoomer:
                angel = "TheDoomer";
                break;
            case XPAngel:
                angel = "XPAngel";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + angelType);
        }
        return angel;
    }

    private static String getHero(final HeroType heroType) {
        String hero = "";
        switch (heroType) {
            case KNIGHT:
                hero = "Knight";
                break;
            case PYROMANCER:
                hero = "Pyromancer";
                break;
            case ROGUE:
                hero = "Rogue";
                break;
            case WIZARD:
                hero = "Wizard";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + heroType);
        }
        return hero;
    }

    /**
     * Message to show when a player is hit by an angel.
     * @param angelType The angel that hit
     * @param heroType The hit player/hero
     * @param playerID The player/hero id
     */
    public void angelHit(final AngelType angelType, final HeroType heroType,
                         final int playerID) {
        String angel = getAngel(angelType);
        String hero = getHero(heroType);

        try {
            fileWriter.write(String.format("%s hit %s %d\n", angel, hero, playerID));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Message to show when an angel helps a player.
     * @param angelType The helper angel
     * @param heroType The helped hero/player
     * @param playerID The id of the hero/player
     */
    public void angelHelp(final AngelType angelType, final HeroType heroType,
                         final int playerID) {
        String angel = getAngel(angelType);
        String hero = getHero(heroType);

        try {
            fileWriter.write(String.format("%s helped %s %d\n", angel, hero, playerID));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Message to show when an angel spawns.
     * @param angel The angel spawned
     */
    void angelSpawned(final Angel angel) {
        String angelType = getAngel(angel.getType());
        String position = angel.getPosition().toString();
        try {
            fileWriter.write(String.format("Angel %s was spawned at %s\n",
                    angelType, position));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Message to show when a player was killed by another player.
     * @param source The killer
     * @param target The killed player
     */
    public void playerKilled(final Hero source, final Hero target) {
        String killed = getHero(target.getType());
        String killer = getHero(source.getType());
        String output = String.format("Player %s %d was killed by %s %d\n",
                killed, target.getId(), killer, source.getId());
        try {
            fileWriter.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Message to show when a player was killed by an angel.
     * @param target The player killed
     */
    public void playerKilled(final Hero target) {
        String killed = getHero(target.getType());
        String output = String.format("Player %s %d was killed by an angel\n",
                killed, target.getId());
        try {
            fileWriter.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Message to show when a player/hero reached a level
     * @param target The player/hero affected
     * @param level The level reached
     */
    public void playerLevel(final Hero target, final int level) {
        String hero = getHero(target.getType());
        String output = String.format("%s %d was reached lvl %d\n",
                hero, target.getId(), level);
        try {
            fileWriter.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void printStats() {
        try {
            fileWriter.write("\n~~ Results ~~\n");
            for (Hero player : Game.getInstance().getPlayers()) {
                fileWriter.write(player.getStats());
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
