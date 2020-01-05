package angels;

import heroes.Hero;
import maps.Point;

public class Dracula extends Angel {
    public static final float KNIGHT_MODIFIER = 0.2f;
    public static final float PYROMANCER_MODIFIER = 0.3f;
    public static final float ROGUE_MODIFIER = 0.1f;
    public static final float WIZARD_MODIFIER = 0.4f;

    public static final int KNIGHT_HP = 60;
    public static final int PYROMANCER_HP = 40;
    public static final int ROGUE_HP = 35;
    public static final int WIZARD_HP = 20;

    protected Dracula(final Point position) {
        super(AngelType.Dracula, position);
    }

    /**
     * Give a debuff to the target hero.
     * @param target The hero to be debuffed
     */
    @Override
    public void apply(final Hero target) {
        debuffText(target);
        switch (target.getType()) {
            case KNIGHT:
                target.takeAngelDamage(KNIGHT_HP);
                target.decreaseStatsModifier(KNIGHT_MODIFIER);
                break;
            case PYROMANCER:
                target.takeAngelDamage(PYROMANCER_HP);
                target.decreaseStatsModifier(PYROMANCER_MODIFIER);
                break;
            case ROGUE:
                target.takeAngelDamage(ROGUE_HP);
                target.decreaseStatsModifier(ROGUE_MODIFIER);
                break;
            case WIZARD:
                target.takeAngelDamage(WIZARD_HP);
                target.decreaseStatsModifier(WIZARD_MODIFIER);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + target.getType());
        }
    }
}
