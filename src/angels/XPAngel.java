package angels;

import heroes.Hero;
import maps.Point;

public class XPAngel extends Angel {
    public static final int KNIGHT_XP = 45;
    public static final int PYROMANCER_XP = 50;
    public static final int ROGUE_XP = 40;
    public static final int WIZARD_XP = 60;

    protected XPAngel(final Point position) {
        super(AngelType.XPAngel, position);
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
                target.giveXP(KNIGHT_XP);
                break;
            case PYROMANCER:
                target.giveXP(PYROMANCER_XP);
                break;
            case ROGUE:
                target.giveXP(ROGUE_XP);
                break;
            case WIZARD:
                target.giveXP(WIZARD_XP);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + target.getType());
        }
    }
}
