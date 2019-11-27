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
                return new Knight(500, 50, position);
            case PYROMANCER:
                return null;
            case ROGUE:
                return null;
            case WIZARD:
                return null;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
