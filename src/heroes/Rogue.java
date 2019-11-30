package heroes;

import abilities.Ability;
import abilities.Backstab;
import abilities.Paralysis;
import maps.Point;
import maps.TerrainType;

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
        ability.affect(this);
    }

    /**
     * Attack another hero.
     *
     * @param target The targeted knight
     */
    @Override
    public void attack(final Knight target) {
        Backstab backstab = new Backstab(this);
        backstab.affect(target);
        Paralysis paralysis = new Paralysis(this);
        paralysis.affect(target);
    }

    /**
     * Attack another hero.
     *
     * @param target The targeted pyromancer
     */
    @Override
    public void attack(final Pyromancer target) {
        Backstab backstab = new Backstab(this);
        backstab.affect(target);
        Paralysis paralysis = new Paralysis(this);
        paralysis.affect(target);
    }

    /**
     * Attack another hero.
     *
     * @param target The targeted wizard
     */
    @Override
    public void attack(final Wizard target) {
        Backstab backstab = new Backstab(this);
        backstab.affect(target);
        Paralysis paralysis = new Paralysis(this);
        paralysis.affect(target);
    }

    /**
     * Attack another hero.
     *
     * @param target The targeted rogue
     */
    @Override
    public void attack(final Rogue target) {
        Backstab backstab = new Backstab(this);
        backstab.affect(target);
        Paralysis paralysis = new Paralysis(this);
        paralysis.affect(target);
    }

    /**
     * @return The terrain bonus for the hero
     */
    @Override
    public int getTerrainBonus() {
        if (getTerrain() == HOME_TERRAIN) {
            return TERRAIN_MODIFIER;
        } else {
            return 0;
        }
    }
}
