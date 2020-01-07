package heroes;

import abilities.Ability;
import abilities.Fireblast;
import abilities.Ignite;
import maps.Point;
import maps.TerrainType;
import strategies.AttackStrategy;
import strategies.DefendStrategy;
import strategies.Strategy;

public class Pyromancer extends Hero {
    private static final int BASE_HP = 500;
    private static final int SCALING_HP = 50;
    private static final int MIN_STRAT_FRACTION = 4;
    private static final int MAX_STRAT_FRACTION = 3;

    private static final int ATTACK_HP_FRACTION = 4;
    private static final int DEFEND_HP_FRACTION = 3;
    private static final float ATTACK_STATS_PERCENT = 0.7f;
    private static final float DEFEND_STATS_PERCENT = 0.3f;

    public static final float TERRAIN_MODIFIER = 1.25f;
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
        target.accept(fireblast);
        Ignite ignite = new Ignite(this);
        target.accept(ignite);
    }

    /**
     * Attack another hero.
     * @param target The targeted pyromancer
     */
    @Override
    public void attack(final Pyromancer target) {
        Fireblast fireblast = new Fireblast(this);
        target.accept(fireblast);
        Ignite ignite = new Ignite(this);
        target.accept(ignite);
    }

    /**
     * Attack another hero.
     * @param target The targeted wizard
     */
    @Override
    public void attack(final Wizard target) {
        Fireblast fireblast = new Fireblast(this);
        target.accept(fireblast);
        Ignite ignite = new Ignite(this);
        target.accept(ignite);
    }

    /**
     * Attack another hero.
     * @param target The targeted rogue
     */
    @Override
    public void attack(final Rogue target) {
        Fireblast fireblast = new Fireblast(this);
        target.accept(fireblast);
        Ignite ignite = new Ignite(this);
        target.accept(ignite);
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
     * Use the AttackStrategy.
     */
    @Override
    protected Strategy offensiveStrategy() {
        Strategy strategy = new AttackStrategy();
        strategy.setOrigin(this);
        strategy.setValues(ATTACK_HP_FRACTION, ATTACK_STATS_PERCENT);
        return strategy;
    }

    /**
     * Use the DefendStrategy.
     */
    @Override
    protected Strategy defensiveStrategy() {
        Strategy strategy = new DefendStrategy();
        strategy.setOrigin(this);
        strategy.setValues(DEFEND_HP_FRACTION, DEFEND_STATS_PERCENT);
        return strategy;
    }


    /**
     * @return The maximum treshold for strategy (as fraction denominator)
     */
    @Override
    protected int getMaxStratFraction() {
        return MAX_STRAT_FRACTION;
    }

    /**
     * @return The minimum treshold for strategy (as fraction denominator)
     */
    @Override
    protected int getMinStratFraction() {
        return MIN_STRAT_FRACTION;
    }

}
