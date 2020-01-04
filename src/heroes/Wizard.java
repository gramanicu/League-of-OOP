package heroes;

import abilities.Ability;
import abilities.Deflect;
import abilities.Drain;
import maps.Point;
import maps.TerrainType;
import strategies.AttackStrategy;
import strategies.DefendStrategy;
import strategies.Strategy;

public class Wizard extends Hero {
    private static final int BASE_HP = 400;
    private static final int SCALING_HP = 30;
    private static final float TERRAIN_MODIFIER = 1.1f;
    private static final TerrainType HOME_TERRAIN = TerrainType.DESERT;
    private static final int MIN_STRAT_FRACTION = 3;
    private static final int MAX_STRAT_FRACTION = 2;

    private static final int ATTACK_HP_FRACTION = 10;
    private static final int DEFEND_HP_FRACTION = 5;
    private static final float ATTACK_STATS_PERCENT = 0.6f;
    private static final float DEFEND_STATS_PERCENT = 0.2f;

    private float damageTotal = 0f;
    private int deflected = 0;

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
        Deflect deflect = new Deflect(this);
        deflected++;
        switch (ability.getCaster().getType()) {
            case KNIGHT:
                deflect.affect(((Knight) ability.getCaster()));
                break;
            case PYROMANCER:
                deflect.affect(((Pyromancer) ability.getCaster()));
                break;
            case ROGUE:
                deflect.affect(((Rogue) ability.getCaster()));
                break;
            case WIZARD:
                deflected++;
                break;
            default:
                throw new IllegalStateException("Unexpected value: "
                        + ability.getCaster().getType());
        }
    }

    /**
     * Attack another hero.
     * @param target The targeted knight
     */
    @Override
    public void attack(final Knight target) {
        Drain drain = new Drain(this);
        target.accept(drain);
    }

    /**
     * Attack another hero.
     * @param target The targeted pyromancer
     */
    @Override
    public void attack(final Pyromancer target) {
        Drain drain = new Drain(this);
        target.accept(drain);
    }

    /**
     * Attack another hero.
     * @param target The targeted wizard
     */
    @Override
    public void attack(final Wizard target) {
        Drain drain = new Drain(this);
        target.accept(drain);
    }

    /**
     * Attack another hero.
     * @param target The targeted rogue
     */
    @Override
    public void attack(final Rogue target) {
        Drain drain = new Drain(this);
        target.accept(drain);
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

    /**
     * @return The number of times the wizard deflected
     */
    public int getDeflected() {
        return deflected;
    }

    /**
     * Set the damage that accumulated for a deflection.
     * @param amount The damage amount
     */
    public void setDamageTotal(final float amount) {
        this.damageTotal = amount;
    }

    /**
     * @return The damage that accumulated for deflect
     */
    public float getDamageTotal() {
        return damageTotal;
    }
}
