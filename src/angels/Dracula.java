package angels;

import maps.Point;

public class Dracula extends Angel {
    public static final float KNIGHT_MODIFIER = 0.2f;
    public static final float PYROMANCER_MODIFIER = 0.3f;
    public static final float ROGUE_MODIFIER = 0.1f;
    public static final float WIZARD_MODIFIER = 0.4f;

    public static final int KNIGHT_HP = 60;
    public static final int PYROMANCER_HP = 40;
    public static final int ROGUE_HP = 35;
    public static final int WIZARD_HP = 20;

    protected Dracula(final Point position) {
        super(AngelType.Dracula, position);
    }
}
