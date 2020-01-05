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

    @Override
    public void apply(final Hero target) {

    }
}
