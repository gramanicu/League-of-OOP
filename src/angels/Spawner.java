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

    @Override
    public void apply(final Hero target) {

    }
}
