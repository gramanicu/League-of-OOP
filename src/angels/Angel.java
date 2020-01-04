package angels;

import maps.Point;

public abstract class Angel {
    private final AngelType type;
    private Point position;

    protected Angel(final AngelType type, final Point position) {
        this.type = type;
        this.position = position;
    }

    /**
     * @return The type of the angel (as a AngelType)
     */
    public AngelType getType() {
        return type;
    }

    /**
     * @return The position of the angel
     */
    public Point getPosition() {
        return position;
    }
}
