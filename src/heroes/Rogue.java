package heroes;

import abilities.Ability;
import abilities.Backstab;
import abilities.Paralysis;
import maps.Point;
import maps.TerrainType;

public class Rogue extends Hero {
    private static final int BASE_HP = 600;
    private static final int SCALING_HP = 40;
    public static final float TERRAIN_MODIFIER = 1.5f;
    public static final TerrainType HOME_TERRAIN = TerrainType.WOODS;

    private int attacks = 0;

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
        target.accept(backstab);
        Paralysis paralysis = new Paralysis(this);
        target.accept(paralysis);
        attacks++;
    }

    /**
     * Attack another hero.
     *
     * @param target The targeted pyromancer
     */
    @Override
    public void attack(final Pyromancer target) {
        Backstab backstab = new Backstab(this);
        target.accept(backstab);
        Paralysis paralysis = new Paralysis(this);
        target.accept(paralysis);
        attacks++;
    }

    /**
     * Attack another hero.
     *
     * @param target The targeted wizard
     */
    @Override
    public void attack(final Wizard target) {
        Backstab backstab = new Backstab(this);
        target.accept(backstab);
        Paralysis paralysis = new Paralysis(this);
        target.accept(paralysis);
        attacks++;
    }

    /**
     * Attack another hero.
     *
     * @param target The targeted rogue
     */
    @Override
    public void attack(final Rogue target) {
        Backstab backstab = new Backstab(this);
        target.accept(backstab);
        Paralysis paralysis = new Paralysis(this);
        target.accept(paralysis);
        attacks++;
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
    public float getTerrainBonus() {
        if (getTerrain() == HOME_TERRAIN) {
            return TERRAIN_MODIFIER;
        } else {
            return 1f;
        }
    }

    /**
     * @return How many times the rogue attacked
     */
    public int getAttacksCount() {
        return attacks;
    }
}
