package heroes;

import abilities.Ability;
import abilities.Execute;
import abilities.Slam;
import maps.Point;
import maps.TerrainType;

public class Knight extends Hero {
    private static final int BASE_HP = 900;
    private static final int SCALING_HP = 80;
    public static final int TERRAIN_MODIFIER = 15;
    public static final TerrainType HOME_TERRAIN = TerrainType.LAND;

    public Knight(final Point position) {
        super(HeroType.KNIGHT, BASE_HP, SCALING_HP, position);
    }

    /**
     * Get affected by an ability.
<<<<<<< HEAD
=======
     *
>>>>>>> 7df00b33df6fcb1003d0f7314efa0b22c75a2d77
     * @param ability The ability that will affect the hero
     */
    @Override
    public void accept(final Ability ability) {
        ability.affect(this);
    }

    /**
     * Attack another hero.
<<<<<<< HEAD
=======
     *
>>>>>>> 7df00b33df6fcb1003d0f7314efa0b22c75a2d77
     * @param target The targeted knight
     */
    @Override
    public void attack(final Knight target) {
        Execute execute = new Execute(this);
        execute.affect(target);
        Slam slam = new Slam(this);
        slam.affect(target);
    }

    /**
     * Attack another hero.
<<<<<<< HEAD
=======
     *
>>>>>>> 7df00b33df6fcb1003d0f7314efa0b22c75a2d77
     * @param target The targeted pyromancer
     */
    @Override
    public void attack(final Pyromancer target) {
        Execute execute = new Execute(this);
        execute.affect(target);
        Slam slam = new Slam(this);
        slam.affect(target);
    }

    /**
     * Attack another hero.
<<<<<<< HEAD
=======
     *
>>>>>>> 7df00b33df6fcb1003d0f7314efa0b22c75a2d77
     * @param target The targeted wizard
     */
    @Override
    public void attack(final Wizard target) {
        Execute execute = new Execute(this);
        execute.affect(target);
        Slam slam = new Slam(this);
        slam.affect(target);
    }

    /**
     * Attack another hero.
<<<<<<< HEAD
=======
     *
>>>>>>> 7df00b33df6fcb1003d0f7314efa0b22c75a2d77
     * @param target The targeted rogue
     */
    @Override
    public void attack(final Rogue target) {
        Execute execute = new Execute(this);
        execute.affect(target);
        Slam slam = new Slam(this);
        slam.affect(target);
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
