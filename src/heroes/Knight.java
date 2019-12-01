package heroes;

import abilities.Ability;
import abilities.Execute;
import abilities.Slam;
import maps.Point;
import maps.TerrainType;

public class Knight extends Hero {
    private static final int BASE_HP = 900;
    private static final int SCALING_HP = 80;
    public static final float TERRAIN_MODIFIER = 1.5f;
    public static final TerrainType HOME_TERRAIN = TerrainType.LAND;

    public Knight(final Point position) {
        super(HeroType.KNIGHT, BASE_HP, SCALING_HP, position);
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
        Execute execute = new Execute(this);
        target.accept(execute);
        Slam slam = new Slam(this);
        target.accept(slam);
    }

    /**
     * Attack another hero.
     *
     * @param target The targeted pyromancer
     */
    @Override
    public void attack(final Pyromancer target) {
        Execute execute = new Execute(this);
        target.accept(execute);
        Slam slam = new Slam(this);
        target.accept(slam);
    }

    /**
     * Attack another hero.
     *
     * @param target The targeted wizard
     */
    @Override
    public void attack(final Wizard target) {
        Execute execute = new Execute(this);
        target.accept(execute);
        Slam slam = new Slam(this);
        target.accept(slam);
    }

    /**
     * Attack another hero.
     *
     * @param target The targeted rogue
     */
    @Override
    public void attack(final Rogue target) {
        Execute execute = new Execute(this);
        target.accept(execute);
        Slam slam = new Slam(this);
        target.accept(slam);
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
}
