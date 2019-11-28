package com.gramanicu.heroes;

import com.gramanicu.maps.Point;

public final class HeroesFactory {
    private static HeroesFactory instance = null;

    private HeroesFactory() { }

    public static HeroesFactory getInstance() {
        if (instance == null) {
            instance = new HeroesFactory();
        }
        return instance;
    }

    /**
     * Creates a new hero of the specified type.
     * @param type The hero type
     * @return The hero
     */
    public Hero getHero(final char type, final Point position) {
        HeroType heroType;

        switch (Character.toUpperCase(type)) {
            case 'K':
                heroType = HeroType.KNIGHT;
                break;
            case 'P':
                heroType = HeroType.PYROMANCER;
                break;
            case 'R':
                heroType = HeroType.ROGUE;
                break;
            case 'W':
                heroType = HeroType.WIZARD;
                break;
            default:
                return null;
        }

        switch (heroType) {
            case KNIGHT:
                return new Knight(position);
            case PYROMANCER:
                return new Pyromancer(position);
            case ROGUE:
                return new Rogue(position);
            case WIZARD:
                return new Wizard(position);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
