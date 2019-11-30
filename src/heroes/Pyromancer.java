package heroes;

import abilities.Ability;
import abilities.Fireblast;
import abilities.Ignite;
import maps.Point;
import maps.TerrainType;

public class Pyromancer extends Hero {
    private static final int BASE_HP = 500;
    private static final int SCALING_HP = 50;
    public static final int TERRAIN_MODIFIER = 25;
    public static final TerrainType HOME_TERRAIN = TerrainType.VOLCANIC;

    public Pyromancer(final Point position) {
        super(HeroType.PYROMANCER, BASE_HP, SCALING_HP, position);
    }

    /**
     * Get affected by an ability.
     * @param ability The ability that will affect the hero
     */
    @Override
    public void accept(final Ability ability) {
        ability.affect(this);
    }

    /**
     * Attack another hero.
     * @param target The targeted knight
     */
    @Override
    public void attack(final Knight target) {
        Fireblast fireblast = new Fireblast(this);
        fireblast.affect(target);
        Ignite ignite = new Ignite(this);
        ignite.affect(target);
    }

    /**
     * Attack another hero.
     * @param target The targeted pyromancer
     */
    @Override
    public void attack(final Pyromancer target) {
        Fireblast fireblast = new Fireblast(this);
        fireblast.affect(target);
        Ignite ignite = new Ignite(this);
        ignite.affect(target);
    }

    /**
     * Attack another hero.
     * @param target The targeted wizard
     */
    @Override
    public void attack(final Wizard target) {
        Fireblast fireblast = new Fireblast(this);
        fireblast.affect(target);
        Ignite ignite = new Ignite(this);
        ignite.affect(target);
    }

    /**
     * Attack another hero.
     * @param target The targeted rogue
     */
    @Override
    public void attack(final Rogue target) {
        Fireblast fireblast = new Fireblast(this);
        fireblast.affect(target);
        Ignite ignite = new Ignite(this);
        ignite.affect(target);
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
