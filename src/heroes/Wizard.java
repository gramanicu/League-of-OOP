package heroes;

import abilities.Ability;
import abilities.Deflect;
import abilities.Drain;
import maps.Point;
import maps.TerrainType;

public class Wizard extends Hero {
    private static final int BASE_HP = 400;
    private static final int SCALING_HP = 30;
    public static final int TERRAIN_MODIFIER = 10;
    public static final TerrainType HOME_TERRAIN = TerrainType.DESERT;

    public Wizard(final Point position) {
        super(HeroType.WIZARD, BASE_HP, SCALING_HP, position);
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
        Drain drain = new Drain(this);
        drain.affect(target);
        Deflect deflect = new Deflect(this);
        deflect.affect(target);
    }

    /**
     * Attack another hero.
     * @param target The targeted pyromancer
     */
    @Override
    public void attack(final Pyromancer target) {
        Drain drain = new Drain(this);
        drain.affect(target);
        Deflect deflect = new Deflect(this);
        deflect.affect(target);
    }

    /**
     * Attack another hero.
     * @param target The targeted wizard
     */
    @Override
    public void attack(final Wizard target) {
        Drain drain = new Drain(this);
        drain.affect(target);
//        Deflect deflect = new Deflect(this);
//        deflect.affect(target);
    }

    /**
     * Attack another hero.
     * @param target The targeted rogue
     */
    @Override
    public void attack(final Rogue target) {
        Drain drain = new Drain(this);
        drain.affect(target);
        Deflect deflect = new Deflect(this);
        deflect.affect(target);
    }

    /**
     * @return The starting hp of the hero
     */
    @Override
    protected int getBaseHP() {
        return BASE_HP;
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
