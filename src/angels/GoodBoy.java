package angels;

import maps.Point;

public class GoodBoy extends Angel {
    public static final float KNIGHT_MODIFIER = 0.4f;
    public static final float PYROMANCER_MODIFIER = 0.5f;
    public static final float ROGUE_MODIFIER = 0.4f;
    public static final float WIZARD_MODIFIER = 0.3f;

    public static final int KNIGHT_HP = 20;
    public static final int PYROMANCER_HP = 30;
    public static final int ROGUE_HP = 40;
    public static final int WIZARD_HP = 50;

    protected GoodBoy(final Point position) {
        super(AngelType.GoodBoy, position);
    }
}
