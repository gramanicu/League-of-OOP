package angels;

import heroes.Hero;
import maps.Point;

public class LifeGiver extends Angel {
    public static final int KNIGHT_HP = 100;
    public static final int PYROMANCER_HP = 80;
    public static final int ROGUE_HP = 90;
    public static final int WIZARD_HP = 120;

    protected LifeGiver(final Point position) {
        super(AngelType.LifeGiver, position);
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
                break;
            case PYROMANCER:
                target.receiveAngelHP(PYROMANCER_HP);
                break;
            case ROGUE:
                target.receiveAngelHP(ROGUE_HP);
                break;
            case WIZARD:
                target.receiveAngelHP(WIZARD_HP);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + target.getType());
        }
    }
}
