package com.gramanicu.maps;

import java.util.Objects;

public class Point {
    private int xPos;
    private int yPos;

    private static int xBound;
    private static int yBound;

    public Point() { };
    public Point(final int width, final int height) {
        xPos = width;
        yPos = height;
    }

    public static void setBounds(final int width, final int height) {
        xBound = width;
        yBound = height;
    }

    /**
     * Changes the position.
     * @param movement The direction of movement
     */
    public void move(final Movement movement) {
        switch (movement) {
            case UP:
                if (yPos > 0) {
                    yPos--;
                }
                break;
            case DOWN:
                if (yPos < yBound) {
                    yPos++;
                }
                break;
            case LEFT:
                if (xPos > 0) {
                    xPos--;
                }
                break;
            case RIGHT:
                if (xPos < xBound) {
                    xPos++;
                }
                break;
            case NONE:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + movement);
        }
    }

    /**
     * @return The x pos (width)
     */
    public int getX() {
        return xPos;
    }

    /**
     * @return The y pos (height)
     */
    public int getY() {
        return yPos;
    }


    /**
     * Used to compare two points.
     * @param obj The other point
     * @return If they are equal
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            Point q = (Point) obj;
            return xPos == q.xPos && yPos == q.yPos;
        }
        return false;
    }

    /**
     * Used to solve a checkstyle problem.
     */
    @Override
    public int hashCode() {
        return Objects.hash(xPos, yPos);
    }
}
