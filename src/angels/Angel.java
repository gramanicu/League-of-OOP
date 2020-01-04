package angels;

import maps.Point;

public abstract class Angel {
    private final AngelType type;
    private Point position;

    protected Angel(final AngelType type, final Point position) {
        this.type = type;
        this.position = position;
    }
}
