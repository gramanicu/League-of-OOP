package angels;

import heroes.Hero;
import maps.Point;

public class SmallAngel extends Angel {
    public static final float KNIGHT_MODIFIER = 0.1f;
    public static final float PYROMANCER_MODIFIER = 0.15f;
    public static final float ROGUE_MODIFIER = 0.05f;
    public static final float WIZARD_MODIFIER = 0.1f;

    public static final int KNIGHT_HP = 10;
    public static final int PYROMANCER_HP = 15;
    public static final int ROGUE_HP = 20;
    public static final int WIZARD_HP = 25;

    protected SmallAngel(final Point position) {
        super(AngelType.SmallAngel, position);
    }

    /**
     * Give a buff to the target hero.
     * @param target The hero to be buffed
     */
    @Override
    public void apply(final Hero target) {
        buffText(target);
        switch (target.getType()) {
            case KNIGHT:
                target.receiveAngelHP(KNIGHT_HP);
                target.increaseStatsModifier(KNIGHT_MODIFIER);
                break;
            case PYROMANCER:
                target.receiveAngelHP(PYROMANCER_HP);
                target.increaseStatsModifier(PYROMANCER_MODIFIER);
                break;
            case ROGUE:
                target.receiveAngelHP(ROGUE_HP);
                target.increaseStatsModifier(ROGUE_MODIFIER);
                break;
            case WIZARD:
                target.receiveAngelHP(WIZARD_HP);
                target.increaseStatsModifier(WIZARD_MODIFIER);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + target.getType());
        }
    }
}
