package angels;

import heroes.Hero;
import maps.Point;

public class Spawner extends Angel {
    public static final int KNIGHT_HP = 200;
    public static final int PYROMANCER_HP = 150;
    public static final int ROGUE_HP = 180;
    public static final int WIZARD_HP = 120;

    protected Spawner(final Point position) {
        super(AngelType.Spawner, position);
    }

    /**
     * Give a buff to the target hero.
     * @param target The hero to be buffed
     */
    @Override
    public void apply(final Hero target) {
        buffText(target);
        target.resurrect();
        switch (target.getType()) {
            case KNIGHT:
                target.setHp(KNIGHT_HP);
                break;
            case PYROMANCER:
                target.setHp(PYROMANCER_HP);
                break;
            case ROGUE:
                target.setHp(ROGUE_HP);
                break;
            case WIZARD:
                target.setHp(WIZARD_HP);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + target.getType());
        }
    }
}
