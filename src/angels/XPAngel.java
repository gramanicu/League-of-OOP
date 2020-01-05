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

    @Override
    public void apply(final Hero target) {

    }
}
