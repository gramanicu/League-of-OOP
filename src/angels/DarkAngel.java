package angels;

import heroes.Hero;
import maps.Point;

public class DarkAngel extends Angel {
    public static final int KNIGHT_HP = 40;
    public static final int PYROMANCER_HP = 30;
    public static final int ROGUE_HP = 10;
    public static final int WIZARD_HP = 20;

    protected DarkAngel(final Point position) {
        super(AngelType.DarkAngel, position);
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
                break;
            case PYROMANCER:
                target.takeAngelDamage(PYROMANCER_HP);
                break;
            case ROGUE:
                target.takeAngelDamage(ROGUE_HP);
                break;
            case WIZARD:
                target.takeAngelDamage(WIZARD_HP);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + target.getType());
        }
    }
}
