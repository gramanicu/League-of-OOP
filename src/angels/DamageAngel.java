package angels;

import maps.Point;

public class DamageAngel extends Angel {
    public static final float KNIGHT_MODIFIER = 0.15f;
    public static final float PYROMANCER_MODIFIER = 0.2f;
    public static final float ROGUE_MODIFIER = 0.3f;
    public static final float WIZARD_MODIFIER = 0.4f;

    protected DamageAngel(final Point position) {
        super(AngelType.DamageAngel, position);
    }
}
