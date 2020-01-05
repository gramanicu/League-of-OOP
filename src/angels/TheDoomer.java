package angels;

import heroes.Hero;
import maps.Point;

public class TheDoomer extends Angel {
    protected TheDoomer(final Point position) {
        super(AngelType.TheDoomer, position);
    }

    /**
     * Give a debuff to the target hero.
     * @param target The hero to be debuffed
     */
    @Override
    public void apply(final Hero target) {
        debuffText(target);
        target.kill();
        killText(target);
    }
}
