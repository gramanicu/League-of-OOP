package angels;

import maps.Point;

public class LevelUPAngel extends Angel {
    public static final float KNIGHT_MODIFIER = 0.1f;
    public static final float PYROMANCER_MODIFIER = 0.2f;
    public static final float ROGUE_MODIFIER = 0.15f;
    public static final float WIZARD_MODIFIER = 0.25f;

    protected LevelUPAngel(final Point position) {
        super(AngelType.LevelUpAngel, position);
    }
}
