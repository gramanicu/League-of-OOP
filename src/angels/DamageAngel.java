package angels;

import heroes.Hero;
import maps.Point;

public class DamageAngel extends Angel {
    public static final float KNIGHT_MODIFIER = 0.15f;
    public static final float PYROMANCER_MODIFIER = 0.2f;
    public static final float ROGUE_MODIFIER = 0.3f;
    public static final float WIZARD_MODIFIER = 0.4f;

    protected DamageAngel(final Point position) {
        super(AngelType.DamageAngel, position);
    }

    /**
     * Give a debuff to the target hero.
     * @param target The hero to be buffed
     */
    @Override
    public void apply(final Hero target) {
        buffText(target);
        switch (target.getType()) {
            case KNIGHT:
                target.modifyAngelStatsModifier(KNIGHT_MODIFIER);
                break;
            case PYROMANCER:
                target.modifyAngelStatsModifier(PYROMANCER_MODIFIER);
                break;
            case ROGUE:
                target.modifyAngelStatsModifier(ROGUE_MODIFIER);
                break;
            case WIZARD:
                target.modifyAngelStatsModifier(WIZARD_MODIFIER);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + target.getType());
        }
    }
}
