package angels;

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
}
