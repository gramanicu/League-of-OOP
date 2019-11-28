package com.gramanicu.heroes;

import com.gramanicu.abilities.Ability;
import com.gramanicu.maps.Point;
import com.gramanicu.maps.TerrainType;

public class Rogue extends Hero {
    private static final int BASE_HP = 600;
    private static final int SCALING_HP = 40;
    public static final int TERRAIN_MODIFIER = 15;
    public static final TerrainType HOME_TERRAIN = TerrainType.WOODS;

    public Rogue(final Point position) {
        super(HeroType.ROGUE, BASE_HP, SCALING_HP, position);
    }

    /**
     * Get affected by an ability.
     *
     * @param ability The ability that will affect the hero
     */
    @Override
    public void accept(final Ability ability) {

    }

    /**
     * Attack another hero.
     *
     * @param target The targeted knight
     */
    @Override
    public void attack(final Knight target) {

    }

    /**
     * Attack another hero.
     *
     * @param target The targeted pyromancer
     */
    @Override
    public void attack(final Pyromancer target) {

    }

    /**
     * Attack another hero.
     *
     * @param target The targeted wizard
     */
    @Override
    public void attack(final Wizard target) {

    }

    /**
     * Attack another hero.
     *
     * @param target The targeted rogue
     */
    @Override
    public void attack(final Rogue target) {

    }

    /**
     * @return The terrain bonus for the hero
     */
    @Override
    public int getTerrainBonus() {
        return 0;
    }
}
