package angels;

import heroes.Hero;
import maps.Point;

public class LevelUPAngel extends Angel {
    public static final float KNIGHT_MODIFIER = 0.1f;
    public static final float PYROMANCER_MODIFIER = 0.2f;
    public static final float ROGUE_MODIFIER = 0.15f;
    public static final float WIZARD_MODIFIER = 0.25f;

    protected LevelUPAngel(final Point position) {
        super(AngelType.LevelUpAngel, position);
    }

    /**
     * Give a buff to the target hero.
     * @param target The hero to be buffed
     */
    @Override
    public void apply(final Hero target) {
        buffText(target);
        target.levelUP();
        switch (target.getType()) {
            case KNIGHT:
                target.increaseStatsModifier(KNIGHT_MODIFIER);
                break;
            case PYROMANCER:
                target.increaseStatsModifier(PYROMANCER_MODIFIER);
                break;
            case ROGUE:
                target.increaseStatsModifier(ROGUE_MODIFIER);
                break;
            case WIZARD:
                target.increaseStatsModifier(WIZARD_MODIFIER);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + target.getType());
        }
    }
}
