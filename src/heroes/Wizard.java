package heroes;

import abilities.Ability;
import abilities.Deflect;
import abilities.Drain;
import maps.Point;
import maps.TerrainType;

public class Wizard extends Hero {
    private static final int BASE_HP = 400;
    private static final int SCALING_HP = 30;
    private static final int TERRAIN_MODIFIER = 10;
    private static final TerrainType HOME_TERRAIN = TerrainType.DESERT;

    private float damageTotal = 0;
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
    public int getTerrainBonus() {
        if (getTerrain() == HOME_TERRAIN) {
            return TERRAIN_MODIFIER;
        } else {
            return 0;
        }
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
